package by.grsu.gloktionov.avia.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.gloktionov.avia.db.dao.IDao;
import by.grsu.gloktionov.avia.db.model.Employee;


public class EmployeeDaoTest extends AbstractTest {
	private static final IDao<Integer, Employee> dao = EmployeeDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Employee entity = new Employee();
		entity.setName("German");
		entity.setSurname("Loktionov");;
		entity.setAge(20);
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Employee entity = new Employee();
		entity.setName("German");
		entity.setSurname("Loktionov");;
		entity.setAge(20);
		dao.insert(entity);

		String newName = "Vasya";
		entity.setName(newName);
		entity.setSurname("Vasin");;
		dao.update(entity);

		Employee updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newName, updatedEntity.getName());
		Assertions.assertNotEquals(updatedEntity.getName(), updatedEntity.getSurname());
	}

	@Test
	public void testDelete() {
		Employee entity = new Employee();
		entity.setName("German");
		entity.setSurname("Loktionov");;
		entity.setAge(20);
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Employee entity = new Employee();
		entity.setName("German");
		entity.setSurname("Loktionov");;
		entity.setAge(20);
		dao.insert(entity);

		Employee selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getSurname(), selectedEntity.getSurname());
		Assertions.assertEquals(entity.getAge(), selectedEntity.getAge());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Employee entity = new Employee();
			entity.setName("German");
			entity.setSurname("Loktionov");;
			entity.setAge(20);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
