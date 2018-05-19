package Controller;

import java.awt.Component;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import Database.ConnectDatabase;
import Database.DatabaseReader;
import Database.FileManager;
import Model.MiniNetModel;
import View.TheToolBar;

/*
 * 
 * Triggered when the user import data including both database and text files
 */
public class ImportDataListener implements ActionListener{

	//create variables for both readers
	private MiniNetModel model;
	private DatabaseReader dreader;
	private FileManager freader;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		model = MiniNetModel.getModel();
		
		dreader = model.getDataReader();
		
		freader = model.getFileManager();
		
		try {
			
			//Data can only import once.
			if(TheToolBar.getImportCheck() == true) {
				JOptionPane.showMessageDialog(null, "Already imported");
				return;
			}
			
			//call file reader first.
			model.getProfileManager().importList(freader.readPeople());
			freader.readConnection(model.getProfileManager(), model.getConnectionManager());
			JOptionPane.showMessageDialog((Component) e.getSource(),"Data import success.");
			
			//update combo box after import data
			TheToolBar.updateProfiles();
			TheToolBar.setImportCheck() ;

		} 
		catch(IOException e1) {
			
			//import database after text files failed.
			JOptionPane.showMessageDialog(null, "File not found, import database instead...");
			importDatabase(e);
			
		}catch(Exception e2) {
			
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}	
	}
	
	
	//Import data if text files reader fail.
	public void importDatabase(ActionEvent e) {
		
		try {
		
			ConnectDatabase.connecting();
			model.getProfileManager().importList(dreader.readPeople());
			dreader.readConnection(model.getProfileManager(), model.getConnectionManager());
            JOptionPane.showMessageDialog(null,"Data import success.");
			
			//update combo box after import data
			TheToolBar.updateProfiles();
			TheToolBar.setImportCheck();
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());

		} 
	}


}
