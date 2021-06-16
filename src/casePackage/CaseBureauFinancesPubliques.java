package casePackage;

import exceptionPackage.JoueurBrokeException;
import jeuPackage.Simulation;
import joueurPackage.*;


public class CaseBureauFinancesPubliques extends Case{
	// la valeur de benefice est compris entre 0 et 100 : c'est une porcentage 
	  protected double impotPercentage;
	  protected boolean taxArgent; // ce boolean nous indique si c est un tax argent ou partimoine si c est pas argent c est forcement partimoine donc tue si c est argent false si c est patrimoine 
	  
	  public CaseBureauFinancesPubliques(int indice, double impotPer, boolean typeTax){
		  super(indice);
		  this.impotPercentage = impotPer;
		  this.taxArgent = typeTax;
	  }

	  public double getImpotPercentage(){
	    return this.impotPercentage;
	  }
	  
	  public boolean getTaxArgent(){
		  return this.taxArgent;
	  }
	  
	  public void action (Joueur j,Simulation simul) throws JoueurBrokeException {
			double tax;
			if(this.taxArgent) {
				tax = this.impotPercentage*j.getSoldesLiquide();
			}else {
				tax = this.impotPercentage*j.getSoldesInvestissement();
			}
			j.deduct(tax);
			simul.crediterEtat(tax);
	  }
}
