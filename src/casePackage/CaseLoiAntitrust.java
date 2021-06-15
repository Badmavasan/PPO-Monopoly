package casePackage;

import etatPackage.Etat;
import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.PlayerHasNoInvestissementException;
import joueurPackage.Joueur;
import joueurPackage.JoueurAgressif;
import plateauPackage.*;

public class CaseLoiAntitrust extends Case {
	  protected double seuil;

	  public CaseLoiAntitrust(int indice,double value_seuil){
		  super(indice);
		  this.seuil = value_seuil;
	  }
	  public double getSeuil(){
	    return this.seuil;
	  }
	  
	  public void action (Joueur j,Etat etat,Plateau plateau) throws CaseDoesNotExistEtatInvestissement, PlayerHasNoInvestissementException {
		  if(j.getSoldesInvestissement()>seuil) {
			  CaseInvestissement toRemove = j.getMinInvestissement();
			  if(j instanceof JoueurAgressif) {
				  try {
					  etat.addToInvestissement(toRemove);
					  j.removeInvestissement(toRemove);
					  ((CaseInvestissement)plateau.cases.get(toRemove.getIndice()-1)).investissementBackToEtat();
				  }
				  catch(CaseDoesNotExistEtatInvestissement ex) {
					  throw new CaseDoesNotExistEtatInvestissement();
				  }
			  }else {
				  try {
					  etat.addToInvestissement(toRemove);
					  j.removeInvestissement(toRemove);
					  ((CaseInvestissement)plateau.cases.get(toRemove.getIndice()-1)).investissementBackToEtat();
				  }
				  catch(CaseDoesNotExistEtatInvestissement ex) {
					  throw new CaseDoesNotExistEtatInvestissement();
				  }
			  }
		  }
	  }
}
