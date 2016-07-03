package com.anka.inheritance.asset;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FB_ASSET")
@PrimaryKeyJoinColumn(name="ID")
@Data
public class FBAsset extends Asset {

    @Column(name = "FRIENDS", precision = 10)
    private int friends;

}
