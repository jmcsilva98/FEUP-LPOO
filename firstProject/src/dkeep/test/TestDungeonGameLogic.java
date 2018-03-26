package dkeep.test;

import static org.junit.Assert.*;
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
			{"X", "X", "X", " ", "X", "X", "X", "X", " ", "X"},
			{"X", " ", "I", " ", "I", " ", "X", "k", " ", "X"},
			{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
	};

	String[][] map2={		 
			{"X", "X", "X", "X"},
			{"X", " ", "C", "X"},
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
	public void testRookiePath() {
		Gamestate gamestate=new Gamestate();
		RookieGuard rookie=new RookieGuard();
		gamestate.setGuard(rookie);
		String[] path= {"0", "L", "D", "D","D","D", "L",  "L", "L", "L", "L", "L", "D", "R", "R", "R", "R", "R", "R", "R", "U","U","U","U" };
		assertEquals(rookie.rookieMovement, path);

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
		Gamestate gamestate=new Gamestate(map2);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		CellPosition cell= gamestate.getHero().position();
		assertEquals(new CellPosition(1,1) , cell);
		gamestate.heroMovement("R");
		assertTrue(gamestate.getHero().isArmed);

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

		Gamestate gamestate=new Gamestate(map2);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		CellPosition cell= gamestate.getHero().position();
		assertEquals(new CellPosition(1,1) , cell);
		gamestate.heroMovement("R");
		assertTrue(gamestate.getHero().isArmed);
		gamestate.heroMovement("D");
		assertEquals(new CellPosition(2,2), gamestate.getHero().position());
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
		gamestate.heroMovement("D");
		gamestate.heroMovement("R");
		gamestate.heroMovement("D"); 
		gamestate.heroMovement("D");
		assertTrue(gamestate.gameWon());	
	}

	/*@Test(timeout=1000)
	public void testSomeRandomBehaviour(){
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		String move="";
		hero.setX(1);
		hero.setY(1);
		int count=0;
		gamestate.setHero(hero);
		Ogre ogre=new Ogre();
		ogre.setX(1);
		ogre.setY(4);
		gamestate.setOgre(ogre);
		boolean up=false, down=false,right=false, left=false;
		while((!up || !down || !right || !left) || count<50 ) {
			move=gamestate.ogreMovement();	
				if (move=="U")up=true;
			else if (move=="D") down=true;
			else if (move=="R") right=true;
			else if (move=="L") left=true;
			else if (move ==null) count++;
			}
		if (count==50)
			fail("Some error message");
	}*/

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
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(3);
		gamestate.setLevel(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("D");
		assertFalse(gamestate.isFree());

	}
	@Test
	public void testHeroisCapturedUp() {
		Gamestate gamestate=new Gamestate(map3);
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(3);
		gamestate.setLevel(1);
		gamestate.setHero(hero); 
		gamestate.heroMovement("U");
		assertFalse(gamestate.isFree());

	}

	@Test
	public void testHeroisCapturedLeft() {
		Gamestate gamestate=new Gamestate(map3);
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(3);
		gamestate.setLevel(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("L");
		assertFalse(gamestate.isFree());

	}

	@Test
	public void testHeroisCapturedRight() {
		Gamestate gamestate=new Gamestate(map3);
		Hero hero= new Hero();
		hero.setX(3);
		hero.setY(3);
		gamestate.setLevel(1);
		gamestate.setHero(hero);
		gamestate.heroMovement("R");
		assertFalse(gamestate.isFree());

	}
}