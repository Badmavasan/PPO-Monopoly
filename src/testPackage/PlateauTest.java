package testPackage;
import plateauPackage.*;
import exceptionPackage.*;
import casePackage.*;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
public class PlateauTest {
	
	@Test
	public void TestNombreCasesInvesstissement() throws PlateauCreationFailedException{
		try{
			Plateau plat = new Plateau();
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
		try{
			Plateau plat = new Plateau();
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
		try{
			Plateau plat = new Plateau();
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
		try{
			Plateau plat = new Plateau();
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
		try{
			Plateau plat = new Plateau();
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
}
