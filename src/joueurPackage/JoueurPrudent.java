package joueurPackage;

import java.util.Collections;

public class JoueurPrudent extends Joueur{
	
	protected int investMax;
	
	public JoueurPrudent(double initialSum,int val){
	    super(initialSum);
	    this.investMax=val;
	  }
	
	public boolean actionInvestissement(int valeur_achat){
		boolean achete =false;
		if(investissement.size() < investMax && valeur_achat < 0.20*soldes_liquide ){
			achete=true;
			this.soldes_liquide -= valeur_achat;
			//modifier l'etat de la case + ajouter la case a la liste de soldeInvestissement
		}
		return achete;
	  }

	  public void actionAntiTrust(int max){
		  while (this.getSoldesInvestissement()>max){
			  this.investissement.remove(Collections.max(investissement));
		 }
	  }

}
