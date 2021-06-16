/*
 * C'est le fichier principal, c'est le fichier qui contient le main du projet.
 * Le pseudo code de cette algorithm est disponible dans le rapport final 
 */
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
	
	protected ConfigurationJeu configs;
	protected Plateau plateau;
	protected Joueurs joueurs;
	protected Joueurs joueursPerdu;	
	protected Etat etat;

	public Simulation() throws PlateauCreationFailedException, JoueurListCreationFailedException {
		this.configs = menu();
		this.etat = new Etat(configs);
		this.plateau = new Plateau(configs,etat); /* Sera initalisé apres avoir apris les configuration de la part de l'utilisateur */
		this.joueurs = new Joueurs(configs);
		this.joueursPerdu = new Joueurs();
	}
	
	public void crediterEtat(double tax) {
		this.etat.crediter(tax);
	}
	
	public void deductEtat(double sum) throws EtatBrokeException {
		this.etat.deduct(sum);
	}
	
	public void removeInvestissementEtat(CaseInvestissement c) throws CaseDoesNotExistEtatInvestissement {
		this.etat.removeInvestissement(c);
	}
	
	public void addToInvestissementEtat(CaseInvestissement c) {
		this.etat.addToInvestissement(c);
	}
	
	public void transferCaseToEtat(CaseInvestissement c) {
		((CaseInvestissement)this.plateau.cases.get(c.getIndice()-1)).investissementBackToEtat();
	}
	
	public void payTaxToAnotherPlayer(Joueur j,double tax,Joueur appartenanceJoueur) throws JoueurBrokeException, JoueurNotFoundException {
		j.transferTo(this.joueurs.joueurs.get(joueurs.getJoueurById(appartenanceJoueur)), tax);
	}
	
	public void run() throws CaseDoesNotExistException, CaseDoesNotExistEtatInvestissement, PlayerInvestissementException, JoueurNotFoundException{
		
			/* JeuFini est une variable qui est definie pour verifie si le jeu a terminer
			 * Cela verifie 2 conditions :
			 * Condition 1 : S'il reste plus qu'un joueur
			 * Condition 2 : Si l'utilisateur veut continuer la simulation
			 */
			boolean jeuFini = false; 
			/*
			 * La variable DiceValuer est la valuer compris entre 1 et 6 aleatoire 
			 */
			int dice_value;
			/*
			 * Comme on a une boucle while, on utilise la variable player_current_location pour trouver l'indice de la case de joueur, 
			 * On utilise cela pour trouver la nature de case
			 */
			int player_current_location;
			/*
			 * La variable determine si l'etat a echoue ou pas 
			 */
			boolean etatLost = false;
			/*
			 * La variable stocke le choixe d'utilisateur de soit continuer la simulation ou arreter la simulation
			 */
			boolean cont = true;
			
			/*--------------- BOUCLE PRINCIPÂL ------------- */
			while(!jeuFini && !etatLost){
				List <Joueur> JoueursToRemove = new ArrayList<Joueur>();
				int parcours_liste_joueurs = 0;
				int nb_joueurs = joueurs.joueurs.size();
				while(parcours_liste_joueurs <  nb_joueurs && !etatLost) {
					
					dice_value = getRandomNumberUsingNextInt(1, 6);

					joueurs.joueurs.get(parcours_liste_joueurs).movePlayerTo(dice_value);
					player_current_location = joueurs.joueurs.get(parcours_liste_joueurs).getPosition();
					Case c = plateau.cases.get(player_current_location-1);
					
					Joueur j = joueurs.joueurs.get(parcours_liste_joueurs);
					try {
						c.action(j,this);
					}
					catch(JoueurBrokeException ex) {
						JoueursToRemove.add(j);
					}
					catch(EtatBrokeException ex) {
						etatLost = true;
					}
					parcours_liste_joueurs = parcours_liste_joueurs + 1;
				}
				removeJoueurPerdu(joueurs,joueursPerdu,JoueursToRemove,etat,plateau);
				
				//cont = toContinue();
				jeuFini = checkEndofGame(joueurs/*,cont*/);
			}
			print_jeu(/*cont,*/etatLost,joueursPerdu,joueurs,etat);
			/* -------------------------------------------- */
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
	
	/*
	 * La fonction permet de intialiser la configuration du jeu en fonction des donnees de l'utilisateur
	 * Donnees : clavier
	 * @param : ne prend pas de paramteres
	 * @return : Un objet ConfigurationJeu
	 */
	
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
			joueurs_prudents = 0; // on intialise a 0 donc on n a pas besoin de tester 
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
	
	/*
	 * La fonction permet de generer un entier entre 1 et 6 : cela ressemble a notre de 
	 * @param1 : min pour generer un entier superieur a cette valeur 
	 * @param2 : max pour generer un entier inferieur a cette valeur
	 * @return un entier compris entre 1 et 6 
	 * 
	 *  STATIC : cela est une fonction static propre a cette classe comme on l'utilise pas aupauvravant 
	 */
	
	public static int getRandomNumberUsingNextInt(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	    
	}
	
	/*
	 * La fonction permet d'afficher la fin du jeu : On a adapter la fin du jeu comme indique dans l ennonce 
	 * @param1 : fini : Boolean : qui permet de savoir si c'est l'utilisateur qui a arreter la simulation. Donc si l'utilisateur arrret le jeu, fini est true sinon false
	 * @param2 : etatLost : Boolean : permet de savoir si l'etat a echoue : true si l'etat a echoue ou false sinnon
	 * le jeu se termine en 3 cas : ces deux represente 2 cas, sinon l'autre condition a laquelle cette fonction est appele est quand il reste plus qu'un joueur
	 * Cela permet de savoir pourquoi ca s'arrete
	 */
	
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
		System.out.println("#\tNom          Investissements   Liquide       Patrimoine");
		for (int i=0; i<joueursCourants.joueurs.size(); i++){
			int sum; 
			int liq= ((int)joueursCourants.joueurs.get(i).getSoldesLiquide()*100)/100;
			int inv= ((int)joueursCourants.joueurs.get(i).getSoldesInvestissement()*100)/100;
			sum=liq+inv;
			System.out.println("1 -    Joueur "+joueursCourants.joueurs.get(i).getId()+"\t\t"+ inv +"\t\t"+liq+"\t\t"+ sum);
		}
		for (int i=0; i<joueursPerdus.joueurs.size(); i++){
			int sum;
			int liq= ((int)joueursPerdus.joueurs.get(i).getSoldesLiquide()*100)/100;
			int inv=((int)joueursPerdus.joueurs.get(i).getSoldesInvestissement()*100)/100;
			sum=liq+inv;
			System.out.println("0 -    Joueur "+joueursPerdus.joueurs.get(i).getId()+"\t\t"+ inv +"\t\t"+liq+"\t"+sum);
		}
		System.out.println("====================================================");
		System.out.println("Etat - ");
		System.out.println("       Investissements:"+ etat.getSoldesInvestissement());
		System.out.println("       Liquide: "+ etat.getSoldesLiquide());
	}

	/*
	 * La fonction permet de supprimer les joeuurs de la liste joueuers
	 * Cette fonction est appele a la fin de chaque tour 
	 * @param1 : liste joueurs
	 * @param2 : liste de joueurs perdu (une fois qu'on supprime les joueurs de la liste joueurs, on l ajoute dans la liste joueurs perdu)
	 * @param3 : liste d'indice des joeuurs a supprimer de la liste joueurs
	 * ---------- cette liste est genere a l'interieur de la boucle quand il y a joueurBrokeException --------------------------------
	 * @return : void   
	 */
	
	public static void removeJoueurPerdu(Joueurs joueurs,Joueurs joueursPerdu,List<Joueur> indiceOfJoueursToRemove,Etat etat,Plateau plateau) throws CaseDoesNotExistException {
		if(indiceOfJoueursToRemove.size()>0) {
			for(Joueur i : indiceOfJoueursToRemove){
				joueurs.joueurs.remove(i);
				i.transferInvestissemntBackToEtat(etat,plateau);
				joueursPerdu.joueurs.add(i);
			}
		}
	}
}
