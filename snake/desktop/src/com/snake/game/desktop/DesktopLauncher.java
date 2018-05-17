package com.snake.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.snake.game.SnakeSmash;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SnakeSmash.WIDTH;
		config.height = SnakeSmash.HEIGHT;
		config.resizable = false;
		config.foregroundFPS=60;

		new LwjglApplication(new SnakeSmash(), config);
	}
}
