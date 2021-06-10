package casePackage;

import etatPackage.Etat;
import exceptionPackage.CaseDoesNotExistEtatInvestissement;
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
	  
	  public void action (Joueur j,Etat etat,Plateau plateau) throws CaseDoesNotExistEtatInvestissement {
		  if(j.getSoldesInvestissement()>seuil) {
			  if(j instanceof JoueurAgressif) {
				  int i = j.getIndiceMinInvestissement().getIndice();
				  try {
					  etat.addToInvestissement(j.getIndiceMinInvestissement());
					  j.removeInvestissementByIndice(i);
					  ((CaseInvestissement)plateau.cases.get(i-1)).investissementBackToEtat();
				  }
				  catch(CaseDoesNotExistEtatInvestissement ex) {
					  throw new CaseDoesNotExistEtatInvestissement();
				  }
			  }else {
				  int i = j.getIndiceMaxInvestissement().getIndice();
				  try {
					  j.removeInvestissementByIndice(i);
					  etat.addToInvestissement(j.getIndiceMinInvestissement());
					  ((CaseInvestissement)plateau.cases.get(i-1)).investissementBackToEtat();
				  }
				  catch(CaseDoesNotExistEtatInvestissement ex) {
					  throw new CaseDoesNotExistEtatInvestissement();
				  }
			  }
		  }
	  }
}
