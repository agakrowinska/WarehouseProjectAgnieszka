package main.java;

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
  public static List<String> SESSION_ACTIONS;

  // =====================================================================================
  // Public Member Methods
  // =====================================================================================

  /**
   * Welcome User
   */
  public void welcomeUser() {
    this.seekUserName();
    this.greetUser();
  }

  /**
   * Ask for user's choice of action
   */
  public int getUsersChoice() {
    int choice;
    System.out.println("What would you like to do now?");
    for (String option : this.userOptions) {
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


  /**
   * Initiate an action based on given option
   */
  public void performAction(int option) {
    switch (option) {
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

  /**
   * End the application
   */
  public void quit() {
    System.out.printf("\nThanks for visiting %s!\n", this.userName);
    this.listSessionActions();
    System.exit(0);
  }

  public int getTotalListedItems() {
    //StockRepository.getAllItems();
    int count = StockRepository.getAllItems().size();
    return count;
  }

  public String getAppropriateIndefiniteArticle(String itemName) {
    String article;
    if (itemName.charAt(0) == 'a' || itemName.charAt(0) == 'e' || itemName.charAt(0) == 'i' || itemName.charAt(0) == 'o' || itemName.charAt(0) == 'u') {
      article = "an";
    } else {
      article = "a";
    }
    return article;

  }


  // =====================================================================================
  // Private Methods
  // =====================================================================================

  /**
   * Get user's name via CLI
   */
  private void seekUserName() {
    System.out.print("Hey!Great to see you, please tell my what is your name.");
    this.userName = this.reader.nextLine();

  }

  /**
   * Print a welcome message with the given user's name
   */
  private void greetUser() {
    System.out.printf("\nWelcome, %s !\n", this.userName);
  }

  private void listItemsByWarehouse() {

    int total = this.getTotalListedItems();
    String listedItems = "Listed " + total + " items";
    SESSION_ACTIONS.add(listedItems);
    Set<Integer> warehouses=StockRepository.getWarehouses();
    //for(int warehouse:warehouses){
    //System.out.printf("Items in warehouse %d:%n",warehouse);
    //this.listItems(StockRepository.getItemsByWarehouse(warehouse));
  }

  //System.out.println();
  //for(int warehouse:warehouses){
  //System.out.printf(
  //"Total items in warehouse %d: %d%n", warehouse, StockRepository.getItemsByWarehouse(warehouse).size());
  //}
  //}

  private void listItems(List<Item> items) {
    for (Item item : items) {
      System.out.printf("- %s%n", item.toString());
    }
  }

  private void searchItemAndPlaceOrder() {
    String itemName = askItemToOrder();
    String answer = this.getAppropriateIndefiniteArticle(itemName);
    String appropriateArticle = "Searched " + answer + itemName;
    SESSION_ACTIONS.add(appropriateArticle);

    List<Item> availableItems = this.listAvailableItems(itemName);
    if (availableItems.size() > 0) {
      this.askAmountAndConfirmOrder(availableItems.size(), itemName);
    }
  }

  private void browseByCategory() {
    Map<String, List<Item>> categoryWiseItems = new HashMap<>();
    List<String> categories = new ArrayList<>(StockRepository.getCategories());
    for (int i = 0; i < categories.size(); i++) {
      String category = categories.get(i);
      String browseAction = "Browsed the category " + category;
      SESSION_ACTIONS.add(browseAction);
      List<Item> catItems = StockRepository.getItemsByCategory(category);
      categoryWiseItems.put(category, catItems);
      System.out.printf("%d. %s (%d)%n", (i + 1), category, catItems.size());
    }

    int catIndex = 0;
    do {
      System.out.println("Type the number of the category to browse:");
      try {
        catIndex = Integer.parseInt(reader.nextLine());
      } catch (Exception e) {
        System.out.printf("Enter an integer between 1 and %d%n", categories.size());
      }

    } while (catIndex <= 0 || catIndex > categories.size());

    String category = categories.get(catIndex - 1);
    System.out.printf("List of %ss available:%n", category.toLowerCase());
    List<Item> catItems = categoryWiseItems.get(category);
    for (Item item : catItems) {
      System.out.printf("%s, Warehouse %d%n", item.toString(), item.getWarehouse());
    }
  }

  /**
   * Ask the user to specify an Item to Order
   *
   * @return String itemName
   */
  private String askItemToOrder() {
    System.out.println("Which item you are interested in?");
    return this.reader.nextLine();

  }

  /**
   * List availability of the given item
   *
   * @param itemName the item name
   * @return List<Item> availableItems
   */
  private List<Item> listAvailableItems(String itemName) {
    // find availability in Repository
    List<Item> availableItems = this.find(itemName);
    int availableCount = availableItems.size();
    System.out.printf("Amount available: %d%n", availableCount);

    if (availableCount > 0) {
      System.out.println("Location:");
      for (Item item : availableItems) {
        long noOfDaysInStock =
                TimeUnit.DAYS.convert(
                        (new Date().getTime() - item.getDateOfStock().getTime()), TimeUnit.MILLISECONDS);
        System.out.printf(
                "- Warehouse %d (in stock for %d days)%n", item.getWarehouse(), noOfDaysInStock);
      }

      // get warehouse wise availability
      int maxWarehouse = 0;
      int maxAvailability = 0;
      Set<Integer> warehouses = StockRepository.getWarehouses();
      for (int wh : warehouses) {
        int whCount = StockRepository.getItemsByWarehouse(wh, availableItems).size();
        if (whCount > maxAvailability) {
          maxWarehouse = wh;
          maxAvailability = whCount;
        }
      }

      // find the warehouse with max
      System.out.printf(
              "Maximum availability: %d in warehouse %d%n", maxAvailability, maxWarehouse);
    } else {
      System.out.println("Location: Not in stock");
    }

    // return available items
    return availableItems;
  }

  /**
   * Find matching items in the repository
   *
   * @param item the item
   * @return items
   */
  private List<Item> find(String item) {
    List<Item> items = new ArrayList<>();
    for (Item wItem : StockRepository.getAllItems()) {
      if (wItem.toString().equalsIgnoreCase(item)) {
        items.add(wItem);
      }
    }
    return items;
  }

  /**
   * Ask order amount and confirm order
   */
  private void askAmountAndConfirmOrder(int availableAmount, String item) {
    // Check if user want's to order
    boolean toOrder = this.confirm("Would you like to order this item?");
    if (toOrder) {
      // get the amount to order
      int orderAmount = this.getOrderAmount(availableAmount);
      // Confirm order if amount is positive
      if (orderAmount > 0) {
        System.out.printf(
                "%d %s %s been ordered", orderAmount, item, (orderAmount == 1 ? "has" : "have"));
      }
    }
  }

  /**
   * private method named listSessionActions which returns nothing and prints the Session summary at the end of the session.
   */
  private void listSessionActions() {
    if (SESSION_ACTIONS == null) {
      System.out.println("In this session you have not done anything.");
    } else {
      System.out.println(SESSION_ACTIONS);
    }
  }

  /**
   * Get amount of order
   *
   * @param availableAmount the available amount
   * @return the order amount
   */
  private int getOrderAmount(int availableAmount) {
    int orderAmount;
    System.out.println("How many would you like?");
    do {
      String orderAmtValue = this.reader.nextLine();
      try {
        orderAmount = Integer.parseInt(orderAmtValue);
        if (orderAmount > availableAmount) {
          System.out.println("**************************************************");
          System.out.println(
                  "There are not this many available. The maximum amount that can be ordered is "
                          + availableAmount);
          System.out.println("**************************************************");

          boolean orderAll = this.confirm("Would you like to order the maximum available?");
          orderAmount = orderAll ? availableAmount : 0;
        } else if (orderAmount < 0) {
          System.out.println("Please enter a value more than or equal to 0.");
        }
      } catch (Exception e) {
        orderAmount = -1;
      }
    } while (orderAmount < 0 || orderAmount > availableAmount);

    return orderAmount;
  }
}
