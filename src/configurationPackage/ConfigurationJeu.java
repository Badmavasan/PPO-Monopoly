package configurationPackage;
import java.util.*;

public class ConfigurationJeu {
	
	protected int joueurs_agressifs;
	protected int joueurs_prudents;
	protected double capital_joueurs_dep;
	protected double capital_etat;
	protected List<Integer> investissement_max_joueur_prudent;
	
	public ConfigurationJeu (int ja, int jp, double cj, double ce, int[] inv){
		joueurs_agressifs=ja;
		joueurs_prudents=jp;
		capital_joueurs_dep=cj;
		capital_etat=ce;
		for(int i: inv){
			investissement_max_joueur_prudent.add(i);
		}
	}
	
	public int getJoueursAgressifs(){
		return joueurs_agressifs;
	}
	
	public int getJoueursPrudents(){
		return joueurs_prudents;
	}
	
	public double getCapitalEtat (){
		return capital_etat;
	}
	
	public double getCapitalJoueursDep(){
		return capital_joueurs_dep;
	}
	
	public List<Integer> getInvestissementMax(){
		return investissement_max_joueur_prudent;
	}
}
