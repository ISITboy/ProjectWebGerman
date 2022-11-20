package by.grsu.gloktionov.avia.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.gloktionov.avia.db.dao.AbstractDao;
import by.grsu.gloktionov.avia.db.dao.IDao;
import by.grsu.gloktionov.avia.db.model.Employee;

public class EmployeeDaoImpl extends AbstractDao implements IDao<Integer, Employee> {

	// single instance of this class to be used by the all consumers
	public static final EmployeeDaoImpl INSTANCE = new EmployeeDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private EmployeeDaoImpl() {
		super();
	}

	@Override
	public void insert(Employee entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into employee(name, surname, age) values(?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getSurname());
			pstmt.setInt(3, entity.getAge());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "employee"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Employee entity", e);
		}
	}

	@Override
	public void update(Employee entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update employee set name=?, surname=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getSurname());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Employee entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from employee where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Employee entity", e);
		}

	}

	@Override
	public Employee getById(Integer id) {
		Employee entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from employee where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Employee entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from employee");
			while (rs.next()) {
				Employee entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Employee entities", e);
		}

		return entitiesList;
	}

	private Employee rowToEntity(ResultSet rs) throws SQLException {
		Employee entity = new Employee();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setSurname(rs.getString("surname"));
		entity.setAge(rs.getInt("age"));
		return entity;
	}

}
