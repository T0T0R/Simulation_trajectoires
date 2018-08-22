/**

 Copyright(c) 2016 LANGLARD Arthur
 All rights reserved.

 Redistribution and use of this program and parts of this program in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public class Main {
	static ArrayList particulesLocal;

	public static void main(String[] args){

		final long c = 299792458;

		int i;

		Scanner sc= new Scanner(System.in);
		System.out.println("Saisir le nombre d'electrons");
		int nbElectrons = sc.nextInt();
		sc.close();

		Temps temps = new Temps(0.0, 1.0E-5, 1.0E-2);
		//Choisir le point de mesure d'arrivee a tempsMesure dans la classe Cible

		//Ajouter un champ electrique en decommentant la ligne ci-dessous, ainsi qu'a la ligne 70
		/*TensionPlans electrodes = new TensionPlans(-1.0, 0.005, 0.0, 3.1E4);
		TensionPlans electrodes2 = new TensionPlans(0.245, 0.255, 0.0, 3.1E4);
		TensionPlans electrodes3 = new TensionPlans(0.584, 0.594, 0.0, 3.1E4);
		TensionPlans electrodes4 = new TensionPlans(0.984, 0.994, 0.0, 3.1E4);
		TensionPlans electrodes5 = new TensionPlans(1.429, 1.439, 0.0, 3.1E4);
		TensionPlans electrodes6 = new TensionPlans(1.91, 1.92, 0.0, 3.1E4);
		TensionPlans electrodes7 = new TensionPlans(2.42, 2.43, 0.0, 3.1E4);
		TensionPlans electrodes8 = new TensionPlans(2.954, 2.964, 0.0, 3.1E4);*/

		//Creation d'un ArrayList contenant toutes les particules a simuler
		ArrayList electrons = new ArrayList();

		for(i=0; i<nbElectrons; i++){
			double nbAleaX =  0.01*(Math.random());
			double nbAleaY =  0.01*(Math.random());
			double nbAleaZ =  0.01*(Math.random());
			//Ajout d'un electron  l'ArrayList de coordonnees aleatoires dans le secteur Nord-Est

			electrons.add(new Electron(nbAleaX,nbAleaY,nbAleaZ, 0.0, 0.0, 0.0, temps));

			nbAleaX =  0.01*(Math.random());
			nbAleaY =  0.01*(Math.random());
			nbAleaZ =  0.01*(Math.random());

			electrons.add(new Proton(nbAleaX,nbAleaY,nbAleaZ, 0.0, 0.0, 0.0, temps));
		}


		//Donne le nombre d'etapes de modelisation : nombreMesuresTotal = (tempsFinal - tempsInitial)/deltaTemps
		int nbCalculs = (int)temps.getNombreMesuresTotal();

		for(i=0; i<nbCalculs; i++){
			temps.nextTime();   //Etape suivante
			analyse(electrons, temps/*, electrodes, electrodes2, electrodes3, electrodes4, electrodes5, electrodes6, electrodes7, electrodes8*/); //Simulation des trajectoires des particules
			System.out.println("");

		}

		//Stocke l'ArrayList des particules comme global pour pouvoir le transmettre a la classe Panneau, pour pouvoir acceder aux positions et afficher les points correspondants
		setParticulesLocal(electrons);

		Panneau jc = new Panneau();
		jc.setBackground(Color.BLACK);
		jc.setPreferredSize(new Dimension(1920,1080));
		GUI.showOnFrame(jc,"tube");

		Depart depart = new Depart();
		depart.setBackground(Color.BLACK);
		depart.setPreferredSize(new Dimension(1000,1000));
		GUI.showOnFrame(depart,"depart");

		Cible cible = new Cible();
		cible.setBackground(Color.BLACK);
		cible.setPreferredSize(new Dimension(1000,1000));
		GUI.showOnFrame(cible,"cible");
	}

	public static void analyse(ArrayList particules, Temps temps){

		//Pour chaque particule...
		for(int i = 0; i<particules.size(); i++){
			//Appliquer les changements entre la particule et [toutes les autres sauf elle-meme], ce qui revient a separer en deux intervalles:

			//Applique les changements a objet avec objets' sur l'intervalle [0 ; objet[
			for(int j = 0; j<i; j++){
				((Particule) particules.get(i)).interaction((Particule) particules.get(j));
			}

			//Applique les changements a objet avec objets' sur l'intervalle ]objet ; fin]
			for(int j = i+1; j<particules.size(); j++){
				((Particule) particules.get(i)).interaction((Particule) particules.get(j));
			}

		}

		int i;
		for(i=0; i<particules.size(); i++){
			System.out.println("Donnees a l'indice no" + i + " : " + particules.get(i));    //Debug
			((Particule)particules.get(i)).convertirForceVitesse(temps);    //Le total des forces calcule plus haut est transforme en vitesse
			((Particule)particules.get(i)).convertirVitessePosition(temps);     //La vitesse des particules est maintenant covertie en position
			((Particule)particules.get(i)).stockPosition(temps);    //La position est stockee dans un tableau a part
			((Particule) particules.get(i)).addForcesX(-1*((Particule) particules.get(i)).getForcesX());    //Reset des forces en faisant     force = force - force = 0
			((Particule) particules.get(i)).addForcesY(-1*((Particule) particules.get(i)).getForcesY());
			((Particule) particules.get(i)).addForcesZ(-1*((Particule) particules.get(i)).getForcesZ());
			System.out.println("Donnees a l'indice no" + i + " : " + particules.get(i));    //Debug
		}
	}

	//Surcharge de analyse avec le champ electrique
	public static void analyse(ArrayList particules, Temps temps, TensionPlans champ, TensionPlans champ2, TensionPlans champ3, TensionPlans champ4, TensionPlans champ5, TensionPlans champ6, TensionPlans champ7, TensionPlans champ8){

		for(int i = 0; i<particules.size(); i++){
			//Appliquer les changements entre la particule et [toutes les autres sauf elle-meme]

			//Applique les changements a objet avec objets' sur [0 ; objet[
			for(int j = 0; j<i; j++){
				((Particule) particules.get(i)).interaction((Particule) particules.get(j));
			}

			//Applique les changements a objet avec objets' sur ]objet ; fin]
			for(int j = i+1; j<particules.size(); j++){
				((Particule) particules.get(i)).interaction((Particule) particules.get(j));
			}

			//Appliquer le champ electrique
			champ.appliquer((Particule) particules.get(i));
			champ2.appliquer((Particule) particules.get(i));
			champ3.appliquer((Particule) particules.get(i));
			champ4.appliquer((Particule) particules.get(i));
			champ5.appliquer((Particule) particules.get(i));
			champ6.appliquer((Particule) particules.get(i));
			champ7.appliquer((Particule) particules.get(i));
			champ8.appliquer((Particule) particules.get(i));
		}

		int i;
		for(i=0; i<particules.size(); i++){
			System.out.println("Donnees a l'indice no" + i + " : " + particules.get(i));
			((Particule)particules.get(i)).convertirForceVitesse(temps);
			((Particule)particules.get(i)).convertirVitessePosition(temps);
			((Particule)particules.get(i)).stockPosition(temps);
			((Particule) particules.get(i)).addForcesX(-1*((Particule) particules.get(i)).getForcesX());
			((Particule) particules.get(i)).addForcesY(-1*((Particule) particules.get(i)).getForcesY());
			((Particule) particules.get(i)).addForcesZ(-1*((Particule) particules.get(i)).getForcesZ());
			System.out.println("Donnees a l'indice no" + i + " : " + particules.get(i));
		}

	}

	//Accesseur qui permet a la classe Panneau d'acceder a l'ArrayList des particules
	public static ArrayList giveParticules(){
		return particulesLocal;
	}

	//Stocke l'ArrayList des particules comme global pour pouvoir le transmettre a la classe Panneau, pour pouvoir acceder aux positions et afficher les points correspondants
	public static void setParticulesLocal(ArrayList particules){
		particulesLocal = particules;
	}
}