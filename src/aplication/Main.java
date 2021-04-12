package aplication;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Testando"); 
		
		Department departament = new Department(1, "Games");
		
		Seller seller = new Seller(21,"Bruno", "b.emanueandrade@hotmail.com", new Date(), 1000.00, departament);
		
		System.out.println(seller);
	
	}

}
