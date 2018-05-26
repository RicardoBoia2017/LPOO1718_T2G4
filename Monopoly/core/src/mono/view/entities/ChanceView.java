package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class ChanceView extends EntityView {

	private int cardId;
	
	public ChanceView(Monopoly game, int cardId) {
		super (game);
		this.cardId = cardId;
		sprite = createSprite ();
	}

	@Override
	public Sprite createSprite() {
	
		Texture chance = null;
		
		switch(cardId)
		{
		case 1:
		chance = game.getAssetManager().get("ChanceCards/AExam.png",Texture.class);
		break;
		
		case 2:
		chance = game.getAssetManager().get("ChanceCards/Birthday.png",Texture.class);
		break;
		
		case 3:		
		chance = game.getAssetManager().get("ChanceCards/CaughtStealing.png",Texture.class);
		break;
		
		case 4:
		chance = game.getAssetManager().get("ChanceCards/FoundMoney.png",Texture.class);
		break;
		
		case 5:
		chance = game.getAssetManager().get("ChanceCards/GoToMoscow.png",Texture.class);
		break;
		
		case 6:
		chance = game.getAssetManager().get("ChanceCards/LostBet.png",Texture.class);
		break;
		
		case 7:
		chance = game.getAssetManager().get("ChanceCards/RealEstateTaxes.png",Texture.class);
		break;
		
		case 8:
		chance = game.getAssetManager().get("ChanceCards/SummerComing.png",Texture.class);
		break;
		
		case 9:
		chance = game.getAssetManager().get("ChanceCards/WrongWay.png",Texture.class);	
		break;
		
		case 10:
		chance = game.getAssetManager().get("ChanceCards/LateCreditCard.png",Texture.class);	
		break;
		
		default:
			System.out.println("Error: Error loading chance card with number " + cardId );

		}
		
        return new Sprite(chance, chance.getWidth(), chance.getHeight());
	}

}
