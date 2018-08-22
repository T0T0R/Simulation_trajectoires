//Difference de potentiel entre deux plans
public class TensionPlans extends Tension {

	protected double planAX;
	protected double planBX;

	protected double getPlanAX(){
		return planAX;
	}

	protected double getPlanBX(){
		return planBX;
	}
	/******METHODES******/

	public void appliquer(Particule other){
		if (other.getPosX()>getPlanAX()&&other.getPosX()<getPlanBX()){
			other.addForcesX((getTensionA()-getTensionB())/(getPlanBX()-getPlanAX())*other.getCharge());
		}
	}

	/******CONSTRUCTEUR AVEC PARAMETRES******/

	TensionPlans(double AXa, double BXa, double tensionA, double tensionB) {
		planAX = AXa;
		planBX = BXa;

		setTensionA(tensionA);
		setTensionB(tensionB);
	}
}