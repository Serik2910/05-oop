package com.epam.knight.controller;

import com.epam.knight.model.Knight;
import com.epam.knight.model.ammunition.Ammunition;
import com.epam.knight.model.ammunition.AmmunitionCharacter;
import com.epam.knight.model.ammunition.KnightAmmunition;
import com.epam.knight.model.ammunition.WeaponType;

public class KnightGenerator {

    /**
     * Use it to quickly generate knight
     * @return knight
     */
    public static Knight generateKnight() {
        KnightAmmunition knightAmmunitionPike = new KnightAmmunition("Pike",
                new AmmunitionCharacter[]{
                        new AmmunitionCharacter("prick", 40, WeaponType.Offensive)
                }, 35, 70);
        KnightAmmunition knightAmmunitionSword = new KnightAmmunition("Sword",
                new AmmunitionCharacter[]{
                        new AmmunitionCharacter("cut", 30, WeaponType.Offensive),
                        new AmmunitionCharacter("block", 10, WeaponType.Defensive)
                }, 28, 100);
        KnightAmmunition knightAmmunitionHelmet = new KnightAmmunition("Helmet",
                new AmmunitionCharacter[]{
                        new AmmunitionCharacter("armor", 35, WeaponType.Defensive)
                }, 50, 40);
        Knight knight = new Knight();
        knight.equip(knightAmmunitionPike);
        knight.equip(knightAmmunitionSword);
        knight.equip(knightAmmunitionHelmet);
        return knight;
    }

}
