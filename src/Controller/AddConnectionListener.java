package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Exceptions.NoAvailableException;
import Exceptions.NoParentException;
import Exceptions.NotToBeFriendsException;
import Exceptions.ProfileNotFoundException;
import Exceptions.RepeatException;
import Model.MiniNetModel;
import Model.Profile;
import View.TheToolBar;
/*
 * Trigger when try to create a new connection.
 * 
 * 
 */
public class AddConnectionListener implements ActionListener{
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		MiniNetModel model = MiniNetModel.getModel();
		
		//Select profile from combo box
		Profile profile = (Profile)(TheToolBar.getSelection().getSelectedItem());
		
		try {
			if(profile == null) {
				throw new NoAvailableException("Please select a person first.");
			}
			
			
			int chose = Integer.parseInt(JOptionPane.showInputDialog(null, "Plese enter the type of connection you want to add--"
					+ "\n\n1. Friend\n2. Couple \n3. Parent\n4. Colleague\n5. Classmate"));
		
			String name = JOptionPane.showInputDialog(null, "Plese enter the name of other person in this connection: ");

			if( profile.getName().equals(name)) {
				throw new NoAvailableException("Profile name repeated.");
			}			
			
			//determine what type of connection the user want to add
			switch(chose) {
			case 1:
				model.getConnectionManager().addConnection(profile.getName(), name, "friends");
				JOptionPane.showMessageDialog(null, "Add Connection Success. ");

			    break;
			    
			case 2:
				model.getConnectionManager().addConnection(profile.getName(), name, "couple");
				JOptionPane.showMessageDialog(null, "Add Connection Success. ");
				break;
			
			case 3:
				addParent(profile, name, model);
				JOptionPane.showMessageDialog(null, "Add Connection Success. ");
				break;
			
			case 4:
				model.getConnectionManager().addConnection(profile.getName(), name, "colleague");
				JOptionPane.showMessageDialog(null, "Add Connection Success. ");
				break;
				
			case 5:
				model.getConnectionManager().addConnection(profile.getName(), name, "classmate");
				JOptionPane.showMessageDialog(null, "Add Connection Success. ");
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "Not valid choice.");
				break;
			}
			
			}catch(NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Not valid choice");

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				
			}
		}

	//Helper method to create a parent relation
	private void addParent(Profile profile, String name, MiniNetModel model) throws NoParentException, RepeatException, 
	                                                                                ProfileNotFoundException, NotToBeFriendsException {

			int type = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter: 1. Add a parent\n2. Add a child\n"));
			if(type == 1) {
				model.getConnectionManager().addParentConnection(name, profile.getName());

			}else if(type == 2) {
				model.getConnectionManager().addParentConnection(profile.getName(), name);
	
			}else {
				throw new NumberFormatException();
			}
	}
	
	


}
