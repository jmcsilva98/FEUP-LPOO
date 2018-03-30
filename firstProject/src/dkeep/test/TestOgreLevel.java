package dkeep.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import dkeep.logic.*;
import dkeep.logic.Character;

public class TestOgreLevel {

	String[][] map1={		
			{" ", " ", "O", " ", " ", " ", "O", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " ", " "},
			{"O", " ", " ", " ", "O", " ", " ", " ", "O"},
			{" ", " ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", "O", " ", " ", " ", "O", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " ", " "},
			{"O", " ", " ", " ", "O", " ", " ", " ", "O"},
			{" ", " ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", "O", " ", " ", " ", "O", " ", " "},

	};


	String[][] map2={		
			{" ", " ", "*", " ", " ", " ", "*", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " ", " "},
			{"*", " ", " ", " ", "*", " ", " ", " ", "*"},
			{" ", " ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", "*", " ", " ", " ", "*", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " ", " "},
			{"*", " ", " ", " ", "*", " ", " ", " ", "*"},
			{" ", " ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", "*", " ", " ", " ", "*", " ", " "},

	};

	String[][] map3={		 
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
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		gamestate.setOgres(ogres);
		ogres = gamestate.getOgres();
		assertEquals(ogres, gamestate.getOgres());

	}

	@Test
	public void testSetArrayOgres() {
		Gamestate gamestate=new Gamestate();
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		gamestate.setOgres(ogres);
		Gamestate gamestate2=new Gamestate();
		gamestate2.setOgres(3);
	}



	@Test(timeout=1000)
	public void testSomeRandomBehaviour(){
		Gamestate gamestate=new Gamestate(map3);
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
	public void testHeroIsCaptureByOgre() {		
		Gamestate gamestate=new Gamestate(map3);
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
		Gamestate gamestate=new Gamestate(map1);
		Gamestate gamestate2=new Gamestate(map2);
		Hero hero= new Hero();
		hero.setX(4);
		hero.setY(4);
		gamestate.setHero(hero);
		gamestate2.setHero(hero);
		assertTrue(gamestate.isFreeOgre());

		gamestate.heroMovement("D");
		gamestate2.heroMovement("D");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(4);
		hero.setY(0);
		gamestate.heroMovement("D");
		gamestate2.heroMovement("D");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(0);
		hero.setY(4);
		gamestate.heroMovement("D");
		gamestate2.heroMovement("D");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(4);
		hero.setY(8);
		gamestate.heroMovement("D");
		gamestate2.heroMovement("D");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());



	}

	@Test
	public void testHeroisCapturedUp() {
		Gamestate gamestate=new Gamestate(map1);
		Gamestate gamestate2=new Gamestate(map2);
		Hero hero= new Hero();
		hero.setX(4);
		hero.setY(4);
		gamestate.setHero(hero);
		gamestate2.setHero(hero);
		assertTrue(gamestate.isFreeOgre());

		gamestate.heroMovement("U");
		gamestate2.heroMovement("U");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(4);
		hero.setY(0);
		gamestate.heroMovement("U");
		gamestate2.heroMovement("U");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(4);
		hero.setY(8);
		gamestate.heroMovement("U");
		gamestate2.heroMovement("U");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(8);
		hero.setY(4);
		gamestate.heroMovement("U");
		gamestate2.heroMovement("U");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());


	}

	@Test
	public void testHeroisCapturedLeft() {
		Gamestate gamestate=new Gamestate(map1);
		Gamestate gamestate2=new Gamestate(map2);

		Hero hero= new Hero();
		hero.setX(4);
		hero.setY(4);
		gamestate.setHero(hero);
		gamestate2.setHero(hero);
		assertTrue(gamestate.isFreeOgre());
		gamestate.heroMovement("L");
		gamestate2.heroMovement("L");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(0);
		hero.setY(4);
		gamestate.heroMovement("L");
		gamestate2.heroMovement("L");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(8);
		hero.setY(4);
		gamestate.heroMovement("L");
		gamestate2.heroMovement("L");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());


		hero.setX(4);
		hero.setY(8);
		gamestate.heroMovement("L");
		gamestate2.heroMovement("L");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());



	}

	@Test
	public void testHeroisCapturedRight() {
		Gamestate gamestate=new Gamestate(map1);
		Gamestate gamestate2=new Gamestate(map2);
		Hero hero= new Hero();
		hero.setX(4);
		hero.setY(4);
		gamestate.setHero(hero);
		gamestate2.setHero(hero);
		assertTrue(gamestate.isFreeOgre());

		gamestate.heroMovement("R");
		gamestate2.heroMovement("R");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(4);
		hero.setY(0);
		gamestate.heroMovement("R");
		gamestate2.heroMovement("R");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(0);
		hero.setY(4);
		gamestate.heroMovement("R");
		gamestate2.heroMovement("R");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());

		hero.setX(8);
		hero.setY(4);
		gamestate.heroMovement("R");
		gamestate2.heroMovement("R");
		assertFalse(gamestate.isFreeOgre());
		assertFalse(gamestate2.isFreeOgre());



	}

	@Test

	public void testClubMovement() {
		Gamestate gamestate = new Gamestate();
		Character club = new Character();
		Ogre ogre = new Ogre();
		gamestate.setOgre(ogre);
		gamestate.getOgre().setClub(club);
		ogre.clubMovement();
		assertEquals(club, ogre.getClub());
	
		ogre.clubMovement();
		ogre.setX(8);
		club.setX(9);
		gamestate.setOgre(ogre);
		gamestate.getOgre().setClub(club);
		assertEquals(ogre.getClub().getX(), ogre.getX()+1);

		ogre.clubMovement();
		ogre.setY(8);
		club.setY(9);
		gamestate.setOgre(ogre);
		gamestate.getOgre().setClub(club);
		assertEquals(ogre.getClub().getY(), ogre.getY()+1);

	}


	@Test
	public void testNewPositionClub() {
		Gamestate gamestate = new Gamestate();
		Ogre ogre = new Ogre();
		Character club = new Character();


		gamestate.newPositionClub(ogre);
		ogre.setX(3);
		ogre.setY(3);
		gamestate.setOgre(ogre);
		club.setX(2);
		club.setY(3);
		gamestate.getOgre().setClub(club);
		gamestate.currentMap[ogre.getX()-1][ogre.getY()] = " ";
		assertEquals(ogre.getClub().getX(), ogre.getX()-1);
		assertEquals(ogre.getClub().getY(), ogre.getY());

		gamestate.newPositionClub(ogre);
		ogre.setX(3);
		ogre.setY(3);
		gamestate.setOgre(ogre);
		club.setX(4);
		club.setY(3);
		gamestate.getOgre().setClub(club);
		gamestate.currentMap[ogre.getX()+1][ogre.getY()] = " ";
		assertEquals(ogre.getClub().getX(), ogre.getX()+1);
		assertEquals(ogre.getClub().getY(), ogre.getY());

		gamestate.newPositionClub(ogre);
		ogre.setX(3);
		ogre.setY(3);
		gamestate.setOgre(ogre);
		ogre.getClub().setX(3);
		ogre.getClub().setY(2);
		gamestate.currentMap[ogre.getX()][ogre.getY()-1] = " ";
		assertEquals(ogre.getClub().getX(), ogre.getX());
		assertEquals(ogre.getClub().getY(), ogre.getY()-1);

		gamestate.newPositionClub(ogre);
		ogre.setX(3);
		ogre.setY(3);
		gamestate.setOgre(ogre);
		club.setX(3);
		club.setY(4);
		gamestate.getOgre().setClub(club);
		gamestate.currentMap[ogre.getX()][ogre.getY()+1] = " ";
		assertEquals(ogre.getClub().getX(), ogre.getX());
		assertEquals(ogre.getClub().getY(), ogre.getY()+1);

	}





}
