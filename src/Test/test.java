package Test;

import Exceptions.NoParentException;
import Exceptions.RepeatException;
import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;
import Model.ConnectionManagerImpl;
import Model.Profile;
import Model.ProfileManagerImpl;

public class test {

	public static void main(String[] args) {

		ProfileManager profiles = new ProfileManagerImpl();  
	
		
		Profile p1 = new Profile("A", 19);
		p1.setID(1);
		
		Profile p2 = new Profile("AB", 17);
		p2.setID(2);

		Profile p3 = new Profile("B", 15);
		p3.setID(3);

		Profile p4 = new Profile("C", 13);
		p4.setID(4);
		
		Profile p5 = new Profile("D",3);
		p5.setID(5);
		
		Profile p6 = new Profile("E", 1);
		p6.setID(6);
		
		Profile p7 = new Profile("F", 20);
		p7.setID(7);
		
		Profile p8 = new Profile("G", 30);
		p8.setID(8);
		
		Profile p9 = new Profile("H", 40);
		p9.setID(9);
		
		profiles.addProfile(p1);
		profiles.addProfile(p2);
		profiles.addProfile(p3);
		profiles.addProfile(p4);
		profiles.addProfile(p5);
		profiles.addProfile(p6);
		profiles.addProfile(p7);
		profiles.addProfile(p8);
		profiles.addProfile(p9);

		
		ConnectionManager conns = new ConnectionManagerImpl(); 
		conns.setPmanager(profiles);
		
//		try {
//			conns.addFriendConnection(1,2);
//			System.out.println("1, 2, success!");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
//		try {
//			conns.addConnection(2,1,1);
//			System.out.println("1, 2, success!! friend");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		try {
//			conns.addConnection(1,2,1);
//			System.out.println("1, 3, success!!");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		try {
//			conns.addConnection(4,3,1);
//			System.out.println("4, 3, success!! friend");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
//		try {
//			conns.addFriendConnection(6,5);
//			System.out.println("6, 5, success!! friend");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		
		//test couple connection
		try {
			conns.addConnection(1,9,2);
			System.out.println("1,9 success!! couple");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//		
		try {
			conns.addConnection(7,8, 2);
			System.out.println("7,8 success!! couple");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			conns.addConnection(7,9, 2);
			System.out.println("7,8 success!! couple");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//		
//		
//		try {
//			conns.addParentConnection(1,9,4 );
//			System.out.println("1, 9, 4 success!! parent");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
////		try {
////			conns.addParentConnection(7, 9, 3);
////			System.out.println("7,8, 3 success!! parent");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
//		
//		try {
//			conns.addParentConnection(7, 8, 3);
//		    System.out.println("7,8, 3 success!! parent");
//		} catch (Exception e) {
//		    System.out.println(e.getMessage());
//		}
//		
//		//delete
//		
//		
////		try {
////			conns.removeConnections(3);
////			System.out.println("delete 1 success!");
////		} catch (NoParentException e) {
////			System.out.println("test");
////		    System.out.println(e.getMessage());
////		}
//		
		
		//test add colleague connection
		try {
			conns.addConnection(1,3,3);
			System.out.println("1, 2, success!! colleague");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//test add classmates connection
		try {
			conns.addConnection(1,6,4);
			System.out.println("1, 6, success!! classmates");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//		
//		
//		
//		
//		
}
//
}
