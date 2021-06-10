package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import casePackage.CaseInvestissement;
import configurationPackage.ConfigurationJeu;
import etatPackage.Etat;
import exceptionPackage.*;
import joueurPackage.*;
import plateauPackage.Plateau;

public class CaseTest {
	
	@Test
	public void caseInvestissementTest() throws PlateauCreationFailedException, JoueurListCreationFailedException, JoueurNotFoundException, CaseDoesNotExistEtatInvestissement {
		/*--------------------------- INITIALISATION -------------------------------------- */
		
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		ConfigurationJeu configs = new ConfigurationJeu(2, 2, 40000, 100000, invest, "Capitaliste");
		Etat etat = new Etat(configs);
		Plateau plateau = new Plateau(configs,etat); 
		Joueurs joueurs = new Joueurs(configs); // on l'initialise meme si ce n'est pas necessaire car on en a besoin pour la methode action de la classe
		Joueurs joueursPerdu = new Joueurs(); // on l'initialise meme si ce n'est pas necessaire car on en a besoin pour la methode action de la classe
		List<Integer> indiceOfJouersToRemove = new ArrayList<Integer>(); // on en a besoin si jamais le joeuur n a pas d'argent 
		/*----------------------------------------------------------------------------------*/
		
		Joueur j = joueurs.joueurs.get(1);  // on prend un joueur 
		j.movePlayerTo(1); // on positionne un joueuer dans la case investissement
		CaseInvestissement c = (CaseInvestissement) plateau.cases.get(j.getPosition()-1); // on prend la case Investissemtn dans le quel le joueur est present 
		
		
		Joueur j2 = joueurs.joueurs.get(2);  // on prend un joueur 
		j2.movePlayerTo(3); // on positionne un joueuer dans la case investissement
		CaseInvestissement c2 = (CaseInvestissement) plateau.cases.get(j2.getPosition()-1); // on prend la case Investissemtn dans le quel le joueur est present 
		
		try{
			c.action(j, etat, joueurs, joueursPerdu,1,indiceOfJouersToRemove);
			assertEquals(0, j.getInvestissement().size()); // on fait get(0) car l investissemtn est stocke dans la premiere indice 
			/*------------------- ASSERT EQUALS THAT SHOULD NOT WORK ----------------------------------- */
			
			c2.action(j2, etat, joueurs, joueursPerdu,1,indiceOfJouersToRemove);
			assertEquals(c2.getValeurNominale(),j2.getInvestissement().get(0).getValeurNominale(),0);
			/*------------------- ASSERT EQUALS THAT SHOULD WORK --------------------------------------- */
		}
		catch(JoueurNotFoundException ex) {
			throw new JoueurNotFoundException();
		}catch(CaseDoesNotExistEtatInvestissement ex){
			throw new CaseDoesNotExistEtatInvestissement();
		}
	}
}
