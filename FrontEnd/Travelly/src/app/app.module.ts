
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { interceptorProvider } from './service/interceptor-service';
import { VuelosComponent } from './components/vuelos/vuelos.component';
import { FooterComponent } from './components/footer/footer.component';
import { AdminComponent } from './components/admin/admin.component';
import { VueloAdmComponent } from './components/vuelos-adm/vuelo-adm.component';
import { ReservasComponent } from './components/reservas/reservas.component';
import { HeaderAdmComponent } from './components/header-adm/header-adm.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegistroComponent } from './components/registro/registro.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CompraComponent } from './components/compra/compra.component';

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSelectModule } from '@angular/material/select';
import { MatAutocompleteModule } from '@angular/material/autocomplete'
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from "@angular/material/form-field";
import { ErrorStateMatcher, MatNativeDateModule, ShowOnDirtyErrorStateMatcher } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button'
import { MatTableModule } from '@angular/material/table'
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatStepperModule } from '@angular/material/stepper';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { DialogADMComponent } from './components/dialog-adm/dialog-adm.component';
import { MatDatetimepickerModule, MatNativeDatetimeModule } from '@mat-datetimepicker/core';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    LoginComponent,
    VuelosComponent,
    FooterComponent,
    AdminComponent,
    VueloAdmComponent,
    ReservasComponent,
    HeaderAdmComponent,
    RegistroComponent,
    CompraComponent,
    DialogADMComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatStepperModule,
    MatTableModule,
    MatMenuModule,
    MatDialogModule,
    MatDatetimepickerModule,
    MatNativeDatetimeModule
  ],
  providers: [
    interceptorProvider,
    ErrorStateMatcher,  ShowOnDirtyErrorStateMatcher,
    MatDatepickerModule
  ],
  bootstrap: [AppComponent],
  exports: [ BrowserAnimationsModule] //componentes para que puedan ser utilizados por otros  modulos
})
export class AppModule { }

