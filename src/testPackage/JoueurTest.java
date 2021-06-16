/*
 * Dans cette classe on test tout les methodes de joueur : crediter, debiter, tranfer d'argent (dans le cas de case investissement)
 * deplacer le joueur, initialisation de liste de joueurs, initalisation des joeurs en cas de profile NeoLiberal (difference de soldes liquides de depart entre joueur agressif et prudent)
 * intialisation des joeueurs en cas de profile Capitaliste : pas besoin cf Simulation ou on initialise capitaliste dans la fonction menu()
 */
package testPackage;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import casePackage.CaseInvestissement;
import configurationPackage.ConfigurationJeu;
import exceptionPackage.JoueurBrokeException;
import exceptionPackage.JoueurListCreationFailedException;
import exceptionPackage.PlayerInvestissementException;
import joueurPackage.Joueur;
import joueurPackage.JoueurAgressif;
import joueurPackage.JoueurPrudent;
import joueurPackage.Joueurs;

public class JoueurTest {

	 @Test
	  public void joueurcreditTest(){
		 /*--------------------------- INITIALISATION -------------------------------------- */
		 
		 JoueurAgressif player = new JoueurAgressif(5000,1);
	    
		 /*----------------------------------------------------------------------------------*/
		 
		 player.credit(5000);
		 
		 /*----------------------------------------------------------------------------------*/
		 
		 assertEquals(player.getSoldesLiquide(),10000,0);
		 /*------------------- ASSERT EQUALS THAT SHOULD WORK --------------------------------------- */
	  }

	  @Test
	  public void joueurTransferToTest() throws JoueurBrokeException{
		  JoueurPrudent player1 = new JoueurPrudent(5000,10,1);
		  JoueurPrudent player2 = new JoueurPrudent(5000,10,2);
		  try{
			  player1.transferTo(player2,2000);
		  }catch(JoueurBrokeException ex){
			  throw new JoueurBrokeException();
		  }
	    assertEquals(player2.getSoldesLiquide(),7000,0);
	  }

	  @Test(expected=JoueurBrokeException.class)
	  public void joueurTransferToJoueurBrokeExceptionTest() throws JoueurBrokeException{
		  JoueurAgressif player1 = new JoueurAgressif(5000,1);
		  JoueurAgressif player2 = new JoueurAgressif(5000,2);
	    try{
	    	player1.transferTo(player2,6000);
	    }catch(JoueurBrokeException ex){
	    	throw new JoueurBrokeException();
	    }
	  }
	  
	  @Test
	  public void joueurMoveTest(){
		  JoueurAgressif player = new JoueurAgressif(5000,1);
		  player.movePlayerTo(5);
		  assertEquals(player.getPosition(),6);
	  }
	  
	  @Test
	  public void joueursInitTest() throws JoueurListCreationFailedException{
		  List<Integer> invest = new ArrayList<Integer>();
			invest.add(5);
			invest.add(4);
			invest.add(3);
			invest.add(2);
			invest.add(1);
			ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "Capitaliste");
		  try{
			  Joueurs joueurs = new Joueurs(configs);
			  assertEquals(joueurs.joueurs.size(),10);
			  Iterator<Joueur> iter = joueurs.joueurs.iterator();
			  int somme1 =0;
			  int somme2 =0;
			  while(iter.hasNext()){
				  Joueur x = iter.next();
				  
				  if(x instanceof JoueurAgressif){
					  somme1++;
				  }
				  else if(x instanceof JoueurPrudent){
					  somme2++;
				  }
				  
			  }
			  assertEquals(5, somme1);
			  assertEquals(5, somme2);
		  }catch(JoueurListCreationFailedException ex){
			  throw new JoueurListCreationFailedException();
		  }
	  }
	  
	  @Test
	  public void joueursInitByProfileTest() throws JoueurListCreationFailedException{
		  List<Integer> invest = new ArrayList<Integer>();
			invest.add(5);
			invest.add(4);
			invest.add(3);
			invest.add(2);
			invest.add(1);
			ConfigurationJeu configs = new ConfigurationJeu(5, 5, 5000, 100000, invest, "NeoLiberal");
		  try{
			  Joueurs joueurs = new Joueurs(configs);
			  assertEquals(joueurs.joueurs.size(),10);
			  Iterator<Joueur> iter = joueurs.joueurs.iterator();
			  boolean finding = false;
			  while(iter.hasNext() && finding==false){
				  Joueur x = iter.next();
				  if(x.getSoldesLiquide()==3750) { // 5000/2 comme defini dans la classe joueur 
					  finding = true;
				  }
			  }
			  assertEquals(true, finding);
		  }
		  catch(JoueurListCreationFailedException ex) {
			  
		  }
	  }
	  
	  @Test
	  public void joueurPrudentMinMaxInvestissemtTest() throws PlayerInvestissementException {
		  CaseInvestissement c1 = new CaseInvestissement(1, 80000, 0.5);
		  CaseInvestissement c2 = new CaseInvestissement(2, 50000, 0.5);
		  
		  JoueurPrudent player1 = new JoueurPrudent(5000,10,1);
		  player1.addToInvestissement(c1);
		  player1.addToInvestissement(c2);
		  try {
			  CaseInvestissement rep1 = player1.getMaxInvestissement();
        	  assertEquals(rep1,c1);
        	  CaseInvestissement rep2 = player1.getMinInvestissement();
    		  assertEquals(rep2,c2);
		  }catch(PlayerInvestissementException ex){
			  throw new PlayerInvestissementException();
		  }		  
	  }
	  
	  @Test(expected=PlayerInvestissementException.class)
	  public void joueurPrudentMinMaxInvestissemtExceptionTest() throws PlayerInvestissementException {
		  CaseInvestissement c1 = new CaseInvestissement(1, 80000, 0.5);
		  CaseInvestissement c2 = new CaseInvestissement(2, 50000, 0.5);
		  
		  JoueurPrudent player1 = new JoueurPrudent(5000,10,1);
		  try {
			  CaseInvestissement rep1 = player1.getMaxInvestissement();
        	  assertEquals(rep1,c1);
        	  CaseInvestissement rep2 = player1.getMinInvestissement();
    		  assertEquals(rep2,c2);
		  }catch(PlayerInvestissementException ex){
			  throw new PlayerInvestissementException();
		  }	  
	  }
	  
	  @Test
	  public void joueurAgressifMinMaxInvestissemtTest() throws PlayerInvestissementException {
		  CaseInvestissement c1 = new CaseInvestissement(1, 80000, 0.5);
		  CaseInvestissement c2 = new CaseInvestissement(2, 50000, 0.5);
		  
		  JoueurAgressif player1 = new JoueurAgressif(5000,1);
		  player1.addToInvestissement(c1);
		  player1.addToInvestissement(c2);
		  try {
			  CaseInvestissement rep1 = player1.getMaxInvestissement();
        	  assertEquals(rep1,c1);
        	  CaseInvestissement rep2 = player1.getMinInvestissement();
    		  assertEquals(rep2,c2);
		  }catch(PlayerInvestissementException ex){
			  throw new PlayerInvestissementException();
		  }		  
	  }
	  
	  @Test(expected=PlayerInvestissementException.class)
	  public void joueurAgressifMinMaxInvestissemtExceptionTest() throws PlayerInvestissementException {
		  CaseInvestissement c1 = new CaseInvestissement(1, 80000, 0.5);
		  CaseInvestissement c2 = new CaseInvestissement(2, 50000, 0.5);
		  
		  JoueurAgressif player1 = new JoueurAgressif(5000,1);
		  try {
			  CaseInvestissement rep1 = player1.getMaxInvestissement();
        	  assertEquals(rep1,c1);
        	  CaseInvestissement rep2 = player1.getMinInvestissement();
    		  assertEquals(rep2,c2);
		  }catch(PlayerInvestissementException ex){
			  throw new PlayerInvestissementException();
		  }	  
	  }
	  
	  @Test
	  public void joueurRemoveInvestissementTest() throws PlayerInvestissementException {
		  CaseInvestissement c1 = new CaseInvestissement(1, 80000, 0.5);
		  JoueurAgressif player1 = new JoueurAgressif(5000,1);
		  player1.addToInvestissement(c1);
		  assertEquals(1, player1.getInvestissement().size());
		  player1.removeInvestissement(c1);
		  assertEquals(0, player1.getInvestissement().size());
	  }
	  
	  @Test(expected=PlayerInvestissementException.class)
	  public void joueurRemoveInvestissementExceptionTest() throws PlayerInvestissementException {
		  CaseInvestissement c1 = new CaseInvestissement(1, 80000, 0.5);
		  JoueurAgressif player1 = new JoueurAgressif(5000,1);
		  player1.removeInvestissement(c1);
		  assertEquals(0, player1.getInvestissement().size());
	  }
	   
}
