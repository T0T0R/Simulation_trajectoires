import java.awt.*;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	protected double[] posX;
	protected double[] posY;
	protected double[] posZ;

	//nb pixels pour un metre.
	protected int echelleX = 400;
	protected int echelleY = 400;


	public void paint(Graphics g) {
		super.paint(g);
		Color c = g.getColor();
		//Axes
		g.setColor(Color.BLACK);
		g.fillRect(getWidth()/2,0,1,getHeight());
		g.fillRect(0, getHeight()/2, getWidth(), 1);

		//Graduations
		for (int i = -2*getHeight()/echelleY; i<getHeight()/echelleY; i++){
			//Axe Ordonnees (Y)
			g.fillRect(getWidth()/2-4, getHeight()/2-i*echelleY, 9, 1);
		}
		for (int i = -2*getWidth()/echelleX; i<getWidth()/echelleX; i++){
			//Axe Abcsisses (X)
			g.fillRect(getWidth()/2-i*echelleX, getHeight()/2 -4, 1, 9);
		}

		ArrayList particules = Main.giveParticules();

		g.setColor(Color.WHITE);

		for(int i = 0; i<particules.size(); i++){
			//Gestion des couleurs (une pour chaque particule) HSB
			g.setColor(new Color(Color.HSBtoRGB( ((float) i)/((float) particules.size()) , 0.70f, 0.75f)));

			for(int k = 0; k<(((Particule) particules.get(i)).getPositionsX()).length; k++){
				double x=(((Particule) particules.get(i)).getPositionsX())[k];
				double y=(((Particule) particules.get(i)).getPositionsY())[k];

				g.fillRect((int) (x*echelleX) +getWidth()/2,((int) (y*(-1*(echelleY)) +getHeight()/2)),1,1);

			}
		}
		g.setColor(c);
	}
}