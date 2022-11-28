import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Avion } from '../model/avion';

@Injectable({
  providedIn: 'root',
})
export class AvionService {
  URL = 'http://localhost:8080/api/avion/';

  constructor(private httpClient: HttpClient) {}

  public getList(): Observable<Avion[]> {
    return this.httpClient.get<Avion[]>(this.URL + 'listar');
  }

  public getAvion(id: number): Observable<Avion> {
    return this.httpClient.get<Avion>(this.URL + `${id}`);
  }

  public save(avion: Avion): Observable<any> {
    return this.httpClient.post<any>(this.URL + `crear`, avion);
  }
  
  public update(id : number, avion : Avion) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `${id}`, avion);
  }
  

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }
}
