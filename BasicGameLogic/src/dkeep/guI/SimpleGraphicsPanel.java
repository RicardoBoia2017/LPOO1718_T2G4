package dkeep.guI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import dkeep.logic.Map; 


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
	private BufferedImage superheropic;
	private BufferedImage emptypic;
	private Map map;
	  
	private void loadImages() {
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
	           stunnedpic = ImageIO.read(new File("Images/stunned.gif")); i++;      //#10			
	           superheropic = ImageIO.read(new File("Images/superhero.gif")); i++;	//#11
		     } 
	       catch (IOException ex) {
	    	   System.out.println("ERROR: Image " + i + " not found.");
	    	   System.exit(1);
		     }
	}
	
	// Constructor, adding mouse and keyboard listeneres 
	public SimpleGraphicsPanel() { 
		loadImages();
	    map = new Map();
	} 
	
	public void setMap(Map mapprint) {
		map = mapprint;
	}
	
	public Map getMap(){return map;}
	
	private BufferedImage getImageByChar(char determinant) {
		switch(determinant) {
		
		case 'H':
			return heropic;
			
		case 'g':
			return sleepic;
		
		case '8':
			return stunnedpic;
		
		case 'A':
			return heropic;
			
		case 'O':
			return ogrepic;
			
		case 'G':
			return guardpic;
			
		case '*':
			return clubpic;
			
		case '$':
			return dollarpic;
			
		case 'k':
			return keypic;
		
		case 'X':
			return wallpic;
			
		case 'I':
			return doorpic;
			
		case 'S':
			return stairspic;
		
		case 'K':
			return superheropic;

		}
		
		return null;
	}
	
	// Redraws the panel, only when requested by SWING
	@Override
	public void paintComponent(Graphics g) { 
		super.paintComponent(g); // limpa fundo ...
		
		char[][] mapmatrix = map.getMatrix();
		
    	for(int i = 0; i < mapmatrix.length; i++) {
			for(int j = 0; j < mapmatrix[i].length; j++) {
				BufferedImage bi = getImageByChar (mapmatrix[i][j]);
				g.drawImage(bi, j*34, i*32, this);
			}
		}
	}
}
