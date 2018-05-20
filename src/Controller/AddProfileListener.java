package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Exceptions.NoAvailableException;
import Exceptions.NoSuchAgeException;

import Model.MiniNetModel;
import Model.Profile;
import View.TheToolBar;

/*
 * Trigger when the user want to add a new Profile
 * 
 * 
 */

public class AddProfileListener implements ActionListener {
	
	MiniNetModel model;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		model = MiniNetModel.getModel();
		
		String name = null;
		
		// A message will show no name entered.
		try {
	    	name = JOptionPane.showInputDialog("Please enter a name:  ");
	    	
	    	if(name == null) {
	    		throw new NoAvailableException( "Name can't be empty.");	    		
	    	}
	    	
    		int age = Integer.parseInt((String) JOptionPane.showInputDialog("Please enter age:  "));
    		String status = JOptionPane.showInputDialog("Please enter status:  ");
    		
    		Profile newprofile = new Profile(name, age);

    		newprofile.setStatus(status);
    	
    		// A helper method will called if age of new profile age under 16
    		if(age < 16) {
    			createChild(newprofile);
    		
    		}else {	
        		model.getProfileManager().addProfile(newprofile);
	    		JOptionPane.showMessageDialog(null, "New Profile added.");

	    		TheToolBar.updateProfiles();
	    	}
	
		} catch (NoSuchAgeException  e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());

		} catch(NoAvailableException e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage());
				
		} catch(NumberFormatException e3) {
			JOptionPane.showMessageDialog(null, "Invalid age");
		}
	}
	

	// A user need to decide the parent for the child.
	//@profile the person selected in the combo box
	private void createChild(Profile profile) {
		
		//at least one of the parents should be chosen.
		String parent1 = JOptionPane.showInputDialog("Please enter one of his/her parent's name:  ");
		
		try {
    		model.getProfileManager().addProfile(profile);
			model.getConnectionManager().addParentConnection(parent1, profile.getName());
			TheToolBar.updateProfiles();
			JOptionPane.showMessageDialog(null, "New Profile Added.");

		}  catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
    		model.getProfileManager().removeProfile(profile);

		}

	}
}


