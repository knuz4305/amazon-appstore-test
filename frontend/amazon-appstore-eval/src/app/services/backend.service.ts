import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private url = 'http://localhost:8080'

  constructor(private httpRequest: HttpClient) { }

  obtenerCategorias(): Observable<any>{
    const headers: HttpHeaders = new HttpHeaders();
    headers.set('Content-Type', 'application/json');
    headers.set('Accept', 'application/json');
    return this.httpRequest.get<any>(this.url+'/v1.0/categorias/',{headers: headers});
  }

  obtenerAplicaciones(): Observable<any>{
    const headers: HttpHeaders = new HttpHeaders();
    headers.set('Content-Type', 'application/json');
    headers.set('Accept', 'application/json');
    return this.httpRequest.get<any>(this.url+'/v1.0/aplicaciones/',{headers: headers});
  }

  obtenerComentariosApp(idAplicacion: number){
    const headers: HttpHeaders = new HttpHeaders();
    headers.set('Content-Type', 'application/json');
    headers.set('Accept', 'application/json');
    return this.httpRequest.get(this.url+'/v1.0/comentarios/getComentariosByAplicacion/'+idAplicacion,{headers: headers});
  }

}
