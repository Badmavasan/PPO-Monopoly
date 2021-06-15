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
	  
	  public void removeInvestissement(CaseInvestissement c) throws CaseDoesNotExistEtatInvestissement{
			boolean remove = investissement.remove(c);
			if(!remove) {
				throw new CaseDoesNotExistEtatInvestissement();
			}
		}
	  
	  public CaseInvestissement getMinInvestissement() throws PlayerHasNoInvestissementException{
		  try {
			  CaseInvestissement rep = Collections.min(this.investissement,Comparator.comparing(s-> s.getValeurNominale()));
			  return rep;
		  }catch(NoSuchElementException ex) {
			  throw new PlayerHasNoInvestissementException();
		  }
	  }
	  
	  public CaseInvestissement getMaxInvestissement() throws PlayerHasNoInvestissementException{
		  try {	  
		  	  CaseInvestissement rep = Collections.max(this.investissement,Comparator.comparing(s-> s.getValeurNominale()));
			  return rep;
		  }catch(NoSuchElementException ex) {
			  throw new PlayerHasNoInvestissementException();
		  }
	  }
}
