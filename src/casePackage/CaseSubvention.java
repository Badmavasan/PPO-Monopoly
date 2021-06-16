package casePackage;

import exceptionPackage.EtatBrokeException;
import jeuPackage.Simulation;
import joueurPackage.Joueur;

public class CaseSubvention extends Case{
	protected int montant;

		/* Constructeur */
	public CaseSubvention(int indice,int mon) {
		super(indice);
		this.montant = mon;
	}
	
	public int getMontant(){
		return this.montant;
	}
	
	public void action(Joueur j,Simulation simul) throws EtatBrokeException {
			simul.deductEtat(this.montant);
			j.credit(this.montant);
	}
}
