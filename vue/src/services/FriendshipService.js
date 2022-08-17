import axios from 'axios';

export default {

  getFriendslist(userId){
    return axios.get(`/users/${userId}/friends`);
  },

  addFriend(userId, friendUserId){
      let friendship = {
          ownerId: userId,
          friendId: friendUserId
      }

      return axios.post("/friends", friendship);
  },

  unfriend(userId, friendUserId){
   

    return axios.delete("/friends", { data: {ownerId: userId, friendId: friendUserId} });
    }
}