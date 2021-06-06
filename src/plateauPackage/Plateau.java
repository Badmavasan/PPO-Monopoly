package plateauPackage;
import casePackage.*;
import configurationPackage.*;

import exceptionPackage.*;

import java.util.*;

public class Plateau {
	protected List<Case> cases;
	
	public Plateau(ConfigurationJeu configs) throws PlateauCreationFailedException{
		String[] ref = {"r","i","bfpa","i","i","at","i","i","r","i","s","i","i","bfpp","i","bfpa","r","i","bfpa","i","i","i","bfpp","i","r","at","i","bfpa","i","i","bfpp","i"};
		// on inialise les valeurs du plateau on va contruire le plateau en fonction de ca nature
		// i: investissement
		// s: subvention 
		// bfpa  : bureau finances publique argent à taxer
		// bfpp : investissement à taxer
		// r : repos
		// at : anti trust 
		// Les valeurs sont entré en dure mais on peut les generer automatiquement
		
		int[] atVal = new int[2]; // L'etat defini des lois d'antitrust leger 
		int[] bfpPour = new int[7];
		int sub = 0;
		double[] investissementPour = new double[18];
		if(configs.getProfile().equals("NeoLiberal")) {
			/* Initialisation des lois anti Trust */ 
			atVal[0] = 2000;
			atVal[1] = 20000;
			/* L'etat defini des lois d'antitrust leger */ 
			
			/* Intialisation des pourcentages de taxes */
			bfpPour[0] = 5;
			bfpPour[0] = 1;
			bfpPour[0] = 10;
			bfpPour[0] = 15;
			bfpPour[0] = 2;
			bfpPour[0] = 10;
			bfpPour[0] = 5;
			/* L etat defini des pourcentages de taxes  normal */ 
			
			/* Intialisation de subvention */
			sub = 1000;
			/* L etat fixe une subvention normal */
			
			/* Initialisation de pourcentage d'investissement */
			investissementPour[0] = 5;
			investissementPour[1] = 10;
			investissementPour[2] = 10;
			investissementPour[3] = 7;
			investissementPour[4] = 2;
			investissementPour[5] = 10;
			investissementPour[6] = 10;
			investissementPour[7] = 10;
			investissementPour[8] = 20;
			investissementPour[9] = 5;
			investissementPour[10] = 3;
			investissementPour[11] = 5;
			investissementPour[12] = 20;
			investissementPour[13] = 0.5;
			investissementPour[14] = 3;
			investissementPour[15] = 5;
			investissementPour[16] = 1;
			investissementPour[17] = 7;
			/* l etat fixe des investissement normal */
		}
		if(configs.getProfile().equals("Socialiste")) {
			
			/* Initialisation des lois anti Trust */ 
			atVal[0] = 3500;
			atVal[1] = 40000;
			/* L'etat defini des lois d'antitrust normal */ 
			
			/* Intialisation des pourcentages de taxes */
			bfpPour[0] = 7;
			bfpPour[0] = 3;
			bfpPour[0] = 13;
			bfpPour[0] = 20;
			bfpPour[0] = 7;
			bfpPour[0] = 11;
			bfpPour[0] = 8;
			/* L'etat fixe beaucoup de taxe donc on augmente le pourcentage de tax */ 
			
			/* Intialisation de subvention */
			sub = 1000;
			/* L etat fixe une subvention normal */
			
			/* Initialisation de pourcentage d'investissement */
			investissementPour[0] = 5;
			investissementPour[1] = 10;
			investissementPour[2] = 10;
			investissementPour[3] = 7;
			investissementPour[4] = 2;
			investissementPour[5] = 10;
			investissementPour[6] = 10;
			investissementPour[7] = 10;
			investissementPour[8] = 20;
			investissementPour[9] = 5;
			investissementPour[10] = 3;
			investissementPour[11] = 5;
			investissementPour[12] = 20;
			investissementPour[13] = 0.5;
			investissementPour[14] = 3;
			investissementPour[15] = 5;
			investissementPour[16] = 1;
			investissementPour[17] = 7;
			/* l etat fixe des investissement normal */
 
		}
		if(configs.getProfile().equals("Capitaliste")) {
			
			/* Initialisation des lois anti Trust */ 
			atVal[0] = 7000;
			atVal[1] = 50000;
			/* L'etat defini des lois d'antitrust normal */ 
			
			/* Intialisation des pourcentages de taxes */
			bfpPour[0] = 7;
			bfpPour[0] = 3;
			bfpPour[0] = 13;
			bfpPour[0] = 20;
			bfpPour[0] = 7;
			bfpPour[0] = 11;
			bfpPour[0] = 8;
			/* L'etat fixe beaucoup de taxe donc on augmente le pourcentage de tax */ 
			
			/* Intialisation de subvention */
			sub = 1000;
			/* L etat fixe une subvention normal */
			
			/* Initialisation de pourcentage d'investissement */
			investissementPour[0] = 10;
			investissementPour[1] = 20;
			investissementPour[2] = 20;
			investissementPour[3] = 17;
			investissementPour[4] = 12;
			investissementPour[5] = 20;
			investissementPour[6] = 20;
			investissementPour[7] = 20;
			investissementPour[8] = 30;
			investissementPour[9] = 10;
			investissementPour[10] = 30;
			investissementPour[11] = 10;
			investissementPour[12] = 30;
			investissementPour[13] = 5;
			investissementPour[14] = 7;
			investissementPour[15] = 10;
			investissementPour[16] = 12;
			investissementPour[17] = 17;
			/* l etat fixe des investissement normal */
			
		}
		if(configs.getProfile().equals("Capitaliste")) {
			
			/* Initialisation des lois anti Trust */ 
			atVal[0] = 3500;
			atVal[1] = 40000;
			/* L'etat defini des lois d'antitrust normal */ 
			
			/* Intialisation des pourcentages de taxes */
			bfpPour[0] = 5;
			bfpPour[0] = 1;
			bfpPour[0] = 10;
			bfpPour[0] = 15;
			bfpPour[0] = 2;
			bfpPour[0] = 10;
			bfpPour[0] = 5;
			/* L'etat fixe beaucoup de taxe donc on augmente le pourcentage de tax */ 
			
			/* Intialisation de subvention */
			sub = 10000;
			/* L etat fixe une subvention eleve */
			
			/* Initialisation de pourcentage d'investissement */
			investissementPour[0] = 7;
			investissementPour[1] = 10;
			investissementPour[2] = 10;
			investissementPour[3] = 7;
			investissementPour[4] = 5;
			investissementPour[5] = 10;
			investissementPour[6] = 10;
			investissementPour[7] = 10;
			investissementPour[8] = 20;
			investissementPour[9] = 5;
			investissementPour[10] = 20;
			investissementPour[11] = 5;
			investissementPour[12] = 8;
			investissementPour[13] = 5;
			investissementPour[14] = 4;
			investissementPour[15] = 5;
			investissementPour[16] = 11;
			investissementPour[17] = 12;
			/* l etat fixe des investissement normal */
			
		}
		
		int[] investissementVal = {400000,70,540,730,1300,5340,5340,70,100,1000,5000,4300,100,400000,5000,400000,10000,730};
		
		
		
		
		cases = new ArrayList<Case>();
		int parcours_investissementVal = 0;
		int parcours_investissementPour = 0;
		int parcours_atVal = 0;
		int parcours_bfpPour = 0;
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
					CaseSubvention caseSub = new CaseSubvention(sub);
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
