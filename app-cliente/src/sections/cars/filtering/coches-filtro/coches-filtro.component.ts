import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { getCarsMatchingCriteria } from '../../../../modules/cars/application/get/getCarsMatchingCriteria/getCarsMatchingCriteria';
import { ApiCarService } from '../../../../modules/cars/infraestructure/api-car.service';
import { Router } from '@angular/router';
import { Car } from '../../../../modules/cars/domain/car/Car';
import { getAllCarBrands } from '../../../../modules/cars/application/get/getAllCarBrands/getAllCarBrands';
import { createFilterFromFormControlData } from '../../../../modules/cars/domain/car-criteria-form-control-names/CarCriteriaFormControlNames';
import { FilterFieldValue, FilterOperatorValue } from '../../../../modules/cars/domain/criteria/Criteria';

@Component({  
  selector: 'app-coches-filtro',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './coches-filtro.component.html',
  styleUrl: './coches-filtro.component.css'
})
export class CochesFiltroComponent {
  public filtro!:FormGroup;
  private cars!:Car[];
  public carBrands!:String[];

  public formControlNames = {
    BRAND : "brand",
    MIN_PRICE : "minprice"
  }
  public fromFormControlToFilterMap: Map<string, object> = new Map([
    [this.formControlNames.BRAND, {
      field : FilterFieldValue.BRAND,
      operator : FilterOperatorValue.EQUAL
    }],
    [this.formControlNames.MIN_PRICE, {
      field : FilterFieldValue.PRICE,
      operator : FilterOperatorValue.GREATER_THAN
    }],
  ]);


  constructor(private api:ApiCarService, private router:Router){
    this.filtro = new FormGroup({
      'brand' :  new FormControl(),
      'minprice' : new FormControl()
    })

    getAllCarBrands(api).then(data => this.carBrands = data)
  }

  filtrar(){
    const {controls} = this.filtro;
    const {brand, minprice} = controls 
    console.log(typeof this.filtro.getRawValue())
    const formRawValue = this.filtro.getRawValue() // contiene el form control nam,e y el valor
    // '[{"field":"brand","operator":"=","value":"bmw"}]'
    const filters = [
      {
        "field":"brand",
        "operator":"=",
        "value":brand.value
      },
      {
        "field":"price",
        "operator":">",
        "value":parseInt(minprice.value)
      }
      ]
      const filtersArray = createFilterFromFormControlData(this.filtro);
      // Habira que construir el criteria 
    getCarsMatchingCriteria(this.api, JSON.stringify(filters)).then(data => {      
      this.cars = data
    this.router.navigate(['/cars'], {queryParams : {cars: JSON.stringify(this.cars)}})
      
    })
    
  }
}
