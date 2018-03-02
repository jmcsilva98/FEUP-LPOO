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
		hero.set_x(1);
		hero.set_y(1);
		gamestate.setHero(hero);
		CellPosition cell= gamestate.getHero().position();
		assertEquals(new CellPosition(1,1) , cell);
		gamestate.hero_movement("D");
		assertEquals(new CellPosition(2,1), gamestate.getHero().position());
	}
	
	@Test
	public void testHeroIsCaptureByGuard() {
		
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.set_x(1);
		hero.set_y(1);
		gamestate.setHero(hero);
		assertFalse(gamestate.isGameOver());
		gamestate.hero_movement("R");
		assertFalse(gamestate.isGameOver());
	}
	
	@Test
	public void testHeroIsCaptureByOgre() {
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.set_x(1);
		hero.set_y(1);
		gamestate.setHero(hero);
		assertFalse(gamestate.isGameOver());
		gamestate.hero_movement("R");
		assertFalse(gamestate.isGameOver());	
	}
	@Test
	public void testHeroHasKey() {
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.set_x(1);
		hero.set_y(1);
		gamestate.setHero(hero);
		assertTrue(gamestate.getHero().hasKey);	
	}
	@Test
		public void testHeroArrivesAtTheDoorWithoutTheKey() {
		
		Map gameMap=new Map(map1);		
		Gamestate gamestate=new Gamestate(gameMap);
		Hero hero= new Hero();
		hero.set_x(1);
		hero.set_y(1);
		gamestate.setHero(hero);
		assertFalse(gamestate.gameWon());	
	}
}
