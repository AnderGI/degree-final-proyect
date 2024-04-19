import { render, screen } from '@testing-library/angular';
import { CochesFiltroComponent } from '../../../../sections/cars/filtering/coches-filtro/coches-filtro.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import '@testing-library/jest-dom'

describe('Coches Filtro form', () => {
   
    beforeEach(async () => {
      await TestBed.configureTestingModule({
      }).compileComponents();
    });

    it('Coches filtro form component gets rendered', async () => {
        await render(CochesFiltroComponent,{
            // necesario por ser un reactive form 
            // https://timdeschryver.dev/blog/good-testing-practices-with-angular-testing-library#getting-started
            imports: [ReactiveFormsModule, HttpClientTestingModule] 
        });
        
        // Testear si los textos estan renderizados
       expect(screen.getByText("Marcas")).toBeVisible()
       //expect(screen.getByLabelText("Precio mínimo:")).toBeInTheDocument()
       //expect(screen.getByLabelText("Ordernar por : ")).toBeInTheDocument()
       //expect(screen.getByText("Buscar")).toBeInTheDocument()
    });
});
