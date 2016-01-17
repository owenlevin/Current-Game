package Panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.JPanel;

import Input.KeyInput;
import Sprites.Background;
import Sprites.BasicEnemy;
import Sprites.Bullet;
import Sprites.Button;
import Sprites.ImageGet;
import Sprites.Platform;
import Sprites.Player;
public class GamePanel extends JPanel implements Runnable, ActionListener{
	
	private static final long serialVersionUID = 1L;
	//ImageGet image = new ImageGet();
	
	final int WIDTH = 600;
	final int HEIGHT = 800;
	
	private int FPS = 60;//variable for aimed fps
	private long targetTime = 1000 / FPS;//target refresh time
	private Thread thread;//game thread
	public boolean isRunning = true;//check to see if the game is running
	
	long start, elapsed, wait;//long for start elapsed and wait
	int backX = 0, backY = 0, backWidth = 900, backHeight = 650;
	int x = 300 ,y = 450;

	double counter = 0;
	int number = 1;
	double animationSpeed = .2;
	
	static KeyInput in = new KeyInput();
	
	Image heroImage = ImageGet.getImage("res/conceptfixed1.png");
	
	int camX, camY;
	
	boolean isJump;
	
	public boolean bRight, bLeft, bUp, bDown;
	
	int backNumber;//number of backgrounds
	
	public int screenSizeX = 800;//x size of the screen
	public int screenSizeY = 600;//y size of the screen
	
	int playerWidth = 70;
	int playerHeight = 200;
	
	public boolean shoot;
	
	public int mouseX, mouseY;
	
	boolean rtouch = false;
	boolean ltouch = false;
	
	int enemyWidth = 80;
	int enemyHeight = 80;
	
	int jumpHeight = 35;
	int grav = 30;
	
	int backCount;
	
	boolean backRendered;
	
	double fireRate;
	double shootCount;
	double allowShoot;
	
	int gravity = 8;
	
	boolean isBounce;
	
	public ArrayList<BasicEnemy> basicEnemy = new ArrayList<>();
	ArrayList<Platform> platforms = new ArrayList<>();
	ArrayList<Bullet> bullets = new ArrayList();
	
	Button button = new Button(1, 1, 1, 1);
	boolean level1;
	boolean level2;
	
	boolean ttouch;
	
	int health;
	boolean hitted = false;
	int ex, ey;
	int platX, platY;
	int platWidth = 100;
	int platHeight = 100;
	public int level;
	
	boolean allDead;
	
	
	ArrayList<Background> backgrounds = new ArrayList<Background>();
	
	int levelNum = 3;
	boolean levels[] = new boolean[levelNum];

	boolean idle;
	int idleTime;
	Player hero = new Player();
	static Menu menu = new Menu();
	public GamePanel(){
		initLevels();
		setTouch();
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				menu.setMouseClick(false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				menu.setMouseClick(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseX = e.getX();
				mouseY = e.getY();
				menu.setMouseX(mouseX);
				menu.setMouseY(mouseY);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	addKeyListener(new KeyAdapter(){
		
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_RIGHT){
					bRight = true;
				}
				if(key == KeyEvent.VK_LEFT){
					bLeft = true;
				}
				if(key == KeyEvent.VK_UP){
					bUp = true;
				}
				if(key == KeyEvent.VK_DOWN){
					bDown = true;
				}
				if(key == KeyEvent.VK_E){
					shoot = true;
				}
				idle = false;
				
			}
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				double startIdle = System.currentTimeMillis();
				if(key == KeyEvent.VK_RIGHT){
					bRight = false;
				}
				if(key == KeyEvent.VK_LEFT){
					bLeft = false;
				}
				if(key == KeyEvent.VK_UP){
					bUp = false;
				}
				if(key == KeyEvent.VK_DOWN){
					bDown = false;
				}
				if(key == KeyEvent.VK_E){
					shoot = false;
				}
				idle = true;
			}
			
		});
		initBoard();
		setPreferredSize(new Dimension(HEIGHT, WIDTH));
		setDoubleBuffered(true);
		repaint();
		start();
	}

void initBoard() {
    setFocusable(true); // VERY IMPORTANT!
}

	public void setTouch(){
		for(int i = 0; i < platforms.size(); i++){
			Platform plat = platforms.get(i);
			ltouch = plat.getLTouch();
			rtouch = plat.getRTouch();
		}
	}
public void start(){
	isRunning = true;
	thread = new Thread(this);
	thread.start();
}
public void run() {
	while(isRunning){
		start = System.nanoTime();
		repaint();
		checkKey();
		camera();
		jump();
		elapsed = System.nanoTime() - start;
		wait = targetTime - elapsed / 1000000000;
		if(wait <= 0){
			wait = 5;
		}
		try {
			Thread.sleep(wait);
			repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	
	}
	}
 
public void checkKey(){
	if(shoot){
		shootCount += .3;
		if(shootCount >= 1){
			fire(2);
			shootCount = 0;		
		}
	}
	if(bRight){
		ltouch = false;
		counter();
		if(!rtouch){
		x += 5;
		}
	}
	if(bLeft && x > 300){
		rtouch = false;
		counter();
		if(!ltouch){
		x -= 5;
	}
	}
	if(bUp){
	     isJump = true;
	}
}



public void jump(){
	rtouch =false;
	ltouch =false;
	if(isJump){
	y -= jumpHeight - grav;
	grav += 2;
	}
}

public class Levels{
	public Levels(){
		
	}
		
public void startMenu(Graphics g2){
		menu.menu(g2);
		if(menu.nextLevel()){
			level = 1;
		}
		
	}


public void level1(Graphics g3){
	Graphics2D g3d = (Graphics2D) g3;
	if(levels[1]){
		enemy(705, 300, 100);
		enemy(800, 300, 100);
		enemy(1000, 300, 100);
		enemy(1200, 300, 100);
		platform(850, 550 - platHeight, platHeight, platWidth);
		platform(1200, 280 - platHeight, platHeight, platWidth);
		platform(1300, 280 - platHeight, platHeight, platWidth);
		levels[1] = false;
		level1 = true;
	}

		levelPhysics(g3);
		if(isDead()){
			level++;
		}
}
public void level2(Graphics g3){
	Graphics2D g3d = (Graphics2D) g3;
	if(levels[2]){
		clearPlatform();
		levels[2] = false;
	}

		levelPhysics(g3);
}
}



public void gravity(){
	y += gravity;
}

public void camera(){
	camX = x - screenSizeX / 2;
	camY = y - screenSizeY / 2;
}

public void platform(int startX, int startY, int width, int height){
	Platform platform = new Platform(startX, startY, width, height, false, false);
	platforms.add(platform);
}
public void renderPlatform(Graphics g){
	for(int i = 0; i < platforms.size(); i++){
	
		
		Rectangle player = new Rectangle(x, y, playerWidth, platHeight);
		Rectangle bot = new Rectangle(x, y + playerHeight, playerWidth, 10);
		Rectangle right = new Rectangle(x + playerWidth, y, 1, playerHeight);
		Rectangle left = new Rectangle(x, y, 1, playerHeight);
		Rectangle top = new Rectangle(x, y, playerWidth, 1);
		
		Platform plat = platforms.get(i);
		platX = plat.getX();
		platY = plat.getY();
		platWidth = plat.getWidth();
		platHeight = plat.getHeight();
		g.drawImage(ImageGet.getImage("res/brick.png"), platX, platY, platWidth, platHeight, null);
		if(bot.intersects(plat.top())){
			grav = 0;
			isJump = false;
			y = platY - playerHeight;
		}
		if(right.intersects(plat.left())){
			x = plat.getX() - playerWidth;
			rtouch = true;
		}
		if(left.intersects(plat.right())){
			x = plat.getX() + platWidth;
			ltouch = true;
		}
		if(top.intersects(plat.bot())){
			y = platY + platHeight;
			grav += 4;
		}
	}
}
public void renderEnemy(Graphics g9){
	Graphics2D g9d = (Graphics2D) g9;
		
	for(int i = 0; i < basicEnemy.size(); i++){
		Rectangle enemyRect = new Rectangle(ex, ey, enemyWidth, enemyHeight);
		Rectangle ground = new Rectangle(0, 550, 60000, 1);

		
		BasicEnemy enemy = basicEnemy.get(i);
		ex = enemy.getX();
		ey = enemy.getY();
		health = enemy.getHealth();
		enemy.plusY(gravity);
		g9d.drawImage(ImageGet.getImage("res/Boron.png"), ex, ey, enemyWidth, enemyHeight, null);
		if(ground.intersects(enemyRect)){
		 enemy.setY(472);	
		}
		
		//g9d.drawImage(ImageGet.getImage("res/skyline.png"),boron.getX(), boron.getY(), 10, 10, null);
	}
}


public void counter(){
	counter += animationSpeed;
	if(counter >= 1){
		number += 1;
		counter = 0;
	}
	
	if(number > 2){
		number = 1;
	}
}
public void animation(){
	heroImage = ImageGet.getImage("res/hero" + number + ".png");
	
}
public void idle(Graphics g4){
	Graphics2D g4d = (Graphics2D) g4;
	if(idle){

		idleCount(true);
		if(idleCount(true) > 300){
			heroImage = ImageGet.getImage("res/Idle " + secondcounter + ".png");
			System.out.println(number);
		}
	}else{
		idleCount(false);
	}
}
double firstcounter;
int secondcounter;
public int idleCount(boolean in){
	if(in){
	firstcounter += .1;
	}
	if(firstcounter > 4){
		secondcounter++;
	}
	
	if(!in){
		firstcounter = 0;
		secondcounter = 0;
	}
	return secondcounter;
	
}
public void enemy(int x, int y, int health){
    BasicEnemy enemy = new BasicEnemy(x, y, 50, 50, health);  
	basicEnemy.add(enemy);
}
public void enemy(int x, int y){
    BasicEnemy enemy = new BasicEnemy(x, y, 50, 50, 100);  
	basicEnemy.add(enemy);
	
}

public void renderBasicEnemy(Graphics g5){
	Graphics2D g25d = (Graphics2D) g5;
	for(int i = 0; i < bullets.size(); i++){
		//System.out.println(i);
		Bullet bullet = bullets.get(i);
	for(int i1 = 0; i1 < basicEnemy.size(); i1++){
		BasicEnemy enemy = basicEnemy.get(i1);
		Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), 200, 200);
		Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), 200, 200);
		if(enemyRect.intersects(bulletRect)){
			if(!enemy.getHit()){
				enemy.hit();
			}
			if(enemy.dead()){
				basicEnemy.remove(i1);
			}
			if(basicEnemy.size() == 0){
				allDead = true;
			}
			
			enemy.setHit(true);
		}else{
			enemy.setHit(false);
		}
		
		//System.out.println(i1);
	}
	
		
	}
	
}

public void bullet(Graphics g4){
	Graphics2D g5d = (Graphics2D) g4;
	for(int i = 0; i < bullets.size(); i++){
		 Bullet bullet = bullets.get(i);
      	 g5d.fillRect(bullet.getX(), bullet.getY() + 150, 10, 10);
		//	Enemy enemy = new Enemy(400, 500);
			///renderEnemies(100, 100);
		//if(enemies.isDead(bullets.get(i))){
		//	enemies.remove(i);
		//}else{
			
	//	}
		
		if(bullet.getVisible()){
			bullet.move(true);
		}else{
			bullets.remove(i);
		//	System.out.println("Deleted");
		}
	//}
	}
	}
public void fire(int recoil){
	
	//if(shootAble == true){
	Bullet tempBullet = new Bullet(x, y, 10, 10, recoil);
	bullets.add(tempBullet);
	//}
}

public void gameOver(Graphics g){
	boolean gameOver = true;
	
}
/* public void enemyCollision(Graphics g){
	for(int i = 0; i < borons.size(); i++){
		Rectangle rect = new Rectangle(borons.get(i).getX(), borons.get(i).getY(), enemyWidth, enemyHeight);
		Rectangle bot = new Rectangle(x, y + playerHeight, playerWidth, 10);
		Rectangle right = new Rectangle(x + 100, y, 1, 90);
		
		if(rect.intersects(bot)){
			isBounce = true;
		}
		if(rect.intersects(right)){
		//	level1 = false;
			//level2 = true;
		//	initLevel2();
		}
	}
}
*/
public void ground(){
	Rectangle ground = new Rectangle(0, 550, 60000, 1);
	Rectangle player = new Rectangle(x, y, playerWidth, playerHeight);
	Rectangle enemy = new Rectangle(ex, ey, enemyWidth, enemyHeight);
	if(ground.intersects(player)){
		y = 550 - playerHeight;
		grav = 0;
		isJump = false;
	}

}
public void infBack(Graphics g4){
	Graphics2D g4d = (Graphics2D) g4;

	
	if(x % 600 == 0){
		backgrounds.add(new Background(ImageGet.getImage("res/background.png"), x, y, HEIGHT, WIDTH, 600));
		System.out.println("Draw");
	}
	for(int i = 0; i < backgrounds.size(); i++){
		g4d.drawImage(backgrounds.get(i).getImage(), backgrounds.get(i).getTrueX(), 0, backgrounds.get(i).getHeight(), backgrounds.get(i).getWidth(), this);
	}
}

public void levelPhysics(Graphics g3){
	Graphics2D g3d = (Graphics2D) g3;
	g3d.translate(-camX - 100, 0);
	gravity();
	ground();
	
	infBack(g3d);
	renderBasicEnemy(g3d);
	renderPlatform(g3d);
	bullet(g3d);
	animation();
	renderEnemy(g3d);
	idle(g3d);
	backNumber = 2;
	g3d.drawImage(heroImage, x, y, playerWidth, playerHeight, null);
	
}
public void levels(Graphics g3){
	Graphics2D g3d = (Graphics2D) g3;
	Levels levels1 = new Levels();
	
	switch(level){
	case 0: levels1.startMenu(g3d);
	break;
	case 1: levels1.level1(g3d);
	break;
	case 2: levels1.level2(g3d);
	break;
	}	

	
}
public void render(Graphics g2){
	Graphics2D g2d = (Graphics2D) g2;
	levels(g2d);	
	
}
	


public void paint(Graphics g){
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	render(g);
}
//public void paintComponent(Graphics g){
//super.paintComponent(g);
//render(g);
//repaint();
//}


	public void initLevels(){
		for(int i1 = 0; i1 < 1; i1++){
		for(int i = 0; i < levels.length; i++){
			boolean temp = true;
			levels[i] = temp;
		}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	public int getPlatHeight(){
		return platHeight;
	}
	public int getPlatWidth(){
		return platWidth;
	}
	public boolean isDead(){
		return allDead;
	}
	public void clearPlatform(){
		for(int i = platforms.size() - 1; i >= 0; i--){
			platforms.remove(i);
		}
	}
	public void clearEnemy(){
		for(int i = basicEnemy.size() - 1; i >= 0; i--){
			basicEnemy.remove(i);
		}
	}	
	

}

