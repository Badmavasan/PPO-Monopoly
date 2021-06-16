package jeuPackage;
import exceptionPackage.*;

public class Monopoly {

	public static void main(String[] args) {
		try {
			Simulation simul = new Simulation();
			simul.run();			
		}
		catch(PlateauCreationFailedException ex){
			System.out.println("Plateau creation has failed");
		}
		catch(JoueurListCreationFailedException ex){
			System.out.println("List Joueur creation has failed"); 
		}
		catch(CaseDoesNotExistEtatInvestissement ex) {
			// errror that should not occur 
		}
		catch(PlayerInvestissementException ex) {
			// error that should not occur
		}
		catch(JoueurNotFoundException ex) {
			System.out.println("Joueur not found exception ");
		}
		catch(CaseDoesNotExistException ex) {
			// error that occurs from tranfering investimment from joueur Perdu to etat 
		}


	}

}
