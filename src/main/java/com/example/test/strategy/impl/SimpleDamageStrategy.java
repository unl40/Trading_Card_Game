package com.example.test.strategy.impl;

import com.example.test.model.CardModel;
import com.example.test.strategy.DamageStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = "cardModel")
@ToString(exclude = "cardModel")
public class SimpleDamageStrategy implements DamageStrategy {

    private CardModel cardModel;

    public static DamageStrategy from(CardModel cardModel) {
        SimpleDamageStrategy simpleDamageStrategy = new SimpleDamageStrategy();
        simpleDamageStrategy.setCardModel(cardModel);
        return simpleDamageStrategy;
    }

    @Override
    public int getDamage() {
        return cardModel.getManaCost();
    }

}
