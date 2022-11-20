package by.grsu.gloktionov.avia.db.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Flight {
	
	
	private int id;
	private String name;
	private Date dateRoud;
	private int plane_id;
	private int team_id;
	private int price;
	
	
	
	
	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public Date getDateFromString(String dateStr) {
		try {
			return new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dateStr).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
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
	public Date getDateRoud() {
		return dateRoud;
	}
	public void setDateRoud(Date date) {
		this.dateRoud = date;
	}



	public int getPlane_id() {
		return plane_id;
	}



	public void setPlane_id(int plane_id) {
		this.plane_id = plane_id;
	}



	public int getTeam_id() {
		return team_id;
	}



	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}



	@Override
	public String toString() {
		return "Flight [id=" + id + ", name=" + name + ", dateRoud=" + dateRoud + ", plane_id=" + plane_id
				+ ", team_id=" + team_id + ", price=" + price + "]";
	}



	

	
	
	
	
	
	
	
	

}
