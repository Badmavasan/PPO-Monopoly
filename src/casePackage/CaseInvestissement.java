package casePackage;

import joueurPackage.*;
public class CaseInvestissement extends Case{
	  protected boolean appartenanceEtat;
	  protected Joueur appartenanceJoueur;
	  protected int valeurNominale;
	  /* la valeur de benefice est compris entre 0 et 100 : c'est une porcentage */
	  protected int benefice;

	  public void setBenefice(int benef){
	    this.benefice = benef;
	  }
	  public int getBenefice(){
	    return this.benefice;
	  }
	  public void setValeurNominale(int valNomiale){
	    this.valeurNominale = valNomiale;
	  }
	  public int getValeurNominale(){
	    return this.valeurNominale;
	  }
}
