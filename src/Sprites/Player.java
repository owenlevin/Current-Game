package Sprites;

import java.awt.Rectangle;

public class Player {
  private int x,y;
  int width, height;
  public Player(){
	//  Rectangle rect = new Rectangle();
   
  }
  public int getX(){
	  return x;
  }
  public int getY(){
	  return y;
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
public int getWidth(){
	return width;
}
public int getHeight(){
	return height;
}

}
