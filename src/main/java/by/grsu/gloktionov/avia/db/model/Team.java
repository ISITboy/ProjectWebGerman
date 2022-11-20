package by.grsu.gloktionov.avia.db.model;

public class Team {
	
	
	private int id;
	private int pilot_id;
	private int navigator_id;
	
	
	
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", pilot_id=" + pilot_id + ", navigator_id=" + navigator_id + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPilot_id() {
		return pilot_id;
	}
	public void setPilot_id(int pilot_id) {
		this.pilot_id = pilot_id;
	}
	public int getNavigator_id() {
		return navigator_id;
	}
	public void setNavigator_id(int navigator_id) {
		this.navigator_id = navigator_id;
	}
	
	

}
