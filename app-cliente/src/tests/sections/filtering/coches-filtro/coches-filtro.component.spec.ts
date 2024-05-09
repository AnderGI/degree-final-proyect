import { render, screen } from '@testing-library/angular';
import userEvent from '@testing-library/user-event'
import { CochesFiltroComponent } from '../../../../sections/cars/filtering/coches-filtro/coches-filtro.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import '@testing-library/jest-dom'
import { CarBrand } from '../../../../modules/cars/domain/car/Car';

describe('Coches Filtro form', () => {
   
    beforeEach(async () => {
      await TestBed.configureTestingModule({
      }).compileComponents();
    });



    it('Form buscar button is visible if all form fields are filled', async () => {
        const container = await render(CochesFiltroComponent, {
            imports: [ReactiveFormsModule, HttpClientTestingModule],
            componentProviders: [
                {
                    provide: 'GetAllCarBrandsHttpClientService',
                    useValue: {
                        getAllCarBrands: jest.fn().mockResolvedValue([{value:"Audi"}, {value:'BMW'}, {value:'Mercedes'}] as CarBrand[])
                    }
                },
                {
                    provide: 'GetAllCarsMatchingCriteriaHttpClientService',
                    useValue: {
                        getCarsMatchingCriteria: jest.fn()
                    }
                }
            ]
        });
        // Establecer el valor del input
        container.container.querySelector('select#brand')
        console.log(container.container.querySelector('select#brand'));
        
        //expect(screen.getByText('Buscar')).toBeVisible();
    });

    it('Coches filtro form component gets rendered correctly', async () => {
        await render(CochesFiltroComponent,{
            // necesario por ser un reactive form 
            // https://timdeschryver.dev/blog/good-testing-practices-with-angular-testing-library#getting-started
            imports: [ReactiveFormsModule, HttpClientTestingModule] 
        });
        
        // Testear si los textos estan renderizados
       expect(screen.getByLabelText("Marcas")).toBeInTheDocument()
       expect(screen.getByLabelText("Precio mínimo:")).toBeInTheDocument()
       expect(screen.getByLabelText("Ordernar por:")).toBeInTheDocument()
       expect(screen.getByText("Buscar")).toBeInTheDocument()

       // Testear que el boton de primeras este hidden
       expect(screen.getByText("Buscar")).not.toBeVisible()
    });

   
});
