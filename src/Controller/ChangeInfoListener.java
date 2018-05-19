package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.Profile;
import View.TheToolBar;

/*
 * This controller triggered whenever the user want to change personal information to a person
 * 
 * 
 */

public class ChangeInfoListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Profile profile = (Profile) TheToolBar.getSelection().getSelectedItem();

		//Ask the user what information need to be changed by showing dialog
		try {
    		int chose = Integer.parseInt(JOptionPane.showInputDialog(null, "Plese enter the type of information you want to change--"
				+ "\n\n1. Name\n2. Status\n3. Gender\n4. Image"));
		
    		switch(chose) {
    		case 1:
	    		String name = JOptionPane.showInputDialog(null, "Plese enter a name: ");
		    	profile.setName(name);
	    		JOptionPane.showMessageDialog(null, "Change Success. ");
    
		        break;
		    
    		case 2:
	    		String status = JOptionPane.showInputDialog(null, "Plese enter status: ");
	    		profile.setStatus(status);
		    	JOptionPane.showMessageDialog(null, "Change Success. ");
 
    			break;
		
	    	case 3:
		    	String gender = JOptionPane.showInputDialog(null, "Plese enter gender (M/F): ");
			    profile.setGender(gender);
		    	JOptionPane.showMessageDialog(null, "Change Success. ");

	    		break;
		
		    case 4:
		    	String image = JOptionPane.showInputDialog(null, "Please enter file name of your image: "); 
	    		profile.setImage(image);
	    		JOptionPane.showMessageDialog(null, "Change Success. ");

    			break;
			
	    	default:
		    	JOptionPane.showMessageDialog(null, "Not valid choice.");
	    		break;
	    	}
		
		}catch(Exception e2) {
			JOptionPane.showMessageDialog(null, "Not valid choice");

		}
		
	}

}
