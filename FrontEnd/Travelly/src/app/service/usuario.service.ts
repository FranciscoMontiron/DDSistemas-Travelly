import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  URL = 'http://localhost:8080/api/usuario/';

  constructor(private httpClient: HttpClient) { }

  public getList() : Observable <Usuario[]>{
    return this.httpClient.get<Usuario[]>(this.URL + 'listar');
  }

  public getUsuario(id : number) : Observable <Usuario>{
    return this.httpClient.get<Usuario>(this.URL + `${id}`);
  }

  public save(usuario : Usuario) : Observable<any>{
    return this.httpClient.post<any>(this.URL + `crear`,usuario);
  }
  
  public update(id : number, usuario :Usuario) : Observable<any>{
    return this.httpClient.put<any>(this.URL + `edit/${id}`, usuario);
  }
  

  public delete(id : number) : Observable<any>{
    return this.httpClient.delete<any>(this.URL + `${id}`);
  }
}
