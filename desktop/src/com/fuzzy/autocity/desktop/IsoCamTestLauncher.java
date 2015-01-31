package com.fuzzy.autocity.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fuzzy.autocity.AutocityGDX;
import com.fuzzy.autocity.IsoCamTest;

public class IsoCamTestLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new IsoCamTest(), config);
	}
}
