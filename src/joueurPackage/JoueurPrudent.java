package joueurPackage;

public class JoueurPrudent extends Joueur{
	
	protected int investMax;
	
	public JoueurPrudent(double initialSum,int val,int id){
	    super(initialSum,id);
	    this.investMax=val;
	  }
	
<<<<<<< HEAD
	public boolean actionInvestissement(double valeur_achat){
		boolean achete =false;
		if(investissement.size() < investMax && valeur_achat < 0.20*soldes_liquide ){
			achete=true;
			this.soldes_liquide -= valeur_achat;
			//modifier l'etat de la case + ajouter la case a la liste de soldeInvestissement
		}
		return achete;
	  }

	  public void actionAntiTrust(double max){
		  while (this.getSoldesInvestissement()>max){
			  this.investissement.remove(Collections.max(investissement));
		 }
	  }
||||||| merged common ancestors
	public boolean actionInvestissement(int valeur_achat){
		boolean achete =false;
		if(investissement.size() < investMax && valeur_achat < 0.20*soldes_liquide ){
			achete=true;
			this.soldes_liquide -= valeur_achat;
			//modifier l'etat de la case + ajouter la case a la liste de soldeInvestissement
		}
		return achete;
	  }

	  public void actionAntiTrust(int max){
		  while (this.getSoldesInvestissement()>max){
			  this.investissement.remove(Collections.max(investissement));
		 }
	  }
=======
	public int getInvestMax() {
		return this.investMax;
	}
	
//	public boolean actionInvestissement(int valeur_achat){
//		boolean achete =false;
//		if(investissement.size() < investMax && valeur_achat < 0.20*soldes_liquide ){
//			achete=true;
//			this.soldes_liquide -= valeur_achat;
//			//modifier l'etat de la case + ajouter la case a la liste de soldeInvestissement
//		}
//		return achete;
//	  }
>>>>>>> 5d3f8b2cfb1dbe51d21d0d524d83251d55679eaa

//	  public void actionAntiTrust(int max){
//		  while (this.getSoldesInvestissement()>max){
//			  this.investissement.remove(Collections.max(investissement));
//		 }
//	  }
}
