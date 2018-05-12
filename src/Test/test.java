package Test;

import java.util.ArrayList;

import Exceptions.NoParentException;
import Exceptions.NoSuchAgeException;
import Exceptions.RepeatException;
import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;
import Model.Connection;
import Model.ConnectionManagerImpl;
import Model.FileManager;
import Model.ProfileManagerImpl;


public class test {

	public static void main(String[] args) {

		ProfileManager profiles = new ProfileManagerImpl();  
	
//		
//		Profile p1 = new Profile("A", 19);
//		p1.setID(1);
//		
//		Profile p2 = new Profile("AB", 17);
//		p2.setID(2);
//
//		Profile p3 = new Profile("B", 15);
//		p3.setID(3);
//
//		Profile p4 = new Profile("C", 13);
//		p4.setID(4);
//		
//		Profile p5 = new Profile("D",3);
//		p5.setID(5);
//		
//		Profile p6 = new Profile("E", 1);
//		p6.setID(6);
//		
//		Profile p7 = new Profile("F", 20);
//		p7.setID(7);
//		
//		Profile p8 = new Profile("G", 30);
//		p8.setID(8);
//		
//		Profile p9 = new Profile("H", 40);
//		p9.setID(9);
//		
//		Profile p10 = new Profile("I", 240);
//		p10.setID(10);
//		
//		try {
//			profiles.addProfile(p1);
//			profiles.addProfile(p2);
//			profiles.addProfile(p3);
//			profiles.addProfile(p4);
//			profiles.addProfile(p5);
//			profiles.addProfile(p6);
//			profiles.addProfile(p7);
//			profiles.addProfile(p8);
//			profiles.addProfile(p9);
//			profiles.addProfile(p10);
//		} catch (NoSuchAgeException e1) {
//			System.out.println(e1.getMessage());
//			}
//	
//
//		
		ConnectionManager conns = new ConnectionManagerImpl(); 
		conns.setPmanager(profiles);
//		
////		try {
////			conns.addFriendConnection(1,2);
////			System.out.println("1, 2, success!");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
//		
//		try {
//			conns.addConnection(2,4,1);
//			System.out.println("2, 4, success!! friend");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
////		
////		try {
////			conns.addConnection(1,2,1);
////			System.out.println("1, 3, success!!");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
////		
////		try {
////			conns.addConnection(4,3,1);
////			System.out.println("4, 3, success!! friend");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
//		
////		try {
////			conns.addFriendConnection(6,5);
////			System.out.println("6, 5, success!! friend");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
//		
//		
//		//test couple connection
////		try {
////			conns.addConnection(1,9,2);
////			System.out.println("1,9 success!! couple");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
////		
//		
////		try {
////			conns.addConnection(7,8, 2);
////			System.out.println("7,8 success!! couple");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
////		
//		
//		try {
//			conns.addConnection(7,8, 2);
//			System.out.println("7,8 success!! couple");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
////		
////		
//		try {
//			conns.addParentConnection(1,9,4 );
//			System.out.println("1, 9, 4 success!! parent");
//		} catch (Exception e) {
//			System.out.println(e.getClass());
//		}
////		
//		try {
//			conns.addParentConnection(7, 8, 4);
//			System.out.println("7, 8, 4 success!! parent");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		
//		try {
//			conns.addConnection(3,4, 1);
//		    System.out.println("3, 4,  success!! friend");
//		} catch (Exception e) {
//		    System.out.println(e.getMessage());
//		}
//		
//		
		
		//delete
////		try {
////			conns.removeConnections(3);
////			System.out.println("delete 1 success!");
////		} catch (NoParentException e) {
////			System.out.println("test");
////		    System.out.println(e.getMessage());
////		}
//		
		
//		//test add colleague connection
//		try {
//			conns.addConnection(1,3,3);
//			System.out.println("1, 2, success!! colleague");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
//		//test add classmates connection
//		try {
//			conns.addConnection(1,6,4);
//			System.out.println("1, 6, success!! classmates");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		
		//test getRelations
//		try {
//			ArrayList<Connection> relations = conns.getRelations(3);
//			for(Connection connection : relations) {
//				System.out.println(connection.getPerson1().getName() + 
//						connection.getPerson2().getName() + 
//						connection.getChild().getName());
//			}
//		}catch(Exception e) {
//			
//			System.out.println(e.getMessage());
//			
//		}
		
	// test reading people
		FileManager fmanager = new FileManager();
		
		try {
			profiles.importList(fmanager.readPeople());
		} catch (NoSuchAgeException e) {
			System.out.println(e.getMessage());
		}
		
//		for(Profile profile : profiles.get_Plist()) {
//			System.out.println(profile.getName());
//		}
		
		//test reading connections
		conns.importList(fmanager.readConnection(profiles, conns));
		
		
		for(Connection connection : conns.get_Clist()) {
			System.out.println(connection.getPerson1().getName()+" "+connection.getPerson2().getName()+connection.getClass());
		}
	}
}