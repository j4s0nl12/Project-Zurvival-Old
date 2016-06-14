package com.mygdx.game.desktop;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game.Zurvival;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		//DisplayMode dm = gd.getDisplayMode();
		config.title = "Project: Zurvival";
		config.width = 1280; //dm.getWidth();
		config.height = 800; //dm.getHeight();
		new LwjglApplication(new Zurvival(), config);
	}
}
