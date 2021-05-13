package aplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Main {

	public static void main(String[] args) {
		
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		//Department department = new Department(2, null);
		/*List<Seller> list = sellerDao.findByDepartment(department);
		Set<Seller> list0 = new HashSet<>();

		for (Seller obj: list) {
			list0.add(obj);
		}
		for (Seller obj: list0) {
			System.out.println(obj);
		}*/
		
		
		
		/*List<Seller> seller = sellerDao.findAll();
		Set<Seller> list = new HashSet<>();
		
		for (Seller obj: seller) {
			list.add(obj);
		}
		for (Seller obj: list) {
			System.out.println(obj);
		}
		
		Seller seller1 = new Seller(null, "Breno", "b.gabriel@hotmail.com", new Date(), 6000.00, new Department(4, null));
		sellerDao.insert(seller1);
		
		System.out.println("----------");
		System.out.println("----------");
		
		
		List<Seller> seller0 = sellerDao.findAll();
		Set<Seller> list0 = new HashSet<>();
		
		for (Seller obj: seller0) {
			list0.add(obj);
		}
		for (Seller obj: list0) {
			System.out.println(obj);
		}*/
		
		/*Department dep = new Department(2, null);
		List<Seller> tuplas = sellerDao.findByDepartment(dep);
		
		for (Seller seller : tuplas) {
			System.out.println(seller);
		}
		
		System.out.println("---------------");
		
		List<Seller> listaInteira = sellerDao.findAll();
		
		for (Seller seller : listaInteira) {
			System.out.println(seller);
		}
		
		
		sellerDao.deleteById(16);
		
		Seller seller01 = new Seller(14, "Gilmara", "gil.mara.lucia@hotmail.com", new Date(), 5000.00, null);
		*/
		
		/*Seller seller = new Seller();
		seller = sellerDao.findById(14);
		seller.setBaseSalary(5000.00);
		seller.setName("Gilmara");
		seller.setEmail("gil.mara.lucia@hotmail.com");
		sellerDao.update(seller);
		*/
		
		/*Department dep01 = new Department(5, "Escolar");
		departmentDao.insert(dep01);*/
		
		
		Department dep = departmentDao.findById(5);
		
		System.out.println(dep);
		
		System.out.println("-----------");
		
		List<Department> departamentos = new ArrayList<Department>();
		
		departamentos = departmentDao.findAll();
		
		for (Department department : departamentos) {
			System.out.println(department);
		}
		
		
		
		
		
	
	}

}
