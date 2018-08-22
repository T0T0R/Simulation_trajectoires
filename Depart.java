import java.awt.*;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Depart extends JPanel {
	protected double[] posX;
	protected double[] posY;
	protected double[] posZ;

	//nb pixels pour un metre.
	protected int echelle = 3000;

	protected int tempsMesure = 1;

	protected double YMesure = 0.5;
	protected double ZMesure = 0.0;


	public void paint(Graphics g) {
		super.paint(g);
		Color c = g.getColor();
		g.setColor(Color.RED);

		//Axes
		g.fillRect(getWidth()/2,0,1,getHeight());
		g.fillRect(0,getHeight()/2, getWidth(), 1);
		//Graduations
		g.fillRect(getWidth()/2 -4, getHeight()/2 - echelle/10, 9, 1);
		g.fillRect(getWidth()/2+echelle/10, getHeight()/2-4, 1, 9);

		ArrayList particules = Main.giveParticules();

		g.setColor(Color.WHITE);

		for(int i = 0; i<particules.size(); i++){
			double x=(((Particule) particules.get(i)).getPositionsZ())[tempsMesure] - ZMesure;
			double y=(((Particule) particules.get(i)).getPositionsY())[tempsMesure] - YMesure;

			g.fillRect((int) (x*echelle) +getWidth()/2,((int) (y*(-1*(echelle)) +getHeight()/2)),1,1);
		}
		g.setColor(c);
	}
}
