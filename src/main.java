import java.util.*;
import joueurPackage.*;
import etatPackage.*;
import casePackage.*;

public class main{
	void printJeu (Etat etat_jeu){
		for(int i=0; i<etat_jeu.getList().size();i++){
			if(etat_jeu.getList().get(i).getAppartenanceEtat() == true){
				System.out.println("La case" + etat_jeu.getList().get(i).getIndice()+ "appartient à l'état");
			}else{
				System.out.println("Le joueur " + etat_jeu.getList().get(i).getAppartenanceJoueur() +" dispose de " + 
			    etat_jeu.getList().get(i).getAppartenanceJoueur().getSoldesLiquide()+
			    " et se trouve à la case numéro " + etat_jeu.getList().get(i).getIndice());
			}
		}
	}
		
	int main(){
		int joueurs_agressifs=0;
		int joueurs_prudents=0;
		double capital_dep=0;
		double capital_etat=0;
		int invest=0;
		Scanner sc= new Scanner (System.in);
		/*
		int config=0;
		System.out.println("Choisissez le type de simulation souhaité:");
		System.out.println("1- NeoLiberal ");
		System.out.println("2-Socialiste ");
		System.out.println("3-Capitaliste ");
		System.out.println("4-Progressiste ");
		System.out.println("5-l'Europe après le Covid-19");
		i=sc.next();
		if(i==1){
		} else if(i==2){
		}else if (i==3){
		}else if (i==4){
		}else{
		}*/
		System.out.println("Choisissez le nombre de joueurs agressifs :");
		joueurs_agressifs=sc.nextInt();
		System.out.println("Choisissez le nombre de joueurs prudents :");
		joueurs_prudents=sc.nextInt();
		System.out.println("Entrez le capital des joueurs au départ:");
		capital_dep=sc.nextDouble();
		System.out.println("Entrez le capital de l'Etat au départ:");
		capital_etat=sc.nextDouble();
		System.out.println("Entrez le nombre d'investissement max des joueurs prudents:");
		invest=sc.nextInt();
		/*for (int i=0; i<joueurs_agressifs; i++){
			JoueurAgressif j= new JoueurAgressif(capital_dep);
		}
		for (int i=0; i<joueurs_prudents; i++){
			JoueurPrudent j= new JoueurPrudent(capital_dep,invest); 
		}*/
		//List<CaseInvestissement> liste_cases;
		//Etat etat= new Etat(capital_etat, liste_cases);
		
		/*
		 
		 */
		return 0;
}
}