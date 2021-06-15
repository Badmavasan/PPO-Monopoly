package etatPackage;
import configurationPackage.ConfigurationJeu;

import java.util.*;

import casePackage.CaseInvestissement;
import exceptionPackage.*;

public class Etat {
	protected double soldes_liquide;

	protected List<CaseInvestissement> investissement; // indice des cases
	
	//constructeur
	public Etat(ConfigurationJeu configs){
		soldes_liquide = configs.getCapitalEtat();
		investissement = new ArrayList<CaseInvestissement>();
	}
	
	//rajoute la case investissement à la liste d'investissements de l'état
	public void addToInvestissement(CaseInvestissement c) {
		this.investissement.add(c);
	}
	
	//rajoute la valeur sum au total de soldes liquide de l'état
	public void crediter(double sum) {
		this.soldes_liquide = this.soldes_liquide + sum;
	}
	
	public List<CaseInvestissement> getList (){
		return this.investissement;
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
	
	public void deduct(double sum) throws EtatBrokeException{
		if(this.soldes_liquide-sum>=0) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Etat debit");
			this.soldes_liquide -= sum;
		}
		else {
			throw new EtatBrokeException();
		}
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
}
