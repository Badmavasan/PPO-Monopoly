package casePackage;

import java.util.List;

import etatPackage.Etat;
import exceptionPackage.*;
import joueurPackage.*;

public class CaseInvestissement extends Case {
	  protected boolean appartenanceEtat;
	  /* Indice du tableau joueurs auquel caseInvestissemnt appartient. Si �a appartient � l�tat alors cela est �gal � -1 */
	  protected int appartenanceJoueur;
	  /*valeur correspondant au coût que représente la case*/
	  protected double valeurNominale;
	  /* la valeur de benefice est compris entre 0 et 100 : c'est une pourcentage */
	  protected double benefice;
	  
	  public CaseInvestissement(int indice, int valNom, double benef) {
		  super(indice);
		  this.valeurNominale = valNom;
		  this.benefice = benef;
		  this.appartenanceEtat = true;
		  this.appartenanceJoueur = -1;
	  }
	  
	  public void setAppartenanceJoueur(int player) {
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
		  this.appartenanceJoueur = -1;
	  }
	  
	  public void action (Joueur j,Etat etat,Joueurs joueurs,int playerIndice, List <Joueur> indiceOfJoueursToRemove) throws JoueurNotFoundException, CaseDoesNotExistEtatInvestissement {
		  if(this.appartenanceEtat) {
			  j.actionInvestissement(this, playerIndice, etat, joueurs, indiceOfJoueursToRemove);
		  }
		  else {
			  double benef = this.benefice*this.valeurNominale;
			  try {
				  j.deduct(benef);
				  joueurs.getJoueurById(this.appartenanceJoueur).credit(benef);
			  }
			  catch(JoueurBrokeException ex) {
				  // remove joueur from liste principale and add to joueuersPerdu  
				  indiceOfJoueursToRemove.add(j);
			  }
		  }
	  }
	
	public boolean getAppartenanceEtat(){
		return this.appartenanceEtat;
	}
	
	public int getAppartenanceJoueur(){
		return this.appartenanceJoueur;
	}
}
