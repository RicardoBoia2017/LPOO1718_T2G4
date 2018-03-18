package dkeep.guI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*; 


public class SimpleGraphicsPanel extends JPanel {
	private BufferedImage wallpic;
	private BufferedImage guardpic;
	private BufferedImage heropic;
	private BufferedImage ogrepic;
	private BufferedImage clubpic;
	private BufferedImage keypic;
	private BufferedImage dollarpic;
	private BufferedImage stairspic;
	private BufferedImage doorpic;
	private BufferedImage sleepic;
	private BufferedImage stunnedpic;
	private char[][] map;
	  
	// Constructor, adding mouse and keyboard listeneres 
	public SimpleGraphicsPanel() { 
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("You touched me.");
			}
		});
		
		int i = 0;
		
	       try {                
	    	   wallpic = ImageIO.read(new File("Images/wall.gif")); i++;			//#0
	           guardpic = ImageIO.read(new File("Images/guard.gif")); i++; 			//#1
	           heropic = ImageIO.read(new File("Images/hero.gif")); i++; 			//#2
	           ogrepic = ImageIO.read(new File("Images/ogre.gif")); i++; 			//#3
	           clubpic = ImageIO.read(new File("Images/club.gif")); i++; 			//#4
	           doorpic = ImageIO.read(new File("Images/door.gif")); i++; 			//#5
	           keypic = ImageIO.read(new File("Images/key.gif")); i++; 				//#6
	           dollarpic = ImageIO.read(new File("Images/dollar.gif")); i++; 		//#7
	           stairspic = ImageIO.read(new File("Images/stairs.gif")); i++; 		//#8
	           sleepic = ImageIO.read(new File("Images/sleep.gif")); i++;  			//#9
	           stunnedpic = ImageIO.read(new File("Images/stunned.gif")); 			//#10
		     } 
	       catch (IOException ex) {
	    	   System.out.println("ERROR: Image " + i + " not found.");
		     }
	       
	       char[][] emptymap = {{' '}};
	       
	       map = emptymap;
	} 
	
	public void setMap(char [][] mapprint) {
		map = mapprint;
	}
	
	public char[][] getMap(){return map;}
	
	// Redraws the panel, only when requested by SWING
	@Override
	public void paintComponent(Graphics g) { 
		super.paintComponent(g); // limpa fundo ... 		
		
    	for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				switch(map[i][j]) {
				
				case 'H':
					g.drawImage(heropic, j*34, i*32, this);
					break;
					
				case 'g':
					g.drawImage(sleepic, j*34, i*32, this);
					break;
				
				case '8':
					g.drawImage(stunnedpic, j*34, i*32, this);
					break;
				
				case 'A':
					g.drawImage(heropic, j*34, i*32, this);
					break;
					
				case 'O':
					g.drawImage(ogrepic, j*34, i*32, this);
					break;
					
				case 'G':
					g.drawImage(guardpic, j*34, i*32, this);
					break;
					
				case '*':
					g.drawImage(clubpic, j*34, i*32, this);
					break;
					
				case '$':
					g.drawImage(dollarpic, j*34, i*32, this);
					break;
					
				case 'k':
					g.drawImage(keypic, j*34, i*32, this);
					break;
				
				case 'X':
					g.drawImage(wallpic, j*34, i*32, this);
					break;
					
				case 'I':
					g.drawImage(doorpic, j*34, i*32, this);
					break;
					
				case 'S':
					g.drawImage(stairspic, j*34, i*32, this);
					break;
				
				}
			}
		}
	}
}
