package com.anka.inheritance.asset;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "FB_ASSET")
@PrimaryKeyJoinColumn(name="ID")
@Data
@EqualsAndHashCode(callSuper = true)
public class FBAsset extends Asset {

    @Column(name = "friends", nullable = false, precision = 10)
    private int friends;

}
