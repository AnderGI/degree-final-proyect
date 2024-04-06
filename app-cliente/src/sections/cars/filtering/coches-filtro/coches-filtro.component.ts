import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { getCarsMatchingCriteria } from '../../../../modules/cars/application/get/getCarsMatchingCriteria/getCarsMatchingCriteria';
import { ApiCarService } from '../../../../modules/cars/infraestructure/api-car.service';
import { Router } from '@angular/router';
import { Car } from '../../../../modules/cars/domain/car/Car';
import { getAllCarBrands } from '../../../../modules/cars/application/get/getAllCarBrands/getAllCarBrands';
import { createFilterFromFormControlData } from '../../../../modules/cars/domain/car-criteria-form-control-names/CarCriteriaFormControlNames';

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

  constructor(private api:ApiCarService, private router:Router){
    this.filtro = new FormGroup({
      'brand' :  new FormControl(),
      'minprice' : new FormControl()
    })

    getAllCarBrands(api).then(data => this.carBrands = data)
  }

  filtrar(){
      createFilterFromFormControlData(this.filtro).then(filtersArray =>  {
        getCarsMatchingCriteria(this.api, JSON.stringify(filtersArray)).then(data => {      
          this.cars = data
        this.router.navigate(['/cars'], {queryParams : {cars: JSON.stringify(this.cars)}})
          
        })
      });
  }
}
