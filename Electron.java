public class Electron extends Particule{

	private long numero;
	public static int nombreElectrons = 0;
	private static int nombreElectronsBis = 0;


	/******ACCESSEURS******/

	//Retourne le nombre d'electrons instancies
	public long getNumero(){
		return numero;
	}



	/******METHODES******/

	//Description
	public String toString() {
		return "Electron numero " + this.getNumero() + " : position(" + posX + ", " + posY + ", " + posZ + "), vitesse(" +
				this.vitesseX + ", " + this.vitesseY + ", " + this.vitesseZ + "), force(" + this.getForcesX() + ", " + this.getForcesY() + ", " + this.getForcesZ() + ");";
	}
	/******CONSTRUCTEUR PAR DEFAUT******/
	public Electron(){
		masseParticule = 9.109E-31;
		chargeParticule = -1.602E-19;

		vitesseX = 0.0;
		vitesseY = 0.0;
		vitesseZ = 0.0;

		posX = 0;
		posY = 0;
		posZ = 0;

		nombreElectrons++;
		nombreElectronsBis++;
		System.out.println("Creation de l'electron no " + nombreElectronsBis);
		numero = nombreElectronsBis;
	}

	/******CONSTRUCTEUR AVEC PARAMETRES******/
	public Electron(double PosX, double PosY, double PosZ,  Temps temps){
		masseParticule = 9.109E-31;
		chargeParticule = -1.602E-19;

		vitesseX = 0.0;
		vitesseY = 0.0;
		vitesseZ = 0.0;

		posX = PosX;
		posY = PosY;
		posZ = PosZ;

		positionsX = new double[(int)temps.getNombreMesuresTotal()];
		positionsY = new double[(int)temps.getNombreMesuresTotal()];
		positionsZ = new double[(int)temps.getNombreMesuresTotal()];

		nombreElectrons++;
		nombreElectronsBis++;
		System.out.println("Creation de l'electron no " + nombreElectronsBis);
		numero = nombreElectronsBis;
	}

	public Electron(double PosX, double PosY, double PosZ, double vX, double vY ,double vZ, Temps temps){
		masseParticule = 9.109E-31;
		chargeParticule = -1.602E-19;

		vitesseX = vX;
		vitesseY = vY;
		vitesseZ = vZ;

		posX = PosX;
		posY = PosY;
		posZ = PosZ;

		positionsX = new double[(int)temps.getNombreMesuresTotal()];
		positionsY = new double[(int)temps.getNombreMesuresTotal()];
		positionsZ = new double[(int)temps.getNombreMesuresTotal()];

		nombreElectrons++;
		nombreElectronsBis++;
		System.out.println("Creation de l'electron no " + nombreElectronsBis);
		numero = nombreElectronsBis;
	}
}
