package api;

import api.exceptions.WrongFoodTypeException;

import java.util.List;

public class DrinkFridge extends Refridgerator {

    public DrinkFridge(List<Food> fridge, int id) {
        super(fridge, id);
    }

    public void addFood(Food food) throws WrongFoodTypeException {
        if (food.getFoodType().toString().equals("DRINK".toUpperCase())) {
            super.getFridge().add(food);
        } else {
            throw new WrongFoodTypeException("You can only put drinks in this fridge!");
        }
    }
}
