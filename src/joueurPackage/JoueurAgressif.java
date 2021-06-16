package joueurPackage;

import casePackage.CaseInvestissement;
import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.JoueurBrokeException;
import exceptionPackage.PlayerInvestissementException;
import jeuPackage.Simulation;

public class JoueurAgressif extends Joueur{
	public JoueurAgressif(double soldes_liq_dep,int id){
	    super(soldes_liq_dep,id);
	  }

	public void actionInvestissement(CaseInvestissement c,Simulation simul) throws CaseDoesNotExistEtatInvestissement, JoueurBrokeException {
		if(this.getSoldesLiquide() > c.getValeurNominale()) {
			  this.deduct(c.getValeurNominale());
			  simul.crediterEtat(c.getValeurNominale());
			  c.setAppartenanceEtat(false);
			  c.setAppartenanceJoueur(this);
			  simul.removeInvestissementEtat(c); // ca commence a 1 les indices de cases 
			  this.addToInvestissement(c);
		  }

	}
	
	public void actionLoiAntiTrust(Simulation simul) throws PlayerInvestissementException {
		CaseInvestissement toRemove = this.getMinInvestissement();
		simul.addToInvestissementEtat(toRemove);
		this.removeInvestissement(toRemove);
		simul.transferCaseToEtat(toRemove);
	}
}
