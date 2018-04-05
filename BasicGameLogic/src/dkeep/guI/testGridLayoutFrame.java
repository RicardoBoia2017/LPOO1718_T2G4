package dkeep.guI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import dkeep.logic.Game;
import dkeep.logic.LevelLogic;
import dkeep.logic.Map;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.NumberFormat;

public class testGridLayoutFrame extends JFrame {

	private JPanel contentPane;

	private int numberOfOgres = 1;

	private String guardPersonality = "Rookie";

	private Game game;

	private boolean newgamestarted = false;

	private boolean customMapMade = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testGridLayoutFrame frame = new testGridLayoutFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testGridLayoutFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20};
		gbl_contentPane.rowHeights = new int[]{20};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints c = new GridBagConstraints();
		

		callInitFunctions();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameScreen.requestFocusInWindow();
			}
		});
		
		c.ipadx = 347;
		c.ipady = 332;
		contentPane.add(gameScreen,c);
		
		contentPane.add(newGameButton);
		
		/*getContentPane().add(loadGameButton);
		getContentPane().add(saveGameButton);
		getContentPane().add(loadGameButton);
		getContentPane().add(numberOfOgresLabel);
		getContentPane().add(numberOfOgresBox);
		getContentPane().add(gameScreen);
		getContentPane().add(gameStatusLabel);
		getContentPane().add(guardPersonalityLabel);
		getContentPane().add(guardPersonalityBox);
		getContentPane().add(moveLeftButton);
		getContentPane().add(moveRightButton);
		getContentPane().add(exitGameButton);
		getContentPane().add(moveDownButton);
		getContentPane().add(moveUpButton);
		getContentPane().add(newGameButton);
		getContentPane().add(createNewMap);*/
	}
	
	private void callInitFunctions() {
		editor = null;

		initNumberOfOgresLabel();
		initNumberOfOgresBox();
		initGuardPersonalityLabel();
		initGuardPersonalityBox();
		initNewGameButton();
		initMoveLeftButton();
		initMoveUpButton();
		initMoveRightButton();
		initMoveDownButton();
		initExitGameButton();
		initGameStatusLabel();
		initGameScreen();
		initSaveGameButton();
		initLoadGameButton();
		initCreateNewMapButton();
	}

	private void initNumberOfOgresLabel() {
		numberOfOgresLabel = new java.awt.Label();
		numberOfOgresLabel.setBounds(1, 22, 174, 33);
		numberOfOgresLabel.setText("Number of ogres:");
	}

	private void initNumberOfOgresBox() {
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1);
		formatter.setMaximum(5);
		formatter.setAllowsInvalid(false);

		numberOfOgresBox = new JFormattedTextField(formatter);
		numberOfOgresBox.setBounds(185, 23, 32, 32);

		numberOfOgresBox.setName("");
		numberOfOgresBox.setSelectionEnd(-1);
	}

	private void initGuardPersonalityLabel() {
		guardPersonalityLabel = new java.awt.Label();
		guardPersonalityLabel.setBounds(1, 95, 171, 37);
		guardPersonalityLabel.setText("Guard personality");
	}

	private void initGuardPersonalityBox() {
		guardPersonalityBox = new javax.swing.JComboBox<>();
		guardPersonalityBox.setBounds(173, 105, 138, 27);

		guardPersonalityBox
				.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rookie", "Drunken", "Suspicious" }));
		guardPersonalityBox.setToolTipText("");
		guardPersonalityBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});
	}

	private void initNewGameButton() {
		newGameButton = new javax.swing.JButton();
		newGameButton.setBounds(459, 95, 180, 21);
		newGameButton.setText("New Game");
		newGameButton.setToolTipText("");
		newGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newGameActionPerformed(evt);
			}
		});
	}

	private void initMoveLeftButton() {
		moveLeftButton = new javax.swing.JButton();
		moveLeftButton.setBounds(432, 248, 74, 35);
		moveLeftButton.setText("Left");
		moveLeftButton.setEnabled(false);
		moveLeftButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveLeftActionPerformed(evt);
			}
		});
	}

	private void initMoveRightButton() {
		moveRightButton = new javax.swing.JButton();
		moveRightButton.setBounds(566, 248, 87, 35);
		moveRightButton.setText("Right");
		moveRightButton.setEnabled(false);
		moveRightButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveRightActionPerformed(evt);
			}
		});
	}

	private void initMoveUpButton() {
		moveUpButton = new javax.swing.JButton();
		moveUpButton.setBounds(499, 201, 72, 38);
		moveUpButton.setText("Up");
		moveUpButton.setEnabled(false);
		moveUpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveUpActionPerformed(evt);
			}
		});

	}

	private void initMoveDownButton() {
		moveDownButton = new javax.swing.JButton();
		moveDownButton.setBounds(485, 295, 98, 35);
		moveDownButton.setText("Down");
		moveDownButton.setEnabled(false);
		moveDownButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveDownActionPerformed(evt);
			}
		});
	}

	private void initExitGameButton() {
		exitGameButton = new javax.swing.JButton();
		exitGameButton.setBounds(412, 525, 241, 35);
		exitGameButton.setText("Exit Game");
		exitGameButton.setToolTipText("");
		exitGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitGameActionPerformed(evt);
			}
		});
	}

	private void initGameStatusLabel() {
		gameStatusLabel = new javax.swing.JLabel();
		gameStatusLabel.setBounds(1, 529, 245, 26);
		gameStatusLabel.setText("<Game status goes here>");
		gameStatusLabel.setToolTipText("");
	}

	private void initGameScreen() {
		gameScreen = new SimpleGraphicsPanel();
		gameScreen.setBounds(1, 191, 347, 332);
		gameScreen.setFocusable(true);

		gameScreen.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					moveLeftButton.doClick();
					break;

				case KeyEvent.VK_RIGHT:
					moveRightButton.doClick();
					break;

				case KeyEvent.VK_UP:
					moveUpButton.doClick();
					break;

				case KeyEvent.VK_DOWN:
					moveDownButton.doClick();
					break;
				}
			}
		});
	}

	private void initSaveGameButton() {
		saveGameButton = new javax.swing.JButton();
		saveGameButton.setText("Save Game");
		saveGameButton.setToolTipText("");
		saveGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveGameActionPerformed(evt);
			}
		});

		saveGameButton.setBounds(400, 350, 166, 56);
		saveGameButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveGameButton.setEnabled(false);
	}

	private void initLoadGameButton() {
		loadGameButton = new javax.swing.JButton();
		loadGameButton.setText("Load Game");
		loadGameButton.setToolTipText("");
		loadGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadGameActionPerformed(evt);
			}
		});

		loadGameButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loadGameButton.setBounds(400, 420, 166, 46);
	}

	private void initCreateNewMapButton() {
		createNewMap = new JButton();
		createNewMap.setBounds(437, 128, 228, 35);
		createNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createNewMapActionPerformed(evt);
			}
		});
		createNewMap.setToolTipText("");
		createNewMap.setText("Create New Map");
	}

	/*
	 * GUARD PERSONALITY
	 */
	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {

		Object selected = guardPersonalityBox.getSelectedItem();

		if (selected.toString().equals("Rookie")) {
			guardPersonality = "Rookie";
		}

		else if (selected.toString().equals("Drunken")) {
			guardPersonality = "Drunken";
		}

		else if (selected.toString().equals("Suspicious")) {
			guardPersonality = "Suspicious";
		}
	}
	
	private void saveFile(String path) {
		JOptionPane popup = new JOptionPane();
		
		File savefile = new File(path);
		
		if(!savefile.exists()) {
			try {
				savefile.createNewFile();
			} catch (IOException e) {
				System.out.println("There was a problem creating the file.");
				e.printStackTrace();
			}
		}
		
		FileOutputStream file = null;
	    ObjectOutputStream out = null;
	    
	    try {
	    	file = new FileOutputStream(savefile,false);
	    	OutputStream buffer = new BufferedOutputStream(file);
	    	out = new ObjectOutputStream(buffer);
	    	out.writeObject(game);
	    	out.flush();
	    	out.close();
	    	System.out.println("Current game saved in: "+path);
	    	
	     } catch (FileNotFoundException e) {
	    	   popup.showMessageDialog(this, "The file you entered is invalid.");
	    	   
	    	   e.printStackTrace();
	     } catch (IOException e) {
	    	   popup.showMessageDialog(this, "Could not create file.");
	           e.printStackTrace();
	     }
	}
	
	private void loadFile(String path) {
		JOptionPane popup = new JOptionPane();
		
		Game savedGame;
		Map savedMap = null;
		LevelLogic savedLogic = null;
		int numOgres = 0;
		String gamestate = "";

        FileInputStream file;
        
       try {  	   
           file = new FileInputStream(path);
           InputStream buffer = new BufferedInputStream(file);
           ObjectInput input = new ObjectInputStream (buffer);
          
           savedGame = (Game)input.readObject();
           savedMap = savedGame.getMap();
           savedLogic = savedGame.getLevelLogic();
           numOgres = savedGame.getNumberOfEnemies();
           
           input.close();
           
           System.out.println("Loaded game from: "+path);
       } catch (FileNotFoundException e) {
           popup.showMessageDialog(this, "The file you entered is invalid, select another one or cancel.");
           e.printStackTrace();
           gameScreen.requestFocusInWindow();
           return;
       } catch (IOException e) {
    	   popup.showMessageDialog(this, "Could not create file.");
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
    	   popup.showMessageDialog(this, "Could not load the file's contents, it is corrupted or empty.");
           e.printStackTrace();
       }
       
       Game toRun = new Game(numOgres,savedMap, savedLogic);
       
       game = toRun;
       
       gameScreen.setMap(game.getMap());
       gameScreen.paint(gameScreen.getGraphics());
       gameScreen.requestFocusInWindow();
       
       startNewGame();
	}
	
	private void startNewGame() {
	       newgamestarted = true;
	       
	       moveLeftButton.setEnabled(true);
	       moveRightButton.setEnabled(true);
	       moveUpButton.setEnabled(true);
	       moveDownButton.setEnabled(true);
	       saveGameButton.setEnabled(true);
	       
	       gameStatusLabel.setText("The game is running.");
	}
	
	private void startCustomGame() {
		
		if (customMapMade == false) {

			game = new Game(editor.getCustomMap().getMatrix());

			gameScreen.setMap(editor.getCustomMap());
			gameScreen.paint(gameScreen.getGraphics());

			gameScreen.requestFocusInWindow();

			saveFile("custom ");

			customMapMade = true;
		}
		
		loadFile("custom ");
		
		startNewGame();
	}
	
	private void startDefaultGame() {

		if (numberOfOgresBox.getValue() != null) {
			// if the text field is empty, it will by omission be 1 ogre

			// otherwise ..
			String s = numberOfOgresBox.getText();

			numberOfOgres = Integer.parseInt(s);
		}

		game = new Game(numberOfOgres, guardPersonality);
		// game.getGuard().setMovementBlocker(true);
		numberOfOgresBox.setValue(null);

		gameScreen.setMap(game.getMap());
		gameScreen.paint(gameScreen.getGraphics());
		gameScreen.requestFocusInWindow();
		
		startNewGame();
	}

	/*
	 * NEW GAME BUTTON
	 */
	private void newGameActionPerformed(ActionEvent evt) {

		boolean normalmap = true;
		
		JOptionPane popup = new JOptionPane();

		String answer = JOptionPane.showInputDialog("Default map or Custom map? (type custom (or c) or default (or d))");

		if (answer == null) {
			return;
		}

		if (answer.equals("custom") || answer.equals("c")) {
			normalmap = false;
		}

		else if (answer.equals("default") || answer.equals("d")) {
			normalmap = true;
		}

		else {
			popup.showMessageDialog(this, "Invalid, please type custom or default.");
			return;
		}

		if (editor != null && editor.getValidMap() && normalmap == false) {
			// in this case it will run the custom map !IF IT IS VALID!
			startCustomGame();
		}

		else {

			if (normalmap == false) {
				popup.showMessageDialog(this, "The editor had no valid custom map made, so we're running default.");
			}
			
			startDefaultGame();
		}
		
	}
	
	private void turnOffButtons() {
		moveLeftButton.setEnabled(false);
		moveRightButton.setEnabled(false);
		moveUpButton.setEnabled(false);
		moveDownButton.setEnabled(false);
	}
	
	private void moveByChar(char movementKey) {
		
		if (newgamestarted && game.getLevelLogic().getLevelState().equals("Running")) {
			game.updateGame(movementKey);
			gameScreen.setMap(game.getMap());
			gameScreen.paint(gameScreen.getGraphics());
		}

		if (game.getLevelLogic().getLevelState().equals("Over")) {
			gameStatusLabel.setText("The game is over.");
			turnOffButtons();
		}

		if (game.getLevelLogic().getLevelState().equals("Victory")) {
			gameStatusLabel.setText("You win!");
			turnOffButtons();
		}
		
	}

	/*
	 * HERO MOVE LEFT
	 */
	private void moveLeftActionPerformed(java.awt.event.ActionEvent evt) {
		moveByChar('a');
		gameScreen.requestFocusInWindow();
	}

	/*
	 * HERO MOVE UP
	 */
	private void moveUpActionPerformed(ActionEvent evt) {
		moveByChar('w');
		gameScreen.requestFocusInWindow();
	}

	/*
	 * HERO MOVE RIGHT
	 **/
	private void moveRightActionPerformed(java.awt.event.ActionEvent evt) {
		moveByChar('d');
		gameScreen.requestFocusInWindow();
	}

	/*
	 * HERO MOVE DOWN
	 */
	private void moveDownActionPerformed(java.awt.event.ActionEvent evt) {
		moveByChar('s');
		gameScreen.requestFocusInWindow();
	}

	// EXIT GAME
	private void exitGameActionPerformed(ActionEvent evt) {
		// exit game button
		System.exit(0);
	}
	
	private boolean checkIfSpaces(String path) {
		
		for(int i = 0; i < path.length(); i++) {
			if(path.charAt(i) == ' ') {
				return true;
			}
		}
		
		return false;
	}

	// SAVE GAME
	private void saveGameActionPerformed(ActionEvent evt) {
		JOptionPane popup = new JOptionPane();

		String path = JOptionPane.showInputDialog("Enter a file");

		if (path == null) {
			return;
		}
		
		if(checkIfSpaces(path)) {
			popup.showMessageDialog(this, "Your savefile cannot have spaces, or be empty.");
			gameScreen.requestFocusInWindow();
			return;
		}

		saveFile(path);

		gameScreen.requestFocusInWindow();
	}

	// LOAD GAME
	private void loadGameActionPerformed(ActionEvent evt) {
		JOptionPane popup = new JOptionPane();

		String path = JOptionPane.showInputDialog("Enter a file");

		if (path == null) {
			return;
		}
		
		else if(path.equals("custom ")) {
			popup.showMessageDialog(this, "You cannot load this file.");
			return;
		}
		
		loadFile(path);
		
		startNewGame();
	}

	// CREATE MAP
	private void createNewMapActionPerformed(ActionEvent evt) {
		editor = new OptionsFrame();
		customMapMade = false;
	}

	// Variables declaration - do not modify
	private javax.swing.JButton newGameButton;
	private OptionsFrame editor;
	private javax.swing.JButton moveLeftButton;
	private javax.swing.JButton moveUpButton;
	private javax.swing.JButton moveRightButton;
	private javax.swing.JButton moveDownButton;
	private javax.swing.JButton exitGameButton;
	private javax.swing.JComboBox<String> guardPersonalityBox;
	private javax.swing.JLabel gameStatusLabel;
	private SimpleGraphicsPanel gameScreen;
	private java.awt.Label numberOfOgresLabel;
	private java.awt.Label guardPersonalityLabel;
	private JFormattedTextField numberOfOgresBox;
	private JButton createNewMap;
	private JButton saveGameButton;
	private JButton loadGameButton;
	// End of variables declaration
}
