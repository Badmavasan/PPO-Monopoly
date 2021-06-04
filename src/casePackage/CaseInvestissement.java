package casePackage;

public class CaseInvestissement extends Case implements Comparable<CaseInvestissement>{
	  protected boolean appartenanceEtat;
	  /* Indice du tableau joueurs auquel caseInvestissemnt appartient. Si �a appartient � l�tat alors cela est �gal � -1 */
	  protected int appartenanceJoueur; 
	  protected int valeurNominale;
	  /* la valeur de benefice est compris entre 0 et 100 : c'est une porcentage */
	  protected double benefice;
	  
	  public CaseInvestissement(int valNom, double benef) {
		  this.valeurNominale = valNom;
		  this.benefice = benef;
		  this.appartenanceEtat = true;
		  this.appartenanceJoueur = -1;
	  }
	  
	  public int getValeurNominale() {
		  return this.valeurNominale;
	  }
	  
	  public double getBenefice() {
		  return this.benefice;
	  }
	  
	  public void caseAcheter(int indice_joueur) {
		  this.appartenanceEtat = false;
		  this.appartenanceJoueur = indice_joueur;
	  }

	@Override
	public int compareTo(CaseInvestissement o) {
		// TODO Auto-generated method stub
		return 0;
	}
	  
}
