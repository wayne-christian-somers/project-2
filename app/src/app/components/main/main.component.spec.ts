import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainComponent } from './main.component';
import { Store, StoreModule } from '@ngrx/store';

describe('MainComponent', () => {
  let component: MainComponent;
  let fixture: ComponentFixture<MainComponent>;
  let store: Store;

  beforeEach(async() => {
    TestBed.configureTestingModule({
      imports: [ StoreModule.forRoot({}) ],
      declarations: [ MainComponent ]
    });

    await TestBed.compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MainComponent);
    component = fixture.componentInstance;
    store = TestBed.inject(Store);

    spyOn(store, 'dispatch').and.callThrough();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
