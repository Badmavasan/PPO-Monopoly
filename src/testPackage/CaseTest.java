/*
 * Ce Fichier est un fichier de test des action de chaque case. on fait 2 types de test, les test aui verifie si la bonne valeur est renvoye et le test qui verifie
 * si les exceptions sont declencher ou pas. 
 */

package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import casePackage.CaseBureauFinancesPubliques;
import casePackage.CaseInvestissement;
import casePackage.CaseLoiAntitrust;
import casePackage.CaseSubvention;
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
		j.movePlayerTo(1); // on positionne un joueuer dans une case investissement
		CaseInvestissement c = (CaseInvestissement) plateau.cases.get(j.getPosition()-1); // on prend la case Investissemtn dans le quel le joueur est present 
		
		Joueur j2 = joueurs.joueurs.get(2);  // on prend un joueur 
		j2.movePlayerTo(3); // on positionne un joueuer dans une case BureauFinancesPubliques
		CaseInvestissement c2 = (CaseInvestissement) plateau.cases.get(j2.getPosition()-1); // on prend la case Investissemtn dans le quel le joueur est present 
		
		/*----------------------------------------------------------------------------------*/
		
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
	
	@Test
	public void caseBureauFinancesPubliquesTest() throws PlateauCreationFailedException, JoueurListCreationFailedException {
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
		j.movePlayerTo(2); // on positionne un joueuer dans une case burureau finances publiques
		CaseBureauFinancesPubliques c = (CaseBureauFinancesPubliques) plateau.cases.get(j.getPosition()-1); // on prend la case Investissemtn dans le quel le joueur est present 
		double soldesLiquideApresAction = j.getSoldesLiquide() - j.getSoldesLiquide()*c.getImpotPercentage();
		c.action(j, etat, joueurs, joueursPerdu,1,indiceOfJouersToRemove);
		
		/*----------------------------------------------------------------------------------*/
		
		assertEquals(soldesLiquideApresAction, j.getSoldesLiquide(),0);  
		/*------------------- ASSERT EQUALS THAT SHOULD WORK ----------------------------------- */
		
		/*----------------------------------------------------------------------------------*/
	}
	
	@Test
	public void caseSubventionTest() throws PlateauCreationFailedException, JoueurListCreationFailedException, EtatBrokeException {
		/*--------------------------- INITIALISATION -------------------------------------- */
		
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		ConfigurationJeu configs = new ConfigurationJeu(2, 2, 40000, 100000, invest, "Capitaliste");
		Etat etat = new Etat(configs);
		Plateau plateau = new Plateau(configs,etat);  
		Joueurs joueurs = new Joueurs(configs); // on l'initialise meme si ce n'est pas necessaire car on en a besoin pour la methode action de la classe
		
		/*----------------------------------------------------------------------------------*/
		
		Joueur j = joueurs.joueurs.get(1);  // on prend un joueur 
		j.movePlayerTo(10); // on positionne un joueuer dans une case subvention 
		CaseSubvention c = (CaseSubvention) plateau.cases.get(j.getPosition()-1);
		double soldesLiquideApreAction = j.getSoldesLiquide() + c.getMontant();
		c.action(j, etat);
		
		/*----------------------------------------------------------------------------------*/
		
		assertEquals(soldesLiquideApreAction,j.getSoldesLiquide(),0);
		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/
		
		/*----------------------------------------------------------------------------------*/
	}
	
	@Test(expected=EtatBrokeException.class)
	public void caseSubventionTest2() throws PlateauCreationFailedException, JoueurListCreationFailedException, EtatBrokeException {
		/*--------------------------- INITIALISATION -------------------------------------- */
		
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		ConfigurationJeu configs = new ConfigurationJeu(2, 2, 40000, 100, invest, "Capitaliste");
		Etat etat = new Etat(configs);
		Plateau plateau = new Plateau(configs,etat);  
		Joueurs joueurs = new Joueurs(configs); // on l'initialise meme si ce n'est pas necessaire car on en a besoin pour la methode action de la classe
		
		/*----------------------------------------------------------------------------------*/
		
		Joueur j = joueurs.joueurs.get(1);  // on prend un joueur 
		j.movePlayerTo(10); // on positionne un joueuer dans une case subvention 
		CaseSubvention c = (CaseSubvention) plateau.cases.get(j.getPosition()-1);
		c.action(j, etat);
		
		/*----------------------------------------------------------------------------------*/

		/*------------------- SHOULD THROW EXCEPTION  -------------------------------*/
		
		/*----------------------------------------------------------------------------------*/
	}
	
	@Test
	public void caseLoiAntitrustTest() throws PlateauCreationFailedException, JoueurListCreationFailedException, JoueurNotFoundException, CaseDoesNotExistEtatInvestissement, PlayerHasNoInvestissementException {
		/*--------------------------- INITIALISATION -------------------------------------- */
		
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		ConfigurationJeu configs = new ConfigurationJeu(2, 2, 4000000, 100000, invest, "Capitaliste");
		Etat etat = new Etat(configs);
		Plateau plateau = new Plateau(configs,etat);  
		Joueurs joueurs = new Joueurs(configs); // on l'initialise meme si ce n'est pas necessaire car on en a besoin pour la methode action de la classe
		Joueurs joueursPerdu = new Joueurs(); // on l'initialise meme si ce n'est pas necessaire car on en a besoin pour la methode action de la classe
		List<Integer> indiceOfJouersToRemove = new ArrayList<Integer>(); // on en a besoin si jamais le joeuur n a pas d'argent
		
		/*----------------------------------------------------------------------------------*/
		
		Joueur j = joueurs.joueurs.get(1);  // on prend un joueur 
		j.movePlayerTo(5);
		CaseLoiAntitrust c = (CaseLoiAntitrust) plateau.cases.get(j.getPosition()-1);;
		CaseInvestissement c2 = new CaseInvestissement(2, 80000, 0.5); // on initialise une case avec indice 2 car dans le plateauy a l indice 2 on a une case investissemnt, de plus on utilise pas directemet celui du p[lateau car on veut une valeur nominale de la case plus petit 
		etat.addToInvestissement(c2); // on ajoute la case investissemnt au joueur 
		int nbCaseInvestissement = etat.getList().size(); // on stocke le nombre de case investissement avant de enlever une case 
		c2.action(j, etat, joueurs, joueursPerdu,1,indiceOfJouersToRemove); // on ajoute la case au joueur , il a assez d argent donc ca passe 
		c.action(j, etat, plateau); // on fait l action de loi anti trust aui doit eliminer la case 
		
		/*----------------------------------------------------------------------------------*/
		
		assertEquals(0, j.getInvestissement().size());
		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/
		assertEquals(nbCaseInvestissement, etat.getList().size());
		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/
		
		/*----------------------------------------------------------------------------------*/
	}
	
	// on ne fait pas de test pour la case Repos 
}
