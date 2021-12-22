package main.java;

import java.util.*;

/**
 *
 * @author riteshp
 */
public class TheWarehouseApp {
  /**
   * Execute the <i>TheWarehouseApp</i>
   *
   * @param args
   */
  public static void main(String[] args) {
    List<String> SESSION_ACTIONS = new ArrayList<>();
    TheWarehouseManager theManager = new TheWarehouseManager();

    // Welcome User
    theManager.welcomeUser();

    // Get the user's choice of action and perform action
    do {
      int choice = theManager.getUsersChoice();
      theManager.performAction(choice);

      // confirm to do more
      if (!theManager.confirm("Do you want to perform another action?")) {
        theManager.quit();
      }

    } while (true);
  }
}
