<template>
  <div class="container">
    <header class="jumbotron">
      <h1>Seccion de cambios</h1>
    </header>

  <section v-if="showListRows == true">
      <table class="table table-striped table-bordered">
          <caption><a href="#" title="" @click="showAddForm()">Agregar Estado de un cambio</a></caption>
          <thead>
              <tr>
                  <th>Accion</th>
                  <th>solicitud</th>
                  <th>Fecha solicitud aceptada</th>
                  <th>Nombre repositorio</th>
              </tr>
          </thead>
          <tbody v-for="row in tareas " v-bind:value="row.cambio.id" :key="row.cambio.id">
              <td>
                  <a href="#" title="" @click="showCambioForm(row.cambio.id)">Ver cambios</a>
              </td>
		<td>
                  {{row.solicitud[0].solicitud.titulo}}
              </td>
              <td>
                  {{row.cambio.fecha}}
              </td>
              <td>
                  {{row.solicitud[0].repo[0].repoName}}
              </td>
          </tbody>
      </table>
  </section>

 <section v-show="formAdd == true">
      <h2>Agregar estado</h2>
      <form role="form"  @submit.prevent="add()">

	<div class="form-group">
	<label>Seleccione cambio  |</label>
          <select v-model="todo.id_cambio">
		
		<option v-for="item in cambios" v-bind:value="item.cambio.id" :key="item.cambio.id">
			{{ item.solicitud[0].solicitud.titulo }}
		</option>
          </select>
	</div>

	<div class="form-group">
	<label>Seleccionar usuario  |</label>
          <select v-model="todo.id_usuario">
		
		<option v-for="item in usuarios" v-bind:value="item.userId" :key="item.userId">
			{{ item.username }}
		</option>
          </select>
	</div>

	<div class="form-group">
	<label>seleccionar rama   |</label>
          <select v-model="todo.id_rama">
		
		<option v-for="item in ramas" v-bind:value="item.ramaId" :key="item.ramaId">
			{{ item.ramaName }}
		</option>
          </select>
	</div>

	<div class="form-group">
	<label>seleccionar estado  |</label>
          <select v-model="todo.estado">
		
		<option v-for="item in estados" v-bind:value="item.id" :key="item.id">
			{{ item.estado }}
		</option>
          </select>
	</div>

          <button type="submit" class="btn btn-primary">Guardar</button>
          <button type="button" class="btn btn-default" v-on:click="hideAddForm">Cancelar</button>
      </form>
  </section>

  <section v-if="formCambio == true">
      <table class="table table-striped table-bordered">
          <thead>
              <tr>
                  <th>Accion</th>
                  <th>Estado del cambio</th>
                  <th>fecha inicio</th>
                  <th>fecha termino</th>
                  <th>usuario encargado</th>
                  <th>Rama del proyecto</th>
              </tr>
          </thead>
          <tbody v-for="row2 in estados_cambios " v-bind:value="row2.cambioId" :key="row2.cambioId">
              <td>
                  <a href="#" title="" @click="finalizar(row2.estadoCambio.cambioId)">Finalizar fecha</a>
              </td>
		<td>
                  {{row2.estado[0].estado}}
              </td>
              <td>
                  {{row2.estadoCambio.fechaInicio}}
              </td>
              <td>
                  {{row2.estadoCambio.fechaTermino}}
              </td>

		<td>
                  {{row2.user[0].username}}
              </td>
              <td>
                  {{row2.rama[0].ramaName}}
              </td>
          </tbody>

          <button type="button" class="btn btn-default" v-on:click="hideCambioForm">Volver</button>
      </table>
  </section>
  </div>
</template>

<script>

import axios from 'axios';
var BASE_URL = "http://localhost:8081/"; 
export default {
  name: 'Home',
  data() {
    return {

    todo: {
      estado: '',
      id_cambio: '',
      id_usuario:'',
      id_rama:'',
    },
    tareas:[],
    usuarios:[],
    ramas:[],
    cambios:[],
    estados:[],
    estados_cambios:[],

    showListRows:true,
    formAdd:false,
    formUpdate:false,
    formCambio:false,
    };
  },
  mounted() {

    this.todasLasTareas();
	axios.get(BASE_URL + "users").then((response) =>{
			this.usuarios = response.data
			console.log(this.usuarios)
		});

	axios.get(BASE_URL + "Ramas").then((response) =>{
			this.ramas = response.data
			console.log(this.ramas)
		});

	axios.get(BASE_URL + "cambio").then((response) =>{
			this.cambios = response.data
			console.log(this.cambios)
		});

	axios.get(BASE_URL + "estadoCambios/estados").then((response) =>{
			this.estados = response.data
			console.log(this.estados)
		});
      
  },

  methods: {
    todasLasTareas: function ()
    {
	axios.get(BASE_URL+'cambio').then((resultado) =>{
			this.tareas = resultado.data
    })},
    add: function () {
      axios.post(BASE_URL + 'estadoCambios',
      {
        'estado': this.todo.estado,
        'idCambio': this.todo.id_cambio,
        'idUsuario': this.todo.id_usuario,
        'idRama': this.todo.id_rama,
      })
      .then(() => {
        this.hideAddForm();
        this.todasLasTareas();
      });
    },
    showAddForm: function(){
      this.showListRows = false;
      this.formAdd = true;
    },
     hideAddForm: function(){
      this.todo = {
        titulo: '',
        texto: '',
        id_usuario: '',
        id_repositorio:'',
      };
      this.showListRows = true;
      this.formAdd = false;
      this.todasLasTareas();
    },
    showCambioForm: function(cambioId){
	axios.get(BASE_URL + "estadoCambios/detallados/"+cambioId).then((response) =>{
			this.estados_cambios = response.data
			console.log(this.estados_cambios)
		});
      this.showListRows = false;
      this.formCambio = true;

	},

    hideCambioForm: function(){
      this.showListRows = true;
      this.formCambio = false;
      this.todasLasTareas();
    },

    finalizar: function (id) { axios.put(BASE_URL + 'estadoCambios', {
      cambioId:  id,
    })},
	
}
};
</script>

