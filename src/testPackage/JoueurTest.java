package testPackage;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import exceptionPackage.JoueurBrokeException;
import exceptionPackage.JoueurListCreationFailedException;
import joueurPackage.Joueur;
import joueurPackage.JoueurAgressif;
import joueurPackage.JoueurPrudent;
import joueurPackage.Joueurs;

public class JoueurTest {

	 @Test
	  public void joueurcreditTest(){
		JoueurAgressif player = new JoueurAgressif(5000);
	    player.credit(5000);
	    assertEquals(player.getSoldesLiquide(),10000,0);
	  }

	  @Test
	  public void joueurTransferToTest() throws JoueurBrokeException{
		  JoueurPrudent player1 = new JoueurPrudent(5000,10);
		  JoueurPrudent player2 = new JoueurPrudent(5000,10);
		  try{
			  player1.transferTo(player2,2000);
		  }catch(JoueurBrokeException ex){
			  throw new JoueurBrokeException();
		  }
	    assertEquals(player2.getSoldesLiquide(),7000,0);
	  }

	  @Test(expected=JoueurBrokeException.class)
	  public void joueurTransferToJoueurBrokeExceptionTest() throws JoueurBrokeException{
		  JoueurAgressif player1 = new JoueurAgressif(5000);
		  JoueurAgressif player2 = new JoueurAgressif(5000);
	    try{
	    	player1.transferTo(player2,6000);
	    }catch(JoueurBrokeException ex){
	    	throw new JoueurBrokeException();
	    }
	  }
	  
	  @Test
	  public void joueurMoveTest(){
		  JoueurAgressif player = new JoueurAgressif(5000);
		  player.movePlayerTo(5);
		  assertEquals(player.getPosition(),5,0);
	  }
	  
	  @Test
	  public void joueursInitTest() throws JoueurListCreationFailedException{
		  int[] valInvestMax = {2,3,2,3,3};
		  try{
			  Joueurs joueurs = new Joueurs(5,5, 5000, valInvestMax);
			  assertEquals(joueurs.getJoueurs().size(),10);
			  Iterator<Joueur> iter = joueurs.getJoueurs().iterator();
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

}
