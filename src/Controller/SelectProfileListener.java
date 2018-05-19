package Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;

import Model.Profile;
import View.TheToolBar;

/*
 * Trigger this action when the user click a profile from combo box.
 * 
 */

public class SelectProfileListener implements ItemListener{
	
	Profile profile = (Profile) TheToolBar.getSelection().getSelectedItem();

	@Override
	public void itemStateChanged(ItemEvent e) {

	    //change image
		if(profile != null) {
    		TheToolBar.getImage().setIcon(new ImageIcon(profile.getImage()));		
		}
	}
	
}
