package by.grsu.gloktionov.avia.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.gloktionov.avia.db.dao.AbstractDao;
import by.grsu.gloktionov.avia.db.dao.IDao;
import by.grsu.gloktionov.avia.db.model.Flight;

public class FlightDaoImpl extends AbstractDao implements IDao<Integer, Flight> {
	public static final FlightDaoImpl INSTANCE = new FlightDaoImpl();

	private FlightDaoImpl() {
		super();
	}

	@Override
	public void insert(Flight entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into flight(name, dateRoud, price, plane_id, team_id) values(?,?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setDate(2, entity.getDateRoud());
			// owner_id has type Integer, but if it can be null we have to use setObject()
			// instead of setInt()
			pstmt.setInt(3, entity.getPrice());
			pstmt.setInt(4, entity.getPlane_id());
			pstmt.setInt(5, entity.getTeam_id());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "flight"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Flight entity", e);
		}

	}

	@Override
	public void update(Flight entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update flight set plane_id=?, name=? where id=?");
			pstmt.setInt(1, entity.getPlane_id());
			pstmt.setString(2, entity.getName());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Flight entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from flight where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Flight entity", e);
		}
	}

	@Override
	public Flight getById(Integer id) {
		Flight entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from flight where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Flight entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Flight> getAll() {
		List<Flight> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from flight");
			while (rs.next()) {
				Flight entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Flight entities", e);
		}

		return entitiesList;
	}

	private Flight rowToEntity(ResultSet rs) throws SQLException {
		Flight entity = new Flight();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("Minsk-Moscow"));
		entity.setDateRoud(rs.getDate("10/10/2022"));
		// getObject() is unsupported by current JDBC driver. We will get "0" if field is NULL in DB
		entity.setPrice(rs.getInt(1000));
		entity.setPlane_id(rs.getInt("plane_id"));
		entity.setTeam_id(rs.getInt("team_id"));
		return entity;
	}
}