package dkeep.guI;

import java.awt.BorderLayout;
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
//import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.NumberFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

import dkeep.logic.*;

public class OptionsFrame extends JFrame{

	private JFrame frame;
	private JTextField HeightValue;
	private JTextField WidthValue;
	private SimpleGraphicsPanel map;
	private JPopupMenu menu;
	private String menuselection;
	private int mousecoordX;
	private int mousecoordY;
	
	private int height;
	private int width;

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
	public void addtoPopupMenu(String nome, ActionListener menuListener) {
		 JMenuItem object = new JMenuItem(nome);
	     object.addActionListener(menuListener);
	     menu.add(object);
	}
	
	/**
	 * Set map cell to the popup selection.
	 */
	public char selectionToId(String menuselection) {
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
		
		return ' ';
	}
	
	/**
	 * Create the popup menu for the mouse.
	 */
	public void createMenu() {
        menu = new JPopupMenu("Menu");
        
        ActionListener menuListener = new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  menuselection = ((JMenuItem) event.getSource()).getText();
        		  
                  if(menuselection != null) {
                	  try {
						map.getMap().setMap(mousecoordY, mousecoordX, selectionToId(menuselection));
					} catch (IllegalMapChangeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                  }
                  
                  map.paint(map.getGraphics());
        	  }
        	};
        	
        addtoPopupMenu("wall", menuListener);
        addtoPopupMenu("exit door", menuListener);
        addtoPopupMenu("key", menuListener);
        addtoPopupMenu("ogre", menuListener);
        addtoPopupMenu("hero", menuListener);
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
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(600, 500));
		frame.setBounds(100, 100, 742, 653);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		frame.getContentPane().add(panel);
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(4);
		formatter.setMaximum(10);
		formatter.setAllowsInvalid(true);
		
		JLabel WidthLabel = new JLabel("Width : ");
		WidthLabel.setBounds(262, 21, 107, 37);
		WidthLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		WidthLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		WidthLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		WidthLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		WidthValue = new JFormattedTextField(formatter);
		WidthValue.setBounds(399, 25, 111, 32);
		WidthValue.setFont(new Font("Tahoma", Font.PLAIN, 25));
		WidthValue.setPreferredSize(new Dimension(10, 32));
		WidthValue.setColumns(5);
		WidthValue.setName("");
		
		JLabel HeightLabel = new JLabel("Height : ");
		HeightLabel.setBounds(253, 79, 116, 37);
		HeightLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		HeightValue = new JFormattedTextField(formatter);
		HeightValue.setBounds(399, 78, 111, 32);
		HeightValue.setFont(new Font("Tahoma", Font.PLAIN, 25));
		HeightValue.setPreferredSize(new Dimension(10, 32));
		HeightValue.setColumns(5);
		panel.setLayout(null);
		panel.add(WidthLabel);
		panel.add(WidthValue);
		panel.add(HeightLabel);
		panel.add(HeightValue);
		
		// GENERATING THE ACTUAL MAP
		JButton btnCreateMap = new JButton("Create Map");
		btnCreateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateMapActionPerformed(e);
			}
		});
		
		JButton btnEndEdition = new JButton("End Edition");
		btnEndEdition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEndEditionActionPerformed(e);
			}
		});
		
		btnCreateMap.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnCreateMap.setBounds(306, 137, 166, 56);
		panel.add(btnCreateMap);
		
		createMenu();
		
		map = new SimpleGraphicsPanel();
		map.setBounds(102, 241, 523, 341);
		panel.add(map);
		
		map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mousecoordX = e.getX()/34;
				mousecoordY = e.getY()/32;
				
                menu.show(map, e.getX(), e.getY());
			}
		});
	}

	public void btnCreateMapActionPerformed(ActionEvent e)
	{
		String s;
		if (!WidthValue.getText().trim().isEmpty())
		{
			s = WidthValue.getText();
			this.width = Integer.parseInt(s);
		}
		
		if (!HeightValue.getText().trim().isEmpty())
		{
			s = HeightValue.getText();
			this.height = Integer.parseInt(s);
		}
		
		Map m = new Map (width, height);

		map.setFocusable(true);
		map.setBounds(150,241,34*width, 32*height);
		map.setMap(m);
		map.paint(map.getGraphics());
	}
	
	/**
	 * Checking for amount of heroes, ogres ..
	 */
	
	private boolean checkmap() {
		
		int heroCounter = 0;
		int ogreCounter = 0;
		int keyCounter = 0;
		int doorCounter = 0;
		int wallCounter = 0;
		
		for(int i = 0; i < map.getMap().getmap().length; i++) {
			for(int j = 0; j < map.getMap().getmap()[i].length; j++) {
				if(map.getMap().getmap()[i][j] == 'H') {
					//counting heroes
					heroCounter++;
				}
				
				if(map.getMap().getmap()[i][j] == 'O') {
					//counting ogres
					ogreCounter++;
				}
				
				if(map.getMap().getmap()[i][j] == 'k') {
					//counting key
					keyCounter++;
				}
				
				if(map.getMap().getmap()[i][j] == 'X') {
					wallCounter++;
				}
					
				if(map.getMap().getmap()[i][j] == 'I') {
					doorCounter++;
				}
			}
		}
		
		if(!(heroCounter == 1 && ogreCounter > 0 && keyCounter == 1 && wallCounter > 12 && doorCounter > 0)) {
			return false;
		} else {
			return true;
		}
	}
	
	private void btnEndEditionActionPerformed(ActionEvent e) {
		if(!checkmap()) {
			//TODO: Show popup window
		}
		
		//TODO: create function for deleting elements
		//TODO: add a club.
	}
	
	public Dimension getFramePreferredSize() {
		return frame.getPreferredSize();
	}
	
	public void setFramePreferredSize(Dimension preferredSize) {
		frame.setPreferredSize(preferredSize);
	}
	
	public Map getCustomMap() {return map.getMap();}
}
