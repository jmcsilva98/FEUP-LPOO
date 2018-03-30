package dkeep.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import dkeep.logic.*;

public class TestFirstLevel { 

	String[][] map1={		 
			{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"X", " ", "O", " ", "I", " ", "X", " ", " ", "X"},
			{"X", " ", " ", "k", "X", "X", "X", " ", " ", "X"}, 
			{"X", "I", " ", " ", "I", " ", "X", " ", " ", "X"},
			{"X", "X", "S", " ", "X", "X", "X", " ", " ", "X"},
			{"I", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"I", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", "X", "X", " ", " ", " ", " ", " ", " ", "X"},
			{"X", " ", "I", " ", "I", " ", " ", "k", " ", "X"},
			{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
	};

	String[][] map2={		 
			{"X", "X", "X", "X"},
			{"X", " ", " ", "X"},
			{"X", " ", " ", "X"},
			{"X", " ", " ", "X"},
			{"X", "X", "S", "X"},

	};

	String[][] map3={		
			{" ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " "},
			{"G", " ", " ", " ", "G", " ", " "},
			{" ", " ", " ", " ", " ", " ", " "},
			{" ", " ", "G", " ", " ", " ", "G"},
			{" ", " ", " ", " ", " ", " ", " "},
			{"G", " ", " ", " ", "G", " ", " "},
			{" ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " "},

	};

	String[][] map4={		
			{" ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " "},
			{"g", " ", " ", " ", "g", " ", " "},
			{" ", " ", " ", " ", " ", " ", " "},
			{" ", " ", "g", " ", " ", " ", "g"},
			{" ", " ", " ", " ", " ", " ", " "},
			{"g", " ", " ", " ", "g", " ", " "},
			{" ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " "},
	};

	@Test
	public void testHeroIsCaptureByGuard() {

		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertFalse(gamestate.isGameOver());
		gamestate.heroMovement("R");
		assertFalse(gamestate.isGameOver());
	}

	@Test
	public void testHeroisCapturedDown() {
		Gamestate gamestate=new Gamestate(map3);
		Gamestate gamestate2=new Gamestate(map4);
		Hero hero= new Hero();
		hero.setX(4);
		hero.setY(4);
		gamestate.setHero(hero);
		gamestate2.setHero(hero);

		gamestate.heroMovement("D");
		gamestate2.heroMovement("D");
		assertFalse(gamestate.isFreeGuard());
		assertFalse(gamestate2.isFreeGuard());

		hero.setX(4);
		hero.setY(0);
		gamestate.heroMovement("D");
		gamestate2.heroMovement("D");
		assertFalse(gamestate.isFreeGuard());
		assertFalse(gamestate2.isFreeGuard());



	}

	@Test
	public void testHeroisCapturedUp() {
		Gamestate gamestate=new Gamestate(map3);
		Gamestate gamestate2=new Gamestate(map4);
		Hero hero= new Hero();
		hero.setX(4);
		hero.setY(4);
		gamestate.setHero(hero);
		gamestate2.setHero(hero);

		gamestate.heroMovement("U");
		gamestate2.heroMovement("U");
		assertFalse(gamestate.isFreeGuard());
		assertFalse(gamestate2.isFreeGuard());

		hero.setX(4);
		hero.setY(0);
		gamestate.heroMovement("U");
		gamestate2.heroMovement("U");
		assertFalse(gamestate.isFreeGuard());
		assertFalse(gamestate2.isFreeGuard());




	}

	@Test
	public void testHeroisCapturedLeft() {
		Gamestate gamestate=new Gamestate(map3);
		Gamestate gamestate2=new Gamestate(map4);

		Hero hero= new Hero();
		hero.setX(4);
		hero.setY(4);
		gamestate.setHero(hero);
		gamestate2.setHero(hero);

		gamestate.heroMovement("L");
		gamestate2.heroMovement("L");
		assertFalse(gamestate.isFreeGuard());
		assertFalse(gamestate2.isFreeGuard());



	}

	@Test
	public void testHeroisCapturedRight() {
		Gamestate gamestate=new Gamestate(map3);
		Gamestate gamestate2=new Gamestate(map4);
		Hero hero= new Hero();
		hero.setX(4);
		hero.setY(4);
		gamestate.setHero(hero);
		gamestate2.setHero(hero);

		gamestate.heroMovement("R");
		gamestate2.heroMovement("R");
		assertFalse(gamestate.isFreeGuard());
		assertFalse(gamestate2.isFreeGuard());

		hero.setX(4);
		hero.setY(0);
		gamestate.heroMovement("R");
		gamestate2.heroMovement("R");
		assertFalse(gamestate.isFreeGuard());
		assertFalse(gamestate2.isFreeGuard());


	}


	@Test 
	public void testRookiePath() {
		Gamestate gamestate=new Gamestate();
		RookieGuard rookie=new RookieGuard();
		gamestate.setGuard(rookie);

		String[] path= {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };
		assertEquals(rookie.movement, path);
		int length = rookie.getLength();


		while(length < 24) {
			rookie.movement();
			int xn = rookie.getX();
			int yn = rookie.getY();

			if(rookie.movement[length] == "U") 
				assertEquals(rookie.getX(), xn--);
			else if(rookie.movement[length] == "D") 
				assertEquals(rookie.getX(), xn++);
			else if(rookie.movement[length] == "R") 
				assertEquals(rookie.getY(), yn++);
			else if(rookie.movement[length] == "L") 
				assertEquals(rookie.getY(), yn--);
			length++;
		}

		rookie.movement();
		if(length == 24) {
			assertEquals(rookie.getLength(), 1);
		}

	}

	@Test(timeout = 1000)
	public void testDrunkenGuardMovement() {
		Gamestate gamestate=new Gamestate();
		DrunkenGuard drunkenGuard = new DrunkenGuard();
		int count = 0;
		boolean test1 = false, test2 = false;
		while((!test1 || !test2) && count < 500)
		{
			drunkenGuard.movement();
			if(drunkenGuard.getX()<0 || drunkenGuard.getX() >9) {
				assertEquals(drunkenGuard.getX(), 	drunkenGuard.getXn());
				test1 = true;
			}
			if(drunkenGuard.getY() <0 ||drunkenGuard.getY()>9) {
				assertEquals(drunkenGuard.getY(), 	drunkenGuard.getYn());
				test2 = true;
			}

			count++;

		}
	}


	/*@Test
	public void testGuardMovement() {
		Gamestate gamestate=new Gamestate();
		RookieGuard guard = new RookieGuard();
		gamestate.setGuard(guard);
		gamestate.currentMap[guard.getXn()][guard.getYn()] = " ";
		assertEquals(gamestate.currentMap[guard.getXn()][guard.getYn()] , " ");


		boolean empty = false;
		while(!empty) {
			gamestate.guardMovement();
			if(gamestate.testCase) {
				assertEquals(guard.getX(), guard.getXn());
				assertEquals(guard.getY(), guard.getYn());
				empty = true;

			}

		}
		assertEquals(gamestate.currentMap[guard.getX()][guard.getY()] , "G");

	}*/



	@Test
	public void testSuspiciousMovement() {
		SuspiciousGuard suspiciousGuard = new SuspiciousGuard();
	
		suspiciousGuard.position = 24;
		suspiciousGuard.movement();
		assertTrue(suspiciousGuard.backMovement);
		
	}
	
	
	
	@Test 
	public void testAuxMovement() {
		
		SuspiciousGuard suspicious = new SuspiciousGuard();
		suspicious.movement();
		suspicious.setX(3);
		suspicious.setY(3);
		suspicious.movement[suspicious.position] = "U";
		assertEquals(suspicious.getX()-1, 2);
		
		suspicious.movement();
		suspicious.setX(3);
		suspicious.setY(3);
		suspicious.movement[suspicious.position] = "D";
		assertEquals(suspicious.getX()+1, 4);
		
		suspicious.movement();
		suspicious.setX(3);
		suspicious.setY(3);
		suspicious.movement[suspicious.position] = "R";
		assertEquals(suspicious.getY()+1, 4);
		
		suspicious.movement();
		suspicious.setX(3);
		suspicious.setY(3);
		suspicious.movement[suspicious.position] = "L";
		assertEquals(suspicious.getY()-1, 2);
		
	}
	
}
