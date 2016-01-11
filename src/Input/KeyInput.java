package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Panels.GamePanel;

public class KeyInput extends GamePanel implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	

