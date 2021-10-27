import axios from 'axios';

const API_URL = 'http://localhost:8081/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      password: user.password,
      firstName: user.firstName,
      lastName: user.lastName,
      cargo: user.cargo,
      email: user.email,
    });
  }
}

export default new AuthService();
