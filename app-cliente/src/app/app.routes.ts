import { Routes } from '@angular/router';
import { ListCarsComponent } from '../sections/cars/list-cars/list-cars/list-cars.component';
import { CarComponent } from '../sections/cars/car/car/car.component';
import { CarDetailsComponent } from '../sections/cars/view-car-details/car-details/car-details.component';

export const routes: Routes = [
    {path:'cars', component:ListCarsComponent},
    {path:'**', redirectTo:'cars'}
];
