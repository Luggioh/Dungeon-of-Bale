package de.dungeonofbale.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class ButtonClickListener extends ClickListener {
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		buttonClick(event, x, y);
	}
	
	public abstract void buttonClick(InputEvent paramEvent, float paramX, float paramY);

}
