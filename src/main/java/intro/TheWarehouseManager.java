package main.java.intro;

import static main.java.intro.Repository.WAREHOUSE1;
import static main.java.intro.Repository.WAREHOUSE2;
import static main.java.intro.Repository.WAREHOUSE1;

import java.util.Scanner;
import java.util.*;

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
          "3. Quit"
  };
  // To refer the user provided name.
  private String userName;

  // =====================================================================================
  // Public Member Methods
  // =====================================================================================

  /** Welcome User */
  public void welcomeUser() {
    this.seekUserName();
    //this.greetUser();


  }

  /** Ask for user's choice of action */
  public int getUsersChoice() {
    System.out.println("You probably came here with a purpose. Please tell me what would you like to do?");
    for (String i: userOptions){
    System.out.println(i);
    }

    System.out.println("Please type the number below");
    Scanner inputChoice = new Scanner(System.in);
    String usersChoice = inputChoice.nextLine();

    return Integer.parseInt(usersChoice);

  }

  /** Initiate an action based on given option */
  public void performAction(int option) {
  //if(option == 1) {
    //System.out.println("Here is what we have in Warehouse 1: "+Arrays.toString(Repository.WAREHOUSE1));
    //System.out.println("Here is what we have in Warehouse 2: "+Arrays.toString(Repository.WAREHOUSE2));
  //}else if (option == 2){
    //System.out.println("Which item you are interested in?");
    //Scanner itemName = new Scanner(System.in);
    //String itemInput = itemName.nextLine();
    //for (String j: Repository.WAREHOUSE1){
      //if(j == itemInput){
        //int numberOfItems = numberOfItems++;
      //}
    //}


  }

  }
    //


  /**
   * Confirm an action
   *
   * @return action
   */
  public boolean confirm(String message) {
    // TODO
  //}

  /** End the application */
  public void quit() {
    System.out.printf("\nThank you for your visit, %s!\n", this.userName);
    System.exit(0);
  }

  // =====================================================================================
  // Private Methods
  // =====================================================================================

  /** Get user's name via CLI */
  private void seekUserName() {
    Scanner userScanner = new Scanner(System.in);
    System.out.println("Hello! Great to see you! Please give me your name so I can help you further");
    String userName = userScanner.nextLine();
    greetUser(userName);


  }

  /** Print a welcome message with the given user's name */
  private void greetUser(String givenName) {
    System.out.println("Hey "+givenName+" thanks for being here! ");
  }

  private void listItemsByWarehouse() {
    //
    }
    // TODO here you use loops to show each item in each warehouse
    //print "warehouse1:"
    //listItems(Repository.WAREHOUSE1)
    //print "warehouse2"
    //listItems(Repository.WAREHOUSE2)
    System.out.println("Here is what we have in Warehouse 1: ");

  }

  private void listItems(String[] warehouse) {
    // TODO
    ////loop over warehouse: inside the loop print each item
  }

  private void searchItemAndPlaceOrder() {
    // TODO
    //item = askItemToOrder();
    //getAvaialbleAmount(item);
    //if AvaialbleAmount > 0 then askAmountAndConfirmOrder(item, AvaialbleAmount)
  }

  /**
   * Ask the user to specify an Item to Order
   *
   * @return String itemName
   */
  private String askItemToOrder() {
    // TODO
    // print What is the name of the item you want
    //scan the input to see get the tem for the user input .nextLine()
    //return the input
  }

  /**
   * Calculate total availability of the given item
   *
   * @param itemName itemName
   * @return integer availableCount
   */
 private int getAvailableAmount(String itemName) {
    // TODO
   //find availability in each warehouse
   // find(item , WAREHOUSE1)
   // find(item, WAREHOUSE2)
   //calculate the total available amount
   //check which warehouse hase more amount , w1 > w2, w1 < w2, w1 = w2
   // display availability in each warehouse, with a string telling maximum or minimum also equality
   //return the total amount
  }

  /**
   * Find the count of an item in a given warehouse
   *
   * @param item the item
   * @param warehouse the warehouse
   * @return count
   */
 private int find(String item, String[] warehouse) {
    // TODO
  }

  /** Ask order amount and confirm order */
  private void askAmountAndConfirmOrder(int availableAmount, String item) {
    // TODO
    //ask the user how many they want
    // if they want more than availableAmount then tell itz is not possible and ask them to choose wehter to contionue with the available amount or cancle
    //else show the message to confirm the order
  }

  /**
   * Get amount of order
   *
   * @param availableAmount
   * @return
   */
  private int getOrderAmount(int availableAmount) {
    // TODO
  }
}
