package casePackage;

import exceptionPackage.JoueurBrokeException;
import joueurPackage.*;
import etatPackage.*;

public class CaseBureauFinancesPubliques extends Case{
	// la valeur de benefice est compris entre 0 et 100 : c'est une porcentage 
	  protected double impotPercentage;
	  protected boolean taxArgent; // ce boolean nous indique si c est un tax argent ou partimoine si c est pas argent c est forcement partimoine donc tue si c est argent false si c est patrimoine 
	  
	  public CaseBureauFinancesPubliques(int indice, double impotPer, boolean typeTax){
		  super(indice);
		  this.impotPercentage = impotPer;
		  this.taxArgent = typeTax;
	  }
	  
	  public void setImpotPercentage(int valImpotPercentage){
	    this.impotPercentage = valImpotPercentage;
	  }

	  public double getImpotPercentage(){
	    return this.impotPercentage;
	  }
	  
	  public boolean getTaxArgent(){
		  return this.taxArgent;
	  }
	  
	  public void action (Joueur j,Etat etat,Joueurs joueurs,Joueurs joueursPerdu,int parcours_joueurs_liste) {
			double tax;
			if(this.taxArgent) {
				tax = this.impotPercentage*j.getSoldesLiquide();
			}else {
				tax = this.impotPercentage*j.getSoldesInvestissement();
			}
			try {
				j.deduct(tax);
				etat.crediter(tax);
			}
			catch(JoueurBrokeException ex) {
				// remove joueur from liste principale and add to joueuersPerdu  
				Joueur jr = joueurs.joueurs.get(parcours_joueurs_liste); // joueur id starts at 1
				joueurs.joueurs.remove(parcours_joueurs_liste);
				joueursPerdu.joueurs.add(jr);
			}
	  }
}
