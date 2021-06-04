package joueurPackage;

import java.util.ArrayList;
import java.util.List;

public class Joueurs {
	protected List<Joueur> joueurs;
	
	public Joueurs(int nbJoueursAgressif,int nbJoueursPrudent,int somme_initiale, int[] valInvestMax){
		joueurs = new ArrayList<Joueur>();
		while(nbJoueursAgressif>0){
			JoueurAgressif joueurAg = new JoueurAgressif(somme_initiale);
			joueurs.add(joueurAg);
		}
		int parcours_valInvestMax = 0;
		while(nbJoueursPrudent>0){
			JoueurPrudent joueurPr= new JoueurPrudent(somme_initiale, valInvestMax[parcours_valInvestMax]);
			parcours_valInvestMax++;
			joueurs.add(joueurPr);
		}
	}
	
	
	public List<Joueur> getJoueurs(){
		return this.joueurs;
	}
}
