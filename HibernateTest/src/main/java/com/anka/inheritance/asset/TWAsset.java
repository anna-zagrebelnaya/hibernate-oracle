package com.anka.inheritance.asset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TWITTER_ASSET")
@PrimaryKeyJoinColumn(name="ID")
public class TWAsset extends Asset{

    @Column(name = "LIKES", precision = 10)
    private int friendsOrLikes;

    public int getFriendsOrLikes() {
        return friendsOrLikes;
    }

    public void setFriendsOrLikes(int friendsOrLikes) {
        this.friendsOrLikes = friendsOrLikes;
    }
}
