package api;

import java.util.List;

import api.exceptions.*;

public class Refridgerator {
    private List<Food> fridge;
    private int id;

    public Refridgerator(List<Food> fridge, int id) {
        this.fridge = fridge;
        this.id = id;
    }

    public List<Food> getFridge() {
        return fridge;
    }

    //Not implemented!
    public String showMeatinFridge() throws EmptyFridgeException {
        if(fridge == null)
        {
            throw new EmptyFridgeException("The fridge is empty");
        }
        else
        {
            String meatList ="Meat: \n";

            for(Food food : fridge)
            {
                if(food instanceof Meat)
                {
                    meatList += "\t" + food.getName() + "husibol " + food.getAmount() + " darab van a hűtőben.\n";
                }
            }
            return meatList;
        }

    }

    //Not implemented!
    public String showVegetableinFridge() throws EmptyFridgeException {
        if(fridge == null)
        {
            throw new EmptyFridgeException("The fridge is empty");
        }
        String vegetableList = "Vegetable: " + "\n";

        for(Food food : fridge)
        {
            if(food instanceof Vegetable)
            {
                vegetableList += "\t" + food.getName() + "bol " + food.getAmount() + " darab van a hűtőben.\n";
            }
        }
        return vegetableList;
    }

    //Not implemented!
    public String showDrinksinFridge() throws EmptyFridgeException {
        if(fridge == null)
        {
            throw new EmptyFridgeException("The fridge is empty");
        }
        String drinkList ="Drink: \n";

        for(Food food : fridge)
        {
            if(food instanceof Drink)
            {
                drinkList += "\t" + food.getName() + "bol " + food.getAmount() + " darab van a hűtőben.\n";
            }
        }
        return drinkList;
    }

    public int getId() {
        return id;
    }

    //Shows this Fridge and its content.
    @Override
    public String toString() {
        return "Refrigerator no." +
                id + " contains: \n" +
                fridge + "\n";
    }
}
