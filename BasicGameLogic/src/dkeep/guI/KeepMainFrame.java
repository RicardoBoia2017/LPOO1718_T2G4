/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dkeep.guI;

import java.awt.event.ActionEvent;
import java.text.NumberFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

import dkeep.logic.Game;

/**
 *
 * @author luis
 */
public class KeepMainFrame extends javax.swing.JFrame {
	
	//by default ..
	
	private int numberOfOgres = 1;
	
	private String guardPersonality = "Rookie";
	
	private Game game;
	
	private Boolean newgamestarted = false;
	
    /**
     * Creates new form KeepMainFrame
     */
    public KeepMainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
    	
    	//formating the textfield for integer number of ogres
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(5);
        formatter.setAllowsInvalid(false);

        label1 = new java.awt.Label();
        nOgresBox = new JFormattedTextField(formatter);
        label2 = new java.awt.Label();
        jComboBox1 = new javax.swing.JComboBox<>();
        NewGame = new javax.swing.JButton();
        moveLeft = new javax.swing.JButton();
        moveUp = new javax.swing.JButton();
        moveRight = new javax.swing.JButton();
        moveDown = new javax.swing.JButton();
        ExitGame = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GameScreen = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 500));

        label1.setText("Number of ogres:");

        nOgresBox.setName(""); // NOI18N
        nOgresBox.setSelectionEnd(-1);

        label2.setText("Guard personality");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rookie", "Drunken", "Suspicious" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        NewGame.setText("New Game");
        NewGame.setToolTipText("");
        NewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        moveLeft.setText("Left");
        moveLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        moveUp.setText("Up");
        moveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });


        moveRight.setText("Right");
        moveRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        moveDown.setText("Down");
        moveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        ExitGame.setText("Exit Game");
        ExitGame.setToolTipText("");
        ExitGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setText("<Game status goes here>");
        jLabel1.setToolTipText("");

        GameScreen.setEditable(false);
        GameScreen.setColumns(20);
        GameScreen.setFont(new java.awt.Font("Courier 10 Pitch", 0, 20)); // NOI18N
        GameScreen.setRows(5);
        jScrollPane1.setViewportView(GameScreen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nOgresBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(108, 108, 108))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(NewGame, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ExitGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(moveDown))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(moveLeft)
                                        .addGap(18, 18, 18)
                                        .addComponent(moveRight)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(moveUp)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nOgresBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NewGame)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(moveUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(moveLeft)
                            .addComponent(moveRight))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveDown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ExitGame))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>       
    
    /*
     * CONVERT MAP TO STRING
     * */
    private String convertmaptoString(char[][] mapprint) {
		
    	String s = "";
    	
    	for(int i = 0; i < mapprint.length; i++) {
			for(int j = 0; j < mapprint[i].length; j++) {
				s += mapprint[i][j]; 
				
				if(j == mapprint[i].length - 1) {
					s += "\n";
				}
			}
		}
    	
    	return s;
    }
    
    /*
     * GUARD PERSONALITY
     * */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:

        Object selected = jComboBox1.getSelectedItem();
        
        if(selected.toString().equals("Rookie")) {
        	guardPersonality = "Rookie";
        }
       
        else if(selected.toString().equals("Drunken")) {
        	guardPersonality = "Drunken";
        }
        
        else if(selected.toString().equals("Suspicious")) {
        	guardPersonality = "Suspicious";
        }
    } 
    
    /*
     * NEW GAME BUTTON
     * */
	private void jButton1ActionPerformed(ActionEvent evt) {
		//to make a new game we need to know the number of ogres and guardPersonaility
		
		//guard personality has already been selected in the combobox (by omittion it  will be rookie)
		
		//as for number of ogres?
		
		if(nOgresBox.getValue() != null) {
			//if the text field is empty, it will by omission be 1 ogre
			
			//otherwise ..
			String s = nOgresBox.getText();
			 
			numberOfOgres = Integer.parseInt(s);
		}
		
		//call game constructor with Game(int numberOfOgres, String guardPersonality)
		game = new Game(numberOfOgres, guardPersonality);
		//game.getGuard().setMovementBlocker(true);
		nOgresBox.setValue(null);
		
		//printing out the current map using a custom function that converts it to string first
		char[][] mapprint;
		
		mapprint = game.getmap();
		
		GameScreen.setText(convertmaptoString(mapprint));
		
		newgamestarted = true;
		
		jLabel1.setText("The game is running.");
	}

	 /*
     * HERO MOVE LEFT
     * */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
    	if(newgamestarted && game.getGameState().equals("Running")) {
    		game.updateGame('a');
    		
    		char[][] mapprint;
    		
    		mapprint = game.getmap();
    		
    		GameScreen.setText(null);
    		
    		GameScreen.setText(convertmaptoString(mapprint));
    	} else {
    		jLabel1.setText("The game is over.");
    		moveLeft.setEnabled(false);
    		moveRight.setEnabled(false);
    		moveUp.setEnabled(false);
    		moveDown.setEnabled(false);
    	}
    }
    
	/*
    * HERO MOVE UP
    * */
	private void jButton3ActionPerformed(ActionEvent evt) {
		
		if(newgamestarted && game.getGameState().equals("Running")) {
    		game.updateGame('w');
    		
    		char[][] mapprint;
    		
    		mapprint = game.getmap();
    		
    		GameScreen.setText(null);
    		
    		GameScreen.setText(convertmaptoString(mapprint));
    	} else {
    		jLabel1.setText("The game is over.");
    		moveLeft.setEnabled(false);
    		moveRight.setEnabled(false);
    		moveUp.setEnabled(false);
    		moveDown.setEnabled(false);
    	}
	}

	/*
    * HERO MOVE RIGHT
    **/
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if(newgamestarted && game.getGameState().equals("Running")) {
    		game.updateGame('d');
    		
    		char[][] mapprint;
    		
    		mapprint = game.getmap();
    		
    		GameScreen.setText(null);
    		
    		GameScreen.setText(convertmaptoString(mapprint));
    	} else {
    		jLabel1.setText("The game is over.");
    		moveLeft.setEnabled(false);
    		moveRight.setEnabled(false);
    		moveUp.setEnabled(false);
    		moveDown.setEnabled(false);
    	}
    }                                        

	/*
    * HERO MOVE DOWN
    * */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if(newgamestarted && game.getGameState().equals("Running")) {
    		game.updateGame('s');
    		
    		char[][] mapprint;
    		
    		mapprint = game.getmap();
    		
    		GameScreen.setText(null);
    		
    		GameScreen.setText(convertmaptoString(mapprint));
    	} else {
    		jLabel1.setText("The game is over.");
    		moveLeft.setEnabled(false);
    		moveRight.setEnabled(false);
    		moveUp.setEnabled(false);
    		moveDown.setEnabled(false);
    	}
    }
    
	private void jButton6ActionPerformed(ActionEvent evt) {
		// exit game button
		System.exit(0);
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KeepMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KeepMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KeepMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KeepMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KeepMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton NewGame;
    private javax.swing.JButton moveLeft;
    private javax.swing.JButton moveUp;
    private javax.swing.JButton moveRight;
    private javax.swing.JButton moveDown;
    private javax.swing.JButton ExitGame;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea GameScreen;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private JFormattedTextField nOgresBox;
    // End of variables declaration                   
}

