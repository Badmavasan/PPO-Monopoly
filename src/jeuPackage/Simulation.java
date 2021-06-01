package jeuPackage;
import configurationPackage.*;
import java.util.*;
import joueurPackage.*;
import plateauPackage.*;

public class Simulation {
	
	List <Joueur> joueurs = new ArrayList<Joueur>(); 
	Plateau plateau; /* Sera initalisé apres avoir apris les configuration de la part de l'utilisateur */
	
	public static void main(String[] args) {
		

	}
	
	public static ConfigurationJeu menu() {
        ConfigurationJeu configs = new ConfigurationJeu();
        
        return configs;
    }
	
	public static void jeuSimulation() {
		
	}
	
	public static void initJoueurList(int nbJoueursAgressif,int nbJoueursPrudent) {
		
	}

}
