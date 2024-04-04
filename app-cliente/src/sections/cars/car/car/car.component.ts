import { Component, Input } from '@angular/core';
import { ApiCarService } from '../../../../modules/cars/infraestructure/api-car.service';
import { getCarById } from '../../../../modules/cars/application/get/getCar/getCarById';
import { Car } from '../../../../modules/cars/domain/car/Car'; 
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { getCarsMatchingCriteria } from '../../../../modules/cars/application/get/getCarsMatchingCriteria/getCarsMatchingCriteria';

@Component({
  selector: 'app-car',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './car.component.html',
  styleUrl: './car.component.css'
})
export class CarComponent {
  @Input() public car!:Car;
  constructor(private apiRepo : ApiCarService, private router:Router) {
   
  }

  ver(){
    this.router.navigate(['/cars', this.car.id.value])
  }
}
