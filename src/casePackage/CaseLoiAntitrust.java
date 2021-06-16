package casePackage;

import etatPackage.Etat;
import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.PlayerInvestissementException;
import joueurPackage.Joueur;
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

	  public void action (Joueur j,Etat etat,Plateau plateau) throws CaseDoesNotExistEtatInvestissement, PlayerInvestissementException {
		  if(j.getSoldesInvestissement()>seuil) {
			  j.actionLoiAntiTrust(etat, plateau);
		  }
	  }
}
