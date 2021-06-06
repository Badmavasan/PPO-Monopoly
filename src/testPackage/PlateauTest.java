package testPackage;
import plateauPackage.*;
import configurationPackage.*;
import exceptionPackage.*;
import casePackage.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
public class PlateauTest {
	
	@Test
	public void TestNombreCasesInvesstissement() throws PlateauCreationFailedException{
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		
		try{
			ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "NeoLiberal");
			Plateau plat = new Plateau(configs);
			int somme = 0;
		    Iterator<Case> iter = plat.getCases().iterator();
		    while(iter.hasNext()){
		    	if(iter.next() instanceof CaseInvestissement){
		    		somme = somme + 1;
		    	}
		    }			
			assertEquals(18,somme);
		}catch(PlateauCreationFailedException ex){
			throw new PlateauCreationFailedException();
		}
	}
	
	@Test
	public void TestNombreCasesSubvention() throws PlateauCreationFailedException{
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		
		try{
			ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "NeoLiberal");
			Plateau plat = new Plateau(configs);
			int somme = 0;
		    Iterator<Case> iter = plat.getCases().iterator();
		    while(iter.hasNext()){
		    	if(iter.next() instanceof CaseSubvention){
		    		somme = somme + 1;
		    	}
		    }			
			assertEquals(1,somme);
		}catch(PlateauCreationFailedException ex){
			throw new PlateauCreationFailedException();
		}
	}
	
	@Test
	public void TestNombreCasesRepos() throws PlateauCreationFailedException{
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		
		try{
			ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "NeoLiberal");
			Plateau plat = new Plateau(configs);
			int somme = 0;
		    Iterator<Case> iter = plat.getCases().iterator();
		    while(iter.hasNext()){
		    	if(iter.next() instanceof CaseRepos){
		    		somme = somme + 1;
		    	}
		    }			
			assertEquals(4,somme);
		}catch(PlateauCreationFailedException ex){
			throw new PlateauCreationFailedException();
		}
	}
	
	@Test
	public void TestNombreCasesBureauFinancesPubliqueArgent() throws PlateauCreationFailedException{
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		
		try{
			ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "NeoLiberal");
			Plateau plat = new Plateau(configs);
			int somme = 0;
		    Iterator<Case> iter = plat.getCases().iterator();
		    while(iter.hasNext()){
		    	Case x = iter.next();
		    	if(x instanceof CaseBureauFinancesPubliques){
		    		CaseBureauFinancesPubliques y = (CaseBureauFinancesPubliques) x;
		    		if(y.getTaxArgent()==true){
			    		somme = somme + 1;		    			
		    		}
		    	}
		    }			
			assertEquals(4,somme);
		}catch(PlateauCreationFailedException ex){
			throw new PlateauCreationFailedException();
		}
	}
	
	@Test
	public void TestNombreCasesBureauFinancesPubliquePartimoine() throws PlateauCreationFailedException{
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		
		try{
			ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "NeoLiberal");
			Plateau plat = new Plateau(configs);
			int somme = 0;
		    Iterator<Case> iter = plat.getCases().iterator();
		    while(iter.hasNext()){
		    	Case x = iter.next();
		    	if(x instanceof CaseBureauFinancesPubliques){
		    		CaseBureauFinancesPubliques y = (CaseBureauFinancesPubliques) x;
		    		if(y.getTaxArgent()==false){
			    		somme = somme + 1;		    			
		    		}
		    	}
		    }			
			assertEquals(3,somme);
		}catch(PlateauCreationFailedException ex){
			throw new PlateauCreationFailedException();
		}
	}
	
	@Test
	public void TestProfilePlateau() throws PlateauCreationFailedException{
		List<Integer> invest = new ArrayList<Integer>();
		invest.add(5);
		invest.add(4);
		invest.add(3);
		invest.add(2);
		invest.add(1);
		
		try{
			ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
			Plateau plat = new Plateau(configs);
			boolean finding = false;
		    Iterator<Case> iter = plat.getCases().iterator();
		    while(iter.hasNext()){
		    	Case x = iter.next();
		    	if(x instanceof CaseSubvention){
		    		CaseSubvention y = (CaseSubvention) x;
		    		if(y.getMontant()==10000){
			    		finding = true;		    			
		    		}
		    	}
		    }			
			assertEquals(true,finding);
		}catch(PlateauCreationFailedException ex){
			throw new PlateauCreationFailedException();
		}
	}
}
