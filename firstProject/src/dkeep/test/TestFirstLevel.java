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
	public void testHeroisFree() {
		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertTrue(gamestate.isFreeGuard());

	}

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

		assertTrue(gamestate.isFreeGuardY());

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

		rookie.setLength(1);
		int length;
		

		for(length = rookie.getLength(); length < 24; length++)
		{				
			rookie.movement();
			int xn = rookie.getX();
			int yn = rookie.getY();	

			if(rookie.movement[length].equals("U")) 
				assertEquals(rookie.getX(), xn--);
			else if(rookie.movement[length].equals("D")) 
				assertEquals(rookie.getX(), xn++);
			else if(rookie.movement[length].equals("R")) 
				assertEquals(rookie.getY(), yn++);
			else 
				assertEquals(rookie.getY(), yn--);

		}
		rookie.movement();
		rookie.setLength(1);		
		assertEquals(rookie.getLength(), 1);


		/*while(length < 24) {
			rookie.movement();
			int xn = rookie.getX();
			int yn = rookie.getY();

			if(rookie.movement[length].equals("U")) 
				assertEquals(rookie.getX(), xn--);
			else if(rookie.movement[length].equals("D")) 
				assertEquals(rookie.getX(), xn++);
			else if(rookie.movement[length].equals("R")) 
				assertEquals(rookie.getY(), yn++);
			else if(rookie.movement[length].equals("L")) 
				assertEquals(rookie.getY(), yn--);

			length++;
		}*/




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
			assertTrue(drunkenGuard.functionWasCalled);
			if(drunkenGuard.getX()<0 || drunkenGuard.getX() > gamestate.getMap().length) {
				assertEquals(drunkenGuard.getX(), 	drunkenGuard.getXn());
				test1 = true;
			}
			if(drunkenGuard.getY() <0 ||drunkenGuard.getY()>gamestate.getMap().length) {
				assertEquals(drunkenGuard.getY(), 	drunkenGuard.getYn());
				test2 = true;
			}

			count++;

		} //VER QUAL DAS VERSOES E MELHOR
	}

	@Test
	public void testRandomMovement() {
		DrunkenGuard drunkenGuard = new DrunkenGuard();
		drunkenGuard.setX(5);
		drunkenGuard.setY(5);
		int x = drunkenGuard.getX();
		int y = drunkenGuard.getY();
		drunkenGuard.randomMovement();
		assertEquals( drunkenGuard.getX(), x+drunkenGuard.xMove);
		assertEquals( drunkenGuard.getY(), y+drunkenGuard.yMove);
	}



	/*@Test

	public void testDrunkenGuardMovement() {

		DrunkenGuard drunkenGuard = new DrunkenGuard();

		drunkenGuard.movement();
		drunkenGuard.setX(-2);
		drunkenGuard.setXn(-2);
		assertEquals(drunkenGuard.getX(), 	drunkenGuard.getXn());

		drunkenGuard.movement();
		drunkenGuard.setX(10);
		drunkenGuard.setXn(10);
		assertEquals(drunkenGuard.getX(), 	drunkenGuard.getXn());

		drunkenGuard.movement();
		drunkenGuard.setY(-2);
		drunkenGuard.setYn(-2);
		assertEquals(drunkenGuard.getY(), 	drunkenGuard.getYn());

		drunkenGuard.movement();
		drunkenGuard.setY(10);
		drunkenGuard.setYn(10);
		assertEquals(drunkenGuard.getY(), 	drunkenGuard.getYn());



	}*/


	@Test
	public void testGuardMovement() {
		Gamestate gamestate=new Gamestate(map3);
		RookieGuard guard = new RookieGuard();
		gamestate.setGuard(guard);


		gamestate.currentMap[guard.getXn()][guard.getYn()] = " ";
		assertEquals(gamestate.currentMap[guard.getXn()][guard.getYn()] , " ");

		guard.setX(1);
		guard.setY(4);
		guard.setXn(1);
		guard.setYn(4);
		gamestate.setGuard(guard);
		gamestate.currentMap[guard.getX()][guard.getY()] = "G";
		gamestate.guardMovement();

		assertEquals(guard.getX(),guard.getXn());
		assertEquals(guard.getY(),guard.getYn());


		gamestate.currentMap[guard.getX()][guard.getY()] = " ";
		gamestate.guardMovement();
		assertEquals(gamestate.currentMap[guard.getX()][guard.getY()] , "G");

	}



	@Test
	public void testSuspiciousMovement() {
		SuspiciousGuard suspiciousGuard = new SuspiciousGuard();


		suspiciousGuard.position = 24;
		suspiciousGuard.movement();
		assertTrue(suspiciousGuard.backMovement);

		suspiciousGuard.position = 1;
		suspiciousGuard.movement();
		assertFalse(suspiciousGuard.backMovement);

		assertTrue(suspiciousGuard.functionWasCalled);


	}



	@Test (timeout = 1000)
	public void testAuxMovement() {

		SuspiciousGuard suspicious = new SuspiciousGuard();
		boolean up = false, down = false, right = false, left = false;
		while(!up || !down || !left || !right) {
			suspicious.movement();

			if(suspicious.movement[suspicious.position] == "U")
			{	
				suspicious.setX(3);
				suspicious.setY(3);
				assertEquals(suspicious.getX()-1, 2);
				up = true;
			}
			if(suspicious.movement[suspicious.position] =="D") {
				suspicious.setX(3);
				suspicious.setY(3);
				assertEquals(suspicious.getX()+1, 4);
				down = true;
			}
			if(suspicious.movement[suspicious.position] == "R") {
				suspicious.setX(3);
				suspicious.setY(3);
				assertEquals(suspicious.getY()+1, 4);
				right = true;
			}
			if(suspicious.movement[suspicious.position] == "L") {
				suspicious.setX(3);
				suspicious.setY(3);
				assertEquals(suspicious.getY()-1, 2);
				left = true;
			}

			if(suspicious.movement[suspicious.position] == null) {
				continue;
			}
		}
	}

}
