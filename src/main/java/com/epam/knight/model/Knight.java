package com.epam.knight.model;

import com.epam.knight.model.ammunition.Ammunition;
import com.epam.knight.model.ammunition.AmmunitionCharacter;
import com.epam.knight.model.ammunition.KnightAmmunition;
import com.epam.knight.model.ammunition.WeaponType;

import java.util.Arrays;

public class Knight {

    private Ammunition[] ammunition;

    public Ammunition[] getAmmunition() {
        return this.ammunition;
    }

    /**
     * Add new ammunition element to knight
     * @param element that should be equipped to the knight
     */
    public void equip(Ammunition element) {
        if(this.ammunition == null){
            this.ammunition = new Ammunition[]{element};
        }
        else
        {
            Arrays.copyOf(this.ammunition, this.ammunition.length+1);
            this.ammunition[this.ammunition.length-1] = element;
        }
    }

    public int calculateAmmunitionWeight() {
        int sum = 0;
        if(this.ammunition!=null) {
            for (Ammunition ammunition :
                    this.ammunition) {
                sum+=ammunition.getWeight();
            }
        }
        return sum;
    }

    public int calculateAmmunitionCost() {
        int sum = 0;
        if(this.ammunition!=null) {
            for (Ammunition ammunition :
                    this.ammunition) {
                sum+=ammunition.getCost();
            }
        }
        return sum;
    }

    public int calculateAmmunitionDamage() {
        int sum = 0;
        if(this.ammunition!=null) {
            for (Ammunition ammunition :
                    this.ammunition) {
                if( ammunition instanceof KnightAmmunition) {
                    AmmunitionCharacter[] ammunitionCharacters =  ((KnightAmmunition) ammunition).getAmmunitionCharacter();
                    for (AmmunitionCharacter ac:
                         ammunitionCharacters) {
                        if (ac.getWeaponType()== WeaponType.Offensive) {
                            sum +=ac.getValue();
                        }
                    }
                }
            }
        }
        return sum;
    }

    public int calculateAmmunitionProtection() {
        int sum = 0;
        if(this.ammunition!=null) {
            for (Ammunition ammunition :
                    this.ammunition) {
                if( ammunition instanceof KnightAmmunition) {
                    AmmunitionCharacter[] ammunitionCharacters =  ((KnightAmmunition) ammunition).getAmmunitionCharacter();
                    for (AmmunitionCharacter ac:
                            ammunitionCharacters) {
                        if (ac.getWeaponType()== WeaponType.Defensive) {
                            sum +=ac.getValue();
                        }
                    }
                }
            }
        }
        return sum;
    }

}