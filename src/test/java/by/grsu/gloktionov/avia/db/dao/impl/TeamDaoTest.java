package by.grsu.gloktionov.avia.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.gloktionov.avia.db.dao.IDao;
import by.grsu.gloktionov.avia.db.model.Employee;
import by.grsu.gloktionov.avia.db.model.Team;

public class TeamDaoTest extends AbstractTest {
	private static final IDao<Integer, Team> dao = TeamDaoImpl.INSTANCE;
	private static final IDao<Integer, Employee> employeeDao = EmployeeDaoImpl.INSTANCE;


	@Test
	public void testInsert() {
		Team entity = new Team();
		entity.setPilot_id(saveEmployee().getId());
		entity.setNavigator_id(saveEmployee().getId());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Team entity = new Team();
		entity.setPilot_id(saveEmployee().getId());
		entity.setNavigator_id(saveEmployee().getId());
		dao.insert(entity);

		int newID = 1;
		entity.setPilot_id(newID);
		entity.setNavigator_id(4);
		dao.update(entity);

		Team updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newID, updatedEntity.getPilot_id());
		Assertions.assertNotEquals(updatedEntity.getNavigator_id(),updatedEntity.getPilot_id());
	}

	@Test
	public void testDelete() {
		Team entity = new Team();
		entity.setPilot_id(saveEmployee().getId());
		entity.setNavigator_id(saveEmployee().getId());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Team entity = new Team();
		entity.setPilot_id(saveEmployee().getId());
		entity.setNavigator_id(saveEmployee().getId());
		dao.insert(entity);

		Team selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getPilot_id(), selectedEntity.getPilot_id());
		Assertions.assertEquals(entity.getNavigator_id(), selectedEntity.getNavigator_id());
		}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Team entity = new Team();
			entity.setPilot_id(saveEmployee().getId());; // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setNavigator_id(saveEmployee().getId());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
	
	
	
	
	private Employee saveEmployee() {
		Employee entity = new Employee();
		entity.setName("Ivan");
		entity.setSurname("Ivanov");
		entity.setAge(30);
		employeeDao.insert(entity);
		return entity;
	}
	
	
	
}