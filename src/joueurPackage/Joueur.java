package joueurPackage;
import casePackage.*;
import java.util.*;
import exceptionPackage.*;

public abstract class Joueur {
	/* ATTRIBUTS */

	  // the indice is the location of the player in the board 
	  // The first place is initialized as 0
	  protected int position;
	  
	  protected int id;

	  // the soldes_liquide is the balance in terms of money (liquid)
	  protected double soldes_liquide;

	  // All the investments of the player is stocked in list
	  protected List<CaseInvestissement> investissement;

	  /* CONSTRUCTEUR */
	  public Joueur(double soldesLiquideDepart,int id){ // les joueurs sont par d�fault � la case 0
	    this.position = 1;
	    this.soldes_liquide = soldesLiquideDepart;
	    this.investissement = new ArrayList <CaseInvestissement> ();
	    this.id = id;
	  }

	  public double getSoldesLiquide(){
	    return this.soldes_liquide;
	  }

	  public double getSoldesInvestissement(){
	    double somme = 0;
	    Iterator<CaseInvestissement> iter = investissement.iterator();
	    while(iter.hasNext()){
	      somme = somme + iter.next().getValeurNominale();
	    }
	    return somme;
	  }
	  
	  public int getPosition() {
		  return this.position;
	  }
	  public void deduct(double sumToDeduct) throws JoueurBrokeException{
	    if(this.soldes_liquide - sumToDeduct>0){
	      this.soldes_liquide = this.soldes_liquide - sumToDeduct;
	    }else{
	      throw new JoueurBrokeException();
	    }
	  }

	  public void transferTo(Joueur player2,double transferValue)throws JoueurBrokeException{
	    try{
	      this.deduct(transferValue);
	      player2.credit(transferValue);
	    }catch(JoueurBrokeException exp){
	      throw new JoueurBrokeException();
	    }
	  }

	  public void credit(double value){
	    this.soldes_liquide = this.soldes_liquide + value;
	  }
	  
	  public void movePlayerTo(int numberOfCases) {
		  if(this.position+ numberOfCases > 32) {
			  this.position = this.position  + numberOfCases - 32;
		  }else {
			  this.position = this.position + numberOfCases;
		  }
	  }
	  
	  public int getId() {
		  return this.id;
	  }
	  
	  public List<CaseInvestissement> getInvestissement(){
		  return this.investissement;
	  }
	  
	  public void addToInvestissement(CaseInvestissement c) {
			this.investissement.add(c);
	  }
	  
	  public void removeInvestissementByIndice(int i) throws CaseDoesNotExistEtatInvestissement{
			boolean found = false;
			int indice = 0;
			System.out.println("DEbug findd the errorr of i to remove : " + i + "where as investissement taille  : " + investissement.size());
			while(!found && indice<investissement.size()) {
				System.out.println("debug");
				 if(investissement.get(indice).getIndice()==i) {
					 found = true;
				 }
				 indice ++;
			} // liste commence a 0 mais la numerotation commence a partir de 1
			if(found) {
				investissement.remove(indice-1);
			}
			else {
				throw new CaseDoesNotExistEtatInvestissement();
			}
		}
	  
	  public CaseInvestissement getIndiceMinInvestissement() {
		  CaseInvestissement rep = Collections.min(this.investissement,Comparator.comparing(s-> s.getValeurNominale()));
		  return rep;
	  }
	  
	  public CaseInvestissement getIndiceMaxInvestissement() {
		  CaseInvestissement rep = Collections.max(this.investissement,Comparator.comparing(s-> s.getValeurNominale()));
		  return rep;
	  }
	  
	  public int debugInvestissementSize() {
		  return this.investissement.size();
	  }
}
