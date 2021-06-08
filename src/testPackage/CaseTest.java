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
	public void caseInvestissementTest() throws PlateauCreationFailedException, JoueurListCreationFailedException {
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		ConfigurationJeu configs = new ConfigurationJeu(2, 2, 5000, 100000, invest, "Capitaliste");
		Etat etat = new Etat(configs);
		Plateau plateau = new Plateau(configs,etat); 
		Joueurs joueurs = new Joueurs(configs);
		Joueurs joueursPerdu = new Joueurs();
		Joueur j = joueurs.joueurs.get(1);
		j.movePlayerTo(1);
		CaseInvestissement c = (CaseInvestissement) plateau.cases.get(j.getPosition()-1);
		
		try{
			c.action(j, etat, joueurs, joueursPerdu,1);
			assertEquals(joueurs.joueurs.size(),3);
		}
		catch(JoueurNotFoundException ex) {
			
		}catch(CaseDoesNotExistEtatInvestissement ex){
			
		}
	}
}
