import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
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
    RegistroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    BrowserAnimationsModule
  ],
  providers: [
    interceptorProvider
  ],
  bootstrap: [AppComponent],
  exports: [ VuelosComponent] //componentes para que puedan ser utilizados por otros  modulos
})
export class AppModule { }
