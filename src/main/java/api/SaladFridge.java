package api;

import api.exceptions.WrongFoodTypeException;

import java.util.List;

public class SaladFridge extends Refridgerator{

    public SaladFridge(List<Food> fridge, int id) {
        super(fridge, id);
    }

    public void addFood(Food food) throws WrongFoodTypeException {
        if(food.getFoodType().toString().equals("VEGETABLE".toUpperCase()))
        {
            super.getFridge().add(food);
        }
        else
        {
            throw new WrongFoodTypeException ("You can only put vegetables in this fridge!");
        }
    }
}
