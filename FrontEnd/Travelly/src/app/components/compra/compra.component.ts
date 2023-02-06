import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Asiento } from 'src/app/model/asiento';
import { Pasajero } from 'src/app/model/pasajero';
import { AsientosService } from 'src/app/service/asientos.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {
/*
  firstFormGroup = this.fb.group({
    firstCtrl: ['', Validators.required],
  });*/


  fb!: FormGroup;
  
  isLinear = false;

  cantidadPasajeros: number = 1;

  pasajeros: Pasajero[] = [];
  
  mostrar = false;

  // --------------seleccion de butacas------------------
  asientos: Asiento[] = [];
  asientosDisponibles: Asiento[] = [];

  // -------------- Datos de los pasajeros --------------
  asientosSeleccionados: Asiento[] = [];
    // --- FormArray --
      fg!: FormGroup
      dataSourcePacks!: MatTableDataSource<any>;
      displayedColumns = ["nombre", "apellido", "dni"]
    
      nombre = new FormControl('')
      apellido = new FormControl('')
      dni = new FormControl(0)

  constructor(private tokenService: TokenService,private _fb: FormBuilder, private asientoService: AsientosService, private cd: ChangeDetectorRef){}

  isLogged = false;
  
  
  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
    this.cargarAsientosDisponibles();

    this.fg = this._fb.group({
      cantDesde: this.nombre,
      precio: this.apellido,
      dni: this.dni,
      formsPasajeros: this._fb.array([])
    });
  }  

  get formsPasajeros() {
    return this.fg.controls["formsPasajeros"] as FormArray;
  };

  addPersona(): void {
    const personaForm = this._fb.group({
      nombre: [''],
      apellido: [''],
      dni: [0]
    });

    this.formsPasajeros.push(personaForm);
    this.dataSourcePacks = new MatTableDataSource(this.formsPasajeros.controls);

    this.cd.detectChanges();
  }

  datosPasajeros(): void {
     for (let i = 0; i < this.asientosSeleccionados.length; i++){
      this.pasajeros.push(new Pasajero("","",0));
      this.addPersona(); 
     }
  }

  cargarAsientosDisponibles(): void {
      this.asientosDisponibles = [];
      this.asientoService.getList().subscribe((data) => {
      (this.asientos = data);
      this.asientos.forEach(element => {
        if (element.estado == true) {
          this.asientosDisponibles.push(element);
        }
      });  
      console.log(this.asientosDisponibles);
      });  
  }

  reserva(asientoSelecionado: Asiento): void {

    let indice = this.asientosDisponibles.findIndex(asiento => asiento.id === asientoSelecionado.id);
    var lista = this.asientosDisponibles
    lista[indice].estado = !lista[indice].estado;
    this.asientosDisponibles = lista;
    
    if(this.asientosSeleccionados.includes(asientoSelecionado)){
      let indiceSelect = this.asientosSeleccionados.findIndex(asiento => asiento.id === asientoSelecionado.id);
      this.asientosSeleccionados.splice(indiceSelect);
    }else{
      this.asientosSeleccionados.push(asientoSelecionado);
    }

    console.log(this.asientosSeleccionados);
  }
  
  submitFormPasajeros(): void {
    console.log(this.formsPasajeros.value);
  }


}
