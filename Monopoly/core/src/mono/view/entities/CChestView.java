package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

/**
 * Creates Community Chest view
 * @author ricar
 *
 */
public class CChestView extends EntityView {

	private int cardId;

	/**
	 * Creates community chest view
	 * 
	 * @param game game
	 * @param cardId card id
	 */
	public CChestView(Monopoly game, int cardId) {
		super (game);
		this.cardId = cardId;
		sprite = createSprite ();
	}
	
	@Override
	public Sprite createSprite() {
	
		Texture cChest = null;
		
		switch(cardId)
		{
		case 1:
		cChest = game.getAssetManager().get("CChestCards/Birthday.png",Texture.class);
		break;
		
		case 2:
		cChest = game.getAssetManager().get("CChestCards/CarToaded.png",Texture.class);
		break;
		
		case 3:		
		cChest = game.getAssetManager().get("CChestCards/Eurovision.png",Texture.class);
		break;
		
		case 4:
		cChest = game.getAssetManager().get("CChestCards/GettingLate.png",Texture.class);
		break;
		
		case 5:
		cChest = game.getAssetManager().get("CChestCards/iPhone.png",Texture.class);
		break;
		
		case 6:
		cChest = game.getAssetManager().get("CChestCards/LateWork.png",Texture.class);
		break;
		
		case 7:
		cChest = game.getAssetManager().get("CChestCards/Lottery.png",Texture.class);
		break;
		
		case 8:
		cChest = game.getAssetManager().get("CChestCards/MSG.png",Texture.class);
		break;
		
		case 9:
		cChest = game.getAssetManager().get("CChestCards/OldLady.png",Texture.class);	
		break;
		
		case 10:
		cChest = game.getAssetManager().get("CChestCards/PCS.png",Texture.class);	
		break;
		
		default:
			System.out.println("Error: Error loading community chest card with number " + cardId );

		}
		
        return new Sprite(cChest, cChest.getWidth(), cChest.getHeight());
	}
}
