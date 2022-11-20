package by.grsu.gloktionov.avia.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.gloktionov.avia.db.dao.AbstractDao;
import by.grsu.gloktionov.avia.db.dao.IDao;
import by.grsu.gloktionov.avia.db.model.Plane;

public class PlaneDaoImpl extends AbstractDao implements IDao<Integer, Plane> {

	// single instance of this class to be used by the all consumers
	public static final PlaneDaoImpl INSTANCE = new PlaneDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private PlaneDaoImpl() {
		super();
	}

	@Override
	public void insert(Plane entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into plane(name, weight, speed) values(?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getWeight());
			pstmt.setString(3, entity.getSpeed());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "plane"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Plane entity", e);
		}
	}

	@Override
	public void update(Plane entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update plane set weight=?, speed=? where id=?");
			pstmt.setInt(1, entity.getWeight());
			pstmt.setString(2, entity.getSpeed());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Plane entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from plane where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Plane entity", e);
		}

	}

	@Override
	public Plane getById(Integer id) {
		Plane entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from plane where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Brand entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Plane> getAll() {
		List<Plane> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from plane");
			while (rs.next()) {
				Plane entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Brand entities", e);
		}

		return entitiesList;
	}

	private Plane rowToEntity(ResultSet rs) throws SQLException {
		Plane entity = new Plane();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setWeight(rs.getInt("weight"));
		entity.setSpeed(rs.getString("speed"));
		return entity;
	}

}
