package api;

import api.enums.FoodType;
import api.enums.Portion;

public abstract class Food{
    protected String name;
    protected FoodType foodType;
    protected Portion calories;
    protected int weight;

    public Food(String name, FoodType foodType, Portion calories, int weight) {
        this.name = name;
        this.foodType = foodType;
        this.calories = calories;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getCalories() {
        return calories.getCalories();
    }

    public void setCalories(Portion calories) {
        this.calories = calories;
    }

    public int getAmount() {
        return weight;
    }

    public void setAmount(int amount) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return  "Food: \n" +
                "name: " + name + "\n" +
                "foodType: " + foodType + "\n" +
                "calories: " + calories + "\n" +
                "weight: " + weight + "kg";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return getName().equals(food.getName());
    }

}
