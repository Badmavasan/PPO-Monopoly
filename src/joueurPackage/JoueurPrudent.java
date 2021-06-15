package joueurPackage;

import java.util.List;

import casePackage.CaseInvestissement;
import etatPackage.Etat;
import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.JoueurBrokeException;
import exceptionPackage.JoueurNotFoundException;
import exceptionPackage.PlayerInvestissementException;
import plateauPackage.Plateau;

public class JoueurPrudent extends Joueur{
	
	protected int investMax;
	
	public JoueurPrudent(double initialSum,int val,int id){
	    super(initialSum,id);
	    this.investMax=val;
	  }

	public int getInvestMax() {
		return this.investMax;
	}
	
	public void actionInvestissement(CaseInvestissement c,int playerIndice,Etat etat,Joueurs joueurs,List<Joueur> indiceOfJoueursToRemove) throws CaseDoesNotExistEtatInvestissement, JoueurNotFoundException {
		if(this.getInvestissement().size() < this.investMax && c.getValeurNominale() < 0.20*this.soldes_liquide){
			try {
				this.deduct(c.getValeurNominale());
				etat.crediter(c.getValeurNominale());
				c.setAppartenanceEtat(false);
				c.setAppartenanceJoueur(this.getId());
				etat.removeInvestissement(c); // ca commence a 1 les indices de cases 
				this.addToInvestissement(c);
			}
			catch(JoueurBrokeException ex) {
				// remove joueur from liste principale and add to joueuersPerdu  
				indiceOfJoueursToRemove.add(this);
			}
		}
	}
	
	public void actionLoiAntiTrust(Etat etat,Plateau plateau) throws PlayerInvestissementException {
		CaseInvestissement toRemove = this.getMaxInvestissement();
		etat.addToInvestissement(toRemove);
		this.removeInvestissement(toRemove);
		((CaseInvestissement)plateau.cases.get(toRemove.getIndice()-1)).investissementBackToEtat();
	}
	
}
