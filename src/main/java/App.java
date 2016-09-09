import java.io.Console;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
  public static void main(String[] args) {
    Console console = System.console();
    Event event = new Event();
    boolean running = true;
    while (running) {
      setScreen(event.getNumberOfAttendees(), event.getTotalEventCost());
      event.setNumberOfAttendees(getAttendees());

      setScreen(event.getNumberOfAttendees(), event.getTotalEventCost());
      event.setMealId(getMealChoice());

      ConsoleUtils.clearConsole();
      setScreen(event.getNumberOfAttendees(), event.getTotalEventCost());
      System.out.println(String.format("The total cost for your event is: $%.2f", event.getTotalEventCost()));
      System.out.println("Would you like to plan another event?");
      System.out.print("[Y/n] ");
      String choice = console.readLine();
      if (choice.equals("N") || choice.equals("n")) {
        running = false;
      } else {
        event = new Event();
      }
    }
  }

  private static Integer getAttendees() {
    Console console = System.console();
    System.out.println("We'll need to collect a bit of information about your upcoming event.");

    System.out.println("How many people will be attending your upcoming event?");
    System.out.print("People: ");
    Integer people = Integer.parseInt(console.readLine());
    System.out.println("Thanks!");
    System.out.println();
    ConsoleUtils.pauseScreen();
    return (people);
  }

  private static Integer getMealChoice() {
    Console console = System.console();
    System.out.println("What type of meal will we serve?");
    System.out.println("The options are:");
    System.out.println();
    System.out.println(ConsoleUtils.underline("ID Name             Cost/Person"));
    for (int i = 0; i < Meal.getAllMealNames().length; i++){
      String spacer = "";
      int spacesNeeded = 24 - (Meal.getAllMealNames()[i].length() + Meal.getAllMealCosts()[i].toString().length());
      for (int j = 0; j < spacesNeeded; j++) {
        spacer += " ";
      }
      System.out.println(String.format("%d: %s%s$%.2f", i, Meal.getAllMealNames()[i], spacer, Meal.getAllMealCosts()[i]));
    }
    System.out.println();
    System.out.print("ID: ");
    Integer meal = Integer.parseInt(console.readLine());
    System.out.println("Thanks!");
    System.out.println();
    ConsoleUtils.pauseScreen();
    return meal;
  }

  private static void setScreen(Integer people, Double cost) {
    ConsoleUtils.clearConsole();
    ConsoleUtils.setFooter(String.format("People: %d", people), String.format("Current Cost: $%.2f", cost));
    ConsoleUtils.setTitle("Welcome to Epicodus Event Planners!");
  }
}
