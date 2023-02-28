import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vuelo } from '../model/vuelo';

@Injectable({
  providedIn: 'root'
})
export class VueloService {  

  URL = 'http://localhost:8080/api/vuelos/';

  constructor(private httpClient: HttpClient) {}

  public getList() : Observable <Vuelo[]>{
    return this.httpClient.get<Vuelo[]>(this.URL + 'listar');
  }

  public getVuelo(id : number) : Observable <Vuelo>{
    return this.httpClient.get<Vuelo>(this.URL + `${id}`);
  }

  public save(vuelo : Vuelo) {
    
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    const isoDateStringArribo = vuelo.fechaYHoraArribo;
    const dateObjArribo = new Date(isoDateStringArribo);
    const serializedDateArribo = dateObjArribo.toISOString();

    const isoDateStringPartida = vuelo.fechaYHoraPartida;
    const dateObjPartida = new Date(isoDateStringPartida);
    const serializedDatePartida = dateObjPartida.toISOString();

    const body = {
      fechaYHoraArribo: serializedDateArribo,
      fechaYHoraPartida: serializedDatePartida,
      precio: vuelo.precio,
      avion: vuelo.avion,
      aeropuertoPartida: vuelo.aeropuertoPartida,
      aeropuertoLlegada: vuelo.aeropuertoLlegada
    }


    return this.httpClient.post<any>(this.URL + `crear`, body, { headers });
  }
  
  public update(id : number, vuelo : Vuelo) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    const isoDateStringArribo = vuelo.fechaYHoraArribo;
    const dateObjArribo = new Date(isoDateStringArribo);
    const serializedDateArribo = dateObjArribo.toISOString();

    const isoDateStringPartida = vuelo.fechaYHoraPartida;
    const dateObjPartida = new Date(isoDateStringPartida);
    const serializedDatePartida = dateObjPartida.toISOString();

    const body = {
      fechaYHoraArribo: serializedDateArribo,
      fechaYHoraPartida: serializedDatePartida,
      precio: vuelo.precio,
      avion: vuelo.avion,
      aeropuertoPartida: vuelo.aeropuertoPartida,
      aeropuertoLlegada: vuelo.aeropuertoLlegada
    }


    return this.httpClient.put<any>(this.URL + `${id}`, body, { headers });
  }
  

  public delete(id : number) : Observable<any>{
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }

  public traerVueloFiltrados(fecha : string, origen: string, destino: string) : Observable<Vuelo[]>{
    return this.httpClient.get<Vuelo[]>(this.URL + `traerVuelosFiltrados/${fecha}/${origen}/${destino}`);
  }


}
