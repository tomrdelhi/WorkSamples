package planechaseWithGui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.*;

public class PlanchaseGuiEdition implements ActionListener {
	ArrayList<String> currentPlane = new ArrayList<String>();
	ArrayList<ArrayList<String>> PlaneList = new ArrayList<ArrayList<String>>();
	JButton walkButton = new JButton("Planeswalk");
	JButton exitButton = new JButton("Done");
	JButton nextButton = new JButton("Reveal Next");
	JButton nextExitButton = new JButton("Done");
	JPanel displayWindow = new JPanel();
	int locatorx = 600;
	int locatory = 100;
	String fileName;
	
	public PlanchaseGuiEdition (String fileName) throws FileNotFoundException, UnsupportedEncodingException, IOException{
		String line;
		String encoding = "UTF-16";
		this.fileName = fileName;
		if (fileName == "Schemes.txt"){
			encoding = "UTF-8";
		}
		BufferedReader planes = new BufferedReader(new InputStreamReader(
				this.getClass().getResourceAsStream("/" + fileName), 
				encoding));
		while((line = planes.readLine())!= null){
			ArrayList<String> IndiPlane = new ArrayList<String>();
			IndiPlane.add(line);
			IndiPlane.add(planes.readLine());
			IndiPlane.add(planes.readLine());
			IndiPlane.add(planes.readLine());
			IndiPlane.add("N");
			PlaneList.add(IndiPlane);
		}
	}
	
	public ArrayList<String> planeswalk() throws IOException{
		int checker = 0;
		ArrayList<String> returnlist = new ArrayList<String>();
		for (ArrayList<String> item:PlaneList){
			if(item.get(4) == "N"){
				checker = 1;
				break;
			}
		}
		if (checker == 0){
			for (ArrayList<String> thingy: PlaneList){
				thingy.remove(4);
				thingy.add("N");
			}
		}
		int x = 1;
		while (x == 1){
			int randomplane = (int)Math.floor(Math.random()*PlaneList.size());
			if(PlaneList.get(randomplane).get(4) == "N"){
				PlaneList.get(randomplane).remove(4);
				PlaneList.get(randomplane).add("Y");
				return (PlaneList.get(randomplane));
			}
		}
	return (returnlist);
	}
	
	public JFrame revealNextItem() throws IOException{
		JFrame nextWindow = new JFrame("Next Plane");
		ArrayList<String> nextPlane = planeswalk();
		if (nextPlane.get(0) == currentPlane.get(0)){
			nextPlane = planeswalk();
		}
		GridBagConstraints nextConstraints = new GridBagConstraints();
		JPanel nextPanel = new JPanel();
		nextPanel.setLayout(new GridBagLayout());
		JLabel nextText = new JLabel();
		nextExitButton.addActionListener(this);
		nextText.setText("<html> <center>" + nextPlane.get(0) + "<br> <br>" + nextPlane.get(1) + 
				"<br> <br>" + nextPlane.get(2) + "<br> <br>" + nextPlane.get(3) + "<br>  <br></center> </html>");
		nextText.setHorizontalAlignment(JLabel.CENTER);
		nextConstraints.fill = GridBagConstraints.HORIZONTAL;
		nextConstraints.gridheight = 1;
		nextConstraints.gridwidth = 2;
		nextConstraints.gridx = 0;
		nextConstraints.gridy = 5;
		nextConstraints.ipadx = 100;
		//nextPanel.add(nextExitButton, nextConstraints);
		nextConstraints.fill = GridBagConstraints.BOTH;
		nextConstraints.gridx = 0;
		nextConstraints.gridy = 1;
		nextConstraints.gridwidth = 2;
		nextConstraints.gridheight = 3;
		nextPanel.add(nextText, nextConstraints);
	    nextWindow.setContentPane(nextPanel);
	    nextWindow.setSize(400,400);
	    nextWindow.setLocation(locatorx, locatory);
	    if (locatorx == 800){
	    	locatorx = 500;
	    	locatory = 0;
	    }
	    locatorx += 100;
	    locatory += 100;
	    nextWindow.setVisible(true);
	    return (nextWindow);
	}
	
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		PlanchaseGuiEdition thistime = null;
		JFrame current = new JFrame();
		try {
			thistime = new PlanchaseGuiEdition(fileName);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (e.getSource() == this.exitButton){
		    System.exit(0);}
		if (e.getSource() == this.nextButton){
			try {
				current = this.revealNextItem();
			} catch (IOException e1) {
				System.out.println("IOException in the nextButton");
				e1.printStackTrace();
			}
		}
		if (e.getSource() == this.nextExitButton){
			current.dispose();
		}
		if (e.getSource() == this.walkButton){
			GridBagConstraints c = new GridBagConstraints();
			try {
				currentPlane = this.planeswalk();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			displayWindow.removeAll();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 5;
			displayWindow.add(walkButton, c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = 5;
			displayWindow.add(exitButton, c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 2;
			c.gridy = 5;
			displayWindow.add(nextButton, c);
			JLabel textDisplay = new JLabel();
			textDisplay.setText("<html> <center>" + currentPlane.get(0) + "<br> <br>" + currentPlane.get(1) + 
					"<br> <br>" + currentPlane.get(2) + "<br> <br>" + currentPlane.get(3) + "<br>  <br></center> </html>");
			textDisplay.setHorizontalAlignment(JLabel.CENTER);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 3;
			c.gridheight = 3;
			displayWindow.add(textDisplay, c);
			displayWindow.updateUI();
		}
	}

	
	public void createWindow() throws FileNotFoundException, UnsupportedEncodingException, IOException{
		displayWindow.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		walkButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		displayWindow.add(walkButton, c);
		exitButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		displayWindow.add(exitButton, c);
		nextButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		displayWindow.add(nextButton, c);
		//ArrayList<String> currentPlane = thistime.planeswalk();
		JLabel textDisplay = new JLabel();
		//textDisplay.setText("<html> <center>" + currentPlane.get(0) + "<br> <br>" + currentPlane.get(1) + 
		//		"<br> <br>" + currentPlane.get(2) + "<br> <br>" + currentPlane.get(3) + "<br>  <br></center> </html>");
		
		textDisplay.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 3;
		displayWindow.add(textDisplay, c);
		JFrame window = new JFrame("Planeswalker 2.2.1");
	    window.setContentPane(displayWindow);
	    window.setSize(400,400);
	    window.setLocation(100,100);
	    window.setVisible(true);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		MainMenu menu = new MainMenu();
		menu.CreateWindow();
	}
	
}
