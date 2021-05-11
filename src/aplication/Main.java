package aplication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Main {

	public static void main(String[] args) {
		
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		//Department department = new Department(2, null);
		/*List<Seller> list = sellerDao.findByDepartment(department);
		Set<Seller> list0 = new HashSet<>();

		for (Seller obj: list) {
			list0.add(obj);
		}
		for (Seller obj: list0) {
			System.out.println(obj);
		}*/
		
		List<Seller> seller = sellerDao.findAll();
		Set<Seller> list = new HashSet<>();
		
		for (Seller obj: seller) {
			list.add(obj);
		}
		for (Seller obj: list) {
			System.out.println(obj);
		}
	
	}

}
