package com.fuzzy.autocity;

import com.badlogic.gdx.ApplicationAdapter;
import com.fuzzy.autocity.debugui.DebugUI;

public class AutocityGDX extends ApplicationAdapter {
	Game game;
	DebugUI debugUI;
	
	@Override
	public void create () {
		game = new Game();
		debugUI = new DebugUI(game);

		game.start();
		debugUI.start();
	}

	@Override
	public void render () {
		// Nothing to render on debug client
	}
}
