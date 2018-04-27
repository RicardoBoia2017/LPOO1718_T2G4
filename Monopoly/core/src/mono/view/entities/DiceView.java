package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class DiceView extends EntityView {

	private int number;
	
	public DiceView(Monopoly game, int number) {
		super (game);
		this.number = number;
	}

	@Override
	public Sprite createSprite(Monopoly game) {
		Texture dice = new Texture("Dice1.png");
		
		switch (number)
		{
		case 1:
			dice = game.getAssetManager().get("Dice1.png");
			break;
		case 2:
			dice = game.getAssetManager().get("Dice2.png");
			break;
		case 3:
			dice = game.getAssetManager().get("Dice3.png");
			break;
		case 4:
			dice = game.getAssetManager().get("Dice4.png");
			break;
		case 5:
			dice = game.getAssetManager().get("Dice5.png");
			break;
		case 6:
			dice = game.getAssetManager().get("Dice6.png");
			break;
		default:
			System.out.println("Error: Error loading dice with number " + number );
		}
		
        return new Sprite(dice, dice.getWidth(), dice.getHeight());

	}
	
	

}
