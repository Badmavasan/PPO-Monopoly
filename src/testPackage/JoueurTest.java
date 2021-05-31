package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptionPackage.JoueurBrokeException;
import joueurPackage.JoueurAgressif;
import joueurPackage.JoueurPrudent;

public class JoueurTest {

	 @Test
	  public void joueurcreditTest(){
		JoueurAgressif player = new JoueurAgressif(5000);
	    player.credit(5000);
	    assertEquals(player.getSoldesLiquide(),10000,0);
	  }

	  @Test
	  public void joueurTransferToTest() throws JoueurBrokeException{
		  JoueurPrudent player1 = new JoueurPrudent(5000);
		  JoueurPrudent player2 = new JoueurPrudent(5000);
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

}
