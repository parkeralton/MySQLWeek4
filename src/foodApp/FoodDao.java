package foodApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class FoodDao {
	private static final String FOOD_TABLE = "food";
	
	public void addFood(Food food) {
		String sql = ""
				+ "INSERT INTO " + FOOD_TABLE + " (food_name) VALUES (?)";
		//takes in string and adds it to table, ID is autoincremented
		try(Connection conn = DbConnection.getConnection()){
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1, food.getFoodName());
				
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public List<Food> returnAllFood() {
		String sql = "SELECT * FROM " + FOOD_TABLE + " ORDER BY food_name";
		
		try(Connection conn = DbConnection.getConnection()){
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				try(ResultSet rs = stmt.executeQuery()){
					List<Food> foods = new LinkedList<>();
					//loops through all items and returns name and ID
					while (rs.next()){
						Food food = new Food();
						food.setFoodID(rs.getObject("food_id", Integer.class));
						food.setFoodName(rs.getString("food_name"));
						
						foods.add(food);
					}
					return foods;
				}
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void updateFood(Food food) {
		String sql = "UPDATE " + FOOD_TABLE + " SET food_name = ? WHERE food_id = ?";
		//update food name using provided food object. takes the ID and 
		//sets foodname to input provided
		try(Connection conn = DbConnection.getConnection()){
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1, food.getFoodName());
				stmt.setInt(2, food.getFoodID());
				
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void deleteFood(Integer foodID) {
		String sql = "DELETE FROM " + FOOD_TABLE + " WHERE food_id = ?";
		//deletes item based on food_id integer inputed. 
		try(Connection conn = DbConnection.getConnection()){
			try (PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setInt(1, foodID);
				
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
