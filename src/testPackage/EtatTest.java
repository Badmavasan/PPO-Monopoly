package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import casePackage.CaseInvestissement;
import configurationPackage.ConfigurationJeu;
import exceptionPackage.EtatBrokeException;
import exceptionPackage.JoueurListCreationFailedException;
import etatPackage.Etat;

public class EtatTest {
	
	@Test
	public void addToInvestissementTest (){
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
		CaseInvestissement new_case = new CaseInvestissement(3, 200, 30.0);
		Etat etat= new Etat(configs);
		etat.addToInvestissement(new_case);
		assertEquals(etat.getList().get(0).getBenefice(),30.0,0);
	}
	
	@Test
	public void crediterTest(){
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
	}
	
	@Test
	public void deductTest() throws EtatBrokeException {
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
	}
	
	
}
