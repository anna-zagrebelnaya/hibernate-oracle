package com.anka.inheritance.asset;

import javax.persistence.*;

@Entity
@Table(name = "FB_ASSET")
@PrimaryKeyJoinColumn(name="ID")
public class FBAsset extends Asset {

    @Column(name = "FRIENDS", precision = 10)
    private int friendsOrLikes;

    public int getFriendsOrLikes() {
        return friendsOrLikes;
    }

    public void setFriendsOrLikes(int friendsOrLikes) {
        this.friendsOrLikes = friendsOrLikes;
    }
}
