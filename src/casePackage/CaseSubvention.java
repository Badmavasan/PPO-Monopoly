package casePackage;

import etatPackage.Etat;
import exceptionPackage.EtatBrokeException;
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
	
	public void action(Joueur j,Etat etat) throws EtatBrokeException {
		try {
			etat.deduct(this.montant);
			j.credit(this.montant);
		}catch(EtatBrokeException ex) {
			throw new EtatBrokeException();
		}
	}
}
