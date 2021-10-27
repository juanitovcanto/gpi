<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        Perfil de <strong>{{currentUser.username}}</strong>
      </h3>
    </header>
  <h1>Solicitudes</h1>
  <section v-if="showListRows == true">
      <table class="table table-striped table-bordered">
          <caption><a href="#" title="" @click="showAddForm()">Agregar</a></caption>
          <thead>
              <tr>
                  <th>Acciones</th>
                  <th>Titulo</th>
                  <th>estado</th>
                  <th>fecha</th>
                  <th>Usuario Solicitante</th>
                  <th>Nombre usuario</th>
                  <th>Apellido usuario</th>
                  <th>Repositorio</th>
              </tr>
          </thead>
          <tbody v-for="row in tareas " v-bind:value="row.solicitud.solicitudId" :key="row.solicitud.solicitudId">
              <td>
                  <a href="#" title="" @click="aceptar(row.solicitud.solicitudId)">Aceptar Solicitud</a>
                  <a href="#" title="" @click="rechazar(row.solicitud.solicitudId)">Rechazar solicitud</a>
              </td><td>
                  {{row.solicitud.titulo}}
              </td>
              <td style="">
                  {{row.estado[0].estado}}
              </td>

              <td>
                  {{row.solicitud.fecha}}
              </td>

              <td>
                  {{row.user[0].username}}
              </td>
              <td>
                  {{row.user[0].firstName}}
              </td>
              <td>
                  {{row.user[0].lastName}}
              </td>
              <td>
                  {{row.repo[0].repoName}}
              </td>
          </tbody>
      </table>
  </section>
 <section v-show="formAdd == true">
      <h2>Agregar</h2>
      <form role="form"  @submit.prevent="add()">
          <div class="form-group">
              <label for="exampleInputEmail1">Titulo solicitud</label>
              <input type="text" class="form-control" v-model="todo.titulo" placeholder="">
          </div>
          <div class="form-group">
              <label for="exampleInputEmail1">Texto solicitud</label>
              <input type="test" class="form-control" v-model="todo.texto" placeholder="">
          </div>

	<div class="form-group">	
	<label>repositorio</label>
          <select v-model="todo.id_repositorio">
		
		<option v-for="item in repositorios" v-bind:value="item.repoId" :key="item.reporId">
			{{ item.repoName }}
		</option>
          </select>
	</div>
          <button type="submit" class="btn btn-primary">Guardar</button>
          <button type="button" class="btn btn-default" v-on:click="hideAddForm">Cancelar</button>
      </form>
  </section>
  </div>
</template>

<script>
var BASE_URL = "http://localhost:8081/"; 
var urlAPI = BASE_URL + "solicituds";
import axios from 'axios';
export default {

  name: 'Profile',
  data() { return {
    todo: {
      titulo: '',
      texto: '',
      id_usuario:'',
      id_repositorio:'',
    },
    tareas:[],
    usuarios:[],
    repositorios:[],
    showListRows:true,
    formAdd:false,
    formUpdate:false,
		};
	},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {

    this.todasLasTareas();
	axios.get(BASE_URL + "users").then((response) =>{
			this.usuarios = response.data
			console.log(this.usuarios)
		});

	axios.get(BASE_URL + "repos").then((response) =>{
			this.repositorios = response.data
			console.log(this.repositorios)
		});
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },
  methods: {
    todasLasTareas: function ()
    {
	axios.get(urlAPI+'/detallados').then((resultado) =>{
			this.tareas = resultado.data
    })},
    add: function () {
      axios.post(BASE_URL + 'solicituds',
      {
        'titulo': this.todo.titulo,
        'texto': this.todo.texto,
        'id_usuario': this.currentUser.id,
        'id_repositorio': this.todo.id_repositorio,
      })
      .then(() => {
        this.hideAddForm();
        this.todasLasTareas();
      });
    },
    aceptar: function (id) { axios.put(urlAPI, {
      solicitudId:  id,
      estado: 2,
    })
    this.todasLasTareas();
    axios.post(BASE_URL + 'cambio' , {
	idSolicitudCambio: id,
    })
    },	
    rechazar: function (id) { axios.put(urlAPI, {
      solicitudId: id,
      estado: 3,
    })
    this.todasLasTareas();
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
}
}
</script>

