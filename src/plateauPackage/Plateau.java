package plateauPackage;
import casePackage.*;
import exceptionPackage.*;

import java.util.*;

public class Plateau {
	protected List<Case> cases;
	
	public Plateau() throws PlateauCreationFailedException{
		String[] ref = {"r","i","bfpa","i","i","at","i","i","r","i","s","i","i","bfpp","i","bfpa","r","i","bfpa","i","i","i","bfpp","i","r","at","i","bfpa","i","i","bfpp","i"};
		// on inialise les valeurs du plateau on va contruire le plateau en fonction de ca nature
		// i: investissement
		// s: subvention 
		// bfpa  : bureau finances publique argent à taxer
		// bfpp : investissement à taxer
		// r : repos
		// at : anti trust 
		// Les valeurs seront genere aleatoirement 
		int[] investissementVal = {400000,70,540,730,1300,5340,5340,70,100,1000,5000,4300,100,400000,5000,400000,10000,730};
		double[] investissementPour = {5,10,10,7,2,10,10,10,20,5,3,5,20,0.5,3,5,1,7};
		int[] atVal= {3500,40000};
		int[] bfpPour = {5,1,10,15,2,10,5};
		int[] sub = {1000};
		
		cases = new ArrayList<Case>();
		int parcours_investissementVal = 0;
		int parcours_investissementPour = 0;
		int parcours_atVal = 0;
		int parcours_bfpPour = 0;
		int parcours_sub = 0;
		for (String i : ref){
			if(i=="i"){
				try{
					CaseInvestissement caseIn = new CaseInvestissement(investissementVal[parcours_investissementVal], investissementPour[parcours_investissementPour]);
					parcours_investissementVal++;
					parcours_investissementPour++;
					cases.add(caseIn);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="bfpa"){
				try{
					CaseBureauFinancesPubliques caseBfp = new CaseBureauFinancesPubliques(bfpPour[parcours_bfpPour],true);
					parcours_bfpPour++;
					cases.add(caseBfp);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="bfpp"){
				try{
					CaseBureauFinancesPubliques caseBfp = new CaseBureauFinancesPubliques(bfpPour[parcours_bfpPour],false);
					parcours_bfpPour++;
					cases.add(caseBfp);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="s"){
				try{
					CaseSubvention caseSub = new CaseSubvention(sub[parcours_sub]);
					parcours_sub++;
					cases.add(caseSub);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="r"){
				try{
					CaseRepos caseRep = new CaseRepos();
					cases.add(caseRep);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="at"){
				try{
					CaseLoiAntitrust caseAt = new CaseLoiAntitrust(atVal[parcours_atVal]);
					parcours_atVal++;
					cases.add(caseAt);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else{
				throw new PlateauCreationFailedException();
			}
		}
		
		
	}
	
	public List<Case> getCases(){
		return this.cases;
	}
	
}
