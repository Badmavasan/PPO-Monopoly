package casePackage;

public class CaseBureauFinancesPubliques extends Case{
	// la valeur de benefice est compris entre 0 et 100 : c'est une porcentage 
	  protected int impotPercentage;

	  protected String nameImpot;

	  public void setImpotPercentage(int valImpotPercentage){
	    this.impotPercentage = valImpotPercentage;
	  }

	  public int getImpotPercentage(){
	    return this.impotPercentage;
	  }

	  public void setName(String name){
		    this.nameImpot = name;
	  }

	  public String getName(){
	    return this.nameImpot;
	  }
}
