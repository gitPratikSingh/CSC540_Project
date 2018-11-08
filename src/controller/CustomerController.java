package controller;

import java.sql.Connection;

import model.HasParts;
import model.Parts;
import model.ServiceCenter;

public class CustomerController {

	
	public static void main(String[] args) {
		int service_center_id = ServiceCenter.create("1223223", "name1", "address1");
		int part_id = Parts.create("part_name1", "1.23");
		System.out.println("service_center_id : "+service_center_id);
		System.out.println("part_id : "+part_id);

		HasParts.create(service_center_id, part_id, 1, 1, 1);

	}

}
