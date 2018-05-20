package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import Model.MiniNetModel;
import Model.Profile;
import View.TheToolBar;

/*
 * Trigger when the user try to remove current player on combo box.
 * 
 */
public class removeProfileListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//access the player form combo box
		Profile profile = (Profile) TheToolBar.getSelection().getSelectedItem();
		
		//remove connection first.
		try {
			MiniNetModel.getModel().getConnectionManager().removeConnections(profile.getName());
			MiniNetModel.getModel().getProfileManager().removeProfile(profile);
			
			//Update combo box
			TheToolBar.updateProfiles();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

}
