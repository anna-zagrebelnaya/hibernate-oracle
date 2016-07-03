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
    private int likes;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
