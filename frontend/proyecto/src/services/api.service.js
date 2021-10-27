import axios from 'axios';

const API_URL = 'http://localhost:8081/';

class ApiService {
  getCargosUsuarios() {
    return axios.get(API_URL + 'auth/cargoUsuarios');
  }

}

export default new ApiService();
