import { Component, OnInit, Renderer2 } from '@angular/core';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  
  faSearch = faSearch;

  apps: any[]=[];
  categorias: any[]=[];
  comentarios: any;

  visible: boolean = false;
  value!: number;

  app: any;

  valorFree!: string;

  constructor(private service: BackendService) {}

  ngOnInit(): void {
    this.cargarCategorias();
    this.cargarAplicaciones();
  }

  appSelected(item: any) {
    this.cargarComentariosApp(item.idAplicacion);
    this.visible = true;
    this.app = item;
    this.value = item.calificacionActual;
  }

  cargarCategorias(){
    this.service.obtenerCategorias().subscribe(data => {
      this.categorias = data;
    });
  }
  
  cargarAplicaciones(){
    this.service.obtenerAplicaciones().subscribe(data => {
      this.apps = data;
    });
  }

  cargarComentariosApp(idAplicacion: number){
    this.service.obtenerComentariosApp(idAplicacion).subscribe(data => {
      this.comentarios = data;
      console.log(JSON.stringify(this.comentarios));
    });
  }

  cerrarModal(){
    this.visible=false;
  }

  validarCifra(cifra: number) {
    if (cifra <= 0.5) {
      return this.valorFree = 'FREE';
    } else {
      return this.valorFree = String(cifra);
    }
  }

}
