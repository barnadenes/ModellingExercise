package api;

import api.enums.FoodType;
import api.enums.Portion;
import api.interfaces.Edible;

public class Drink extends Food implements Edible {
    private String brand;
    private int alcoholContent;


    public Drink(String name, FoodType foodType, Portion calories, int weight, String brand, int alcohol) {
        super(name, foodType, calories, weight);
        this.brand = brand;
        this.alcoholContent = alcohol;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(int alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    @Override
    public String toString() {
        return "Drinks: \n" +
                " name : " + name + "\n" +
                " type : " + foodType + "\n" +
                " calories : " + calories.getCalories() + "kcal\n" +
                " amount : " + weight + "l" + "\n" +
                " brand : "  + brand + "\n" +
                " alcoholContent : " + alcoholContent + "%";
    }

    public boolean edile() {
        return false;
    }
}
