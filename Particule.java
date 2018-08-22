public class Particule extends Item{

	//Position Axe X, Y, et Z
	protected double posX;
	protected double posY;
	protected double posZ;

	protected double positionsX[];
	protected double positionsY[];
	protected double positionsZ[];

	protected double forcesX;
	protected double forcesY;
	protected double forcesZ;

	protected double vitesseX;
	protected double vitesseY;
	protected double vitesseZ;
	protected double vitesseGlobale;

	protected double gamma; //Facteur relativiste (lie la masse et la vitesse)

	protected double masseParticule;
	protected double chargeParticule;
	protected float spinParticule;

	/******ACCESSEURS******/

	//Retourne la position X
	protected double getPosX(){
		return posX;
	}

	//Retourne la position Y
	protected double getPosY(){
		return posY;
	}

	//Retourne la position Z
	protected double getPosZ(){
		return posZ;
	}

	public double[] getPositionsX(){
		return positionsX;
	}

	public double[] getPositionsY(){
		return positionsY;
	}

	public double[] getPositionsZ(){
		return positionsZ;
	}

	public double getGamma(){
		return gamma;
	}

	//Retourne la vitesse X
	protected double getVitesseX(){
		return vitesseX;
	}

	//Retourne la vitesse Y
	protected double getVitesseY(){
		return vitesseY;
	}

	//Retourne la vitesse Z
	protected double getVitesseZ(){
		return vitesseZ;
	}

	//Retourne la vitesse globale
	protected double getVitesseGlobale(){
		this.setVitesseGlobale(Math.sqrt(vitesseX*vitesseX+vitesseY*vitesseY+vitesseZ*vitesseZ));   //Pythagore     V = sqrt(v_a^2+v_b^2+v_c^2)
		return vitesseGlobale;
	}

	protected double getForcesX(){
		return forcesX;
	}

	protected double getForcesY(){
		return forcesY;
	}

	protected double getForcesZ(){
		return forcesZ;
	}


	//Retourne la charge de la particule
	protected double getCharge(){
		return chargeParticule;
	}

	//Retourne la masse de la particule
	protected double getMasse(){
		return masseParticule;
	}

	//Retourne le spin de la particule
	protected double getSpin(){
		return spinParticule;
	}

	/******MUTATEURS******/

	//Definit la position X
	protected void setPosX(double x){
		posX = x;
	}
	//Definit la position Y
	protected void setPosY(double y){
		posY = y;
	}
	//Definit la position Z
	protected void setPosZ(double z){
		posZ = z;
	}
	//Definit la position complete
	protected void setPos(double x, double y, double z){
		posX = x;
		posY = y;
		posZ = z;
	}

	//Definit la vitesse X
	protected void setVitesseX(double speedX){
		vitesseX = speedX;
	}
	//Definit la vitesse Y
	protected void setVitesseY(double speedY){
		vitesseY = speedY;
	}
	//Definit la vitesse Z
	protected void setVitesseZ(double speedZ){
		vitesseZ = speedZ;
	}
	//Definit les differentes vitesses
	protected void setVitesse(double speedX, double speedY, double speedZ){
		vitesseX = speedX;
		vitesseY = speedY;
		vitesseZ = speedZ;
	}
	//Definit la vitesse globale
	protected void setVitesseGlobale(double speed){
		vitesseGlobale = speed;
	}

	protected void setGamma(double g){
		gamma = g;
	}

	//Ajoute la force passee en parametre a celle pre-existente
	protected void addForcesX(double force){
		forcesX += force;
	}
	protected void addForcesY(double force){
		forcesY += force;
	}
	protected void addForcesZ(double force){
		forcesZ += force;
	}
	protected void addForces(double X, double Y, double Z){
		forcesX += X;
		forcesY += Y;
		forcesZ += Z;
	}

	protected void setSpin(float spin){
		spinParticule = spin;
	}

	/******METHODES******/

	//Calcule la distance delta X avec une autre particule
	protected double distanceX(Particule other){
		//Calcul de la difference X:
		double deltaX = this.getPosX() - other.getPosX();
		//Calcul de la distance X:
		return deltaX;
	}
	//Calcule la distance delta Y avec une autre particule
	protected double distanceY(Particule other){
		//Calcul de la difference Y:
		double deltaY = this.getPosY() - other.getPosY();
		//Calcul de la distance Y:
		return deltaY;
	}
	//Calcule la distance delta Z avec une autre particule
	protected double distanceZ(Particule other){
		//Calcul de la difference Z:
		double deltaZ = this.getPosZ() - other.getPosZ();
		//Calcul de la distance Z:
		return deltaZ;
	}
	//Calcule la distance dans l'espace avec une autre particule
	protected double distance(Particule other){
		//Calcul des differences:
		double deltaX = this.getPosX() - other.getPosX();
		double deltaY = this.getPosY() - other.getPosY();
		double deltaZ = this.getPosZ() - other.getPosZ();
		//Calcul de la distance:
		return Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ);
	}

	//Calcul la force X de gravitation entre deux particules
	protected double forceGravitationX(Particule other){
		double constGravitation = 6.67384E-11;
		// F = G*(mA*mB)/d^2
		return ((constGravitation * this.getMasse()*other.getMasse() * this.distanceX(other)) / Math.pow(this.distanceX(other)*this.distanceX(other) + this.distanceY(other)*this.distanceY(other) + this.distanceZ(other)*this.distanceZ(other),1.5));
	}
	//Calcul la force Y de gravitation entre deux particules
	protected double forceGravitationY(Particule other){
		double constGravitation = 6.67384E-11;
		return ((constGravitation * this.getMasse()*other.getMasse() * this.distanceY(other)) / Math.pow(this.distanceX(other)*this.distanceX(other) + this.distanceY(other)*this.distanceY(other) + this.distanceZ(other)*this.distanceZ(other),1.5));
	}
	//Calcul la force Z de gravitation entre deux particules
	protected double forceGravitationZ(Particule other){
		double constGravitation = 6.67384E-11;
		return ((constGravitation * this.getMasse()*other.getMasse() * this.distanceZ(other)) / Math.pow(this.distanceX(other)*this.distanceX(other) + this.distanceY(other)*this.distanceY(other) + this.distanceZ(other)*this.distanceZ(other),1.5));
	}
	//Calcul la force de gravitation entre deux particules
	protected double forceGravitation(Particule other){
		double constGravitation = 6.67384E-11;
		return ((constGravitation * this.getMasse() * other.getMasse()) / (this.distance(other) * this.distance(other)));
	}

	//Calcule la force X de Coulomb entre deux particule
	protected double forceCoulombX(Particule other) {
		double kCoulomb = 8.9875517873681764E9;
		// Fx = k*(qA*qB)*(Bx-Ax)/((Bx-Ax)^2+(By-Ay)^2+(Bz-Az)^2)
		double debug = ((kCoulomb * this.getCharge()*other.getCharge() * this.distanceX(other)) / Math.pow(this.distanceX(other)*this.distanceX(other) + this.distanceY(other)*this.distanceY(other) + this.distanceZ(other)*this.distanceZ(other),1.5));
		return debug;
		// +A -> Repulsion;   -A -> Attraction
	}
	//Calcule la force Y de Coulomb entre deux particule
	protected double forceCoulombY(Particule other) {
		double kCoulomb = 8.9875517873681764E9;

		return ((kCoulomb * this.getCharge()*other.getCharge() * this.distanceY(other)) / Math.pow(this.distanceX(other)*this.distanceX(other) + this.distanceY(other)*this.distanceY(other) + this.distanceZ(other)*this.distanceZ(other),1.5));
		// +A -> Repulsion;   -A -> Attraction
	}
	//Calcule la force Z de Coulomb entre deux particule
	protected double forceCoulombZ(Particule other) {
		double kCoulomb = 8.9875517873681764E9;

		return ((kCoulomb * this.getCharge()*other.getCharge() * this.distanceZ(other)) / Math.pow(this.distanceX(other)*this.distanceX(other) + this.distanceY(other)*this.distanceY(other) + this.distanceZ(other)*this.distanceZ(other),1.5));
		// +A -> Repulsion;   -A -> Attraction
	}
	//Calcule la force de Coulomb entre deux particule
	protected double forceCoulomb(Particule other) {
		double kCoulomb = 8.9875517873681764E9;
		// F = k*(qA*qB)/d^2
		return ((kCoulomb * this.getCharge() * other.getCharge()) / (this.distance(other) * this.distance(other)));
		// +A -> Repulsion;   -A -> Attraction
	}

	//Calcule et applique les interactions gravitationneles + Coulomb
	protected void interaction(Particule other){
		addForces(this.forceCoulombX(other)-this.forceGravitationX(other) ,this.forceCoulombY(other)-this.forceGravitationY(other) ,this.forceCoulombZ(other)-this.forceGravitationZ(other) );
	}

	//Ajoute une position dans le tableau repertoriant toutes les positions en fonction du temps (i est l'indice temporel)
	public void addPositionsX(int i, double X){
		positionsX[i]=X;
	}
	public void addPositionsY(int i, double Y){
		positionsY[i]=Y;
	}
	public void addPositionsZ(int i, double Z){
		positionsZ[i]=Z;
	}

	public void convertirForceVitesse(Temps temps){
		vitesseX = ((temps.getTempsActuel()-temps.getTempsPrecedent())*getForcesX()/getMasse())+getVitesseX();  //Grace a la seconde loi de Newton : sommeDesForces = masse * (deltaV / deltaT),
		vitesseY = ((temps.getTempsActuel()-temps.getTempsPrecedent())*getForcesY()/getMasse())+getVitesseY();  //on arrive a vApres = [(tApres - tAvant)*sommeDesForces] / masse   + vAvant
		vitesseZ = ((temps.getTempsActuel()-temps.getTempsPrecedent())*getForcesZ()/getMasse())+getVitesseZ();
	}

	public void convertirVitessePosition(Temps temps){
		posX += (temps.getTempsActuel()-temps.getTempsPrecedent())*getVitesseX(); //v = d/t     donc d = t*v
		posY += (temps.getTempsActuel()-temps.getTempsPrecedent())*getVitesseY();
		posZ += (temps.getTempsActuel()-temps.getTempsPrecedent())*getVitesseZ();
	}

	public void stockPosition(Temps temps){
		addPositionsX((int)temps.getNombreMesuresActuel()-1, getPosX());
		addPositionsY((int)temps.getNombreMesuresActuel()-1, getPosY());
		addPositionsZ((int)temps.getNombreMesuresActuel()-1, getPosZ());
	}

	//Change la masse en fonction de la vitesse
	public void newGamma(){
		setGamma(1/(Math.sqrt(1-((getVitesseGlobale()*getVitesseGlobale())/(299792458.0*299792458.0)))));
	}


	/******CONSTRUCTEUR PAR DEFAUT******/
	protected Particule(){
		masseParticule = 0.0;
		chargeParticule = 0.0;

		vitesseX = 0.0;
		vitesseY = 0.0;
		vitesseZ = 0.0;

		posX = 0;
		posY = 0;
		posZ = 0;

		System.out.print("Creation d'une particule : ");
	}

	/******CONSTRUCTEUR AVEC PARAMETRES******/
	protected Particule(double masse, double charge, double PosX, double PosY, double PosZ, Temps temps){
		masseParticule = masse;
		chargeParticule = charge;

		vitesseX = 0.0;
		vitesseY = 0.0;
		vitesseZ = 0.0;

		posX = PosX;
		posY = PosY;
		posZ = PosZ;

		positionsX = new double[(int)temps.getNombreMesuresTotal()];
		positionsY = new double[(int)temps.getNombreMesuresTotal()];
		positionsZ = new double[(int)temps.getNombreMesuresTotal()];

		System.out.print("Creation d'une particule avec parametres : ");
	}
}