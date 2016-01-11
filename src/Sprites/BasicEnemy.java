package Sprites;

public class BasicEnemy {
private int x,y,width,height;
int hit, health;
boolean hitted;
public BasicEnemy(int inX, int inY, int inWidth, int inHeight, int tempHealth){
	x = inX;
	y = inY;
	height = inHeight;
	width = inWidth;
	health = tempHealth;
}
public boolean getHit(){
	return hitted;
}
public void setHit(boolean in){
	hitted = in;
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
	public void plusX(int tempX){
		x += tempX;
	}
	public void plusY(int tempY){
		y += tempY;
	}
	public int getHeight(){
		  return height;
	}
	public int getWidth(){
		  return width;
	}
		public void setHeight(int tempHeight){
			x = tempHeight;
		}
		public void setWidth(int tempWidth){
			y = tempWidth;
		}
		public void hit(){
			health -= 1;
		}
		public boolean dead(){
			if(health <= 0){
				return true;
			}else{
				return false;
			}
		}
		public int getHealth(){
			return health;
		}
}
