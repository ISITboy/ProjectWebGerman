package by.grsu.gloktionov.avia;

import by.grsu.gloktionov.avia.db.model.Flight;

public class Main {

	public static void main(String[] args) {
		
		Flight flight = new Flight();
		flight.setDateRoud(flight.getDateFromString("10/10/2002"));
		System.out.println(flight.toString());
		
		
	}

}
