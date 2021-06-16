package joueurPackage;

import casePackage.CaseInvestissement;
import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.JoueurBrokeException;
import exceptionPackage.PlayerInvestissementException;
import jeuPackage.Simulation;

public class JoueurPrudent extends Joueur{
	
	protected int investMax;
	
	public JoueurPrudent(double initialSum,int val,int id){
	    super(initialSum,id);
	    this.investMax=val;
	  }

	public int getInvestMax() {
		return this.investMax;
	}
	
	public void actionInvestissement(CaseInvestissement c,Simulation simul) throws CaseDoesNotExistEtatInvestissement, JoueurBrokeException{ // normally this function should never throw a joueurbroke exception
		if(this.getInvestissement().size() < this.investMax && c.getValeurNominale() < 0.20*this.soldes_liquide){
			 this.deduct(c.getValeurNominale());
			  simul.crediterEtat(c.getValeurNominale());
			  c.setAppartenanceEtat(false);
			  c.setAppartenanceJoueur(this);
			  simul.removeInvestissementEtat(c); // ca commence a 1 les indices de cases 
			  this.addToInvestissement(c);
		}
	}
	
	public void actionLoiAntiTrust(Simulation simul) throws PlayerInvestissementException {
		CaseInvestissement toRemove = this.getMaxInvestissement();
		simul.addToInvestissementEtat(toRemove);
		this.removeInvestissement(toRemove);
		simul.transferCaseToEtat(toRemove);
	}
	
}
