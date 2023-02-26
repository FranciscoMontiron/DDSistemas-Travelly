import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Aeropuerto } from '../model/aeropuerto';

@Injectable({
  providedIn: 'root'
})
export class AeropuertoService {

  URL = 'http://localhost:8080/api/aeropuertos/';

  constructor(private httpClient: HttpClient) {}
  
  public getList() : Observable <Aeropuerto[]>{
    return this.httpClient.get<Aeropuerto[]>(this.URL + 'listar');
  }

  public getVuelo(id : number) : Observable <Aeropuerto>{
    return this.httpClient.get<Aeropuerto>(this.URL + `${id}`);
  }

  public save(aeropuerto : Aeropuerto) : Observable<any>{
    return this.httpClient.post<any>(this.URL + `crear`, aeropuerto);
  }
  
  public update(id : number, aeropuerto : Aeropuerto) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `edit/${id}`, aeropuerto);
  }
  

  public delete(id : number) : Observable<any>{
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }

}

