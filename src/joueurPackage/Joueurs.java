package joueurPackage;
import exceptionPackage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import configurationPackage.ConfigurationJeu;

public class Joueurs {
	public List<Joueur> joueurs;
	
	public Joueurs(ConfigurationJeu configs)throws JoueurListCreationFailedException{
		joueurs = new ArrayList<Joueur>();
		double somme_initiale;
		double somme_initiale2;
		int nbJoueursAgressif = configs.getJoueursAgressifs();
		int nbJoueursPrudent = configs.getJoueursPrudents();
		int i = nbJoueursAgressif;
		int j = nbJoueursPrudent; 
		int joueurId = 1;
		List<Integer> valInvestMax = configs.getInvestissementMax();
		
		
		if(configs.getProfile().equals("NeoLiberal")){
			somme_initiale = configs.getCapitalJoueursDep();
			somme_initiale2 = somme_initiale - .25*somme_initiale;
			
			while(i>nbJoueursAgressif/2){
				JoueurAgressif joueurAg = new JoueurAgressif(somme_initiale,joueurId);
				joueurs.add(joueurAg);
				i--;
				joueurId++;
			}
			while(i>0){
				JoueurAgressif joueurAg = new JoueurAgressif(somme_initiale2,joueurId);
				joueurs.add(joueurAg);
				i--;
				joueurId++;
			}
			int parcours_valInvestMax = 0;
			while(j>nbJoueursPrudent/2){
				try{
					JoueurPrudent joueurPr= new JoueurPrudent(somme_initiale, valInvestMax.get(parcours_valInvestMax),joueurId);
					parcours_valInvestMax++;
					joueurs.add(joueurPr);
					j--;
					joueurId++;
				}catch(NullPointerException ex){
					throw new JoueurListCreationFailedException();
				}
			}
			while(j>0){
				try{
					JoueurPrudent joueurPr= new JoueurPrudent(somme_initiale2, valInvestMax.get(parcours_valInvestMax),joueurId);
					parcours_valInvestMax++;
					joueurs.add(joueurPr);
					j--;
					joueurId++;
				}catch(NullPointerException ex){
					throw new JoueurListCreationFailedException();
				}
			}
		}
		else {
			somme_initiale = configs.getCapitalJoueursDep();
			
			while(nbJoueursAgressif>0){
				JoueurAgressif joueurAg = new JoueurAgressif(somme_initiale,joueurId);
				joueurs.add(joueurAg);
				nbJoueursAgressif--;
				joueurId++;
			}
			int parcours_valInvestMax = 0;
			while(nbJoueursPrudent>0){
				try{
					JoueurPrudent joueurPr= new JoueurPrudent(somme_initiale, valInvestMax.get(parcours_valInvestMax),joueurId);
					parcours_valInvestMax++;
					joueurs.add(joueurPr);
					nbJoueursPrudent--;
					joueurId++;
				}catch(NullPointerException ex){
					throw new JoueurListCreationFailedException();
				}
			}
		}
		
		Collections.shuffle(joueurs);
		Collections.shuffle(joueurs);
		Collections.shuffle(joueurs);
	}
	
	public Joueurs(){
		joueurs = new ArrayList<Joueur>();
	}
	
	public int getJoueurById(Joueur j) throws JoueurNotFoundException {
		int indice = this.joueurs.indexOf(j);
		if(indice == -1) {
			System.out.println("DEbug : " + j.getId());
			throw new JoueurNotFoundException();
		}else {
			return indice;
		}
	}
}
