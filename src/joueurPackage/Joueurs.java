package joueurPackage;
import exceptionPackage.*;

import java.util.ArrayList;
import java.util.List;

import configurationPackage.ConfigurationJeu;

public class Joueurs {
	protected List<Joueur> joueurs;
	
	public Joueurs(ConfigurationJeu configs)throws JoueurListCreationFailedException{
		joueurs = new ArrayList<Joueur>();
		int nbJoueursAgressif = configs.getJoueursAgressifs();
		int nbJoueursPrudent = configs.getJoueursPrudents();
		double somme_initiale = configs.getCapitalJoueursDep();
		List<Integer> valInvestMax = configs.getInvestissementMax();
		while(nbJoueursAgressif>0){
			JoueurAgressif joueurAg = new JoueurAgressif(somme_initiale);
			joueurs.add(joueurAg);
			nbJoueursAgressif--;
		}
		int parcours_valInvestMax = 0;
		while(nbJoueursPrudent>0){
			try{
				JoueurPrudent joueurPr= new JoueurPrudent(somme_initiale, valInvestMax.get(parcours_valInvestMax));
				parcours_valInvestMax++;
				joueurs.add(joueurPr);
				nbJoueursPrudent--;
			}catch(NullPointerException ex){
				throw new JoueurListCreationFailedException();
			}
		}
	}
	
	public Joueurs(){
		joueurs = new ArrayList<Joueur>();
	}
	
	
	public List<Joueur> getJoueurs(){
		return this.joueurs;
	}
}
