package com.epam.knight.model.ammunition;

public class KnightAmmunition implements Ammunition {
    private String name;
    private AmmunitionCharacter[] ammunitionCharacter;
    private int weight;
    private int cost;

    public KnightAmmunition(String name, AmmunitionCharacter[] ammunitionCharacter, int weight, int cost) {
        this.name = name;
        this.ammunitionCharacter = ammunitionCharacter;
        this.weight = weight;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getCost() {
        return cost;
    }

    public AmmunitionCharacter[] getAmmunitionCharacter() {
        return ammunitionCharacter;
    }
}
