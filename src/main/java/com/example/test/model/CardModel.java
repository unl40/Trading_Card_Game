package com.example.test.model;

import com.example.test.strategy.DamageStrategy;
import lombok.Data;

@Data
public class CardModel {

    private int manaCost;
    private DamageStrategy damageStrategy;


    public int getDamage() {
        return getDamageStrategy().getDamage();
    }

}
