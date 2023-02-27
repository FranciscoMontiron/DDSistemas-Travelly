import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pago } from '../model/pago';

@Injectable({
  providedIn: 'root'
})
export class PagoService {

  URL = 'http://localhost:8080/api/pagos/';

  constructor(private httpClient: HttpClient) {}

  public getList(): Observable<Pago[]> {
    return this.httpClient.get<Pago[]>(this.URL + 'listar');
  }

  public getPago(id: number): Observable<Pago> {
    return this.httpClient.get<Pago>(this.URL + `${id}`);
  }

  /*public save(pago: Pago): Observable<any> {
    return this.httpClient.post<any>(this.URL + `crear`, pago);
  }*/
  crearPago(pago: Pago) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    const body = {
      fechaYHora: pago.fechaYHora,
      monto: pago.monto,
      reserva: {
        id: pago.reserva.id,
        estado: pago.reserva.estado,
        fechaYHora: pago.reserva.fechaYHora,
        usuario: pago.reserva.usuario,
        vuelo: pago.reserva.vuelo
      }}


    return this.httpClient.post(this.URL + 'crear', body, { headers });
  }

  
  public update(id : number, pago : Pago) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `${id}`, pago);
  }
  

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }

  public traerPagoDeReserva(id : number): Observable<Pago> {
    return this.httpClient.get<Pago>(this.URL + `traerPagoDeReserva/${id}`);
  }
}
