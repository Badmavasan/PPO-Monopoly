package configurationPackage;


public class ConfigurationJeu {
	
	protected int joueurs_agressifs;
	protected int joueurs_prudents;
	protected double capital_joueurs_dep;
	protected double capital_etat;
	protected int investissement_max_joueur_prudent;
	
	public ConfigurationJeu (int ja, int jp, double cj, double ce, int inv){
		joueurs_agressifs=ja;
		joueurs_prudents=jp;
		capital_joueurs_dep=cj;
		capital_etat=ce;
		investissement_max_joueur_prudent=inv;
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
	
	public int getInvestissementMax(){
		return investissement_max_joueur_prudent;
	}
}
