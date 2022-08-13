import axios from 'axios';

export default {

  getUserByUserId(userId) {
    return axios.get(`/users/${userId}`)
  }

}
