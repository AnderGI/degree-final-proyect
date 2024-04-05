import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { getCarsMatchingCriteria } from '../../../../modules/cars/application/get/getCarsMatchingCriteria/getCarsMatchingCriteria';
import { ApiCarService } from '../../../../modules/cars/infraestructure/api-car.service';
import { Router } from '@angular/router';
import { Car } from '../../../../modules/cars/domain/car/Car';
import { getAllCarBrands } from '../../../../modules/cars/application/get/getAllCarBrands/getAllCarBrands';

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
  constructor(private api:ApiCarService, private router:Router){
    this.filtro = new FormGroup({
      'brand' :  new FormControl(),
      'price' : new FormControl()
    })

    getAllCarBrands(api).then(data => this.carBrands = data)
  }

  filtrar(){
    const {controls} = this.filtro;
    const {brand, price} = controls 
    // '[{"field":"brand","operator":"=","value":"bmw"}]'
    const filters = [
      {
        "field":"brand",
        "operator":"=",
        "value":"bmw"
      },
      {
        "field":"price",
        "operator":"<",
        "value":50000
      }
      ]
    console.log(this.filtro.getRawValue())
    getCarsMatchingCriteria(this.api, JSON.stringify(filters)).then(data => {      
      this.cars = data
    this.router.navigate(['/car', this.cars[0].id.value])
      
    })
    
  }
}
