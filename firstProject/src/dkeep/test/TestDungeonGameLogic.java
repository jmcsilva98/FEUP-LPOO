package dkeep.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import dkeep.logic.Character;
import dkeep.logic.*;
public class TestDungeonGameLogic {

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
			{"X", " ", "X", "X", "X", " ", "X"},
			{"X", " ", "X", "G", " ", " ", "X"},
			{"X", " ", " ", " ", " ", " ", "X"},
			{"X", "G", " ", " ", " ", "G", "X"},
			{"X", " ", " ", " ", " ", " ", "X"},
			{"X", " ", "X", "G", " ", " ", "X"},

	};

	String[][] map4={		
			{"X", " ", "X", "X", "X", " ", "X"},
			{"X", " ", "X", "g", " ", " ", "X"},
			{"X", " ", " ", " ", " ", " ", "X"},
			{"X", "g", " ", " ", " ", "g", "X"},
			{"X", " ", " ", " ", " ", " ", "X"},
			{"X", " ", "X", "g", " ", " ", "X"},

	};
	@Test
	public void testHeroIsArmed() {
		Gamestate gamestate=new Gamestate();
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		gamestate.setLevel(1);
		assertFalse(gamestate.heroIsArmed());
		gamestate.setLevel(2);
		assertTrue(gamestate.heroIsArmed());
	}

	@Test
	public void testGetOgre() {
		Gamestate gamestate=new Gamestate();
		Ogre ogre = new Ogre();
		gamestate.setOgre(ogre);
		assertEquals(ogre, gamestate.getOgre());

	}

	@Test
	public void testGetOgres() {
		Gamestate gamestate=new Gamestate();
		Vector<Ogre> ogres = new Vector<Ogre>();
		gamestate.setOgres(ogres);
		ogres = gamestate.getOgres();
		assertEquals(ogres, gamestate.getOgres());

	}

	@Test
	public void testToString() {
		Gamestate gamestate=new Gamestate();
		assertEquals(gamestate.toString(), "XXXXXXXXXX\nXH  I X  X\nXXX XXX  X\n"
				+ "XI  I X  X\nXXX XXX  X\nI        X\nI        X\nXXX XXXX X\n"
				+ "X I I Xk X\nXXXXXXXXXX\n");
		gamestate.setLevel(2);
		assertEquals(gamestate.toString(), "XXXXXXXXX\nI      kX\nX       X\n"
				+"X       X\nX       X\nX C     X\nX       X\nX       X\nXXXXXXXXX\n");
	}

	@Test(timeout=1000)
	public void testStartConsole() {
		Gamestate gamestate=new Gamestate();
		DrunkenGuard drunken=new DrunkenGuard();
		RookieGuard rookie=new RookieGuard();
		SuspiciousGuard suspicious = new SuspiciousGuard();
		boolean dr = false, roo = false, sus = false;
		int count = 0;
		while((!dr || !roo || !sus) && count < 50 ) {
			gamestate.startConsole();
			if(gamestate.testCase0) {
				gamestate.setGuard(rookie);
				roo = true;
			}
			else if(gamestate.testCase1) {
				gamestate.setGuard(drunken); 
				dr  = true;	
			}		 
			else if(gamestate.testCase2) {
				gamestate.setGuard(suspicious);
				sus = true;
			}
			count++;
		}

	}

	@Test public void testStartApplication() {
		Gamestate gamestate=new Gamestate();
		Gamestate gamestate2=new Gamestate();
		Gamestate gamestate3=new Gamestate();
		Gamestate gamestate4=new Gamestate();

		gamestate.startApplication("Drunken",3 );
		gamestate2.startApplication("Rookie", 3);
		gamestate3.startApplication("Suspicious", 3);
		gamestate4.startApplication("", 3);

	}

	@Test public void testStart() {
		Gamestate gamestate=new Gamestate();
		Gamestate gamestate2=new Gamestate();
		Gamestate gamestate3=new Gamestate();
		Gamestate gamestate4=new Gamestate();
		Gamestate gamestate5=new Gamestate();
		Gamestate gamestate6=new Gamestate();
		Gamestate gamestate7=new Gamestate();


		gamestate.start(false, "Rookie", 2);
		gamestate2.start(false, "Drunken", 2);
		gamestate3.start(false, "Suspicious", 2);
		gamestate4.start(true, "Rookie", 2);
		gamestate5.start(true, "Drunken", 2);
		gamestate6.start(true, "Suspicious", 2);
		gamestate7.setLevelManualy(2);
		gamestate7.start(true, "Suspicious", 2);


	}





	@Test
	public void testSetMap() {
		Gamestate gamestate=new Gamestate();
		gamestate.setMap(map2);
		assertEquals(map2, gamestate.getMap());
	}

	@Test
	public void testSetVectorOgres() {
		Gamestate gamestate=new Gamestate();
		Vector<Ogre> ogres = new Vector<Ogre>();
		gamestate.setOgres(ogres);
		Gamestate gamestate2=new Gamestate();
		gamestate2.setOgres(3);
	}

	@Test
	public void testGameState() {

		Gamestate gamestate=new Gamestate();
		Gamestate gamestate2=new Gamestate();
		gamestate.gameState(0);
		assertTrue(gamestate.isGameOver());
		gamestate2.gameState(1);
		assertTrue(gamestate2.gameWon());

	}



	@Test
	public void testGetX() {
		Gamestate gamestate=new Gamestate();
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertEquals(gamestate.getHero().getX(), hero.getX());
	} 	

	@Test
	public void testGetY() {
		Gamestate gamestate=new Gamestate();
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertEquals(gamestate.getHero().getY(), hero.getY());
	} 	 

	@Test
	public void testGetXn() {
		Gamestate gamestate=new Gamestate();
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertEquals(gamestate.getHero().getXn(), hero.getXn());
	} 	

	@Test
	public void testGetYn() {
		Gamestate gamestate=new Gamestate();
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertEquals(gamestate.getHero().getYn(), hero.getYn());
	} 	 

	@Test 
	public void testRookiePath() {
		Gamestate gamestate=new Gamestate();
		RookieGuard rookie=new RookieGuard();
		gamestate.setGuard(rookie);

		String[] path= {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };
		assertEquals(rookie.rookieMovement, path);
		int length = 1;

		while(length < 24) {
			rookie.movement();
			int xn = rookie.getX();
			int yn = rookie.getY();

			if(rookie.rookieMovement[length] == "U") 
				assertEquals(rookie.getX(), xn--);
			else if(rookie.rookieMovement[length] == "D") 
				assertEquals(rookie.getX(), xn++);
			else if(rookie.rookieMovement[length] == "R") 
				assertEquals(rookie.getY(), yn++);
			else if(rookie.rookieMovement[length] == "L") 
				assertEquals(rookie.getY(), yn--);
			length++;
		}
	

	}

	@Test
	public void testGetMapLevel1() {
		Gamestate gamestate=new Gamestate();
		assertEquals(gamestate.getMap(), Map.map1);
	} 

	@Test
	public void testGetMapLevel2() {
		Gamestate gamestate=new Gamestate();
		gamestate.setLevel(2);
		assertEquals(gamestate.getMap(), Map.map2);
	} 

	@Test
	public void testHeroGetsWeapon() {
		Gamestate gamestate=new Gamestate(Map.map2);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		gamestate.setLevel(2);
		assertTrue(gamestate.heroIsArmed());

	}



	@Test
	public void testLevel() {
		Gamestate gamestate=new Gamestate();
		assertEquals(gamestate.getLevel(), 1);
	}

	@Test
	public void testMoveHeroIntoFreeCell() {

		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1); 
		hero.setY(1);
		gamestate.setHero(hero);
		CellPosition cell= gamestate.getHero().position();
		assertEquals(new CellPosition(1,1) , cell);
		gamestate.heroMovement("D");
		assertEquals(new CellPosition(2,1), gamestate.getHero().position());
	}

	@Test
	public void testMoveHeroIntoFreeCellWithKey() {

		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("D");
		gamestate.heroMovement("D");
		gamestate.heroMovement("R");
		gamestate.heroMovement("R");
		assertTrue(gamestate.getHero().hasKey);	
		gamestate.heroMovement("D");
		assertEquals(new CellPosition(3,3), gamestate.getHero().position());
	} 

	@Test
	public void testMoveHeroIntoFreeCellWithWeapon() {

		Gamestate gamestate=new Gamestate();
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		gamestate.setLevel(2);
		assertTrue(gamestate.heroIsArmed());
		CellPosition cell= gamestate.getHero().position();
		assertEquals(new CellPosition(1,1) , cell);
		gamestate.heroMovement("R");
		assertEquals(new CellPosition(1,2), gamestate.getHero().position());
	} 


	@Test
	public void testMoveHeroIntoWall() {

		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		CellPosition cell= gamestate.getHero().position();
		assertEquals(new CellPosition(1,1) , cell);
		gamestate.heroMovement("U");
		assertEquals(new CellPosition(1,1) , cell);
	}

	@Test
	public void testMoveHeroIntoClosedDoor() {


		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		CellPosition cell= gamestate.getHero().position();
		assertEquals(new CellPosition(1,1) , cell);
		gamestate.heroMovement("D");
		gamestate.heroMovement("D");
		assertEquals(new CellPosition(2,1), gamestate.getHero().position());
		gamestate.setLevelManualy(1);
		
		


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
	public void testGetsKey() {	
		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("D");
		gamestate.heroMovement("D");
		gamestate.heroMovement("R");
		gamestate.heroMovement("R");
		assertTrue(gamestate.getHero().hasKey);
		gamestate.setLevelManualy(1);
		
		}
	
	@Test
	public void testGetsKeyAndOpensDoors() {
		Gamestate gamestate=new Gamestate();
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setLevelManualy(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("R");
		gamestate.heroMovement("R");
		gamestate.heroMovement("D");
		gamestate.heroMovement("D");
		gamestate.heroMovement("D");
		gamestate.heroMovement("D");
		gamestate.heroMovement("D");
		gamestate.heroMovement("R");
		gamestate.heroMovement("R");
		gamestate.heroMovement("R");
		gamestate.heroMovement("R");
		gamestate.heroMovement("R");
		gamestate.heroMovement("D");
		gamestate.heroMovement("D");
		gamestate.heroMovement("L");
		assertTrue(gamestate.getHero().hasKey);	
		
		assertEquals(gamestate.currentMap[5][0], "S");
		assertEquals(gamestate.currentMap[6][0], "S");
	}

	@Test
	public void testHeroIsCaptureByOgre() {		
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
	public void testHeroHasNoKey() {

		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertFalse(gamestate.getHero().hasKey);	
	}

	@Test
	public void testHeroArrivesAtTheDoorWithoutTheKey() {


		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertFalse(gamestate.gameWon());	

	}

	@Test
	public void testHeroArrivesAtTheDoorWithTheKey() {


		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("D");
		//gamestate.heroMovement("D");
		gamestate.heroMovement("R");
		gamestate.heroMovement("D"); 
		gamestate.heroMovement("D");
		assertTrue(gamestate.gameWon());

	}

	@Test
	public void testChangeLevel() {
		Gamestate gamestate=new Gamestate(map2);
		gamestate.setLevelManualy(1);
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(2);
		gamestate.setHero(hero);
		gamestate.heroMovement("D");
		assertEquals(gamestate.getLevel(), 2);

	}


	@Test(timeout=1000)
	public void testSomeRandomBehaviour(){
		Gamestate gamestate=new Gamestate(map1);
		String move="";
		int count=0;
		Ogre ogre=new Ogre();
		ogre.setX(1);
		ogre.setY(2);
		gamestate.setOgre(ogre);
		boolean up=false, down=false,right=false, left=false;
		while(( !up && !down && !right && !left) || (count < 50) ) { //teste funciona com && mas n com ||
			move=ogre.movement();	
			if (move=="U")up=true;
			else if (move=="D") down=true;
			else if (move=="R") right=true; 
			else if (move=="L") left=true;
			else if (move ==null) count++;
		}

	}



	@Test
	public void testHeroisFree() {
		Gamestate gamestate=new Gamestate(map1);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertTrue(gamestate.isFree());

	}
	@Test
	public void testHeroisCapturedDown() {
		Gamestate gamestate=new Gamestate(map3);
		Gamestate gamestate2=new Gamestate(map4);
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(3);
		gamestate.setLevelManualy(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("D");
		gamestate2.setLevelManualy(1);
		gamestate2.setHero(hero);
		gamestate2.heroMovement("D");
		assertFalse(gamestate.isFree());
		assertFalse(gamestate2.isFree());

	}

	@Test
	public void testHeroisCapturedUp() {
		Gamestate gamestate=new Gamestate(map3);
		Gamestate gamestate2=new Gamestate(map4);
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(3);
		gamestate.setLevelManualy(1);
		gamestate.setHero(hero); 
		gamestate.heroMovement("U");
		gamestate2.setLevelManualy(1);
		gamestate2.setHero(hero);
		gamestate2.heroMovement("U");
		assertFalse(gamestate.isFree());
		assertFalse(gamestate2.isFree());

	}

	@Test
	public void testHeroisCapturedLeft() {
		Gamestate gamestate=new Gamestate(map3);
		Gamestate gamestate2 = new Gamestate(map4);
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(3);
		gamestate.setLevelManualy(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("L");
		gamestate2.setLevelManualy(1);
		gamestate2.setHero(hero);
		gamestate2.heroMovement("L");
		assertFalse(gamestate.isFree());
		assertFalse(gamestate2.isFree());

	}

	@Test
	public void testHeroisCapturedRight() {
		Gamestate gamestate=new Gamestate(map3);
		Gamestate gamestate2=new Gamestate(map4);
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(3);
		gamestate.setLevelManualy(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("R");
		gamestate2.setLevelManualy(1);
		gamestate2.setHero(hero);
		gamestate2.heroMovement("R");
		assertFalse(gamestate.isFree());
		assertFalse(gamestate2.isFree());
	}

	@Test
	public void testDrunkenGuardMovement() {
		Gamestate gamestate=new Gamestate();
		DrunkenGuard drunkenGuard = new DrunkenGuard();
		int x = drunkenGuard.getX() +Guard.randomGenerator(4) ;
		int y =  drunkenGuard.getY() +Guard.randomGenerator(4);
		drunkenGuard.movement();


		if(x<0 || x>9)
			assertEquals(drunkenGuard.getX(), 	drunkenGuard.getXn());
		else if(y<0 || y>9)
			assertEquals(drunkenGuard.getY(), 	drunkenGuard.getYn());

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
		if(gamestate.testCase) {
			assertEquals(guard.getX(), guard.getXn());
			assertEquals(guard.getY(), guard.getYn());

		}
		
		}
		assertEquals(gamestate.currentMap[guard.getX()][guard.getY()] , "G");


	}*/



	@Test
	public void testSuspiciousMovement() {
		Gamestate gamestate=new Gamestate();
		SuspiciousGuard suspiciousGuard = new SuspiciousGuard();
		int move = Guard.randomGenerator(5);
		int xn, yn;
		int x = suspiciousGuard.getX();
		int y = suspiciousGuard.getY();
		int suspl = suspiciousGuard.suspLength++;
		int suspos = suspiciousGuard.suspPos++;
		//suspiciousGuard.movement();
		/*
		if(move ==3) {
			if(suspiciousGuard.suspPos == suspiciousGuard.suspLength) {
				xn = Guard.randomGenerator(2);
				x+=xn;
				assertEquals(suspiciousGuard.getY(), suspiciousGuard.getYn());


				assertEquals(suspiciousGuard.suspLength, suspl);
				assertEquals(suspiciousGuard.suspPos, suspos);


			}

			else {
				assertFalse(suspiciousGuard.suspiciousBack);
				assertEquals(suspiciousGuard.suspPos, suspos);

			}
		}
		else if(move == 4) {
			if(suspiciousGuard.suspPos == suspiciousGuard.suspLength) {
				yn = Guard.randomGenerator(2);
				y+=yn;

				assertEquals(suspiciousGuard.suspLength, suspl);

			}
			else {
				assertFalse(suspiciousGuard.suspiciousBack);
				assertEquals(suspiciousGuard.suspPos, suspos);
			}
		}
		else {
			if(suspiciousGuard.suspiciousBack) {
				assertTrue(suspiciousGuard.suspiciousBack);
			}
			assertEquals(suspiciousGuard.suspPos, (suspos -2));
		}
		 */

	}




	@Test
	public void testEquals() {
		

		Gamestate gamestate = new Gamestate();
		
		CellPosition pos = new CellPosition(2, 2);
		CellPosition pos1 = new CellPosition(2, 2);
		DrunkenGuard guard = new DrunkenGuard();
		
		
		assertTrue(pos.equals(pos1));
		assertFalse(pos.equals(guard));
		assertFalse(pos.equals(null));
		assertEquals(true, pos.equals(pos));
		//assertTrue(pos.hashCode()==pos1.hashCode());
		 
	}
}
