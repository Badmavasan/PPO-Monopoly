package casePackage;

public class CaseLoiAntitrust extends Case {
	  protected int seuil;

	  public CaseLoiAntitrust(int value_seuil){
		  this.seuil = value_seuil;
	  }
	  public int getSeuil(){
	    return this.seuil;
	  }
}
