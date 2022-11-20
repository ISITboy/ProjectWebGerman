package by.grsu.gloktionov.avia.db.model;

public class Plane {
	
	
	private int id;
	private String name;
	private int weight;
	private String speed;
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Plane [id=" + id + ", name=" + name + ", weight=" + weight + ", speed=" + speed + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	
	
	

}
