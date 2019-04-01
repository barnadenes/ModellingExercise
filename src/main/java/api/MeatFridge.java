package api;

import api.enums.FoodType;
import api.exceptions.WrongFoodTypeException;

import java.util.List;

public class MeatFridge extends Refridgerator{

    public MeatFridge(List<Food> fridge, int id) {
        super(fridge, id);
    }

    public FoodType getFoodType() {
        return FoodType.MEAT;
    }

    public void addFood(Food food) throws WrongFoodTypeException {
        if (food.getFoodType().toString().equals("MEAT".toUpperCase())) {
            super.getFridge().add(food);
        } else {
            throw new WrongFoodTypeException("You can only put meat in this fridge!");
        }
    }
}
