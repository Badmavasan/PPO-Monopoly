package joueurPackage;

import java.util.*;

import casePackage.CaseInvestissement;
import etatPackage.Etat;
import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.JoueurBrokeException;
import exceptionPackage.PlayerInvestissementException;
import plateauPackage.Plateau;

public class JoueurAgressif extends Joueur{
	public JoueurAgressif(double soldes_liq_dep,int id){
	    super(soldes_liq_dep,id);
	  }

	public void actionInvestissement(CaseInvestissement c,Etat etat,Joueurs joueurs,List<Joueur> indiceOfJoueursToRemove) throws CaseDoesNotExistEtatInvestissement {
		if(this.getSoldesLiquide() > c.getValeurNominale()) {
			  try {
				  this.deduct(c.getValeurNominale());
				  etat.crediter(c.getValeurNominale());
				  c.setAppartenanceEtat(false);
				  c.setAppartenanceJoueur(this);
				  etat.removeInvestissement(c); // ca commence a 1 les indices de cases 
				  this.addToInvestissement(c);
			  }catch(JoueurBrokeException ex) {
					// remove joueur from liste principale and add to joueuersPerdu  
					indiceOfJoueursToRemove.add(this);
			  }
		  }

	}
	
	public void actionLoiAntiTrust(Etat etat,Plateau plateau) throws PlayerInvestissementException {
		CaseInvestissement toRemove = this.getMinInvestissement();
		etat.addToInvestissement(toRemove);
		this.removeInvestissement(toRemove);
		((CaseInvestissement)plateau.cases.get(toRemove.getIndice()-1)).investissementBackToEtat();
	}
}
