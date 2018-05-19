package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.MiniNetModel;

/*
 * Main gui container 
 * 
 * 
 */

public class MainFrame extends JFrame{
	
	 MiniNetModel model = MiniNetModel.getModel();
	 
	 private static JTextArea tarea;
	 
	 private static TheToolBar toolbar;
	 
	 private static ThePanel panel;
	 
	 private static JScrollPane scrollpane;
	 
	 
	 public MainFrame() {
		 
		 super("MiniNet");
		 
		 setLocation(100,200);
		 setSize(600, 400);
		 	 
		 toolbar = new TheToolBar();
		 add(toolbar, BorderLayout.NORTH);
		 
		 panel = new ThePanel();
		 add(panel, BorderLayout.WEST);
		 
		 tarea = new JTextArea(); 
		 
		 scrollpane = new TheScrollPane(tarea);
		 add(scrollpane, BorderLayout.CENTER);
		 
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setVisible(true);
		 
	 }
	 
	 public static JTextArea getTextArea() {
		 return tarea;
	 }
	 
	 public static TheToolBar getToolBar() {
		 return toolbar;
	 }
	 
	 public static ThePanel getToolBar2() {
		 return panel;
	 }
}
