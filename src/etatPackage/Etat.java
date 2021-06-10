package etatPackage;
import casePackage.*;
import java.util.*;

public class Etat {
	protected double soldes_liquide;

	protected List<CaseInvestissement> investissement;
	
	public Etat(double solde, List<CaseInvestissement> liste){
		soldes_liquide=solde;
		investissement=liste;
	}
	
	public List<CaseInvestissement> getList (){
		return this.investissement;
	}
}
