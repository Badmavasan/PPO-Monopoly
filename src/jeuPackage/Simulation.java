package jeuPackage;
import configurationPackage.*;
import exceptionPackage.*;

import java.util.*;
import joueurPackage.*;
import plateauPackage.*;

public class Simulation {
	
	public static void main(String[] args) {
		ConfigurationJeu configs = menu();
		
		Plateau plateau;
		Joueurs joueurs;
		Joueurs joueursPerdu;
		
		/* ---------------- INITIALISATION DE JOUEURS ET PLATEAU EN FONCTION DE CONFIGURATION ------------------------- */
		try{
			plateau = new Plateau(); /* Sera initalisé apres avoir apris les configuration de la part de l'utilisateur */
			joueurs = new Joueurs(configs);
		}catch(PlateauCreationFailedException ex){
//			throw new PlateauCreationFailedException(); faire un system out et exit 
		}catch(JoueurListCreationFailedException ex){
//			throw new JoueurListCreationFailedException(); faire un system out et exit 
		}
		/*--------------------------------------------------------------------------------------------------------------*/
		
			
		/*----- INITIALISATION DE JOUEURS PERDU ------- */
		joueursPerdu = new Joueurs();
		/* -------------------------------------------- */
		
		/*------------------ DEBUT JEU ---------------- */
		
		boolean jeuFini = false;
		/*--------------- BOUCLE PRINCIPÂL ------------- */
		while(!jeuFini){
			
			
			jeuFini = checkEndofGame();
		}
		/* -------------------------------------------- */
	}
	
	public static boolean checkEndofGame(){ 
		boolean condition1 = false;
		boolean condition2 = false;
		boolean condition3 = false;
		if(1){
			condition1 = true;
		}
		if(1){
			condition2 = true;
		}
		if(1){
			condition3 = true;
		}
		return condition1 && condition2 && condition3;
	}
	
	public static ConfigurationJeu menu(){
		int joueurs_agressifs=0;
		int joueurs_prudents=0;
		double capital_dep=0;
		double capital_etat=0;
		int invest=0;
		Scanner sc= new Scanner (System.in);
		
		System.out.println("Choisissez le nombre de joueurs agressifs :");
		joueurs_agressifs=sc.nextInt();
		System.out.println("Choisissez le nombre de joueurs prudents :");
		joueurs_prudents=sc.nextInt();
		System.out.println("Entrez le capital des joueurs au départ:");
		capital_dep=sc.nextDouble();
		System.out.println("Entrez le capital de l'Etat au départ:");
		capital_etat=sc.nextDouble();
		System.out.println("Entrez le nombre d'investissement max des joueurs prudents:");
		invest=sc.nextInt();
		
		ConfigurationJeu config = new ConfigurationJeu(joueurs_agressifs, joueurs_prudents, capital_dep, capital_etat, invest);
		return config;
	}

}
