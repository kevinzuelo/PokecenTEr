package com.techelevator.model;

public class Collection {

    private int collectionId;
    private String name;
    private int  userId;
    private boolean isPrivate;

    public Collection(int collectionId, String name, int userId, boolean isPrivate) {
        this.collectionId = collectionId;
        this.name = name;
        this.userId = userId;
        this.isPrivate = isPrivate;
    }

    public Collection() {

    }


    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

}
