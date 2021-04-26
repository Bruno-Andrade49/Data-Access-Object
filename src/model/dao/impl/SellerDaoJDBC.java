package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class SellerDaoJDBC implements SellerDao{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Connection conn;
	

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
				st = conn.prepareStatement(
						"SELECT seller.*,department.Name as DepName " 
						+"FROM seller INNER JOIN department "
						+"ON seller.DepartmentId = department.Id "
						+"WHERE seller.Id = ?");
				st.setInt(1, id);
				rs = st.executeQuery();
				if (rs.next()) {
					
					Department dep = instatiateDepartment(rs);
					
					Seller seller = instatiateSeller(rs, dep);
					
					return seller;
	
				}
			} catch (SQLException e) {
				throw new db.DbException(e.getMessage());
			}
			finally {
				DB.closeConnection();
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		return null;
		
		}
	
	
	

	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBithDate(rs.getDate("BirthDate"));
		seller.setEmail(rs.getString("Email"));
		seller.setName(rs.getString("Name"));
		seller.setId(rs.getInt("Id"));
		seller.setDepartament(dep);
		
		return seller;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("Name"));		
		return dep;

	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
