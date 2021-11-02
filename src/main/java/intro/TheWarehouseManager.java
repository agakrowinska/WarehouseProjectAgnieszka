package main.java.intro;

import static com.dci.java.intro.data.Repository.WAREHOUSE1;
import static com.dci.java.intro.data.Repository.WAREHOUSE2;

import java.util.Scanner;

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
  private final String[] userOptions = {
    "1. List items by warehouse", "2. Search an item and place an order", "3. Quit"
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
    System.out.println("Hey "+userName+" thanks for being here! ");

  }

  /** Ask for user's choice of action */
  public int getUsersChoice() {
    System.out.println("You probably came here with a purpose. Please tell me what would you like to do?");
    for (String i: userOptions);
    System.out.println(String[i]);
    System.out.println("Please type the number below");
    Scanner inputChoice = new Scanner(System.in);
    String usersChoice = inputChoice.nextLine();

  }

  /** Initiate an action based on given option */
  public void performAction(int option) {
    switch(option);
    case 1:
    System.out.println("Check out what is available in both of our warehouses!");
    //
  }

  /**
   * Confirm an action
   *
   * @return action
   */
  public boolean confirm(String message) {
    // TODO
  }

  /** End the application */
  public void quit() {
    System.out.printf("\nThank you for your visit, %s!\n", this.userName);
    System.exit(0);
  }

  // =====================================================================================
  // Private Methods
  // =====================================================================================

  /** Get user's name via CLI */
  private void seekUserName(String) {
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
    // TODO
  }

  private void listItems(String[] warehouse) {
    // TODO
  }

  private void searchItemAndPlaceOrder() {
    // TODO
  }

  /**
   * Ask the user to specify an Item to Order
   *
   * @return String itemName
   */
  private String askItemToOrder() {
    // TODO
  }

  /**
   * Calculate total availability of the given item
   *
   * @param itemName itemName
   * @return integer availableCount
   */
  private int getAvailableAmount(String itemName) {
    // TODO
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
