package api;
import api.exceptions.AteTooMuchException;
import api.exceptions.EmptyFridgeException;
import api.exceptions.FoodNotFoundException;
import api.exceptions.WrongFoodTypeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {
    private String name;
    private int calories;
    private List<Food> inventory;


    public User(String name, int calories) {
        this.name = name;
        this.calories = calories;
        this.inventory = new ArrayList<Food>();
    }

    //Takes food out of chosen fridge, and adds it to the users inventory.
    public void getFood(String foodName, Refridgerator refridgerator) throws EmptyFridgeException, FoodNotFoundException {
        if (refridgerator.getFridge().isEmpty())
        {
            throw new EmptyFridgeException("Empty fridge");
        }

        Iterator<Food> iterator = refridgerator.getFridge().iterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (food.getName().equals(foodName))
            {
                inventory.add(food);
                iterator.remove();
                break;
            }
            else
            {
                throw new FoodNotFoundException ("Food was not found in the fridge!");
            }
        }
    }

    public void eatFoodFromInventory(String fName) throws AteTooMuchException, FoodNotFoundException {
        Iterator<Food> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (food.getName().equals(fName))
            {
                if (this.calories - food.getCalories() < 0)
                {
                    throw new AteTooMuchException("You're full and can't eat more!");
                }
                else
                {
                    this.calories -= food.getCalories();
                }
                iterator.remove();
                break;
            }
            else if(inventory.isEmpty())
            {
                throw new FoodNotFoundException("Food was not found in the inventory!");
            }
        }
    }

    public void eatFoodFromFridge(String fName, Refridgerator refridgerator) throws AteTooMuchException, FoodNotFoundException {
        Iterator<Food> iterator = refridgerator.getFridge().iterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (food.getName().equals(fName))
            {
                if (this.calories - food.getCalories() < 0)
                {
                    throw new AteTooMuchException("You're full and can't eat more!");
                }
                else
                {
                    this.calories -= food.getCalories();
                }
                iterator.remove();
                break;
            }
            else if(refridgerator.getFridge().isEmpty() || !refridgerator.getFridge().contains(fName))
            {
                throw new FoodNotFoundException("Food was not found in the fridge!");
            }
        }
    }

    public void inventoryToFridge(String name, Refridgerator refridgerator) throws FoodNotFoundException, WrongFoodTypeException {
            Iterator<Food> iterator = inventory.iterator();
            while (iterator.hasNext()) {
                Food food1 = iterator.next();
                if (food1.getName().equals(name))
                {
                    refridgerator.addFood(food1);
                    iterator.remove();
                    break;
                }
                else if(refridgerator.getFridge().isEmpty())
                {
                    throw new FoodNotFoundException("Item was not found!");
                }
            }

    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public List<Food> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        if(inventory.isEmpty()){
            return "\n" + name + " can eat " + calories +
                    "kcal more today!\nUser " + name +
                    "\'s inventory is empty!\n";
        }
        return "\n" + name + " can eat " + calories +
                "kcal more today!\n\tUser " + name +
                "\'s inventory contains these items: " +
                inventory + "\n";
    }
}
