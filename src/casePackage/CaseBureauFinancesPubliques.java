package casePackage;

public class CaseBureauFinancesPubliques extends Case{
	// la valeur de benefice est compris entre 0 et 100 : c'est une porcentage 
	  protected int impotPercentage;
	  protected boolean taxArgent; // ce boolean nous indique si c est un tax argent ou partimoine si c est pas argent c est forcement partimoine donc tue si c est argent false si c est patrimoine 
	  
	  public CaseBureauFinancesPubliques(int impotPer, boolean typeTax){
		  this.impotPercentage = impotPer;
		  this.taxArgent = typeTax;
	  }
	  
	  public void setImpotPercentage(int valImpotPercentage){
	    this.impotPercentage = valImpotPercentage;
	  }

	  public int getImpotPercentage(){
	    return this.impotPercentage;
	  }
}
