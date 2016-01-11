package Sprites;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Button {
	int x, y, width, height;
	public Button(int inX, int inY, int inWidth, int inHeight){
		x = inX;
		y = inY;
		width = inWidth;
		height = inHeight;
		
		Rectangle rect = new Rectangle(x, y, width, height);
		
	}
	public int getX(){
		  return x;
	}
	public int getY(){
		  return y;
	}
	 
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

	public void setX(int tempX){
			x = tempX;
	}
	public void setY(int tempY){
			y = tempY;
	}
	public void setWidth(int tempWidth){
		width = tempWidth;
	}
	public void setHeight(int tempHeight){
		height = tempHeight;
	}
	
	
}
