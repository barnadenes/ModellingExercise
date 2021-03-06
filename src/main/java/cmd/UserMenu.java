package cmd;

import api.*;
import api.enums.FoodType;
import api.enums.Portion;
import api.exceptions.AteTooMuchException;
import api.exceptions.EmptyFridgeException;
import api.exceptions.FoodNotFoundException;
import api.exceptions.WrongFoodTypeException;

import java.util.Scanner;

public class UserMenu {
    private Scanner reader;
    private User user;
    private Refridgerator refridgerator;

    public UserMenu(User user, Refridgerator refridgerator) {
        this.user = user;
        this.refridgerator = refridgerator;
        this.reader = new Scanner(System.in);
    }

    public void userMenuPrint() {
        System.out.println("\n 1)  Add new Food to the Fridge.");
        System.out.println(" 2)  Put food in the Fridge (From inventory)");
        System.out.println(" 3)  Take Food From Fridge (Add it to inventory).");
        System.out.println(" 4)  Take Food From Fridge (Eat it!).");
        System.out.println(" 5)  Take Food From Inventory (Eat it!).");
        System.out.println(" 6)  Check remaining calories for user.");
        System.out.println(" 7)  Fridge Content");
        System.out.println(" 8)  Back");
    }

    public void subMenu() {
        int command = 0;
        while(true) {
            try {
                userMenuPrint();
                System.out.print("What would you like to do? ");
                command = Integer.parseInt(reader.next());
                while(command > 8 || command < 1) {
                    System.out.println("Input must stay between 1 and 8!");
                    command = Integer.parseInt(reader.next());
                }
                if(command == 8)
                {
                    break;
                }

                switch (command) {
                    case 1:
                        addNewFoodToFridge();
                        break;
                    case 2:
                        System.out.println(user);
                        System.out.print("Enter the name of the item: ");
                        String item = reader.next();
                        user.inventoryToFridge(item,this.refridgerator);
                        break;
                    case 3:
                        System.out.println(refridgerator);
                        System.out.print("What is the name of the food?: ");
                        String fName = reader.next();
                        user.getFood(fName, refridgerator);
                        System.out.println(fName + " was placed in your inventory!");
                        break;
                    case 4:
                        System.out.println(refridgerator.toString());
                        if(refridgerator.getFridge().isEmpty())
                        {
                            throw new EmptyFridgeException("Fridge is Empty!");
                        }
                        else
                        {
                            System.out.print("What would you like to eat?: ");
                            String foodToConsume = reader.next();
                            user.eatFoodFromFridge(foodToConsume, this.refridgerator);
                            System.out.println("Food was eaten!");
                            break;
                        }
                    case 5:
                        if(user.getInventory().isEmpty())
                        {
                            throw new EmptyFridgeException("Inventory is Empty!");
                        }
                        else
                        {
                            System.out.println(user);
                            System.out.print("What would you like to eat?: ");
                            String foodToEat = reader.next();
                            user.eatFoodFromInventory(foodToEat);
                            System.out.println("Food was eaten!");
                            break;
                        }
                    case 6:
                        System.out.println(user.getCalories() + "kcal remains.");
                        break;
                    case 7:
                        if(refridgerator.getFridge().isEmpty()) {
                            System.out.println("Empty Fridge!");
                            break;
                        }
                        else if( refridgerator == null )
                        {
                            System.out.println("Empty Fridge!");
                        }
                        else {
                            System.out.println(refridgerator);
                            break;
                        }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nThat's not a valid number input!");
            } catch (AteTooMuchException e) {
                System.out.println(e.getMessage());
            } catch (EmptyFridgeException e) {
                System.out.println(e.getMessage());
            } catch (FoodNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("There is no such option!");
            } catch (WrongFoodTypeException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void addNewFoodToFridge() throws IllegalArgumentException, WrongFoodTypeException {
        System.out.print("What's the name of the Food?: ");
        String foodName = reader.next();
        System.out.print("What kind of Food it is? (DRINK|MEAT|VEGETABLE): ");
        String foodType = reader.next().toUpperCase();

        if(!foodType.equals(refridgerator.getFoodType().name().toUpperCase()))
        {
            throw new WrongFoodTypeException("You can't add " + foodType + " to a " + refridgerator.getClass().getSimpleName());
        }

        while (!foodType.equals(FoodType.MEAT.toString()) && !foodType.equals(FoodType.VEGETABLE.toString()) && !foodType.equals(FoodType.DRINK.toString()))
        {
            System.out.print("Write meat, vegetable or drink: ");
            foodType = reader.next().toUpperCase();
        }

        System.out.print("Does it contain a (LARGE|MEDIUM|SMALL) portion of calories?: ");
        Portion portion = Portion.valueOf(reader.next().toUpperCase());

        if(foodType.equals(FoodType.DRINK.toString()))
        {
            System.out.print("How much would you like to add(in liter)?: ");
            int liter = Integer.parseInt(reader.next());

            System.out.print("What Brand is it?: ");
            String brand = reader.next();

            System.out.print("What's the alcohol percentage?: ");
            int alcohol = Integer.parseInt(reader.next());

            refridgerator.getFridge().add(new Drink(foodName, FoodType.DRINK, portion, liter, brand, alcohol));
        }
        else if(foodType.equals(FoodType.MEAT.toString()))
        {
            System.out.print("How much would you like to add(in kg)?: ");
            int amount = Integer.parseInt(reader.next());

            refridgerator.getFridge().add(new Meat(foodName, FoodType.MEAT, portion, amount));
        }
        else if(foodType.equals(FoodType.VEGETABLE.toString()))
        {
            System.out.print("How much would you like to add(in kg)?: ");
            int amount = Integer.parseInt(reader.next());

            refridgerator.getFridge().add(new Vegetable(foodName, FoodType.VEGETABLE, portion, amount));
        }

    }

}
