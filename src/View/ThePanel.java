package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.AddConnectionListener;
import Controller.ChangeInfoListener;
import Controller.ViewInformationListener;
import Controller.removeProfileListener;

/*
 * Panel with options to use different functions related to a specific person 
 * 
 * 
 */

public class ThePanel extends JPanel{
	
	JButton info;
	
	JButton changeInfo;
	
	JButton checkConnection;
	
	JButton addConnection;
	
	JButton remove;
	
	
	public ThePanel() {
		
		setLayout(new GridLayout(5, 0, 30, 30));
		
		info = new JButton("Personal Information");
		info.addActionListener(new ViewInformationListener());
		add(info);
		
		changeInfo = new JButton("Change Information");
		changeInfo.addActionListener(new ChangeInfoListener());
		add(changeInfo);

		addConnection = new JButton("Add new Connection");
		addConnection.addActionListener(new AddConnectionListener());
		add(addConnection);
		
		remove = new JButton("Remove person");
		remove.addActionListener(new removeProfileListener());
		add(remove);
	}

}
