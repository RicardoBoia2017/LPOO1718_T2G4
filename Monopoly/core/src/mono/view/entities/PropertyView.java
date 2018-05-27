package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class PropertyView extends EntityView {

	private String name;
	
	public PropertyView(Monopoly game, String name) {
		super (game);
		this.name = name;
		sprite = createSprite ();
	}

	@Override
	public Sprite createSprite() {
		Texture property = null;

		switch (name)
		{
		case "Athens":
			property = game.getAssetManager().get("Properties/Athens.png");
			break;
		case "Amsterdam":
			property = game.getAssetManager().get("Properties/Amsterdam.png");
			break;
		case "Berlin":
			property = game.getAssetManager().get("Properties/Berlin.png");
			break;
		case "Brussels":
			property = game.getAssetManager().get("Properties/Brussels.png");
			break;
		case "Buenos Aires":
			property = game.getAssetManager().get("Properties/Buenos Aires.png");
			break;
		case "Cairo":
			property = game.getAssetManager().get("Properties/Cairo.png");
			break;
		case "Cape Town":
			property = game.getAssetManager().get("Properties/Cape Town.png");
			break;
		case "Casablanca":
			property = game.getAssetManager().get("Properties/Casablanca.png");
			break;
		case "Dubai":
			property = game.getAssetManager().get("Properties/Dubai.png");
			break;
		case "Lisbon":
			property = game.getAssetManager().get("Properties/Lisbon.png");
			break;
		case "London":
			property = game.getAssetManager().get("Properties/London.png");
			break;
		case "Madrid":
			property = game.getAssetManager().get("Properties/Madrid.png");
			break;
		case "Mexico City":
			property = game.getAssetManager().get("Properties/Mexico City.png");
			break;
		case "Moscow":
			property = game.getAssetManager().get("Properties/Moscow.png");
			break;
		case "New York":
			property = game.getAssetManager().get("Properties/New York.png");
			break;
		case "Paris":
			property = game.getAssetManager().get("Properties/Paris.png");
			break;
		case "Rio de Janeiro":
			property = game.getAssetManager().get("Properties/Rio de Janeiro.png");
			break;
		case "Rome":
			property = game.getAssetManager().get("Properties/Rome.png");
			break;
		case "Shanghai":
			property = game.getAssetManager().get("Properties/Shanghai.png");
			break;
		case "Singapore":
			property = game.getAssetManager().get("Properties/Singapore.png");
			break;
		case "Sydney":
			property = game.getAssetManager().get("Properties/Sydney.png");
			break;
		case "Tokyo":
			property = game.getAssetManager().get("Properties/Tokyo.png");
			break;
		case "Dunedin Station":
			property = game.getAssetManager().get("Properties/Dunedin Station.png");
			break;
		case "Liege Guillemins":
			property = game.getAssetManager().get("Properties/Liege Guillemins.png");
			break;
		case "Milano Centrale":
			property = game.getAssetManager().get("Properties/Milano Centrale.png");
			break;
		case "Sao Bento":
			property = game.getAssetManager().get("Properties/Sao Bento.png");
			break;
		case "Eletricity":
			property = game.getAssetManager().get("Properties/Eletricity.png");
			break;
		case "Water":
			property = game.getAssetManager().get("Properties/Water.png");
			break;
		default:
			System.out.println("Error: Error loading property " + name);
		}
		
        return new Sprite(property, property.getWidth(), property.getHeight());

	}
	
}
