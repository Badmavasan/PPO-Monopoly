package testPackage;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import casePackage.CaseInvestissement;
import configurationPackage.ConfigurationJeu;
import exceptionPackage.CaseDoesNotExistEtatInvestissement;
import exceptionPackage.EtatBrokeException;
import etatPackage.Etat;

public class EtatTest {
	
	@Test
	public void addToInvestissementTest (){
		/*--------------------------- INITIALISATION -------------------------------------- */

		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
		Etat etat= new Etat(configs);
		
		//création de la case à ajouter
		CaseInvestissement new_case = new CaseInvestissement(3, 200, 30.0);
		etat.addToInvestissement(new_case);
		
		assertEquals(etat.getList().get(0).getBenefice(),30.0,0);
		
		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/
		/*----------------------------------------------------------------------------------*/
	}
	
	@Test
	public void crediterTest(){
		/*--------------------------- INITIALISATION -------------------------------------- */

		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
		Etat etat= new Etat(configs);
		
		etat.crediter(200.0);
		assertEquals(etat.getSoldesLiquide(),100200.0,0);
		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/
		
		etat.crediter(3000.0);
		assertEquals(etat.getSoldesLiquide(),103200.0,0);
		
		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/
		
		/*----------------------------------------------------------------------------------*/
	}
	
	@Test
	public void deductTest() throws EtatBrokeException {
		/*--------------------------- INITIALISATION -------------------------------------- */

		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
		Etat etat= new Etat(configs);
		
		try{
			etat.deduct(200.0);
		}catch (EtatBrokeException ex){
			throw new EtatBrokeException();
		}
		assertEquals(etat.getSoldesLiquide(),99800.0,0);
		
		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/

		try{
			etat.deduct(2000.0);
		}catch (EtatBrokeException ex){
			throw new EtatBrokeException();
		}
		assertEquals(etat.getSoldesLiquide(),97800.0,0);

		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/
		
		/*----------------------------------------------------------------------------------*/
	}
	
	@Test (expected=EtatBrokeException.class)
	public void deductExceptionTest() throws EtatBrokeException {
		/*--------------------------- INITIALISATION -------------------------------------- */

		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
		Etat etat= new Etat(configs);
		
		try{
			etat.deduct(200000.0); //on retire 200000 alors que l'état n'a que 100000
		}catch (EtatBrokeException ex){
			throw new EtatBrokeException();
		}
		/*----------------------------------------------------------------------------------*/

		/*------------------- SHOULD THROW EXCEPTION  -------------------------------*/
		
		/*----------------------------------------------------------------------------------*/
	}
	
	@Test 
	public void removeInvestissementByIndiceTest() throws CaseDoesNotExistEtatInvestissement {
		/*--------------------------- INITIALISATION -------------------------------------- */
		
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
		Etat etat= new Etat(configs);
		
		/*-----------------Nouvelle Case à ajouter puis retirer------------*/
		CaseInvestissement c = new CaseInvestissement(0,200,20.0);
		etat.addToInvestissement(c);
		assertEquals(etat.getList().size(),1,0); //vérification qu'elle a bien été ajoutée
		try{
			etat.removeInvestissementByIndice(0);
		}catch (CaseDoesNotExistEtatInvestissement ex){
			throw new CaseDoesNotExistEtatInvestissement();
		}
		assertEquals(etat.getList().size(),0,0); //vérification que la fonction l'a bien retirée
		
		/*------------------- ASSERT EQUALS THAT SHOULD WORK -------------------------------*/
		
		/*----------------------------------------------------------------------------------*/
	}
	
	@Test(expected=CaseDoesNotExistEtatInvestissement.class)
	public void removeInvestissementByIndiceExceptionTest() throws CaseDoesNotExistEtatInvestissement {
		/*--------------------------- INITIALISATION -------------------------------------- */
		
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
		Etat etat= new Etat(configs);
		
		/*-----------------Nouvelle Case à ajouter puis retirer------------*/
		CaseInvestissement c = new CaseInvestissement(0,200,20.0);
		etat.addToInvestissement(c);
		//assertEquals(etat.getList().size(),1,0); //vérification qu'elle a bien été ajoutée
		try{
			etat.removeInvestissementByIndice(1);
		}catch (CaseDoesNotExistEtatInvestissement ex){
			throw new CaseDoesNotExistEtatInvestissement();
		}
		/*----------------------------------------------------------------------------------*/

		/*------------------- SHOULD THROW EXCEPTION  -------------------------------*/
		
		/*----------------------------------------------------------------------------------*/
	}
	
	
	
}
