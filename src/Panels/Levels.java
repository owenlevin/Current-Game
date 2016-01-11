package Panels;

public class Levels {
	public int level;
	Menu menu = new Menu();
	public void setLevel(int inLevel){
		level = inLevel;
	}
	public int getLevel(){
		return level;
	}
	public void levelGet(){
		if(menu.nextLevel()){
			setLevel(1);
			System.out.println("Nextlevel");
		}
	}
}
