package cmd;

import api.*;
import api.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private List<User> users;
    private List<Refridgerator> fridgeList;
    private UserMenu userMenu;
    private int ID;
    private Scanner reader;

    public UI() {
        this.users = new ArrayList<User>();
        this.fridgeList = new ArrayList<Refridgerator>();
        this.reader = new Scanner(System.in);
        this.ID = 0;
    }

    public void header() {
        System.out.println("\t\t+==================+");
        System.out.println("\t\t| Fridge Simulator |");
        System.out.println("\t\t+==================+");
        System.out.println("");
        System.out.println("\t\t\t  Welcome!\n");
    }

    public void menuPrint() {
        System.out.println(" 1)  Create a User.");
        System.out.println(" 2)  Create a Refrigerator");
        System.out.println(" 3)  List Users.");
        System.out.println(" 4)  List Fridges.");
        System.out.println(" 5)  User Options.");
        System.out.println(" 6)  Quit");
        System.out.println("");
    }

    public void mainMenu() {
        int command = 0;
        header();

        while(true) {
            try {
                menuPrint();
                System.out.print("What would you like to do? ");
                command = Integer.parseInt(reader.next());
                if(command > 6 || command < 0)
                {
                    throw new OutOfRangeException("Choose a number beetween 1 - 6\n");
                }

                switch (command) {
                    case 1:
                        newUser();
                        break;
                    case 2:
                        System.out.println("Available Fridges to buy: |MEAT|VEGETABLE|DRINK|");
                        String coolerType = reader.next();
                        newRefrigerator(coolerType);
                        break;
                    case 3:
                        printUsers();
                        break;
                    case 4:
                        getFridges();
                        break;
                    case 5:
                        if(users.isEmpty() || fridgeList.isEmpty()) {
                            System.out.println("You either have no Users or Fridges! Create them before selecting this option!");
                            break;
                        }
                        System.out.println("Select a Refrigerator by ID: ");
                        printFridges();
                        int SelectID;
                        while(true)
                        {
                            try {
                                SelectID = Integer.parseInt(reader.next());
                                if ((SelectID > 0) && (SelectID <= ID) ){
                                    break;
                                }
                                else
                                {
                                    throw new OutOfRangeException("Number should stay between 1 - " + ID + "\n");
                                }
                            } catch (NumberFormatException e) {
                                System.out.print("Not a number!");
                            }

                        }
                        for (int i = 0; i < users.size(); i++) {
                            System.out.println(users.get(i));
                        }
                        System.out.println("Now select a User by name: ");
                        String name = reader.next();

                        Refridgerator fridgeChosen = fridgeSelect(SelectID);
                        User userChosen = userSelect(name);
                        this.userMenu = new UserMenu(userChosen, fridgeChosen);
                        userMenu.subMenu();
                        break;
                    case 6:
                        System.out.println("\nThanks for using my Fridge Simulator!");
                        System.out.println("               Bye!                  ");
                        System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("\nThat's not a valid number input!");
            }  catch (NoSuchFridgeException e) {
                System.out.println(e.getMessage());
            } catch (OutOfRangeException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchUserException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void newUser() {
        System.out.print("\nHello stranger, what's your name? ");
        String name = reader.next();

        int calorieLimit = 0;
        System.out.print("What is your daily calorie limit \"" + name + "\"?");

        while(true)
        {
            try {
                calorieLimit = Integer.parseInt(reader.next());
                break;
            } catch (NumberFormatException ne) {
                System.out.print("Not a valid number. Try again: ");
            }
        }

        User user = new User(name, calorieLimit);
        users.add(user);
        System.out.println("New user \"" + name + "\" has been created!\n");
    }

    public void newRefrigerator(String type) throws NoSuchFridgeException {
        this.ID++;
        if(type.toUpperCase().equals("MEAT"))
        {
            Refridgerator refridgerator = new MeatFridge(new ArrayList<Food>(), ID);
            fridgeList.add(refridgerator);
            System.out.println("An empty Meat fridge has been bought!\n");
        }
        else if(type.toUpperCase().equals("VEGETABLE"))
        {
            Refridgerator refridgerator = new SaladFridge(new ArrayList<Food>(), ID);
            fridgeList.add(refridgerator);
            System.out.println("An empty Vegetable fridge has been bought!\n");
        }
        else if(type.toUpperCase().equals("DRINK"))
        {
            Refridgerator refridgerator = new DrinkFridge(new ArrayList<Food>(), ID);
            fridgeList.add(refridgerator);
            System.out.println("An empty Drink fridge has been bought!\n");
        }
        else
        {
            if(this.ID != 0)
            {
                --this.ID;
            }
            throw new NoSuchFridgeException("There's no such Fridge type!");
        }
    }

    public void printUsers() {
        try
        {
            if(users.isEmpty())
            {
                throw new NoSuchUserException("User not found!");
            }
            else
            {
                for(User user : users)
                {
                    System.out.println(user);
                }
            }
        }
        catch (NoSuchUserException e)
        {
            System.out.println(e + "\n");
        }
    }

    public void printFridges() {
        if(!fridgeList.isEmpty())
        {
            for(Refridgerator refridgerator : fridgeList)
            {
                System.out.print("\t" + refridgerator.getClass().getSimpleName() + " no.: " + refridgerator.getId());
            }
            System.out.println("");
        }
        else
        {
            System.out.println("You don't have any Refrigerator! Buy one!\n");
        }
    }

    public User userSelect(String name) throws NoSuchUserException {
        while(true)
        {
            for (User user : users) {
                if (user.getName().equals(name)) {
                    return user;
                }
                else if(users.isEmpty() || !user.getName().equals(name))
                {
                    throw new NoSuchUserException("User not found!");
                }
            }
        }
    }

    public Refridgerator fridgeSelect(int ID) {
        try {
            for (Refridgerator refridgerator : fridgeList) {
                if (refridgerator.getId() == ID) {
                    return refridgerator;
                }
                else if(fridgeList.isEmpty())
                {
                    throw new NoSuchFridgeException("Wrong ID!");
                }
            }
        }
        catch (NoSuchFridgeException e) {
            System.out.println(e);
        }
        return null;
    }

    public void getFridges() {
        if (!fridgeList.isEmpty()) {
            for (Refridgerator refridgerator : fridgeList) {
                System.out.print(refridgerator);
            }
            System.out.println("");
        } else {
            System.out.println("You haven't bought any Fridge yet!\n");
        }
    }
}
