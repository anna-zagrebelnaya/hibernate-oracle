package com.anka.inheritance.asset;

import javax.persistence.*;

@Entity
@Table(name = "FB_ASSET")
@PrimaryKeyJoinColumn(name="ID")
public class FBAsset extends Asset {

    @Column(name = "FRIENDS", precision = 10)
    private int friends;

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }
}
