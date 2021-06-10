package jeuPackage;
import configurationPackage.*;
import exceptionPackage.*;

import java.util.*;
import joueurPackage.*;
import plateauPackage.*;
import etatPackage.*;
import casePackage.*;

public class Simulation {
	static Scanner sc = new Scanner (System.in); // opening scanner
	
	public static void main(String[] args) {
		/* ----------------------- INITIALISATION DE JOUEURS ET PLATEAU EN FONCTION DE CONFIGURATION ------------------------- */
		try{
			ConfigurationJeu configs = menu();
			
			Plateau plateau;
			Joueurs joueurs;
			Joueurs joueursPerdu;
			
			boolean cont = true; // continue 
			Etat etat = new Etat(configs);
			plateau = new Plateau(configs,etat); /* Sera initalisé apres avoir apris les configuration de la part de l'utilisateur */
			joueurs = new Joueurs(configs);
			
			
			/*----- INITIALISATION DE JOUEURS PERDU ------- */
			joueursPerdu = new Joueurs();
			/* -------------------------------------------- */
			
			/*------------------ DEBUT JEU ---------------- */
			boolean jeuFini = false;
			int dice_value;
			int player_current_location;
			boolean etatLost = false;
			
			/*--------------- BOUCLE PRINCIPÂL ------------- */
			while(!jeuFini && !etatLost){
				List <Integer> indiceOfJoueursToRemove = new ArrayList<Integer>();
				int parcours_liste_joueurs = 0; 
				System.out.println("--------- One tour ------------");
				int nb_joueurs = joueurs.joueurs.size();
				while(parcours_liste_joueurs <  nb_joueurs && !etatLost) {
					
					dice_value = getRandomNumberUsingNextInt(1, 6);
					System.out.println("Nombres de joueurs au depart de chaque boucle : " + joueurs.joueurs.size());
					System.out.println("parcours joueurs : " + parcours_liste_joueurs);

					joueurs.joueurs.get(parcours_liste_joueurs).movePlayerTo(dice_value);
					player_current_location = joueurs.joueurs.get(parcours_liste_joueurs).getPosition();
					Case c = plateau.cases.get(player_current_location-1);
					
					Joueur j = joueurs.joueurs.get(parcours_liste_joueurs);
					
					if(c instanceof CaseBureauFinancesPubliques) {
						System.out.println("Case Bureau Finances Publiques");
						((CaseBureauFinancesPubliques) c).action (j,etat,joueurs,joueursPerdu,parcours_liste_joueurs,indiceOfJoueursToRemove);
						System.out.println("Tax paying Etat after tax " + etat.getSoldesLiquide());
					}
					else if(c instanceof CaseInvestissement) {
						System.out.println("Case Investissement");
						try {
							((CaseInvestissement) c).action(j, etat, joueurs,joueursPerdu,parcours_liste_joueurs,indiceOfJoueursToRemove);
							System.out.println("Investissement added or not : " + j.debugInvestissementSize());
						}
						catch(JoueurNotFoundException ex) {
							System.out.println("Joueur not found exception ");
						}
						catch(CaseDoesNotExistEtatInvestissement ex) {
							System.out.println("Case does not exist exception ");
						}
					}
					else if(c instanceof CaseSubvention) {
						System.out.println("Case Subvention");
						try {
						 ((CaseSubvention)c).action(j, etat);
						}
						catch(EtatBrokeException ex){
							etatLost = true;
							System.out.println("Etat lost : " + etat.getSoldesLiquide() + "Had to pay : " +((CaseSubvention)c).getMontant());
							
						}
					}
					else if(c instanceof CaseLoiAntitrust) {
						System.out.println("Case Loi Anti Trust ");
						try {
							((CaseLoiAntitrust)c).action(j,etat,plateau);
						}
						catch(CaseDoesNotExistEtatInvestissement ex) {
							// errror that should not occur 
						}
					}
					parcours_liste_joueurs = parcours_liste_joueurs + 1;
//					if(joueurs.joueurs.size()==1) {
//						jeuFini = true;
//					}
				}
				
				removeJoueurPerdu(joueurs,joueursPerdu,indiceOfJoueursToRemove);
				
				System.out.println(">>>>>>>>>>>>>>>>> Etat has : " + etat.getSoldesLiquide());
				System.out.println("No. of current players : " + joueurs.joueurs.size());
				System.out.println("No. of lost players : " + joueursPerdu.joueurs.size());
				//cont = toContinue();
				jeuFini = checkEndofGame(joueurs/*,cont*/);
				System.out.println(" >>>>>>>>>>>>>>>>JeuFini Verif : " + jeuFini);
			}
			System.out.println("Etat echoue : " + etatLost);
			print_jeu(/*cont,*/etatLost,joueursPerdu,joueurs,etat);
			/* -------------------------------------------- */
		}catch(PlateauCreationFailedException ex){
			System.out.println("Plateau creation has failed");
		}catch(JoueurListCreationFailedException ex){
			System.out.println("List Joueur creation has failed"); // throw new JoueurListCreationFailedException(); faire un system out et exit 
		}
		/*--------------------------------------------------------------------------------------------------------------*/
	}

	public static boolean toContinue() {
		char cont;
		System.out.println("Voulez vous continuer le jeu [Y/N] : ");
		cont = sc.next().charAt(0);
		if(cont=='N' || cont =='n') {
			return false;
		}else if(cont=='Y' || cont=='y'){
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean checkEndofGame(Joueurs joueurs/*, boolean cont*/){ 
		if(joueurs.joueurs.size()==1){
			return true;
		}
//		else if(!cont) {
//			return true;
//		}
		else {
			return false;
		}
	}
	
	public static ConfigurationJeu menu(){
		int joueurs_agressifs = 0;
		int joueurs_prudents = 0;
		double capital_dep = 0;
		double capital_etat = 0;
		List<Integer> invest = new ArrayList<Integer>();
		String profile;
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
		ConfigurationJeu config = new ConfigurationJeu(joueurs_agressifs, joueurs_prudents, capital_dep, capital_etat, invest, profile);
		return config;
	}
	
	public static int getRandomNumberUsingNextInt(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	    
	}
	
	public static void print_jeu(/*boolean fini, */boolean etatLost, Joueurs joueursPerdus, Joueurs joueursCourants, Etat etat){
		if(etatLost){
			System.out.println("Le jeu est fini, l'état a échoué !");
		}else /*if (fini)*/{
			System.out.println("Le jeu est fini !");
			System.out.println("Gagnant : Joueur "+ joueursCourants.joueurs.get(0).getId());
		}
//		else {
//			System.out.println("Le jeu est fini, l'utilisateur a arrêté le jeu !");
//		}
		System.out.println("===============");
		System.out.println(" ");
		System.out.println("#    Nom          Investissements   Liquide    Patrimoine");
		for (int i=0; i<joueursCourants.joueurs.size(); i++){
			System.out.println("1 -    Joueur"+joueursCourants.joueurs.get(i).getId()+"\t"+ joueursCourants.joueurs.get(i).getSoldesInvestissement()
					+"\t"+joueursCourants.joueurs.get(i).getSoldesLiquide());
		}
		for (int i=0; i<joueursPerdus.joueurs.size(); i++){
			System.out.println("0 -    Joueur"+joueursPerdus.joueurs.get(i).getId()+"\t"+ joueursPerdus.joueurs.get(i).getSoldesInvestissement()
					+"\t"+joueursPerdus.joueurs.get(i).getSoldesLiquide());
		}
		System.out.println("====================================================");
		System.out.println("Etat - ");
		System.out.println("       Investissements:"+ etat.getSoldesInvestissement());
		System.out.println("       Liquide: "+ etat.getSoldesLiquide());
	}
	
	public static void removeJoueurPerdu(Joueurs joueurs,Joueurs joueursPerdu,List <Integer> indiceOfJoueursToRemove) {
		if(indiceOfJoueursToRemove.size()>0) {
			for(int i : indiceOfJoueursToRemove){
				Joueur jr = joueurs.joueurs.get(i);
				joueurs.joueurs.remove(i);
				joueursPerdu.joueurs.add(jr);
			}
		}
	}
}
