package Sprites;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import Panels.Main;

public class ImageGet {
	public static Image getImage(String path){
		Image tempImage = null;
		try{
			URL imageUrl = Main.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageUrl);
			}catch(Exception e){
				System.out.println("A error occurred: " + e.getMessage());
			}
			return tempImage;
		}
}
