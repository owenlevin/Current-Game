package Panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Sprites.ImageGet;

public class Menu{
	int x, y;
	boolean clickclack;
	boolean bool;

	public void Title(){
		
	}
	public void Continue(){
		bool = true;
	}
	public void Start(){
		
	}
	public void Options(){
		
	}
	public void Exit(){
	 	System.exit(0);
	}
	
	public void menu(Graphics g3){
		Graphics2D g3d = (Graphics2D) g3;
		Rectangle mouse = new Rectangle(x, y, 10, 10);
		
		
		Rectangle titleBox = new Rectangle(300, 150, 200, 100);
		g3d.fillRect(300, 150, 200, 100);
		if(clickclack && mouse.intersects(titleBox)){
			System.out.println("titleBox");
		}
		Rectangle continueBox = new Rectangle(300, 300, 200, 100);
		g3d.drawImage(ImageGet.getImage("res/Start.png"), 300, 300, 200, 100, null);
		if(clickclack && mouse.intersects(continueBox)){
			//System.out.println("continueBox");
			Continue();
			
		}
		Rectangle startBox = new Rectangle(300, 300, 200, 100);
		g3d.drawImage(ImageGet.getImage("res/Options.png"), 300, 400, 200, 100, null);
		if(clickclack && mouse.intersects(continueBox)){
		System.out.println("Start");
		bool = true;
		
		}
		Rectangle optionBox = new Rectangle(300, 300, 200, 100);
		g3d.drawImage(ImageGet.getImage("res/Exit.png"), 300, 500, 200, 100, null);
		if(clickclack && mouse.intersects(continueBox)){
		}
		Rectangle exitBox = new Rectangle(300, 500, 200, 100);
		g3d.drawImage(ImageGet.getImage("res/Exit.png"), 300, 600, 200, 100, null);
		if(clickclack && mouse.intersects(exitBox)){
			System.out.println("exit");
			Exit();
		}

	}
	public void setMouseX(int mousex){
		x = mousex;
	}
	public void setMouseY(int mousey){
		y = mousey;
	}
	public void setMouseClick(boolean click){
		clickclack = click;
	}
	
	public boolean nextLevel(){
		return bool;
	}
	
	
}
