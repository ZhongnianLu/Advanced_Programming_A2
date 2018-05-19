package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.MiniNetModel;
import Model.Profile;
import View.MainFrame;
import View.TheToolBar;

/*
 * Triggered when the user click "view personal information" on tool bar 
 * 
 */
public class ViewInformationListener implements ActionListener{

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		MiniNetModel model = MiniNetModel.getModel();
		
		Profile profile = (Profile) TheToolBar.getSelection().getSelectedItem();
		
		//append information and connections to text area 
		if(profile != null) {
			
			MainFrame.getTextArea().setText(profile.profileInfo());
			
			if(model.getConnectionManager().showConnections(profile) != null) {
	    		MainFrame.getTextArea().append("Connections: "+
			                                    model.getConnectionManager().showConnections(profile));
			}
			
		}else {
			
			//Show error when no player is selected in combo box.
			JOptionPane.showMessageDialog(MainFrame.getToolBar2(), "You have to select a person first.");
		}
	}
}
