package casePackage;

public class CaseInvestissement extends Case{
	  protected boolean appartenanceEtat;
	  /* Indice du tableau joueurs auquel caseInvestissemnt appartient. Si ça appartient à létat alors cela est égal à -1 */
	  protected int appartenanceJoueur; 
	  protected int valeurNominale;
	  /* la valeur de benefice est compris entre 0 et 100 : c'est une porcentage */
	  protected int benefice;
	  
	  public CaseInvestissement(int valNom, int benef) {
		  this.valeurNominale = valNom;
		  this.benefice = benef;
		  this.appartenanceEtat = true;
		  this.appartenanceJoueur = -1;
	  }
	  
	  public int getValeurNominale() {
		  return this.valeurNominale;
	  }
	  
	  public void caseAcheter(int indice_joueur) {
		  this.appartenanceEtat = false;
		  this.appartenanceJoueur = indice_joueur;
	  }
	  
}
