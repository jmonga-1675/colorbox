import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ColorBlock extends Rectangle {
	
		State state;
		Color color;
		
		public ColorBlock(int xPos, int yPos, int w, int h, double v, Color col){
			super(xPos, yPos, w, h);
			
			
			state = new State(y,v,0);

			color = col;
			
		}
		
		public void setColor(Color c){
			color = c;
		}
	
		public void update(double time, double changeTime){
		state.x = state.x + state.v*changeTime;
			
		y = (int) state.x;
		}
		public void draw(Graphics g){
			g.setColor(color);
			g.fillRect(x, y, width, height);
			
		}
		public double getSpeed(){
			return state.v;
		}
		
		public Color getColor(){
			return color;
		}

}
