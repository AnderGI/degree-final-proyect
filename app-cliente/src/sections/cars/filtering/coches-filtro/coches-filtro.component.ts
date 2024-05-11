import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Car, CarBrand } from '../../../../modules/cars/domain/car/Car';
import { getAllCarBrands } from '../../../../modules/cars/application/get/getAllCarBrands/getAllCarBrands';
import { CriteriaJSON } from '../../../../modules/cars/domain/criteria/Criteria';
import { createJsonCriteriaFromHtmlForm } from '../../../../modules/cars/domain/criteria/createCriteriaFromHtmlForm';
import { FORM_CONTROL_NAMES } from '../../../../modules/cars/domain/car-criteria-form-control-names/CarCriteriaFormControlNames';
import { GetAllCarBrandsHttpClientService } from '../../../../modules/cars/infraestructure/get-all-car-brands-service/get-all-car-brands-http-client.service';
import { GetAllCarsMatchingCriteriaHttpClientService } from '../../../../modules/cars/infraestructure/get-all-cars-matching-criteria/get-all-cars-matching-criteria-http-client.service';
import { getCarsMatchingCriteria } from '../../../../modules/cars/application/get/getCarsMatchingCriteria/getCarsMatchingCriteria';

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
  public carBrands!:CarBrand[];
  constructor(
    private brandsRepo:GetAllCarBrandsHttpClientService, 
    private criteriaRepo: GetAllCarsMatchingCriteriaHttpClientService,
    private router:Router){
    this.filtro = new FormGroup({
      [FORM_CONTROL_NAMES.BRAND] :  new FormControl(''),
      [FORM_CONTROL_NAMES.MIN_PRICE]: new FormControl('500'),
      [FORM_CONTROL_NAMES.ORDER_TYPE]: new FormControl('none'),
    })

    getAllCarBrands(brandsRepo.getAllCarBrands)().then(data => this.carBrands = data)
  }

  // Podria hacer una interface con los tipos que recogemos del formulario
  // Esa interface extiende de HtmlFromControllsCollection
  // el método filtrar recogeria el form pasado por el html de angular
  async filtrar(){
    // primero recoger los valores de los elementos html
    const criteriaForm = document.getElementById("criteriaForm") as HTMLFormElement
    const criteriaJson:CriteriaJSON = createJsonCriteriaFromHtmlForm(criteriaForm);
    const filteredCarList = await getCarsMatchingCriteria(this.criteriaRepo.getCarsMatchingCriteria)(criteriaJson)
    this.cars = filteredCarList;
    this.router.navigate(['/cars'], {queryParams : {cars: JSON.stringify(this.cars)}})
    // resetear el valor de la marca
   // this.filtro.get("brand")?.reset()
  }

}
