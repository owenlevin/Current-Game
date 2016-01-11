package Panels;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Input.KeyInput;
import Sprites.ImageGet;

public class Main extends JFrame{

	private static final long serialVersionUID = -6944461607567573729L;

	public static void main(String[] args){
		JFrame frame = new JFrame();	
		frame.setTitle("Game");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(new GamePanel(), BorderLayout.CENTER);
		frame.addKeyListener(new KeyInput());
		frame.pack();
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setIconImage(ImageGet.getImage("res/brick.png"));
	}
	
}
