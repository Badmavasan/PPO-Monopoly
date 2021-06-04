package joueurPackage;
import casePackage.*;
import java.util.*;
import exceptionPackage.*;

public abstract class Joueur {
	/* ATTRIBUTS */

	  // the indice is the location of the player in the board 
	  // The first place is initialized as 0
	  protected int indice;

	  // the soldes_liquide is the balance in terms of money (liquid)
	  protected double soldes_liquide;

	  // All the investments of the player is stocked in list
	  protected List<CaseInvestissement> investissement;

	  // the action of the player in the Investissement case
	  public abstract boolean actionInvestissement(int valeur_achat);

	  // the action of the player in the Loi Antitruist case
	  public abstract void actionAntiTrust(int max);

	  /* CONSTRUCTEUR */
	  public Joueur(int soldesLiquideDepart){ // les joueurs sont par d�fault � la case 0
	    this.indice = 0; // TODO : CHanger l'indice de d�part en fonction de construction du plateau
	    this.soldes_liquide = soldesLiquideDepart;
	    this.investissement = new ArrayList <CaseInvestissement> ();
	  }

	  public double getSoldesLiquide(){
	    return this.soldes_liquide;
	  }

	  public int getSoldesInvestissement(){
	    int somme = 0;
	    Iterator<CaseInvestissement> iter = investissement.iterator();
	    while(iter.hasNext()){
	      somme = somme + iter.next().getValeurNominale();
	    }
	    return somme;
	  }
	  
	  public int getPosition() {
		  return this.indice;
	  }
	  public void deduct(int sumToDeduct) throws JoueurBrokeException{
	    if(this.soldes_liquide - sumToDeduct>0){
	      this.soldes_liquide = this.soldes_liquide - sumToDeduct;
	    }else{
	      throw new JoueurBrokeException();
	    }
	  }

	  public void transferTo(Joueur player2,int transferValue)throws JoueurBrokeException{
	    try{
	      this.deduct(transferValue);
	      player2.credit(transferValue);
	    }catch(JoueurBrokeException exp){
	      throw new JoueurBrokeException();
	    }
	  }

	  public void credit(int value){
	    this.soldes_liquide = this.soldes_liquide + value;
	  }
	  
	  public void movePlayerTo(int numberOfCases) {
		  this.indice += numberOfCases;
	  }
}
