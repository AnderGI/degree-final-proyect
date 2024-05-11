import { Component, Input } from '@angular/core';
import { Car } from '../../../../modules/cars/domain/car/Car'; 
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-car',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './car.component.html',
  styleUrl: './car.component.css'
})
export class CarComponent {
  @Input() public car!:Car;
  constructor(private router:Router) {
   
  }

  ver(){
    this.router.navigate(['/cars', this.car.id.value])
  }
}
