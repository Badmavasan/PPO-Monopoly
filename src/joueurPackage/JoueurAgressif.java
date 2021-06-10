package joueurPackage;

public class JoueurAgressif extends Joueur{
	
<<<<<<< HEAD
	  public JoueurAgressif(double soldes_liq_dep){
	    super(soldes_liq_dep);
	  }
	  
	  public boolean actionInvestissement(double valeur_achat){
		boolean achete =false;
		if(this.soldes_liquide>valeur_achat){
			achete=true;
			this.soldes_liquide -= valeur_achat;
			//modifier l'etat de la case + ajouter la case a la liste de soldeInvestissement
		}
		return achete;
||||||| merged common ancestors
	  public JoueurAgressif(double soldes_liq_dep){
	    super(soldes_liq_dep);
	  }
	  
	  public boolean actionInvestissement(int valeur_achat){
		boolean achete =false;
		if(this.soldes_liquide>valeur_achat){
			achete=true;
			this.soldes_liquide -= valeur_achat;
			//modifier l'etat de la case + ajouter la case a la liste de soldeInvestissement
		}
		return achete;
=======
	  public JoueurAgressif(double soldes_liq_dep,int id){
	    super(soldes_liq_dep,id);
>>>>>>> 5d3f8b2cfb1dbe51d21d0d524d83251d55679eaa
	  }
	  
<<<<<<< HEAD
	  public void actionAntiTrust(double max){
		  while (this.getSoldesInvestissement()>max){
			  this.investissement.remove(Collections.min(investissement));
		 }
		  //rendre l'etat de ces cases dispo
	  }
||||||| merged common ancestors
	  public void actionAntiTrust(int max){
		  while (this.getSoldesInvestissement()>max){
			  this.investissement.remove(Collections.min(investissement));
		 }
		  //rendre l'etat de ces cases dispo
	  }
=======
//	  public void actionAntiTrust(int max){
//		  while (this.getSoldesInvestissement()>max){
//			  this.investissement.remove(Collections.min(investissement));
//		 }
//		  //rendre l'etat de ces cases dispo
//	  }
>>>>>>> 5d3f8b2cfb1dbe51d21d0d524d83251d55679eaa

}
