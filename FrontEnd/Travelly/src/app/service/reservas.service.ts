import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reserva } from '../model/reserva';

@Injectable({
  providedIn: 'root'
})
export class ReservasService {

  URL = 'http://localhost:8080/api/reservas/';

  constructor(private httpClient: HttpClient) { }

  public getList() : Observable <Reserva[]>{
    return this.httpClient.get<Reserva[]>(this.URL + 'listar');
  }

  public getReserva(id : number) : Observable <Reserva>{
    return this.httpClient.get<Reserva>(this.URL + `${id}`);
  }

  public save(reserva : Reserva) : Observable<any>{
    return this.httpClient.post<any>(this.URL + `crear`, reserva);
  }
  
  public update(id : number, reserva : Reserva) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `edit/${id}`, reserva);
  }
  

  public delete(id : number) : Observable<any>{
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }

}
