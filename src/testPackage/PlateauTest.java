package testPackage;
import plateauPackage.*;
import exceptionPackage.*;
import casePackage.*;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import casePackage.CaseInvestissement;
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
}
