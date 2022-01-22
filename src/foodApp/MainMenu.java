package foodApp;

import java.util.Scanner;

public class MainMenu {

	private Scanner scanner = new Scanner(System.in);
	private FoodDao foodDao = new FoodDao();
	public static void main(String[] args) {
		new MainMenu().runMenu();
		

	}

	private void runMenu() {
		boolean isFinished = false;
		
		while (!isFinished) {
			try {
				int selection = getSelection();
				//this is how to stop the application from running
				if (selection == 0) {
					System.out.println("Exiting Application");
					isFinished = true;
				}
			
				//rest of the cases for the application menu
				else {
					switch (selection) {
					case 1:
						addFood();
						break;
					case 2:
						viewFood();
						break;
					case 3:
						updateFood();
						break;
					case 4:
						deleteFood();
						break;
					default:
						System.out.println("Please make a valid choice.");	
						//if any other value picked, asked for valid choice
						break;
					}
				
				}
				
			}
			catch(Exception error) {
				System.out.println("Error: " + error + " Make a valid choice."); 
			}
		}
		
	}

	private void deleteFood() {
		viewFood();//display foods to know what you can delete
		
		System.out.println("Enter the ID of the food you want to delete");
		Integer foodID = scanner.nextInt();//get int to know what to delete
		
		FoodDao.deleteFood(foodID);
		System.out.println("Food has been deleted");
		
	}

	private void updateFood() {
		viewFood();//display food to know what to update
		
		System.out.println("Enter the food ID of the item you want to update");
		Integer foodID = scanner.nextInt();
		//get id to know what to update
		System.out.println("Enter new food name");
		String foodName = scanner.next();
		//get new name for food
		
		//add values to food object
		Food food = new Food();
		food.setFoodID(foodID);
		food.setFoodName(foodName);
		
		FoodDao.updateFood(food);
		
		System.out.println("Name has been updated");
	}

	private void viewFood() {
		//display all foods, obviously
		System.out.println("All foods: ");
		foodDao.returnAllFood().forEach(food -> System.out.println("   " + food));
		
	}

	private void addFood() {
		System.out.println("Enter food name: ");
		String foodName = scanner.next();//get name of new food
		
		Food food = new Food();//food object with food name
		food.setFoodName(foodName);
		foodDao.addFood(food);
		System.out.println(food + " added");
		
	}

	private Integer getSelection() {
		//display menu options
		System.out.println("Please make a selection: ");
		System.out.println("1) Add new food\n2) View existing foods\n3) Update existing food\n4) Delete a food\n0) Exit menu");
		String selection = scanner.next();
		
		try {
			return Integer.valueOf(selection);
		}
		catch(NumberFormatException error) {//if valid choice isnt given
			throw new RuntimeException(selection + " is not a choice.");
		}
	}

}
