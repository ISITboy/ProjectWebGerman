package by.grsu.gloktionov.avia.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.gloktionov.avia.db.dao.IDao;
import by.grsu.gloktionov.avia.db.model.Employee;
import by.grsu.gloktionov.avia.db.model.Flight;
import by.grsu.gloktionov.avia.db.model.Plane;
import by.grsu.gloktionov.avia.db.model.Team;

public class FlightDaoTest extends AbstractTest {
	private static final IDao<Integer, Plane> planeDao = PlaneDaoImpl.INSTANCE;
	private static final IDao<Integer, Team> teamDao = TeamDaoImpl.INSTANCE;
	private static final IDao<Integer, Flight> flightDao = FlightDaoImpl.INSTANCE;
	private static final IDao<Integer, Employee> employeeDao = EmployeeDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Flight entity = new Flight();
		entity.setName("Minsk-moscow");
		entity.setDateRoud(entity.getDateFromString("31/12/1988"));
		entity.setPrice(1000);
		entity.setPlane_id(savePlane().getId());
		entity.setTeam_id(saveTeam().getId());
		flightDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testInsertWithoutOwner() {
		Flight entity = new Flight();
		entity.setName("Minsk-moscow");
		entity.setDateRoud(entity.getDateFromString("31/12/1988"));
		entity.setPrice(1000);
		entity.setPlane_id(savePlane().getId());		
		entity.setTeam_id(saveTeam().getId());
		flightDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Flight entity = new Flight();
		entity.setPlane_id(savePlane().getId());
		entity.setName("Minsk-moscow");
		flightDao.insert(entity);

		String newName = "Minsk-Berlin";
		entity.setPlane_id(2);
		entity.setName(newName);
		flightDao.update(entity);

		Flight updatedEntity = flightDao.getById(entity.getId());
		Assertions.assertEquals( newName, updatedEntity.getName());
		Assertions.assertNotEquals(updatedEntity.getName(), updatedEntity.getPlane_id());
	}

	@Test
	public void testDelete() {
		Flight entity = new Flight();
		entity.setName("Minsk-moscow");
		entity.setDateRoud(entity.getDateFromString("31/12/1988"));
		entity.setPrice(1000);
		entity.setPlane_id(savePlane().getId());		
		entity.setTeam_id(saveTeam().getId());
		flightDao.insert(entity);

		flightDao.delete(entity.getId());

		Assertions.assertNull(flightDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Flight entity = new Flight();
		entity.setName("Minsk-moscow");
		entity.setDateRoud(entity.getDateFromString("31/12/1988"));
		entity.setPrice(1000);
		entity.setPlane_id(savePlane().getId());		
		entity.setTeam_id(saveTeam().getId());
		flightDao.insert(entity);
		Flight selectedEntity = flightDao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getTeam_id(), selectedEntity.getTeam_id());
		Assertions.assertEquals(entity.getPlane_id(), selectedEntity.getPlane_id());
		Assertions.assertEquals(entity.getDateRoud(), selectedEntity.getDateRoud());
		Assertions.assertEquals(entity.getPrice(), selectedEntity.getPrice());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Flight entity = new Flight();
			entity.setName("Minsk-moscow");
			entity.setDateRoud(entity.getDateFromString("31/12/1988"));
			entity.setPrice(1000);
			entity.setPlane_id(savePlane().getId());		
			entity.setTeam_id(saveTeam().getId());
			flightDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, flightDao.getAll().size());
	}

	private Plane savePlane() {
		Plane entity = new Plane();
		entity.setName("Plane_1");
		entity.setSpeed("250 km/h");
		entity.setWeight(1000);
		planeDao.insert(entity);
		return entity;
	}

	private Team saveTeam() {
		
		Employee employeeEntity1 = new Employee();
		employeeEntity1.setName("German");
		employeeEntity1.setSurname("Loct");
		employeeEntity1.setAge(18);
		Employee employeeEntity2 = new Employee();
		employeeEntity1.setName("German");
		employeeEntity1.setSurname("Loct");
		employeeEntity1.setAge(18);
		
		employeeDao.insert(employeeEntity1); 
		employeeDao.insert(employeeEntity2); 
		
		Team teamEntity = new Team();
		teamEntity.setId(employeeEntity1.getId());
		teamEntity.setId(employeeEntity2.getId());
		teamDao.insert(teamEntity);
		
		return teamEntity;
	}
}