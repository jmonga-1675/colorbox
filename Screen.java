import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Screen extends JPanel implements ActionListener, KeyListener{
	static AudioClip themeSong;
	static AudioClip death;
	static AudioClip menu;
	static Font font = new Font("Serif", Font.PLAIN, 20);
	static ArrayList<ColorBlock> colorBlocks;
	static ArrayList<Color> colorList;
	static Player player;
	static Timer timer;
	static double time;
	static double update;
	static Image background;
	static int imgY;

	public Screen(){
		
		imgY = -1600;
		
		JFrame frame = new JFrame("Color Blocks");
		 setOpaque(false);
		 frame.setBackground(Color.BLACK);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(700, 350);
		 frame.add(this);
		 frame.setVisible(true);
		
		
		update = 1;
		timer = new Timer((int) update, this);
		frame.addKeyListener(this);
		start();
	}
	
	public void start(){
		player = new Player(300, 300, 50, 50, 0, 0, colorList.get((int)( colorList.size() * Math.random())));
		
		themeSong.loop();
		colorBlocks.clear();
		
		time = 0;
		addBlockRow();
		timer.start();
	}
	
	public static void main(String[] args) {
		 
		 
		
		 colorList = new ArrayList<Color>();

			colorList.add(Color.BLUE);
			colorList.add(Color.RED);
			colorList.add(Color.GREEN);
			colorList.add(Color.WHITE);
			colorList.add(Color.YELLOW);
			colorList.add(Color.MAGENTA);
			colorList.add(Color.CYAN);
			
		
			colorBlocks = new ArrayList<ColorBlock>();
			player = new Player(300, 300, 50, 50, 0, 0, null);
			themeSong = Applet.newAudioClip(Screen.class.getResource("background.wav"));
			death = Applet.newAudioClip(Screen.class.getResource("death.wav"));
			menu = Applet.newAudioClip(Screen.class.getResource("menu.wav"));
			
			background = null;
			
			try {
				background =  Toolkit.getDefaultToolkit().getImage(Screen.class.getResource("background.png"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Screen screen = new Screen();
		 
			
		 
	}
	
	public static void assignColors() {

		ArrayList<Color> colorRands = new ArrayList<Color>();
		for(int i = 0; i < colorList.size(); i++){
			colorRands.add(colorList.get(i));
		}
		for(int i = 0; i < colorBlocks.size(); i++){
			int randomColorIndex = (int) (Math.random() * colorRands.size());
			colorBlocks.get(i).setColor(colorRands.get(randomColorIndex));
			colorRands.remove(randomColorIndex);
		}
	
	}
	
	public static void addBlockRow(){
		for(int i = 0; i <= 6; i++){
			colorBlocks.add(new ColorBlock(i * 100, -100,100,100, 200, null));
		}
		assignColors();
	}


	
	public void actionPerformed(ActionEvent e) {
	
		if(timer.isRunning()){
		
		double dt = update / 1000;
		
		time += dt;
		
		if(colorBlocks.get(0).state.x >= 400){
			colorBlocks.clear();
			addBlockRow();
			player.setColor( colorList.get((int)( colorList.size() * Math.random())));
		}
		
		
		imgY += 1;
		
		if(imgY >= 0){
			imgY = -1600;
		}
		
		
		player.update(time, dt);
		for(ColorBlock block: colorBlocks){
			block.update(time, dt);
		}
		
		
		
	   repaint();
	   
	   
	   for(ColorBlock block: colorBlocks){
		   if(player.intersects(block) && !player.getColor().equals(block.getColor())){
			   themeSong.stop();
			   death.play();
			   timer.stop();
		   }
	   }
	   
		}
	}
	
	
	
	
	
	
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, imgY, 700,2000, null);
		
		player.draw(g);
		for(ColorBlock block: colorBlocks){
			block.draw(g);
		}
		
		g.setColor(Color.GRAY);
        g.setFont(font);

        g.drawString("Score: " + (int)(time * 10), 10, 20); 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_DOWN){
			player.setYVel(0);
		}if(code == KeyEvent.VK_UP){
			player.setYVel(0);
		}
		if(code == KeyEvent.VK_LEFT){
			player.setXVel(0);
		}
		if(code == KeyEvent.VK_RIGHT){
			player.setXVel(0);
		}
		
	}
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_DOWN){
			player.setYVel(400);
		}if(code == KeyEvent.VK_UP){
			player.setYVel(-400);
		}
		if(code == KeyEvent.VK_LEFT){
			player.setXVel(-400);
		}
		if(code == KeyEvent.VK_RIGHT){
			player.setXVel(400);
		}
		if(code == KeyEvent.VK_R){
			start();
			
		}
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		 
		
	}


}
