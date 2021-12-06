package main.java;

import main.java.data.Item;
import main.java.data.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Provides necessary methods to deal through the Warehouse management actions
 *
 * @author riteshp
 */
public class TheWarehouseManager {
  // =====================================================================================
  // Member Variables
  // =====================================================================================

  // To read inputs from the console/CLI
  private final Scanner reader = new Scanner(System.in);
  public final String[] userOptions = {
    "1. List items by warehouse",
          "2. Search an item and place an order",
          "3. Browse by category",
          "4. Quit"
  };
  // To refer the user provided name.
  private String userName;

  // =====================================================================================
  // Public Member Methods
  // =====================================================================================

  /** Welcome User */
  public void welcomeUser() {
    this.seekUserName();
    this.greetUser();
  }

  /** Ask for user's choice of action */
  public int getUsersChoice() {
    int choice;
    System.out.println("What would you like to do now?");
    for (String option: this.userOptions){
    System.out.println(option);
    }

    System.out.println("Please type the number below:");
    do {
      String selectedOption = this.reader.nextLine();
      try {
        choice = Integer.parseInt(selectedOption);
      } catch (Exception e) {
        choice = 0;
      }
      // Guide the user to enter correct value
      if (choice < 1 || choice > userOptions.length) {
        System.out.printf("Sorry! Enter an integer between 1 and %d. ", this.userOptions.length);
      }
    } while (choice < 1 || choice > userOptions.length);

    // return the valid choice
    return choice;
  }


  /** Initiate an action based on given option */
  public void performAction(int option) {
    switch(option){
      case 1:
        this.listItemsByWarehouse();
        break;
      case 2:
        this.searchItemAndPlaceOrder();
        break;
      case 3:
        this.browseByCategory();
        break;
      case 4:
        this.quit();
        break;
      default:
        System.out.println("It is not a valid operation");
    }
  }

  /**
   * Confirm an action
   *
   * @return action
   */
  public boolean confirm(String message) {
      System.out.printf("%s (y/n)%n", message);
      String choice;
      do {
        choice = this.reader.nextLine();
        if (choice.length() > 0) {
          choice = choice.trim();
        }
      } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));

      return choice.equalsIgnoreCase("y");
    }

    /** End the application */
    public void quit() {
      System.out.printf("\nThanks for visiting %s!\n", this.userName);
      System.exit(0);
    }


    // =====================================================================================
    // Private Methods
    // =====================================================================================

    /** Get user's name via CLI */
    private void seekUserName () {
      System.out.print("Hey!Great to see you, please tell my what is your name.");
      this.userName = this.reader.nextLine();

    }

    /** Print a welcome message with the given user's name */
    private void greetUser () {
      System.out.printf("\nWelcome, %s !\n", this.userName);
    }

    private void listItemsByWarehouse (){
        Set<Integer> warehouses=Repository.getWarehouses();
        for(int warehouse:warehouses){
        System.out.printf("Items in warehouse %d:%n",warehouse);
        this.listItems(Repository.getItemsByWarehouse(warehouse));
        }

        System.out.println();
        for(int warehouse:warehouses){
        System.out.printf(
                "Total items in warehouse %d: %d%n", warehouse, Repository.getItemsByWarehouse(warehouse).size());
        }
        }

  private void listItems(String[] warehouse) {
    for (String item : warehouse) {
      System.out.printf("\n- %s\n", item);
    }
  }

  private void searchItemAndPlaceOrder() {
    String itemName = askItemToOrder();
    int availableAmount = this.getAvailableAmount(itemName);
    if(availableAmount > 0){
      this.askAmountAndConfirmOrder(availableAmount, itemName);
    }


  }

  /**
   * Ask the user to specify an Item to Order
   *
   * @return String itemName
   */
  private String askItemToOrder() {
    System.out.println("Which item you are interested in?");
    return  this.reader.nextLine();

  }

  /**
   * Calculate total availability of the given item
   *
   * @param itemName itemName
   * @return integer availableCount
   */
 private int getAvailableAmount(String itemName) {
   int count1 = find(itemName, WAREHOUSE1);
   int count2 = find(itemName, WAREHOUSE2);
   int availableAmount = count1+count2;

   String foundInWareHouse;
   if(count1 > 0 &&  count2 > 0){
     foundInWareHouse = "Both Warehouses";
   }else if( count1 > 0){
     foundInWareHouse = "Warehouse1";
   }else if( count2 > 0){
     foundInWareHouse = "Warehouse2";
   }else{
     foundInWareHouse = "Not in stock";
   }
   int maximumQuantity;
   String maximumQuantityIn;

   if(foundInWareHouse.equals("Not in stock")){
     maximumQuantity = 0;
     maximumQuantityIn = "Not in stock";
   }else if( count1 > count2 ){
     //there are more quantity of item in warehouse 1
     maximumQuantity = count1;
     maximumQuantityIn = "Warehouse 1";
   }else if(count2 > count1){
     //there are more quantity of item in warehouse 2
     maximumQuantity = count2;
     maximumQuantityIn = "Warehouse 2";
   }else {
     maximumQuantity = count2; // or count 1 because they are equal
     maximumQuantityIn = "Both warehouses have the same Quantity";
   }

   //display the availability details
   //The total amount
   System.out.printf("Amount available: %d\n",availableAmount);
   //The location of those items : foundInWareHouse
   System.out.printf("Location: %s\n",foundInWareHouse);
   //the maximum availability of those items.
   if(foundInWareHouse.equals("Not in stock")){
     System.out.printf("Maximum availability is:0 and  %s is Not in stock",itemName);
   }else if(count1 == count2){
     System.out.printf("Maximum availability is: %d and Both warehouses have the same availability", count1);
   }else{
     System.out.printf("Maximum availability is: %d in %s\n", maximumQuantity, maximumQuantityIn);
   }

   //return total available amount
   return availableAmount;
 }


  /**
   * Find the count of an item in a given warehouse
   *
   * @param item the item
   * @param warehouse the warehouse
   * @return count
   */
 private int find(String item, String[] warehouse) {
   int count = 0;
   for (String wItem :warehouse ) {
     if(item.equals(wItem)){
       count++;
     }
   }
   return count;
  }

  /** Ask order amount and confirm order */
  private void askAmountAndConfirmOrder(int availableAmount, String item) {
    boolean toOrder = this.confirm("Would you like to order this item?");
    if(toOrder){
      //If the answer is yes, it should ask the user how many do they want
      int orderAmount = this.getOrderAmount(availableAmount);

      //if the orderAmount is -1 then just go to the searchItemAndPlaceOrder()  :
      if(orderAmount > 0){
        System.out.printf("%d %s %s been ordered",orderAmount,item,(orderAmount ==1 ? "has" : "have"));
      }

    }
  }

  /**
   * Get amount of order
   *
   * @param availableAmount
   * @return
   */
  private int getOrderAmount(int availableAmount) {
    int orderAmount = -1;
    System.out.println("What amount would you like to have?");
    do{
      // read the amount from the cli
      orderAmount = Integer.parseInt(this.reader.nextLine());
      // if the orderAmount is more than the availableAmount
      if(orderAmount > availableAmount){
        // it should show an error message and should ask

        System.out.println("***************************************************************");
        System.out.println("There are not many available. The maximum amount that can be ordered is "+ availableAmount);
        System.out.println("***************************************************************");

        boolean orderAll = this.confirm("Would you like to order the maximum available?");

        if(orderAll){
          orderAmount = availableAmount;
        }else{
          boolean keepOrdering = this.confirm("Do you want to order another amount of this item?");
          if(keepOrdering){
            orderAmount = -1;
          }else{
            orderAmount = 0;
          }

        }


      }else if(orderAmount < 0){

        System.out.println("It is not the right amount. Please put the number more than 0.");
      }else{
        return -1;
      }
    }while(orderAmount < 0 || orderAmount > availableAmount);

    return orderAmount;
  }
        }
