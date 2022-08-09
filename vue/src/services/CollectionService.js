import axios from 'axios';

export default {



  createCollection(newCollection) {
    return axios.post('/collections', newCollection)
  }

}
