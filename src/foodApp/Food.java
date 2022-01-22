package foodApp;
//food object with getters and setters
public class Food {
	private Integer foodID;
	public Integer getFoodID() {
		return foodID;
	}
	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	private String foodName;
	@Override
	public String toString() {
		return "ID=" + foodID + ", foodName=" + foodName;
	}
	
	
	
}
