package casePackage;

import exceptionPackage.*;
import jeuPackage.Simulation;
import joueurPackage.*;

public class CaseInvestissement extends Case {
	  protected boolean appartenanceEtat;
	  /* Indice du tableau joueurs auquel caseInvestissemnt appartient. Si �a appartient � l�tat alors cela est �gal � -1 */
	  protected Joueur appartenanceJoueur;
	  /*valeur correspondant au coût que représente la case*/
	  protected double valeurNominale;
	  /* la valeur de benefice est compris entre 0 et 100 : c'est une pourcentage */
	  protected double benefice;
	  
	  public CaseInvestissement(int indice, int valNom, double benef) {
		  super(indice);
		  this.valeurNominale = valNom;
		  this.benefice = benef;
		  this.appartenanceEtat = true;
		  this.appartenanceJoueur = null;
	  }
	  
	  public void setAppartenanceJoueur(Joueur player) {
		  this.appartenanceJoueur = player;
	  }
	  
	  public void setAppartenanceEtat(boolean setValue) {
		  this.appartenanceEtat = setValue;
	  }
	  
	  public double getValeurNominale() {
		  return this.valeurNominale;
	  }
	  
	  public double getBenefice() {
		  return this.benefice;
	  }
	  
	  public void investissementBackToEtat() {
		  this.appartenanceEtat = true;
		  this.appartenanceJoueur = null;
	  }
	  
	  public void action (Joueur j,Simulation simul) throws CaseDoesNotExistEtatInvestissement, JoueurNotFoundException, JoueurBrokeException {
		  if(this.appartenanceEtat) {
			  j.actionInvestissement(this, simul);
		  }
		  else {
			  double benef = this.benefice*this.valeurNominale;
			  simul.payTaxToAnotherPlayer(j,benef,this.appartenanceJoueur);
		  }
	  }
	
	public boolean getAppartenanceEtat(){
		return this.appartenanceEtat;
	}
	
	public Joueur getAppartenanceJoueur(){
		return this.appartenanceJoueur;
	}
}
