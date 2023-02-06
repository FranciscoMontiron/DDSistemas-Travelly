import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pais } from '../model/pais';

@Injectable({
  providedIn: 'root'
})
export class PaisService {
  URL = 'http://localhost:8080/api/pais/';

  constructor(private httpClient: HttpClient) {}

  public getList(): Observable<Pais[]> {
    return this.httpClient.get<Pais[]>(this.URL + 'listar');
  }

  public getPais(id: number): Observable<Pais> {
    return this.httpClient.get<Pais>(this.URL + `${id}`);
  }

  public save(pais: Pais): Observable<any> {
    return this.httpClient.post<any>(this.URL + `crear`, pais);
  }
  
  public update(id : number, pais : Pais) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `${id}`, pais);
  }
  

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }
}
