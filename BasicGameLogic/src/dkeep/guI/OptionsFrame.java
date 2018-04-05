package dkeep.guI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.text.NumberFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

import dkeep.logic.*;

public class OptionsFrame extends JFrame{

	private JFrame frame;
	private JTextField heightValue;
	private JTextField widthValue;
	private SimpleGraphicsPanel map;
	private JPopupMenu menu;
	private String menuselection;
	private int mousecoordX;
	private int mousecoordY;
	private boolean validmap = false;
	
	private int height;
	private int width;
	
	int heroCounter;
	int ogreCounter;
	int keyCounter;
	int doorCounter;
	int wallCounter;
	int clubCounter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionsFrame window = new OptionsFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	
	/**
	 * Add to popup menu.
	 */	
	private void addtoPopupMenu(String nome, ActionListener menuListener) {
		 JMenuItem object = new JMenuItem(nome);
	     object.addActionListener(menuListener);
	     menu.add(object);
	}
	
	/**
	 * Set map cell to the popup selection.
	 */
	 private char selectionToId(String menuselection) {
		
		if(menuselection.equals("wall")) {
			return 'X';
		}
		
		if(menuselection.equals("hero")) {
			return 'H';
		}
		
		if(menuselection.equals("ogre")) {
			return 'O';
		}
		
		if(menuselection.equals("exit door")) {
			return 'I';
		}
		
		if(menuselection.equals("key")) {
			return 'k';
		}
		
		if(menuselection.equals("club")) {
			return '*';
		}
		
		return ' ';
	}
	 
	private void addNecessaryComponents(ActionListener menuListener) {
    	addtoPopupMenu("wall", menuListener);
	    addtoPopupMenu("exit door", menuListener);
	    addtoPopupMenu("key", menuListener);
	    addtoPopupMenu("ogre", menuListener);
	    addtoPopupMenu("hero", menuListener);
	    addtoPopupMenu("club", menuListener);
	    addtoPopupMenu("delete", menuListener);
	}
	
	/**
	 * Create the popup menu for the mouse, including instructions on how to change the map.
	 */
	private void createMenu() {
        menu = new JPopupMenu("Menu");
        
        JOptionPane popup = new JOptionPane();
        
        ActionListener menuListener = new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  menuselection = ((JMenuItem) event.getSource()).getText();
        		  
                  if(menuselection != null) {
                	  	
                	    if((mousecoordY == 0 || mousecoordY == height-1 || mousecoordX == 0 || mousecoordX == width-1) && (selectionToId(menuselection) != 'I') && selectionToId(menuselection) != 'X'){
                	  		popup.showMessageDialog(frame, "You cannot place anything but doors in here, also you cannot erase.");
                	  		return;
                	  	}
  						
                	  	map.getMap().updateMap(mousecoordY, mousecoordX, selectionToId(menuselection));
                  }
                    
                    map.paint(map.getGraphics());
                  }
        	};
        	
        addNecessaryComponents(menuListener);
	}

	/**
	 * Create the application.
	 */
	public OptionsFrame() {
		initialize();
		this.frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		initFrame();
		
		JPanel panel = initPanel();
		
		NumberFormatter formatter = initNumberFormatter();
	
		JLabel WidthLabel = initWidthLabel();
		
		initWidthValue(formatter);
		
		JLabel HeightLabel = initHeightLabel();
		
		initHeightValue(formatter);
		
	    JButton btnCreateMap = initButtonCreateMap();
	    
	    JButton btnEndEdition = initButtonEndEdition();
	    
		createMenu();
		
		createMap();

	    panel.setLayout(null);
	    panel.add(WidthLabel);
	    panel.add(HeightLabel);
	    panel.add(heightValue);
	    panel.add(widthValue);
		panel.add(btnCreateMap);
		panel.add(btnEndEdition);
		panel.add(map);
	}
	
	private void initFrame() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(600, 500));
		frame.setBounds(100, 100, 742, 653);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
	}
	
	private JPanel initPanel() {
		JPanel panel = new JPanel();
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		frame.getContentPane().add(panel);
		
		return panel;
	}
	
	private NumberFormatter initNumberFormatter() {
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(4);
		formatter.setMaximum(10);
		formatter.setAllowsInvalid(true);
		
		return formatter;
	}
	
	private JLabel initWidthLabel() {
		JLabel WidthLabel = new JLabel("Width : ");
		WidthLabel.setBounds(262, 21, 107, 37);
		WidthLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		WidthLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		WidthLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		WidthLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		return WidthLabel;
	}
	
	private void initWidthValue(NumberFormatter formatter) {
		widthValue = new JFormattedTextField(formatter);
		widthValue.setBounds(399, 25, 111, 32);
		widthValue.setFont(new Font("Tahoma", Font.PLAIN, 25));
		widthValue.setPreferredSize(new Dimension(10, 32));
		widthValue.setColumns(5);
		widthValue.setName("");
	}
	
	private JLabel initHeightLabel() {
		JLabel HeightLabel = new JLabel("Height : ");
		HeightLabel.setBounds(253, 79, 116, 37);
		HeightLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		return HeightLabel;
	}
	
	private void initHeightValue(NumberFormatter formatter) {
		heightValue = new JFormattedTextField(formatter);
		heightValue.setBounds(399, 78, 111, 32);
		heightValue.setFont(new Font("Tahoma", Font.PLAIN, 25));
		heightValue.setPreferredSize(new Dimension(10, 32));
		heightValue.setColumns(5);
	}
	
	private JButton initButtonCreateMap() {
		JButton btnCreateMap = new JButton("Create Map");
		
		btnCreateMap.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnCreateMap.setBounds(306, 137, 166, 56);
		
		btnCreateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateMapActionPerformed(e);
			}
		});
		
		return btnCreateMap;
	}
	
	private JButton initButtonEndEdition() {
		JButton btnEndEdition = new JButton("End Edition");
		
		btnEndEdition.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnEndEdition.setBounds(500, 137, 166, 56);
		
		btnEndEdition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEndEditionActionPerformed(e);
			}
		});
		
		return btnEndEdition;
	}
	
	private void createMap() {
		map = new SimpleGraphicsPanel();
		map.setBounds(102, 241, 523, 341);
		
		map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mousecoordX = e.getX()/34;
				mousecoordY = e.getY()/32;
                menu.show(map, e.getX(), e.getY());
			}
		});
	}

	private void btnCreateMapActionPerformed(ActionEvent e)
	{
		String s;
		
		if (!widthValue.getText().trim().isEmpty())
		{
			s = widthValue.getText();
			this.width = Integer.parseInt(s);
		}
		
		if (!heightValue.getText().trim().isEmpty())
		{
			s = heightValue.getText();
			this.height = Integer.parseInt(s);
		}
		
		Map m = new Map (width, height);

		map.setFocusable(true);
		map.setBounds(150,241,34*width, 32*height);
		map.setMap(m);
		map.paint(map.getGraphics());
	}
	
	private boolean checkIfClubNearby(char[][] map, int i, int j) {
		if(!(map[i+1][j] == '*'|| map[i-1][j] == '*'|| map[i][j+1] == '*' || map[i][j-1] == '*')) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Checking for amount of heroes, ogres ..
	 */
	
	private boolean incrementCounterByCharacter(char iden, int i, int j) {
		
		if(iden == 'H') {
			//counting heroes
			heroCounter++;
			return true;
		}
		
		if(iden == 'O') {
			//counting ogres
			
			if(!checkIfClubNearby(map.getMap().getMatrix(), i, j)) {
				return false;
			}
			
			ogreCounter++;
			
			return true;
		}
		
		if(iden == 'k') {
			//counting key
			keyCounter++;
			return true;
		}
		
		if(iden == 'X') {
			wallCounter++;
			return true;
		}
			
		if(iden == 'I') {
			doorCounter++;
			return true;
		}
		
		if(iden == '*') {
			clubCounter++;
			return true;
		}
		
		return true;
	}
	
	private boolean checkMapIsValid() {
		
		heroCounter = 0;
		ogreCounter = 0;
		keyCounter = 0;
		doorCounter = 0;
		wallCounter = 0;
		clubCounter = 0;
		
		for(int i = 0; i < map.getMap().getMatrix().length; i++) {
			for(int j = 0; j < map.getMap().getMatrix()[i].length; j++) {
				if(incrementCounterByCharacter(map.getMap().getMatrix()[i][j], i, j) == false) {
					return false;
				}
			}
		}
		
		if(!(heroCounter == 1 && ogreCounter > 0 && keyCounter == 1 && wallCounter > 12 && doorCounter > 0 && clubCounter == ogreCounter)) {
			return false;
		} else {
			return true;
		}
	}
	
	private void btnEndEditionActionPerformed(ActionEvent e) {
		JOptionPane popup = new JOptionPane();
		
		if(!checkMapIsValid()) {
			
			popup.showMessageDialog(frame, "The map you entered is invalid.");
			
			validmap = false;
			
		} else {
			
			popup.showMessageDialog(frame, "The map you entered is valid and will be loaded into New Game.");
			
			validmap = true;
		}
	}
	
	public Dimension getFramePreferredSize() {
		return frame.getPreferredSize();
	}
	
	public void setFramePreferredSize(Dimension preferredSize) {
		frame.setPreferredSize(preferredSize);
	}
	
	public Map getCustomMap() {return map.getMap();}
	
	public boolean getValidMap() {return validmap;}
}
