package casePackage;

import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.EtatBrokeException;
import exceptionPackage.JoueurBrokeException;
import exceptionPackage.JoueurNotFoundException;
import exceptionPackage.PlayerInvestissementException;
import jeuPackage.Simulation;
import joueurPackage.Joueur;

public abstract class Case {
	protected int indice;

	public Case(int indice) {
		this.indice = indice;
	}

	public int getIndice(){
	  return this.indice;
	}
	
	public abstract void action (Joueur j,Simulation simul) throws JoueurBrokeException, EtatBrokeException, CaseDoesNotExistEtatInvestissement, JoueurNotFoundException, PlayerInvestissementException;
	
}
