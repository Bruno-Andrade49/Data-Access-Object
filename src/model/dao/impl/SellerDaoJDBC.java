package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO seller " + 
					"(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
					"VALUES " +
					"(?, ?, ?, ?, ?)", 
					java.sql.Statement.RETURN_GENERATED_KEYS);
					
		
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBithDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setObject(5, obj.getDepartament().getId());
			
			int idAfetado = st.executeUpdate();
			
			if (idAfetado > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new db.DbException("Nenhuma inserção foi localizada");
			}
			
			
			
		} catch (SQLException e) {
		throw new db.DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
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
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {

				Department dep = instatiateDepartment(rs);

				Seller seller = instatiateSeller(rs, dep);

				return seller;

			}
		} catch (SQLException e) {
			throw new db.DbException(e.getMessage());
		} finally {
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
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name DepName " + "FROM seller INNER JOIN department "
					 + "ORDER BY Name");

			

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			Set<Seller> list0 = new HashSet<>();
			List<Seller> listaFinal = new ArrayList<Seller>();

 
			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instatiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller obj = instatiateSeller(rs, dep);
				list.add(obj);
				

			}
			
			for (Seller seller : list) {
				list0.add(seller);
			}
			for (Seller seller : list0) {
				listaFinal.add(seller);
			}
			
			return listaFinal;
		} catch (SQLException e) {
			throw new db.DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
	}
		
	@Override
	public List<Seller> findByDepartment(Department department) {
		
	
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name DepName " + "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = ? " + "ORDER BY Name");

			st.setInt(1, department.getId());

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			Set<Seller> list0 = new HashSet<>();
			List<Seller> listaFinal = new ArrayList<Seller>();

 
			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instatiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller obj = instatiateSeller(rs, dep);
				list.add(obj);
				

			}
			
			for (Seller seller : list) {
				list0.add(seller);
			}
			for (Seller seller : list0) {
				listaFinal.add(seller);
			}
			
			return listaFinal;
		} catch (SQLException e) {
			throw new db.DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
	}
}
