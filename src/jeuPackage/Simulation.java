package jeuPackage;
import configurationPackage.*;
import exceptionPackage.*;

import java.util.*;
import joueurPackage.*;
import plateauPackage.*;

public class Simulation {
	Plateau plateau;
	List <Joueur> joueurs;
	
	public Simulation() throws PlateauCreationFailedException {
		try{
			plateau = new Plateau(); /* Sera initalisé apres avoir apris les configuration de la part de l'utilisateur */
			joueurs = new ArrayList<Joueur>();
			initJoueurList(5,5);
		}catch(PlateauCreationFailedException ex){
			throw new PlateauCreationFailedException();
		}
		
	}
	
	public static ConfigurationJeu menu() {
        ConfigurationJeu configs = new ConfigurationJeu();
        
        return configs;
    }
	
	public static void initJoueurList(int nbJoueursAgressif,int nbJoueursPrudent) {
		
	}

}
