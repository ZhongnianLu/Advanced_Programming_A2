package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.MiniNetModel;
import Model.Profile;

public class CheckConnectionListener implements ActionListener{

	
	MiniNetModel model;
	
	String person1_name;

	String person2_name;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		model = MiniNetModel.getModel();


		person1_name = JOptionPane.showInputDialog(null, "Please enter the first person's name: ");
		person2_name = JOptionPane.showInputDialog(null, "Please enter the second person's name: ");
		
		try {
	    	if(person1_name.equals("") || person2_name.equals("")) {
	    		JOptionPane.showMessageDialog(null, "Invalid name.");		
    		}else {
			
	    		JOptionPane.showMessageDialog(null, "The two person you checked are: \n\n" + 
		                                  checkConnection(person1_name, person2_name));
	    	}
		}catch(Exception e1) {
    		JOptionPane.showMessageDialog(null, "Invalid name" );
    		
		}
		
	}

	
	private String checkConnection(String source, String target) {

		String result = "";
		
		try {
    		Profile profile1 = model.getProfileManager().searchProfile(source);
		
    		Profile profile2 = model.getProfileManager().searchProfile(target);		
		
			result = model.getConnectionManager().showConnections(profile1, profile2);
			
			if(result == "") {
				JOptionPane.showMessageDialog(null, "\n\nNot Connected");
			}
			
	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		
		return result;

	}
	
	

}
