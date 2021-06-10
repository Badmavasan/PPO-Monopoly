package casePackage;

import java.util.List;

import etatPackage.Etat;
import exceptionPackage.*;
import joueurPackage.*;

public class CaseInvestissement extends Case {
	  protected boolean appartenanceEtat;
	  /* Indice du tableau joueurs auquel caseInvestissemnt appartient. Si �a appartient � l�tat alors cela est �gal � -1 */
	  protected int appartenanceJoueur; 
	  protected double valeurNominale;
	  /* la valeur de benefice est compris entre 0 et 100 : c'est une porcentage */
	  protected double benefice;
	  
	  public CaseInvestissement(int indice, int valNom, double benef) {
		  super(indice);
		  this.valeurNominale = valNom;
		  this.benefice = benef;
		  this.appartenanceEtat = true;
		  this.appartenanceJoueur = -1;
	  }
	  
	  public double getValeurNominale() {
		  return this.valeurNominale;
	  }
	  
	  public double getBenefice() {
		  return this.benefice;
	  }
	  
	  public void investissementBackToEtat() {
		  this.appartenanceEtat = true;
		  this.appartenanceJoueur = -1;
	  }
	  
	  public void action (Joueur j,Etat etat,Joueurs js,Joueurs joueursPerdu,int playerIndice, List <Integer> indiceOfJoueursToRemove) throws JoueurNotFoundException, CaseDoesNotExistEtatInvestissement {
		  if(this.appartenanceEtat) {
			  if(j instanceof JoueurAgressif) {
				  if(j.getSoldesLiquide() > this.valeurNominale) {
					  try {
						  j.deduct(this.valeurNominale);
						  etat.crediter(this.valeurNominale);
						  this.appartenanceEtat = false;
						  this.appartenanceJoueur = j.getId();
						  etat.removeInvestissementByIndice(this.indice); // ca commence a 1 les indices de cases 
						  j.addToInvestissement(this);
					  }catch(JoueurBrokeException ex) {
							// remove joueur from liste principale and add to joueuersPerdu  
							indiceOfJoueursToRemove.add(playerIndice);
					  }
				  }
			  }
			  else {
				  if(j.getInvestissement().size() < ((JoueurPrudent) j).getInvestMax() && this.valeurNominale < 0.20*j.getSoldesLiquide()){
						try {
							j.deduct(this.valeurNominale);
							etat.crediter(this.valeurNominale);
							this.appartenanceEtat = false;
							this.appartenanceJoueur = j.getId();
							etat.removeInvestissementByIndice(this.indice);
							j.addToInvestissement(this);
							//modifier l'etat de la case + ajouter la case a la liste de soldeInvestissement
						}
						catch(JoueurBrokeException ex) {
							// remove joueur from liste principale and add to joueuersPerdu  
							indiceOfJoueursToRemove.add(playerIndice);
						}
						catch(CaseDoesNotExistEtatInvestissement ex) {
							throw new CaseDoesNotExistEtatInvestissement();
						}
					}
			  }
		  }
		  else {
			  double benef = this.benefice*this.valeurNominale;
			  try {
				  j.deduct(benef);
				  js.getJoueurById(this.appartenanceJoueur).credit(benef);
			  }
			  catch(JoueurBrokeException ex) {
					// remove joueur from liste principale and add to joueuersPerdu  
				  indiceOfJoueursToRemove.add(playerIndice);
			  }
			  catch(JoueurNotFoundException ex){
				  throw new JoueurNotFoundException();
			  }
		  }
	  }
	
	public boolean getAppartenanceEtat(){
		return this.appartenanceEtat;
	}
	
	public int getAppartenanceJoueur(){
		return this.appartenanceJoueur;
	}
}
