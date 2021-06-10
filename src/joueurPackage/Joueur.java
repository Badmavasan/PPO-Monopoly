package joueurPackage;
import casePackage.*;
import java.util.*;
import exceptionPackage.*;

public abstract class Joueur {
	/* ATTRIBUTS */

	  // the indice is the location of the player in the board 
	  // The first place is initialized as 0
	  protected int indice;
	  
	  protected int id;

	  // the soldes_liquide is the balance in terms of money (liquid)
	  protected double soldes_liquide;

	  // All the investments of the player is stocked in list
	  protected List<CaseInvestissement> investissement;

	  /* CONSTRUCTEUR */
	  public Joueur(double soldesLiquideDepart,int id){ // les joueurs sont par d�fault � la case 0
	    this.indice = 1;
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
		  return this.indice;
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
		  if(this.indice + numberOfCases > 32) {
			  this.indice = this.indice  + numberOfCases - 32;
		  }else {
			  this.indice = this.indice  + numberOfCases;
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
			try {
				investissement.remove(i-1); // liste commence a 0 mais la numerotation commence a partir de 1 
			}
			catch(NullPointerException ex) {
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
}
