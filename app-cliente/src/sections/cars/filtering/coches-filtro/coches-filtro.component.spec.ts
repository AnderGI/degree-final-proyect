import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CochesFiltroComponent } from './coches-filtro.component';

describe('CochesFiltroComponent', () => {
  let component: CochesFiltroComponent;
  let fixture: ComponentFixture<CochesFiltroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CochesFiltroComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CochesFiltroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
