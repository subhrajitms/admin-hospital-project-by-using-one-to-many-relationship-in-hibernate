package org.jsp.adminhospital.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsp.adminhospital.dao.AdminDao;
import org.jsp.adminhospital.dao.HospitalDao;
import org.jsp.adminhospital.dto.Admin;
import org.jsp.adminhospital.dto.Hospital;

public class AdminHospitalController {
	private static AdminDao adminDao = new AdminDao();
	private static HospitalDao hospitalDao = new HospitalDao();
	static Scanner ip = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println("1.Save Admin");
			System.out.println("2.Update Admin");
			System.out.println("3.Find Admin By Id");
			System.out.println("4.Verify Admin By phone number and password.");
			System.out.println("5.Verify Admin By email and password...");
			System.out.println("6.Save Hospital..");
			System.out.println("7.Update Hospital..");
			System.out.println("8.Find Hospital By Admin id..");
			System.out.println("9.Find Hospital By Admin phone number and password.");
			System.out.println("10.Find Hospital By Admin email and password...");
			System.out.println("11.Exit...");

			System.out.println("Enter Choice");
			int choice = ip.nextInt();
			switch (choice) {
			case 1: {
				saveAdmin();
				break;
			}
			case 2: {
				updateAdmin();
				break;
			}
			case 3: {
				findAdminById();
				break;
			}
			case 4: {
				verifyAdminByPhoneAndPassword();
				break;
			}
			case 5: {
				verifyAdminByEmailAndPassword();
				break;
			}
			case 6: {
				saveHospital();
				break;
			}
			case 7: {
				updateHospital();
				break;
			}
			case 8: {
				findHospitalByAdminid();
				break;
			}
			case 9: {
				findHospitalByPhoneAndPassword();
				break;
			}
			case 10: {
				findHospitalByEmailAndPassword();
				break;
			}
			case 11:
				 System.out.println("++++++++++++++++Thank You ++++++++++++++++++++++");
				 System.exit(0);
			}
		}
	}

	public static void saveAdmin() {
		System.out.println("Enter Admin name,phone,email,password to save record...");
		Admin a = new Admin();
		a.setName(ip.next());
		a.setEmail(ip.next());
		a.setPassword(ip.next());
		a.setPhone(ip.nextLong());
		a = adminDao.saveAdmin(a);
		System.out.println("Admin Record Saved Successfully With Id:" + a.getId());
	}

	public static void updateAdmin() {
		System.out.println("Enter Admin Id...");
		int id = ip.nextInt();
		System.out.println("Enter Admin name,phone,email.password to update record...");
		Admin a = new Admin();
		a.setId(id);
		a.setName(ip.next());
		a.setEmail(ip.next());
		a.setPassword(ip.next());
		a.setPhone(ip.nextLong());
		a = adminDao.updateAdmin(a);
		if (a != null) {
			System.out.println("Admin Record Updated Successfully..");
		} else {
			System.out.println("Admin Id is Invalid...");
		}
	}

	public static void findAdminById() {
		System.out.println("Enter Id of Admin..");
		int id = ip.nextInt();
		Admin a = adminDao.findAdminById(id);
		if (a != null) {
			System.out.println("Id:" + a.getId());
			System.out.println("Name is:" + a.getName());
			System.out.println("Phone number is:" + a.getPhone());
			System.out.println("Email id:" + a.getEmail());
			System.out.println("Password. is:" + a.getPassword());
		} else {
			System.out.println("Invalid Admin Id...");
		}
	}

	public static void verifyAdminByPhoneAndPassword() {
		System.out.println("Enter Admin phone number and password.");
		long phone = ip.nextLong();
		String password = ip.next();
		Admin a = adminDao.verifyAdmin(phone, password);
		if (a != null) {
			System.out.println("Id:" + a.getId());
			System.out.println("Name is:" + a.getName());
			System.out.println("Phone number is:" + a.getPhone());
			System.out.println("Email id:" + a.getEmail());
			System.out.println("Password. is:" + a.getPassword());
		} else {
			System.out.println("Invalid Admin phone number And password.");
		}
	}

	public static void verifyAdminByEmailAndPassword() {
		System.out.println("Enter Admin email and password.");
		String email = ip.next();
		String password = ip.next();
		Admin a = adminDao.verifyAdmin(email, password);
		if (a != null) {
			System.out.println("Id:" + a.getId());
			System.out.println("Name is:" + a.getName());
			System.out.println("Phone number is:" + a.getPhone());
			System.out.println("Email id:" + a.getEmail());
			System.out.println("Password. is:" + a.getPassword());
		} else {
			System.out.println("Invalid Admin email And password.");
		}
	}

	public static void saveHospital() {
		System.out.println("Enter the Admin Id to Save hospital...");
		int admin_id = ip.nextInt();
		System.out.println("Enter hospital name,founder,gst and year of estb......");
		Hospital h = new Hospital();
		h.setFounder(ip.next());
		h.setGst(ip.next());
		h.setName(ip.next());
		h.setYear_of_estb(ip.nextInt());
		h = hospitalDao.saveHospital(h, admin_id);
		if (h != null) {
			System.out.println("Hospital record saved with a id:" + h.getId());
		} else {
			System.out.println("Invalid Admin id ........");
		}

	}

	public static void updateHospital() {
		System.out.println("Enter hospital id,name,founder,yearofestb to Update Product....");
		Hospital h = new Hospital();
		h.setId(ip.nextInt());
		h.setName(ip.next());
		h.setFounder(ip.next());
		h.setGst(ip.next());
		h.setYear_of_estb(ip.nextInt());
		h = hospitalDao.updateHospital(h);
		if (h != null) {
			System.out.println("hospital record:" + h.getId() + " is updated");
		} else {
			System.out.println("hospital id is invalid...");
		}
	}

	public static void findHospitalByAdminid() {
		System.out.println("Enter admin id to Find Hospital Details...");
		int admin_id = ip.nextInt();
		List<Hospital> hospitals = hospitalDao.findHospitalByAdminId(admin_id);
		if (hospitals.size() > 0) {
			for (Hospital h : hospitals) {
				System.out.println("Hospital Id:" + h.getId());
				System.out.println("Hospital name:" + h.getName());
				System.out.println("Hospital Founder:" + h.getFounder());
				System.out.println("GST Number:" + h.getGst());
				System.out.println("Year of Establishment:" + h.getYear_of_estb());
				System.out.println("---------------------------------");
			}
		}

	}

	public static void findHospitalByPhoneAndPassword() {
		System.out.println("Enter Admin Phone number and Password...");
		long phone = ip.nextLong();
		String password = ip.next();
		List<Hospital> hospitals = hospitalDao.FindHospital(phone, password);
		if (hospitals.size() > 0) {
			for (Hospital h : hospitals) {
				System.out.println("Hospital Id:" + h.getId());
				System.out.println("Hospital name:" + h.getName());
				System.out.println("Hospital Founder:" + h.getFounder());
				System.out.println("GST Number:" + h.getGst());
				System.out.println("Year of Establishment:" + h.getYear_of_estb());
				System.out.println("---------------------------------");
			}
		}

	}

	public static void findHospitalByEmailAndPassword() {
		System.out.println("Enter Admin email address and password..");
		String email = ip.next();
		String password = ip.next();
		List<Hospital> hospitals = hospitalDao.FindHospital(email, password);
		if (hospitals.size() > 0) {
			for (Hospital h : hospitals) {
				System.out.println("Hospital Id:" + h.getId());
				System.out.println("Hospital name:" + h.getName());
				System.out.println("Hospital Founder:" + h.getFounder());
				System.out.println("GST Number:" + h.getGst());
				System.out.println("Year of Establishment:" + h.getYear_of_estb());
				System.out.println("---------------------------------");
			}
		}
	}
}
