package casePackage;

public class CaseSubvention extends Case{
	protected int montant;

		/* Constructeur */
	public CaseSubvention(int mon) {
		this.montant = mon;
	}
	
	public int getMontant(){
	  return this.montant;
	}
}
