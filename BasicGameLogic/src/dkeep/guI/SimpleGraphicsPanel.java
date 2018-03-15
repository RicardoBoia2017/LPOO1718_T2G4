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
		//addMouseListener(this); 
		//addMouseMotionListener(this); 
		
	       try {                
	           wallpic = ImageIO.read(new File("Images/wall.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       try {                
	           guardpic = ImageIO.read(new File("Images/guard.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       try {                
	           heropic = ImageIO.read(new File("Images/hero.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       
	       try {                
	           ogrepic = ImageIO.read(new File("Images/ogre.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       
	       try {                
	           clubpic = ImageIO.read(new File("Images/club.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       try {                
	           doorpic = ImageIO.read(new File("Images/door.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       try {                
	           keypic = ImageIO.read(new File("Images/key.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       try {                
	           dollarpic = ImageIO.read(new File("Images/dollar.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       try {                
	           stairspic = ImageIO.read(new File("Images/stairs.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       try {                
	           sleepic = ImageIO.read(new File("Images/sleep.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       try {                
	           stunnedpic = ImageIO.read(new File("Images/stunned.gif"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	       
	       char[][] emptymap = {{' '}};
	       
	       map = emptymap;
	} 
	
	public void setMap(char [][] mapprint) {
		map = mapprint;
	}
	
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
