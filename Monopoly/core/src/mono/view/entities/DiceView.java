package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class DiceView extends EntityView {

	private int number;
	
	public DiceView(Monopoly game, int number) {
		super (game);
		this.number = number;
		sprite = createSprite ();
	}

	@Override
	public Sprite createSprite() {
		Texture dice = game.getAssetManager().get("Dice/Dice1.png");
		
		switch (number)
		{
		case 1:
			dice = game.getAssetManager().get("Dice/Dice1.png");
			break;
		case 2:
			dice = game.getAssetManager().get("Dice/Dice2.png");
			break;
		case 3:
			dice = game.getAssetManager().get("Dice/Dice3.png");
			break;
		case 4:
			dice = game.getAssetManager().get("Dice/Dice4.png");
			break;
		case 5:
			dice = game.getAssetManager().get("Dice/Dice5.png");
			break;
		case 6:
			dice = game.getAssetManager().get("Dice/Dice6.png");
			break;
		default:
			System.out.println("Error: Error loading dice with number " + number );
		}
		
        return new Sprite(dice, dice.getWidth(), dice.getHeight());

	}
	
	

}
