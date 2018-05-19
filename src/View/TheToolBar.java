package View;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import Controller.AddProfileListener;
import Controller.ImportDataListener;
import Controller.SelectProfileListener;
import Model.MiniNetModel;
import Model.Profile;

/*
 * Options to add player and select player
 * 
 * 
 */
public class TheToolBar extends JToolBar{
	
	private static JButton importdata;
	
	private static JButton addperson;
	
	private static MiniNetModel model;
	
	private static JLabel image;
	
	//Store a boolean to let import only happen once.
	private static boolean importCheck = false;

	private static JComboBox<Profile> selection;
	
	
	public TheToolBar() {
		
		setSize(200,20);
				
		importdata = new JButton("IMPORT DATA");
		importdata.addActionListener(new ImportDataListener());
		add(importdata);
		
		addperson = new JButton("ADD PROFILE");
		addperson.addActionListener(new AddProfileListener());
		add(addperson);
	
		
		selection = new JComboBox<Profile> ();
		selection.setSize(getPreferredSize());
		selection.addItemListener(new SelectProfileListener());
		add(selection);
		
		image = new JLabel();
		add(image);	
	}
	
	
	public static JComboBox<Profile> getSelection() {
		return selection;
	}
	
	public static JLabel getImage() {
		return image;
	}

	//Update information in combo box to match profile data in running program.
	public static void updateProfiles() {
		model = MiniNetModel.getModel();
		
		selection.removeAllItems();
		
		for(Profile profile : model.getProfileManager().get_Plist()) {
			
	    	selection.addItem(profile);
	    }
	}
	
	
	public static boolean getImportCheck() {
		return importCheck;
	}
	
	public static void setImportCheck() {
		importCheck = true;
	}
}



