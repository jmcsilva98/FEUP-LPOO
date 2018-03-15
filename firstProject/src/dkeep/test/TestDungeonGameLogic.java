package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Gamestate;
import dkeep.logic.Character;
import dkeep.logic.Map;
import dkeep.logic.CellPosition;
import dkeep.logic.*;
public class TestDungeonGameLogic {

	 String[][] map1={		
			{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"X", " ", "O", " ", "I", " ", "X", " ", " ", "X"},
			{"X", " ", "X", " ", "X", "X", "X", " ", " ", "X"},
			{"X", "I", " ", " ", "I", " ", "X", " ", " ", "X"},
			{"X", "X", "X", " ", "X", "X", "X", " ", " ", "X"},
			{"I", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"I", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", "X", "X", " ", "X", "X", "X", "X", " ", "X"},
			{"X", " ", "I", " ", "I", " ", "X", "k", " ", "X"},
			{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
	};
	 
	@Test
	public void testMoveHeroIntoFreeCell() {
		Map gameMap=new Map(map1);
		
		Gamestate gamestate=new Gamestate(gameMap);
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
	public void testHeroIsCaptureByGuard() {
		
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertFalse(gamestate.isGameOver());
		gamestate.heroMovement("R");
		assertFalse(gamestate.isGameOver());
	}
	
	@Test
	public void testHeroIsCaptureByOgre() {
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertFalse(gamestate.isGameOver());
		gamestate.heroMovement("R");
		assertFalse(gamestate.isGameOver());	
	}
	@Test
	public void testHeroHasKey() {
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertTrue(gamestate.getHero().hasKey);	
	}
	@Test
		public void testHeroArrivesAtTheDoorWithoutTheKey() {
		
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.setX(1);
		hero.setY(1);
		gamestate.setHero(hero);
		assertTrue(gamestate.gameWon());	
	}
	
	@Test(timeout=1000)
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
	}
}
