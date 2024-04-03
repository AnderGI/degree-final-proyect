import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavigationBarComponent } from '../sections/cars/navigation/navigation-bar/navigation-bar.component';
import { CochesFiltroComponent } from '../sections/cars/filtering/coches-filtro/coches-filtro.component';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavigationBarComponent, CochesFiltroComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  title = 'app-cliente';
}

