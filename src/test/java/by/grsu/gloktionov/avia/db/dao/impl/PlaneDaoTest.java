package by.grsu.gloktionov.avia.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.gloktionov.avia.db.dao.IDao;
import by.grsu.gloktionov.avia.db.model.Plane;

public class PlaneDaoTest extends AbstractTest {
	private static final IDao<Integer, Plane> dao = PlaneDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Plane entity = new Plane();
		entity.setName("Самолет_№1");
		entity.setWeight(1000);
		entity.setSpeed("200_км/ч");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Plane entity = new Plane();
		entity.setName("Самолет_№1");
		entity.setWeight(1000);
		entity.setSpeed("200_км/ч");
		dao.insert(entity);

		int newWeight = 2000;
		entity.setWeight(newWeight);
		entity.setSpeed("200_км/ч");
		dao.update(entity);

		Plane updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newWeight, updatedEntity.getWeight());
		Assertions.assertNotEquals(updatedEntity.getName(), updatedEntity.getSpeed());
	}

	@Test
	public void testDelete() {
		Plane entity = new Plane();
		entity.setName("Самолет_№1");
		entity.setWeight(1000);
		entity.setSpeed("200_км/ч");
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Plane entity = new Plane();
		entity.setName("Самолет_№1");
		entity.setWeight(1000);
		entity.setSpeed("200_км/ч");
		dao.insert(entity);

		Plane selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getWeight(), selectedEntity.getWeight());
		Assertions.assertEquals(entity.getSpeed(), selectedEntity.getSpeed());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Plane entity = new Plane();
			entity.setName("Самолет_№1"); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setWeight(1000);
			entity.setSpeed("200_км/ч");
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
