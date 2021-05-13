package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO department " + 
					"(Id, Name) " +
					"VALUES " +
					"(?, ?)");
			
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		

	}

	@Override
	public void update(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE department " +
						"SET Id = ?, Name = ? " +
						"WHERE Id = ?");
			
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
		

	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM department " +
				"WHERE Id = ?");
			
			st.setInt(1, id);
		}
		catch (SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Department findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department " + 
							 	"WHERE Id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
			
				Department dep = new Department();
			
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
			
			
			return dep;
	}
		}
		
		catch (SQLException e) {
			throw new db.DbException(e.getMessage()); 
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
		return null;
		
		
		
		
	}

	@Override
	public List<Department> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department");
			
			rs = st.executeQuery();
			
			List<Department> departamentos = new ArrayList<Department>();
			
			
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				departamentos.add(dep);
			}
			return departamentos;
			
			
			
		}
		catch(SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
