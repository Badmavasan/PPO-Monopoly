package jeuPackage;
import configurationPackage.*;
import exceptionPackage.*;

import java.util.*;
import joueurPackage.*;
import plateauPackage.*;
import etatPackage.*;
import casePackage.*;

public class Simulation {
	
	public static void main(String[] args) {
		/* ----------------------- INITIALISATION DE JOUEURS ET PLATEAU EN FONCTION DE CONFIGURATION ------------------------- */
		try{
			ConfigurationJeu configs = menu();
			
			Scanner sc = new Scanner (System.in); // opening scanner
			Plateau plateau;
			Joueurs joueurs;
			Joueurs joueursPerdu;
			
//			String cont; // continue 
			Etat etat = new Etat(configs);
			plateau = new Plateau(configs,etat); /* Sera initalisé apres avoir apris les configuration de la part de l'utilisateur */
			joueurs = new Joueurs(configs);
			
			
			/*----- INITIALISATION DE JOUEURS PERDU ------- */
			joueursPerdu = new Joueurs();
			/* -------------------------------------------- */
			
			/*------------------ DEBUT JEU ---------------- */
			boolean jeuFini = false;
			int total_nb_joueurs = joueurs.joueurs.size();
			int dice_value;
			int player_current_location;
			boolean etatLost = false;
			/*--------------- BOUCLE PRINCIPÂL ------------- */
//			while(!jeuFini && !etatLost){
				
				int parcours_liste_joueurs = 0; 
				
				
				while(parcours_liste_joueurs < joueurs.joueurs.size() && !etatLost && !jeuFini) {
					
					dice_value = getRandomNumberUsingNextInt(1, 6);
					System.out.println("parcours joueurs : " + parcours_liste_joueurs);
					System.out.println("debug 1 before dice : " + joueurs.joueurs.get(parcours_liste_joueurs).getPosition());

					joueurs.joueurs.get(parcours_liste_joueurs).movePlayerTo(dice_value);
					player_current_location = joueurs.joueurs.get(parcours_liste_joueurs).getPosition();
					System.out.println("debug 2 : " + player_current_location);
					Case c = plateau.cases.get(player_current_location-1);
					Joueur j = joueurs.joueurs.get(parcours_liste_joueurs);
					System.out.println("Current player id : " + j.getId());
					if(c instanceof CaseBureauFinancesPubliques) {
						((CaseBureauFinancesPubliques) c).action (j,etat,joueurs,joueursPerdu);
					}
					else if(c instanceof CaseInvestissement) {
						try {
							((CaseInvestissement) c).action(j, etat, joueurs,joueursPerdu,parcours_liste_joueurs);
						}
						catch(JoueurNotFoundException ex) {
							// error that should not occur
						}
						catch(CaseDoesNotExistEtatInvestissement ex) {
							// error that should not occur
						}
					}
					else if(c instanceof CaseSubvention) {
						try {
						 ((CaseSubvention)c).action(j, etat);
						}
						catch(EtatBrokeException ex){
							etatLost = true;
						}
					}
					else if(c instanceof CaseLoiAntitrust) {
						try {
							((CaseLoiAntitrust)c).action(j,etat,plateau);
						}
						catch(CaseDoesNotExistEtatInvestissement ex) {
							// errror that should not occur 
						}
					}
					System.out.println("No. of current players : " + joueurs.joueurs.size());
					System.out.println("No. of lost players : " + joueursPerdu.joueurs.size());
					if(joueurs.joueurs.size()<total_nb_joueurs) {
						parcours_liste_joueurs = parcours_liste_joueurs + 1 -1;
					}else {
						parcours_liste_joueurs = parcours_liste_joueurs + 1;
					}
					if(joueurs.joueurs.size()==1) {
						jeuFini = true;
					}
				}
				//System.out.println("Voulez vous continuer le jeu [Y/N] : ");
				//cont = sc.nextLine();   
//				jeuFini = checkEndofGame(joueurs,/*cont,*/etat);
//				System.out.println(" JeuFini Verif : " + jeuFini);
//			}
			/* -------------------------------------------- */
			sc.close();
		}catch(PlateauCreationFailedException ex){
			System.out.println("Plateau creation has failed");
		}catch(JoueurListCreationFailedException ex){
			System.out.println("List Joueur creation has failed"); // throw new JoueurListCreationFailedException(); faire un system out et exit 
		}
		/*--------------------------------------------------------------------------------------------------------------*/
	}

	
	public static boolean checkEndofGame(Joueurs joueurs, /*String cont,*/ Etat etat){ 
		boolean condition1 = false;
//		boolean condition2 = false;
		if(joueurs.joueurs.size()==1){
			condition1 = true;
		}
//		if(cont=="Y"){
//			condition2 = true;
//		}
		return condition1 /*&& condition2*/;
	}
	
	public static ConfigurationJeu menu(){
		int joueurs_agressifs = 0;
		int joueurs_prudents = 0;
		double capital_dep = 0;
		double capital_etat = 0;
		List<Integer> invest = new ArrayList<Integer>();
		String profile;
		Scanner sc = new Scanner (System.in); // opening scanner 
		System.out.println("Il y a 4 types du jeu NeoLiberal, Socialiste, Capitaliste, Progressiste");
		System.out.println("Entrez le profile du jeu (Utiliser le meme facon qu'au dessus) : ");
		profile = sc.nextLine();
		if(profile.equals("Capitaliste")) {
			System.out.println("Choisissez le nombre de joueurs agressifs : ");
			joueurs_agressifs = sc.nextInt();
			joueurs_prudents = 0;
		}else {
			System.out.println("Choisissez le nombre de joueurs agressifs : ");
			joueurs_agressifs = sc.nextInt();
			System.out.println("Choisissez le nombre de joueurs prudents : ");
			joueurs_prudents = sc.nextInt();
		}
		System.out.println("Entrez le capital des joueurs au départ : ");
		capital_dep = sc.nextDouble();
		System.out.println("Entrez le capital de l'Etat au départ : ");
		capital_etat = sc.nextDouble();
		for(int i=0;i<joueurs_prudents;i++) {
			System.out.println("Entrez le nombre d'investissement max de joueur prudent " + (i+1)+ " : ");
			invest.add(sc.nextInt());
		}
		sc.close(); // closing scanner as it's used inside a function 
		ConfigurationJeu config = new ConfigurationJeu(joueurs_agressifs, joueurs_prudents, capital_dep, capital_etat, invest, profile);
		return config;
	}
	
	public static int getRandomNumberUsingNextInt(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}

}
