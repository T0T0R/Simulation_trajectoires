/**
 * Created by LANGLARD on 21/08/2018.
 */
public class Proton extends Particule{

	private long numero;
	public static int nombreProtons = 0;
	private static int nombreProtonsBis = 0;


	/******ACCESSEURS******/

	public long getNumero(){
		return numero;
	}


	/******METHODES******/

	//Description
	public String toString() {
		return "Proton numero " + this.getNumero() + " : position(" + posX + ", " + posY + ", " + posZ + "), vitesse(" +
				this.vitesseX + ", " + this.vitesseY + ", " + this.vitesseZ + "), force(" + this.getForcesX() + ", " + this.getForcesY() + ", " + this.getForcesZ() + ");";
	}
	/******CONSTRUCTEUR PAR DEFAUT******/
	public Proton(){
		masseParticule = 1.673E-27;
		chargeParticule = 1.602E-19;

		vitesseX = 0.0;
		vitesseY = 0.0;
		vitesseZ = 0.0;

		posX = 0;
		posY = 0;
		posZ = 0;

		nombreProtons++;
		nombreProtonsBis++;
		System.out.println("Creation du proton no " + nombreProtonsBis);
		numero = nombreProtonsBis;
	}

	/******CONSTRUCTEUR AVEC PARAMETRES******/
	public Proton(double PosX, double PosY, double PosZ,  Temps temps){
		masseParticule = 1.673E-27;
		chargeParticule = 1.602E-19;

		vitesseX = 0.0;
		vitesseY = 0.0;
		vitesseZ = 0.0;

		posX = PosX;
		posY = PosY;
		posZ = PosZ;

		positionsX = new double[(int)temps.getNombreMesuresTotal()];
		positionsY = new double[(int)temps.getNombreMesuresTotal()];
		positionsZ = new double[(int)temps.getNombreMesuresTotal()];

		nombreProtons++;
		nombreProtonsBis++;
		System.out.println("Creation du proton no " + nombreProtonsBis);
		numero = nombreProtonsBis;
	}

	public Proton(double PosX, double PosY, double PosZ, double vX, double vY ,double vZ, Temps temps){
		masseParticule = 1.673E-27;
		chargeParticule = 1.602E-19;

		vitesseX = vX;
		vitesseY = vY;
		vitesseZ = vZ;

		posX = PosX;
		posY = PosY;
		posZ = PosZ;

		positionsX = new double[(int)temps.getNombreMesuresTotal()];
		positionsY = new double[(int)temps.getNombreMesuresTotal()];
		positionsZ = new double[(int)temps.getNombreMesuresTotal()];

		nombreProtons++;
		nombreProtonsBis++;
		System.out.println("Creation du proton no " + nombreProtonsBis);
		numero = nombreProtonsBis;
	}
}
