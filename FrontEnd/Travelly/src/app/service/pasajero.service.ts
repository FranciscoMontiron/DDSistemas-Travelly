import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pasajero } from '../model/pasajero';

@Injectable({
  providedIn: 'root'
})
export class PasajeroService {

  URL = 'http://localhost:8080/api/pasajeros/';

  constructor(private httpClient: HttpClient) { }

  public getList() : Observable <Pasajero[]>{
    return this.httpClient.get<Pasajero[]>(this.URL + 'listar');
  }

  public getPasajero(id : number) : Observable <Pasajero>{
    return this.httpClient.get<Pasajero>(this.URL + `${id}`);
  }

  public save(pasajero : Pasajero) : Observable<any>{
    return this.httpClient.post<any>(this.URL + `crear`, pasajero);
  }
  
  public update(id : number, pasajero : Pasajero) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `edit/${id}`, pasajero);
  }
  

  public delete(id : number) : Observable<any>{
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }
}
