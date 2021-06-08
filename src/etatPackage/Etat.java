package etatPackage;
import configurationPackage.ConfigurationJeu;

import java.util.*;

import casePackage.CaseInvestissement;
import exceptionPackage.*;

public class Etat {
	protected double soldes_liquide;

	protected List<CaseInvestissement> investissement; // indice des cases
	
	public Etat(ConfigurationJeu configs){
		soldes_liquide = configs.getCapitalEtat();
		investissement = new ArrayList<CaseInvestissement>();
	}
	
	public void addToInvestissement(CaseInvestissement c) {
		this.investissement.add(c);
	}
	
	public void crediter(double sum) {
		this.soldes_liquide = sum;
	}
	
	public void removeInvestissementByIndice(int i) throws CaseDoesNotExistEtatInvestissement{
		try {
			investissement.remove(i-1); // liste commence a 0 mais la numerotation commence a partir de 1 
		}
		catch(NullPointerException ex) {
			throw new CaseDoesNotExistEtatInvestissement();
		}
	}
	
	public void deduct(double sum) throws EtatBrokeException{
		if(this.soldes_liquide-sum>=0) {
			this.soldes_liquide -= sum;
		}
		else {
			throw new EtatBrokeException();
		}
	}
}
