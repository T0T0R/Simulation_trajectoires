public class Temps extends Item {

	protected double tempsInitial;
	protected double tempsPrecedent;
	protected double tempsActuel;
	protected double deltaTemps; //Intervalle de temps
	protected double tempsFinal; //Fin de la mesure

	protected long nombreMesuresTotal;
	protected long nombreMesuresActuel;

	/******ACCESSEURS******/

	protected double getTempsInitial(){
		return tempsInitial;
	}

	protected double getTempsPrecedent(){
		return tempsPrecedent;
	}

	protected double getTempsActuel(){
		return tempsActuel;
	}

	protected double getDeltaTemps(){
		return deltaTemps;
	}

	protected double getTempsFinal(){
		return tempsFinal;
	}

	protected double getNombreMesuresTotal(){
		return nombreMesuresTotal;
	}

	protected double getNombreMesuresActuel(){
		return nombreMesuresActuel;
	}

	/******MUTATEURS******/

	protected void setTempsPrecedent(double tempsP){
		tempsPrecedent = tempsP;
	}

	protected void setTempsActuel(double tempsA){
		tempsActuel = tempsA;
	}

	/******METHODES******/

	//Avance d'une etape dans le temps (d'un deltaTemps)
	protected double nextTime(){
		setTempsPrecedent(getTempsActuel());
		setTempsActuel( getTempsActuel() + getDeltaTemps());
		System.out.println("Nouveau temps ! : " + getTempsActuel() + "\t -> " + (byte)100*(getTempsActuel()/getTempsFinal()) + "%");
		nombreMesuresActuel++;
		return getTempsActuel();
	}

	/******CONSTRUCTEUR PAR DEFAUT******/
	protected Temps(){
		tempsInitial = 0.0;
		deltaTemps = 1.0E-1; //Intervalle de temps : un dixieme de seconde
		tempsFinal = 1; //1 sec
		tempsActuel = 0.0;

		nombreMesuresTotal = (long) ((tempsFinal - tempsInitial)/deltaTemps);

		System.out.println("Creation d'un objet temps : initial = " + getTempsInitial() + " s, intervalle = " + getDeltaTemps() + " s, final = " + getTempsFinal() +" s. Nombre de mesures necessaires : " + getNombreMesuresTotal());
	}

	/******CONSTRUCTEUR AVEC PARAMETRES******/
	protected Temps(double tempsI, double delta, double tempsF){
		tempsInitial = tempsI;
		deltaTemps = delta;
		tempsFinal = tempsF;
		tempsActuel = tempsI;

		nombreMesuresTotal = (long) ((tempsFinal - tempsInitial)/deltaTemps);

		System.out.println("Creation d'un objet temps : initial = " + getTempsInitial() + " s, intervalle = " + getDeltaTemps() + " s, final = " + getTempsFinal() +" s. Nombre de mesures necessaires : " + getNombreMesuresTotal());
	}
}