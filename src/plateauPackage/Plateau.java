package plateauPackage;
import casePackage.*;
import configurationPackage.*;
import etatPackage.*;

import exceptionPackage.*;

import java.util.*;

public class Plateau {
	public List<Case> cases;
	
	public Plateau(ConfigurationJeu configs,Etat etat) throws PlateauCreationFailedException{
		String[] ref = {"r","i","bfpa","i","i","at","i","i","r","i","s","i","i","bfpp","i","bfpa","r","i","bfpa","i","i","i","bfpp","i","r","at","i","bfpa","i","i","bfpp","i"};
		// on inialise les valeurs du plateau on va contruire le plateau en fonction de ca nature
		// i: investissement
		// s: subvention 
		// bfpa  : bureau finances publique argent à taxer
		// bfpp : investissement à taxer
		// r : repos
		// at : anti trust 
		// Les valeurs sont entré en dure mais on peut les generer automatiquement
		
		double[] atVal = new double[2]; // L'etat defini des lois d'antitrust leger 
		double[] bfpPour = new double[7];
		int sub = 0;
		double[] investissementPour = new double[18];
		if(configs.getProfile().equals("NeoLiberal")) {
			/* Initialisation des lois anti Trust */ 
			atVal[0] = 2000;
			atVal[1] = 20000;
			/* L'etat defini des lois d'antitrust leger */ 
			
			/* Intialisation des pourcentages de taxes */
			bfpPour[0] = 0.05;
			bfpPour[0] = 0.01;
			bfpPour[0] = 0.1;
			bfpPour[0] = 0.15;
			bfpPour[0] = 0.2;
			bfpPour[0] = 0.1;
			bfpPour[0] = 0.5;
			/* L etat defini des pourcentages de taxes  normal */ 
			
			/* Intialisation de subvention */
			sub = 1000;
			/* L etat fixe une subvention normal */
			
			/* Initialisation de pourcentage d'investissement */
			investissementPour[0] = 0.05;
			investissementPour[1] = 0.10;
			investissementPour[2] = 0.10;
			investissementPour[3] = 0.07;
			investissementPour[4] = 0.02;
			investissementPour[5] = 0.10;
			investissementPour[6] = 0.10;
			investissementPour[7] = 0.10;
			investissementPour[8] = 0.20;
			investissementPour[9] = 0.05;
			investissementPour[10] = 0.03;
			investissementPour[11] = 0.05;
			investissementPour[12] = 0.20;
			investissementPour[13] = 0.005;
			investissementPour[14] = 0.03;
			investissementPour[15] = 0.05;
			investissementPour[16] = 0.01;
			investissementPour[17] = 0.07;
			/* l etat fixe des investissement normal */
		}
		else if(configs.getProfile().equals("Socialiste")) {
			
			/* Initialisation des lois anti Trust */ 
			atVal[0] = 3500;
			atVal[1] = 40000;
			/* L'etat defini des lois d'antitrust normal */ 
			
			/* Intialisation des pourcentages de taxes */
			bfpPour[0] = 0.07;
			bfpPour[0] = 0.03;
			bfpPour[0] = 0.13;
			bfpPour[0] = 0.20;
			bfpPour[0] = 0.07;
			bfpPour[0] = 0.11;
			bfpPour[0] = 0.8;
			/* L'etat fixe beaucoup de taxe donc on augmente le pourcentage de tax */ 
			
			/* Intialisation de subvention */
			sub = 1000;
			/* L etat fixe une subvention normal */
			
			/* Initialisation de pourcentage d'investissement */
			investissementPour[0] = 0.5;
			investissementPour[1] = 0.10;
			investissementPour[2] = 0.10;
			investissementPour[3] = 0.07;
			investissementPour[4] = 0.02;
			investissementPour[5] = 0.10;
			investissementPour[6] = 0.10;
			investissementPour[7] = 0.10;
			investissementPour[8] = 0.20;
			investissementPour[9] = 0.05;
			investissementPour[10] = 0.03;
			investissementPour[11] = 0.05;
			investissementPour[12] = 0.20;
			investissementPour[13] = 0.005;
			investissementPour[14] = 0.03;
			investissementPour[15] = 0.05;
			investissementPour[16] = 0.01;
			investissementPour[17] = 0.07;
			/* l etat fixe des investissement normal */
 
		}
		else if(configs.getProfile().equals("Capitaliste")) {
			
			/* Initialisation des lois anti Trust */ 
			atVal[0] = 7000;
			atVal[1] = 50000;
			/* L'etat defini des lois d'antitrust normal */ 
			
			/* Intialisation des pourcentages de taxes */
			bfpPour[0] = 0.07;
			bfpPour[0] = 0.03;
			bfpPour[0] = 0.13;
			bfpPour[0] = 0.20;
			bfpPour[0] = 0.07;
			bfpPour[0] = 0.11;
			bfpPour[0] = 0.08;
			/* L'etat fixe beaucoup de taxe donc on augmente le pourcentage de tax */ 
			
			/* Intialisation de subvention */
			sub = 10000;
			/* L etat fixe une subvention normal */
			
			/* Initialisation de pourcentage d'investissement */
			investissementPour[0] = 0.10;
			investissementPour[1] = 0.20;
			investissementPour[2] = 0.20;
			investissementPour[3] = 0.17;
			investissementPour[4] = 0.12;
			investissementPour[5] = 0.20;
			investissementPour[6] = 0.20;
			investissementPour[7] = 0.20;
			investissementPour[8] = 0.30;
			investissementPour[9] = 0.10;
			investissementPour[10] = 0.30;
			investissementPour[11] = 0.10;
			investissementPour[12] = 0.30;
			investissementPour[13] = 0.05;
			investissementPour[14] = 0.07;
			investissementPour[15] = 0.10;
			investissementPour[16] = 0.12;
			investissementPour[17] = 0.17;
			/* l etat fixe des investissement normal */
			
		}
		else if(configs.getProfile().equals("Capitaliste")) {
			
			/* Initialisation des lois anti Trust */ 
			atVal[0] = 3500;
			atVal[1] = 40000;
			/* L'etat defini des lois d'antitrust normal */ 
			
			/* Intialisation des pourcentages de taxes */
			bfpPour[0] = 0.05;
			bfpPour[0] = 0.01;
			bfpPour[0] = 0.10;
			bfpPour[0] = 0.15;
			bfpPour[0] = 0.02;
			bfpPour[0] = 0.10;
			bfpPour[0] = 0.05;
			/* L'etat fixe beaucoup de taxe donc on augmente le pourcentage de tax */ 
			
			/* Intialisation de subvention */
			sub = 10000;
			/* L etat fixe une subvention eleve */
			
			/* Initialisation de pourcentage d'investissement */
			investissementPour[0] = 0.07;
			investissementPour[1] = 0.10;
			investissementPour[2] = 0.10;
			investissementPour[3] = 0.07;
			investissementPour[4] = 0.05;
			investissementPour[5] = 0.10;
			investissementPour[6] = 0.10;
			investissementPour[7] = 0.10;
			investissementPour[8] = 0.20;
			investissementPour[9] = 0.05;
			investissementPour[10] = 0.20;
			investissementPour[11] = 0.05;
			investissementPour[12] = 0.08;
			investissementPour[13] = 0.05;
			investissementPour[14] = 0.04;
			investissementPour[15] = 0.05;
			investissementPour[16] = 0.11;
			investissementPour[17] = 0.12;
			/* l etat fixe des investissement normal */
			
		}
		
		int[] investissementVal = {400000,70,540,730,1300,5340,5340,70,100,1000,5000,4300,100,400000,5000,400000,10000,730};
		
		
		
		
		cases = new ArrayList<Case>();
		int parcours_investissementVal = 0;
		int parcours_investissementPour = 0;
		int parcours_atVal = 0;
		int parcours_bfpPour = 0;
		int parcours_indice = 1;
		for (String i : ref){
			if(i=="i"){
				try{
					CaseInvestissement caseIn = new CaseInvestissement(parcours_indice,investissementVal[parcours_investissementVal], investissementPour[parcours_investissementPour]);
					parcours_investissementVal++;
					parcours_investissementPour++;
					cases.add(caseIn);
					etat.addToInvestissement(caseIn);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="bfpa"){
				try{
					CaseBureauFinancesPubliques caseBfp = new CaseBureauFinancesPubliques(parcours_indice,bfpPour[parcours_bfpPour],true);
					parcours_bfpPour++;
					cases.add(caseBfp);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="bfpp"){
				try{
					CaseBureauFinancesPubliques caseBfp = new CaseBureauFinancesPubliques(parcours_indice,bfpPour[parcours_bfpPour],false);
					parcours_bfpPour++;
					cases.add(caseBfp);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="s"){
				try{
					CaseSubvention caseSub = new CaseSubvention(parcours_indice,sub);
					cases.add(caseSub);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="r"){
				try{
					CaseRepos caseRep = new CaseRepos(parcours_indice);
					cases.add(caseRep);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else if(i=="at"){
				try{
					CaseLoiAntitrust caseAt = new CaseLoiAntitrust(parcours_indice,atVal[parcours_atVal]);
					parcours_atVal++;
					cases.add(caseAt);
				}catch(NullPointerException ex){
					throw new PlateauCreationFailedException(); 
				}
			}
			else{
				throw new PlateauCreationFailedException();
			}
			parcours_indice++;
		}
		
		
	}
	
}
