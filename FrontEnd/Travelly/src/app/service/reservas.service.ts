import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pago } from '../model/pago';
import { Reserva } from '../model/reserva';
import { Vuelo } from '../model/vuelo';

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
  /*
  public save(reserva : Reserva) : Observable<any>{
    return this.httpClient.post<any>(this.URL + `crear`, reserva);
  }*/

  crearReserva(reserva: Reserva) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    const isoDateStringArribo = reserva.vuelo.fechaYHoraArribo;
    const dateObjArribo = new Date(isoDateStringArribo);
    const serializedDateArribo = dateObjArribo.toISOString();

    const isoDateStringPartida = reserva.vuelo.fechaYHoraPartida;
    const dateObjPartida = new Date(isoDateStringPartida);
    const serializedDatePartida = dateObjPartida.toISOString();

    const body = {
      estado: reserva.estado,
      fechaYHora: reserva.fechaYHora,
      usuario: {
        id: reserva.usuario.id,
        nombre: reserva.usuario.nombre,
        apellido: reserva.usuario.apellido,
        correo: reserva.usuario.correo,
        nombreUsuario: reserva.usuario.nombreUsuario,
        dni: reserva.usuario.dni,
        direccio: reserva.usuario.direccion,
        password: reserva.usuario.password,
        roles: reserva.usuario.roles,
        reservas: reserva.usuario.reservas
      },
      vuelo: {
        id: reserva.vuelo.id,
        fechaYHoraArribo: serializedDateArribo,
        fechaYHoraPartida: serializedDatePartida,
        precio: reserva.vuelo.precio,
        avion: reserva.vuelo.avion,
        aeropuertoPartida: reserva.vuelo.aeropuertoPartida,
        aeropuertoLlegada: reserva.vuelo.aeropuertoLlegada,    
      }
    }


    return this.httpClient.post(this.URL + 'crear', body, { headers });
  }
  
  public update(id : number, reserva : Reserva) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `${id}`, reserva);
  }
  

  public delete(id : number) : Observable<any>{
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }

}
