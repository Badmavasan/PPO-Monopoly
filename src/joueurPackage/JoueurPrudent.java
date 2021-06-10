package joueurPackage;

public class JoueurPrudent extends Joueur{
	
	protected int investMax;
	
	public JoueurPrudent(double initialSum,int val,int id){
	    super(initialSum,id);
	    this.investMax=val;
	  }

	public int getInvestMax() {
		return this.investMax;
	}
	
}
