package com.anka.inheritance.asset;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TWITTER_ASSET")
@PrimaryKeyJoinColumn(name="ID")
@Data
@EqualsAndHashCode(callSuper = true)
public class TWAsset extends Asset{

    @Column(name = "likes", nullable = false, precision = 10)
    private int likes;

}
