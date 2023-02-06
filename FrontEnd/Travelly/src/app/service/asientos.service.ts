import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Asiento } from '../model/asiento';

@Injectable({
  providedIn: 'root'
})
export class AsientosService {

  URL = 'http://localhost:8080/api/asientos/';

  constructor(private httpClient: HttpClient) { }

  public getList() : Observable <Asiento[]>{
    return this.httpClient.get<Asiento[]>(this.URL + 'listar');
  }

  public getAsiento(id : number) : Observable <Asiento>{
    return this.httpClient.get<Asiento>(this.URL + `${id}`);
  }

  public save(asiento : Asiento) : Observable<any>{
    return this.httpClient.post<any>(this.URL + `crear`, asiento);
  }
  
  public update(id : number, asiento : Asiento) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `edit/${id}`, asiento);
  }
  

  public delete(id : number) : Observable<any>{
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }
}
