package joueurPackage;

import java.util.Collections;

public class JoueurAgressif extends Joueur{
	
	  public JoueurAgressif(int soldes_liq_dep){
	    super(soldes_liq_dep);
	  }
	  
	  public boolean actionInvestissement(int valeur_achat){
		boolean achete =false;
		if(this.soldes_liquide>valeur_achat){
			achete=true;
			this.soldes_liquide -= valeur_achat;
			//modifier l'etat de la case + ajouter la case a la liste de soldeInvestissement
		}
		return achete;
	  }
	  
	  public void actionAntiTrust(int max){
		  while (this.getSoldesInvestissement()>max){
			  this.investissement.remove(Collections.min(investissement));
		 }
		  //rendre l'etat de ces cases dispo
	  }

}
