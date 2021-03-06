package joueurPackage;
import casePackage.*;
import etatPackage.Etat;

import java.util.*;
import exceptionPackage.*;
import jeuPackage.Simulation;
import plateauPackage.Plateau;

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
	  public Joueur(double soldesLiquideDepart,int id){ // les joueurs sont par default a la case 0
	    this.position = 1;
	    this.soldes_liquide = soldesLiquideDepart;
	    this.investissement = new ArrayList <CaseInvestissement> ();
	    this.id = id;
	  }

	  /* Getters */
	  public int getId() {
		  return this.id;
	  }
	  
	  public double getSoldesLiquide(){
	    return this.soldes_liquide;
	  }

	  public double getSoldesInvestissement(){
		  return this.investissement.stream().filter(o -> o.getValeurNominale() > 10).mapToDouble(CaseInvestissement::getValeurNominale).sum();
//	    double somme = 0;
//	    Iterator<CaseInvestissement> iter = investissement.iterator();
//	    while(iter.hasNext()){
//	      somme = somme + iter.next().getValeurNominale();
//	    }
//	    return somme;
	  }
	  
	  public int getPosition() {
		  return this.position;
	  }
	  
	  public List<CaseInvestissement> getInvestissement(){
		  return this.investissement;
	  }
	  
	  public void deduct(double sumToDeduct) throws JoueurBrokeException{
	    if(this.soldes_liquide - sumToDeduct>0){
	      this.soldes_liquide = this.soldes_liquide - sumToDeduct;
	    }else{
	      throw new JoueurBrokeException();
	    }
	  }
	  
	  public CaseInvestissement getMinInvestissement() throws PlayerInvestissementException{
		  try {
			  CaseInvestissement rep = Collections.min(this.investissement,Comparator.comparing(s-> s.getValeurNominale()));
			  return rep;
		  }catch(NoSuchElementException ex) {
			  throw new PlayerInvestissementException();
		  }
	  }
	  
	  public CaseInvestissement getMaxInvestissement() throws PlayerInvestissementException{
		  try {	  
		  	  CaseInvestissement rep = Collections.max(this.investissement,Comparator.comparing(s-> s.getValeurNominale()));
			  return rep;
		  }catch(NoSuchElementException ex) {
			  throw new PlayerInvestissementException();
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
	  
	  public void addToInvestissement(CaseInvestissement c) {
			this.investissement.add(c);
	  }
	  
	  public void removeInvestissement(CaseInvestissement c) throws PlayerInvestissementException{
			boolean remove = investissement.remove(c);
			if(!remove) {
				throw new PlayerInvestissementException();
			}
	  }
	  
	  public void transferInvestissemntBackToEtat(Etat etat,Plateau plateau)throws CaseDoesNotExistException {
		  if(this.investissement.size()>0) {
			  for(CaseInvestissement i : this.investissement) {
				  int indice = plateau.cases.indexOf(i);
				  if(indice == -1) {
					  throw new CaseDoesNotExistException();
				  }
				  else {
					  ((CaseInvestissement)plateau.cases.get(indice)).investissementBackToEtat();
					  etat.addToInvestissement(i);
				  }  
			  }
		  }
	  }
	  
	  public abstract void actionInvestissement(CaseInvestissement c,Simulation simul) throws CaseDoesNotExistEtatInvestissement, JoueurBrokeException;
	  
	  public abstract void actionLoiAntiTrust(Simulation simul) throws PlayerInvestissementException;
}
