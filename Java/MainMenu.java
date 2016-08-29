package planechaseWithGui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class MainMenu implements ActionListener {
	
	JButton LaunchPlanechase = new JButton("Planechase");
	JButton LaunchArchenemy = new JButton("Archenemy");
	JButton DoneButton = new JButton("Done");
	
	public MainMenu(){
	}
	
	public void actionPerformed (ActionEvent e){
		PlanchaseGuiEdition gogo = null;
		if (e.getSource() == LaunchPlanechase){
			try {
				gogo = new PlanchaseGuiEdition("Planes.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				gogo.createWindow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == LaunchArchenemy){
			try {
				gogo = new PlanchaseGuiEdition("Schemes.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				try {
					gogo.createWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		if (e.getSource() == DoneButton){
			System.exit(0);
		}
		
	}
	
	public void CreateWindow(){
		JLabel mainMenuText = new JLabel();
		mainMenuText.setText("Choose your game mode (Selection will open in a new window):");
		JPanel mainWindow = new JPanel();
		mainWindow.setLayout(new GridBagLayout());
		JFrame mainWindowDisplay = new JFrame("MTG Multiplayer 2.1.1");
		GridBagConstraints mainMenuConstraints = new GridBagConstraints();
		mainMenuConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainMenuConstraints.gridx = 0;
		mainMenuConstraints.gridy = 1;
		mainWindow.add(mainMenuText, mainMenuConstraints);
		mainMenuConstraints.gridx = 0;
		mainMenuConstraints.gridy = 2;
		LaunchPlanechase.addActionListener(this);
		mainWindow.add(LaunchPlanechase, mainMenuConstraints);
		mainMenuConstraints.gridy = 3;
		LaunchArchenemy.addActionListener(this);
		mainWindow.add(LaunchArchenemy, mainMenuConstraints);
		mainMenuConstraints.gridy = 4;
		DoneButton.addActionListener(this);
		mainWindow.add(DoneButton, mainMenuConstraints);
		mainWindowDisplay.setContentPane(mainWindow);
		mainWindowDisplay.setSize(400, 400);
		mainWindowDisplay.setLocation(100, 100);
		mainWindowDisplay.setVisible(true);
		
		
	}

}
