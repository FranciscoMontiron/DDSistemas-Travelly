import { HttpClient } from '@angular/common/http';
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

  public save(vuelo : Vuelo) : Observable<any>{
    return this.httpClient.post<any>(this.URL + `crear`, vuelo);
  }
  
  public update(id : number, vuelo : Vuelo) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `edit/${id}`, vuelo);
  }
  

  public delete(id : number) : Observable<any>{
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }


}
