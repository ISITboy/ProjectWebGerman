package by.grsu.gloktionov.avia.db.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.gloktionov.avia.db.dao.AbstractDao;
import by.grsu.gloktionov.avia.db.dao.IDao;
import by.grsu.gloktionov.avia.db.model.Team;

public class TeamDaoImpl extends AbstractDao implements IDao<Integer, Team> {
	public static final TeamDaoImpl INSTANCE = new TeamDaoImpl();

	private TeamDaoImpl() {
		super();
	}

	@Override
	public void insert(Team entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into team(pilot_id, navigator_id) values(?,?)");
			// owner_id has type Integer, but if it can be null we have to use setObject()
			// instead of setInt()
			pstmt.setInt(1, entity.getPilot_id());
			pstmt.setInt(2, entity.getNavigator_id());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "team"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Team entity", e);
		}

	}

	@Override
	public void update(Team entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update team set pilot_id=?, navigator_id=? where id=?");
			pstmt.setObject(1, entity.getPilot_id());
			pstmt.setObject(2, entity.getNavigator_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Team entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from team where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Team entity", e);
		}
	}

	@Override
	public Team getById(Integer id) {
		Team entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from team where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Team entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Team> getAll() {
		List<Team> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from team");
			while (rs.next()) {
				Team entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Team entities", e);
		}

		return entitiesList;
	}

	private Team rowToEntity(ResultSet rs) throws SQLException {
		Team entity = new Team();
		entity.setId(rs.getInt("id"));
		// getObject() is unsupported by current JDBC driver. We will get "0" if field is NULL in DB
		entity.setPilot_id(rs.getInt("pilot_id"));
		entity.setNavigator_id(rs.getInt("navigator_id"));
		return entity;
	}
}
