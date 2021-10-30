package com.epam.knight.view;

import com.epam.knight.model.Knight;
import com.epam.knight.model.ammunition.Ammunition;
import com.epam.knight.model.ammunition.AmmunitionCharacter;
import com.epam.knight.model.ammunition.KnightAmmunition;
import com.epam.knight.model.ammunition.WeaponType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ConsoleView {
    public static void menu(Knight knight){
        boolean continue_ = true;
        do {
            System.out.println("Main menu:\n" +
                    "1. Print knight stats\n" +
                    "2. Show ammunition\n" +
                    "3. Equip ammunition\n" +
                    "4. Sort ammunition\n" +
                    "5. Search ammunition\n" +
                    "6. Exit\n" +
                    "Choose option:");
            Scanner in = new Scanner(System.in);
            try {
                byte chooseNumber = in.nextByte();
                switch (chooseNumber){
                    case 1:
                        printKnightStats(knight);
                        break;
                    case 2:
                        showAmmunition(knight);
                        break;
                    case 3:
                        equipAmmunition(knight);
                        break;
                    case 4:
                        sortAmmunition(knight);
                        break;
                    case 5:
                        searchAmmunition(knight);
                        break;
                    case 6:
                        System.out.println("bye");
                        continue_=false;
                        break;
                    default:
                        System.out.println("out of range, try another one");

                }

            } catch (Exception ex) {
                System.out.println("bad option, try another one.");
                in.nextLine();
                continue_=true;
            }
        }while (continue_);

    }
    public static void printKnightStats(Knight knight){
        System.out.printf("Ammunition cost: %d\n" +
                "Ammunition weight: %d\n" +
                "Ammunition damage: %d\n" +
                "Ammunition protection: %d\n",
                knight.calculateAmmunitionCost(),
                knight.calculateAmmunitionWeight(),
                knight.calculateAmmunitionDamage(),
                knight.calculateAmmunitionProtection());
        menu(knight);
    }
    public static void showAmmunition(Knight knight){
        for (Ammunition ammunition:
             knight.getAmmunition()) {
            KnightAmmunition ka = (KnightAmmunition) ammunition;
            String ammunitionPropsString = ka.getName()+"{";
            AmmunitionCharacter[] ACs = ka.getAmmunitionCharacter();
            for (int i = 0; i< ACs.length; i++) {
                ammunitionPropsString+=ACs[i].getTypeAmmunition()+"="+ACs[i].getValue() + ", ";
            }
            ammunitionPropsString +=String.format("weight=%d, cost=%d}", ammunition.getWeight(), ammunition.getCost());
            System.out.println(ammunitionPropsString);
        }
        menu(knight);
    }

    public static void equipAmmunition(Knight knight){
        System.out.println("Enter name of ammunition:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int weight = 0;
        int cost = 0;
        boolean error;
        do {
            try {
                System.out.println("Enter weight of ammunition:");
                weight = in.nextInt();
                error = false;
            } catch (Exception ex) {
                System.out.println("Incorrect value, try again");
                in.nextLine();
                error = true;
            }
        }while (error);

        do {
            try {
                System.out.println("Enter cost of ammunition:");
                cost = in.nextInt();
                error = false;
            } catch (Exception ex) {
                System.out.println("Incorrect value, try again");
                in.nextLine();
                error = true;
            }
        }while (error);


        AmmunitionCharacter[] ammunitionCharacters = new AmmunitionCharacter[0];
        AmmunitionCharacter ammunitionCharacter;
        boolean continue_;
        do{
            System.out.println("1. Add offensive properties\n" +
                    "2. Add defensive properties\n" +
                    "3. End of adding properties(default)\n" +
                    "Choose option:");
            try{
                byte chooseNumber = in.nextByte();
                switch (chooseNumber){
                    case 1:
                        ammunitionCharacter = addAmmunitionCharacter(WeaponType.Offensive);
                        ammunitionCharacters= Arrays.copyOf(ammunitionCharacters, ammunitionCharacters.length+1);
                        ammunitionCharacters[ammunitionCharacters.length-1] = ammunitionCharacter;
                        continue_ = true;
                        break;
                    case 2:
                        ammunitionCharacter = addAmmunitionCharacter(WeaponType.Defensive);
                        ammunitionCharacters= Arrays.copyOf(ammunitionCharacters, ammunitionCharacters.length+1);
                        ammunitionCharacters[ammunitionCharacters.length-1] = ammunitionCharacter;
                        continue_ = true;
                        break;
                    case 3:
                        continue_ = false;
                        if (ammunitionCharacters.length==0)
                        {
                            System.out.println("properties should be at least one, try to choose 1-2");
                            continue_ = true;
                        }
                        break;
                    default:
                        System.out.println("Incorrect value, try again");
                        continue_ = true;
                }
            }catch (Exception exception)
            {
                System.out.println("Incorrect value, try again");
                in.nextLine();
                continue_ = true;
            }
        }while (continue_);

        KnightAmmunition knightAmmunition = new KnightAmmunition(name, ammunitionCharacters, weight, cost );
        knight.equip(knightAmmunition);
    }
    private static AmmunitionCharacter addAmmunitionCharacter(WeaponType weaponType)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name of property");

        String nameProp = in.nextLine();
        int valueProp = 0;
        boolean error;
        do {
            try {
                System.out.println("Enter value of property:");
                valueProp = in.nextInt();
                error = false;
            } catch (Exception ex) {
                System.out.println("Incorrect value, try again");
                in.nextLine();
                error = true;
            }
        }while (error);

        return new AmmunitionCharacter(nameProp, valueProp, weaponType);

    }

    public static void sortAmmunition(Knight knight){
        Scanner in = new Scanner(System.in);
        boolean error;
        do{
            System.out.println("Choose sort type:\n" +
                    "1. Cost\n" +
                    "2. Weight\n" +
                    "Choose option:");
            try{
                byte chooseNumber = in.nextByte();
                switch (chooseNumber){
                    case 1:
                        Comparator<Ammunition> knightAmmunitionComparatorByCost = new Comparator<Ammunition>() {
                            @Override
                            public int compare(Ammunition o1, Ammunition o2) {
                                return Integer.compare(o1.getCost(), o2.getCost());
                            }
                        };
                        Arrays.sort(knight.getAmmunition(), knightAmmunitionComparatorByCost);
                        showAmmunition(knight);
                        error = false;
                        break;
                    case 2:
                        Comparator<Ammunition> knightAmmunitionComparatorByWeight = new Comparator<Ammunition>() {
                            @Override
                            public int compare(Ammunition o1, Ammunition o2) {
                                return Integer.compare(o1.getWeight(), o2.getWeight());
                            }
                        };
                        Arrays.sort(knight.getAmmunition(), knightAmmunitionComparatorByWeight);
                        showAmmunition(knight);
                        error = false;
                        break;

                    default:
                        System.out.println("Incorrect value, try again");
                        error = true;
                }
            }catch (Exception exception)
            {
                System.out.println("Incorrect value, try again");
                in.nextLine();
                error = true;
            }
        }while (error);


    }
    interface PropFilter{
        boolean isFiltered (Ammunition n, int min, int max);
    }
    public static void searchAmmunition(Knight knight){
        Scanner in = new Scanner(System.in);
        boolean error;
        PropFilter funcCost = (p, min, max) -> p.getCost()>min&&p.getCost()<max;
        PropFilter funcWeight = (p, min, max) -> p.getWeight()>min&&p.getWeight()<max;
        PropFilter choosen = funcCost;
        String tail = "";
        do{
            System.out.println("Choose search type:\n" +
                    "1. Cost(default)\n" +
                    "2. Weight\n" +
                    "Choose option:");

            try{
                byte chooseNumber = in.nextByte();
                switch (chooseNumber){

                    case 2:
                        choosen = funcWeight;
                        tail = "weight";
                        error = false;
                        break;
                    case 1:
                    default:
                        choosen = funcCost;
                        tail = "cost";
                        error = false;

                }
            }catch (Exception exception)
            {
                System.out.println("Incorrect value, try again");
                in.nextLine();
                error = true;
            }
        }while (error);

        int min = 0;
        int max = 0;
        do {
            System.out.println("Input minimum "+tail);
            try {
                min = in.nextInt();
                error = false;
            }catch (Exception ex)
            {
                System.out.println("Incorrect value, try again");
                in.nextLine();
                error = true;
            }

        }while (error);
        do {

            System.out.println("Input maximum "+tail);
            try {
                max = in.nextInt();
                error = false;
            }catch (Exception ex)
            {
                System.out.println("Incorrect value, try again");
                in.nextLine();
                error = true;
            }

        }while (error);
        showAmmunitionFiltered(knight,min,max, choosen);

    }
    public static void showAmmunitionFiltered(Knight knight, int min, int max, PropFilter func){
        for (Ammunition ammunition:
                knight.getAmmunition()) {
            if(func.isFiltered(ammunition,min, max)) {
                KnightAmmunition ka = (KnightAmmunition) ammunition;
                String ammunitionPropsString = ka.getName() + "{";
                AmmunitionCharacter[] ACs = ka.getAmmunitionCharacter();
                for (int i = 0; i < ACs.length; i++) {
                    ammunitionPropsString += ACs[i].getTypeAmmunition() + "=" + ACs[i].getValue() + ", ";
                }
                ammunitionPropsString += String.format("weight=%d, cost=%d}", ammunition.getWeight(), ammunition.getCost());
                System.out.println(ammunitionPropsString);
            }
        }
    }
}
