import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Player extends Rectangle{

	State stateX;
	State stateY;
	
	Image blue;
	Image green;
	Image red;
	Image white;
	Image yellow;
	Image cyan;
	Image magenta;

	Color color;
	
	
	public Player(int xPos, int yPos, int w, int h, int velX, int velY, Color col){
		
		
		
		super(xPos, yPos, w, h);
		
		stateX = new State(x, velX, 0);
		stateY = new State(y, velY, 0);
		
		color = col;
		
		
		try{

			red = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/red.png"));

			blue =  Toolkit.getDefaultToolkit().getImage(getClass().getResource("/blue.png"));
			green =  Toolkit.getDefaultToolkit().getImage(getClass().getResource("/green.png"));
			cyan =  Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cyan.png"));
			magenta =Toolkit.getDefaultToolkit().getImage(getClass().getResource("/magenta.png"));
			white =  Toolkit.getDefaultToolkit().getImage(getClass().getResource("/white.png"));
			yellow =Toolkit.getDefaultToolkit().getImage(getClass().getResource("/yellow.png"));
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public Color getColor(){
		
		return color;
		
	}
	
	
	



	public void update(double time, double dt){
		
		
		this.stateX.x = this.stateX.x + this.stateX.v * dt;
		if(stateX.x >= 650){
			stateX.x = 650;
		}
		else if(stateX.x <= 0){
			stateX.x = 0;
		}
		
		
		this.stateY.x = this.stateY.x + this.stateY.v * dt;
		
		if(stateY.x >= 275){
			stateY.x = 275;
		}
		else if(stateY.x <= 0){
			stateY.x = 0;
		}
		x = (int) stateX.x;
		y = (int) stateY.x;
	}
	
	public void setXVel(double vel){
		stateX.v = vel;
	}
	
	public void setYVel(double vel){
		stateY.v = vel;
	}
	
	public void setXAcc(double acc){
		stateX.a = acc;
	}
	
	public void setYAcc(double acc){
		stateY.a = acc;
	}
	
	public void draw(Graphics g){
		Image currentImage = null;
		
		if(color.equals(Color.RED)){
			currentImage = red;
		}
		else if(color.equals(Color.BLUE)){
			currentImage = blue;
		}
		else if(color.equals(Color.GREEN)){
			currentImage = green;
		}
		else if(color.equals(Color.MAGENTA)){
			currentImage = magenta;
		}
		else if(color.equals(Color.WHITE)){
			currentImage = white;
		}
		else if(color.equals(Color.YELLOW)){
			currentImage = yellow;
		}else if(color.equals(Color.CYAN)){
			currentImage = cyan;
		} 
		
		g.drawImage(currentImage,x,y,width,height, null);
		
	}
	public void setColor(Color c){
		color = c;
	}
	
}
