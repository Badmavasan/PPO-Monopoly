package configurationPackage;
import java.util.*;

public class ConfigurationJeu {
	
	protected int joueurs_agressifs;
	protected int joueurs_prudents;
	protected double capital_joueurs_dep;
	protected double capital_etat;
	protected String profile;
	protected List<Integer> investissement_max_joueur_prudent;
	
	public ConfigurationJeu (int joueurs_agressifs, int joueurs_prudents, double capital_joueurs_dep, double capital_etat, List<Integer> inv, String profile){
		this.joueurs_agressifs = joueurs_agressifs;
		this.joueurs_prudents = joueurs_prudents;
		this.capital_joueurs_dep = capital_joueurs_dep;
		this.capital_etat = capital_etat;
		this.investissement_max_joueur_prudent = inv;
		this.profile = profile;
	}
	
	public int getJoueursAgressifs(){
		return this.joueurs_agressifs;
	}
	
	public int getJoueursPrudents(){
		return this.joueurs_prudents;
	}
	
	public double getCapitalEtat (){
		return this.capital_etat;
	}
	
	public double getCapitalJoueursDep(){
		return this.capital_joueurs_dep;
	}
	
	public List<Integer> getInvestissementMax(){
		return this.investissement_max_joueur_prudent;
	}
	
	public String getProfile(){
		return this.profile;
	}
	
	public String toString() {
		return "Nombres de joueurs agressif : " + this.joueurs_agressifs + "\n" + 
			   "Nombres de joueurs prudents : " + this.joueurs_prudents + "\n" +
			   "Captial de depart des joeuurs : " + this.capital_joueurs_dep + "\n" +
			   "Capital de l'etat : " + this.capital_etat + "\n" +
			   "Profile : " + this.profile + "\n" +
			   "Investissement max des joueurs prudents : " + this.investissement_max_joueur_prudent + "\n";
	}
	
}
