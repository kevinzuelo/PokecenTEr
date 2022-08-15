import axios from 'axios';

export default {

  getUserByUserId(userId) {
    return axios.get(`/users/${userId}`)
  },

  getAllUsers() {
    return axios.get('/users')
  },

  updateUser(user){
    return axios.put(`/users/${user.id}`, user)
  }


}
