package api;

import api.enums.FoodType;
import api.enums.Portion;
import api.interfaces.Edible;

public class Meat extends Food implements Edible {

    public Meat(String name, FoodType foodType, Portion calories, int weight) {
        super(name, foodType, calories, weight);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public FoodType getFoodType() {
        return super.getFoodType();
    }

    @Override
    public void setFoodType(FoodType foodType) {
        super.setFoodType(foodType);
    }

    @Override
    public int getCalories() {
        return super.getCalories();
    }

    @Override
    public void setCalories(Portion calories) {
        super.setCalories(calories);
    }

    @Override
    public int getAmount() {
        return super.getAmount();
    }

    @Override
    public void setAmount(int weight) {
        super.setAmount(weight);
    }

    @Override
    public String toString() {
        return  "Meat: \n" +
                " name: " + name + "\n" +
                " foodType: " + foodType + "\n" +
                " calories: " + calories.getCalories() + "kcal\n" +
                " weight: " + weight + "kg";
    }

    public boolean edile() {
        return false;
    }
}
