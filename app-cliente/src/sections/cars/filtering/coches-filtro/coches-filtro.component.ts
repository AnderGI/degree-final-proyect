import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
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
    MIN_PRICE : "minprice",
    ORDER_TYPE:"orderType"
  }

  constructor(private api:ApiCarService, private router:Router){
    this.filtro = new FormGroup({
      'brand' :  new FormControl('', [Validators.required ]),
      'minprice' : new FormControl('1000'),
      'orderType': new FormControl('none'),
    })

    getAllCarBrands(api).then(data => this.carBrands = data)
  }

  async filtrar(){
    // primero recoger los valores de los elementos html
    // const htmlarray = Array.from(document.querySelectorAll('.filter')) as HTMLFormElement[]
    // htmlarray.map(el => console.log(el.dataset));
    const criteriaForm = document.getElementById("criteriaForm") as HTMLFormElement
    // Para construir pares de clave : valor con la info enviada
    // key -> name del elemento 
    // value -> su valor
    const formData = new FormData(criteriaForm);

   
    
    // console.log(Array.from(document.querySelectorAll('.orderType')))
    const {brand, minprice, orderType} = this.filtro.getRawValue()
      const filters = await createFilterFromFormControlData({brand, minprice})
      console.log({
        filters,
        orderType
      })
      const filteredCarList = await getCarsMatchingCriteria(this.api, JSON.stringify(filters))
      this.cars = filteredCarList;
      this.router.navigate(['/cars'], {queryParams : {cars: JSON.stringify(this.cars)}})
  }
}
