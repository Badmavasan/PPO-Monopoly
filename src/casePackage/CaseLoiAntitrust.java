package casePackage;

import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.PlayerInvestissementException;
import jeuPackage.Simulation;
import joueurPackage.Joueur;

public class CaseLoiAntitrust extends Case {
	  protected double seuil;

	  public CaseLoiAntitrust(int indice,double value_seuil){
		  super(indice);
		  this.seuil = value_seuil;
	  }
	  
	  public double getSeuil(){
	    return this.seuil;
	  }

	  public void action (Joueur j,Simulation simul) throws CaseDoesNotExistEtatInvestissement, PlayerInvestissementException {
		  if(j.getSoldesInvestissement()>seuil) {
			  j.actionLoiAntiTrust(simul);
		  }
	  }
}
