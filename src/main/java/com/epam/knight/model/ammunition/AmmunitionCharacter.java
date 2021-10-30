package com.epam.knight.model.ammunition;

public class AmmunitionCharacter{
    private String name;
    private int value;
    private WeaponType weaponType;

    public AmmunitionCharacter(String name, int value, WeaponType weaponType) {
        this.name = name;
        this.value = value;
        this.weaponType = weaponType;
    }

    public String getTypeAmmunition() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
}