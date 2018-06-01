package mono.view;
	
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import appwarp.WarpListener;
import mono.controller.GameController;
import mono.model.Game;
import mono.model.entities.Pair;
import mono.model.entities.Piece;
import mono.model.entities.Player;
import mono.model.entities.Property;
import mono.view.entities.BootView;
import mono.view.entities.CChestView;
import mono.view.entities.CarView;
import mono.view.entities.ChanceView;
import mono.view.entities.DiceView;
import mono.view.entities.EntityView;
import mono.view.entities.HatView;
import mono.view.entities.HotelView;
import mono.view.entities.HouseView;
import mono.view.entities.ThimbleView;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.ScreenManager;
import mono.view.swapper.UIFactory;

public class GameScreen extends AbstractScreen implements WarpListener {
	
    private static GameScreen instance;

    Sound sound;
    boolean outputSound;
    BitmapFont font;
	Skin skin;
	Pair diceValues;
	int firstClick = 0;
	
	static float diceRollTime; 
	static int btnState;
	Animation diceAnimation;
	
	Dialog jailDialog;
	Dialog successfulBuyDialog;
	Dialog notBuyableDialog;
	Dialog alreadyOwnedDialog;
	Dialog noMoneyDialog;
	Dialog sucessfulHouseDialog;
	Dialog cannotPlaceHouseDialog;
	Dialog doNotOwnAllColorsDialog;
	Dialog notValidPlayerDialog;
	Dialog mortgagedDialog; 
	Dialog noMoreHouses;
	Dialog bankruptPlayerDialog;

	TextButton closeBtn; 
	TextButton rollDiceBtn;
	TextButton endTurnBtn;
	TextButton buyPropertyBtn;
	TextButton negotiateBtn;
	TextButton propertyScreenBtn;
	Boolean showCard;
	Boolean removeBankrupcyDialog;

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameScreen getInstance() {
    	
        if (instance == null) 
            instance = new GameScreen(); //Change this 
        
        return instance;
    }
    
	public GameScreen() {
		super(); 
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
				 
		//initialize dice
		loadAssets();
		initVariables();
//		WarpController.getInstance().setListener(this);
		
	}	
    
	private void initVariables() {
		diceValues = new Pair();
		showCard = false;
		font = new BitmapFont ();
		outputSound = true;		
		drawAnimation();
		diceRollTime = 100;
	}

	private static void loadAssets ()
	{
		game.getAssetManager().load ("Board.png", Texture.class);
		game.getAssetManager().load ("house.png", Texture.class);
		game.getAssetManager().load ("hotel.png", Texture.class);
		game.getAssetManager().load ("PlayersBackground.png", Texture.class);
		game.getAssetManager().load ("Note.png", Texture.class);
		loadSounds();
		loadProperties();
		loadCChestCards();
		loadChanceCards();
		loadDices();
        game.getAssetManager().finishLoading();
	}
	
	private static void loadSounds()
	{
		game.getAssetManager().load("Sounds/Athens.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Amsterdam.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Berlin.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Brussels.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Buenos Aires.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Cairo.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Cape Town.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Casablanca.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Dubai.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Lisbon.mp3", Sound.class);
		game.getAssetManager().load("Sounds/London.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Madrid.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Mexico City.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Moscow.mp3", Sound.class);
		game.getAssetManager().load("Sounds/New York.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Paris.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Rio de Janeiro.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Rome.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Shanghai.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Singapore.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Sydney.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Tokyo.mp3", Sound.class);

		game.getAssetManager().load("Sounds/Dunedin Station.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Liege Guillemins.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Milano Centrale.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Sao Bento.mp3", Sound.class);
	
		game.getAssetManager().load("Sounds/Eletricity.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Water.mp3", Sound.class);

		game.getAssetManager().load("Sounds/Start.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Free Parking.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Jail.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Chance.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Community Chest.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Income Tax.mp3", Sound.class);
		game.getAssetManager().load("Sounds/Luxury Tax.mp3", Sound.class);
	}

	private static void loadProperties()
	{
		game.getAssetManager().load("Properties/Athens.png", Texture.class);
		game.getAssetManager().load("Properties/Amsterdam.png", Texture.class);
		game.getAssetManager().load("Properties/Berlin.png", Texture.class);
		game.getAssetManager().load("Properties/Brussels.png", Texture.class);
		game.getAssetManager().load("Properties/Buenos Aires.png", Texture.class);
		game.getAssetManager().load("Properties/Cairo.png", Texture.class);
		game.getAssetManager().load("Properties/Cape Town.png", Texture.class);
		game.getAssetManager().load("Properties/Casablanca.png", Texture.class);
		game.getAssetManager().load("Properties/Dubai.png", Texture.class);
		game.getAssetManager().load("Properties/Lisbon.png", Texture.class);
		game.getAssetManager().load("Properties/London.png", Texture.class);
		game.getAssetManager().load("Properties/Madrid.png", Texture.class);
		game.getAssetManager().load("Properties/Mexico City.png", Texture.class);
		game.getAssetManager().load("Properties/Moscow.png", Texture.class); 
		game.getAssetManager().load("Properties/New York.png", Texture.class);
		game.getAssetManager().load("Properties/Paris.png", Texture.class);
		game.getAssetManager().load("Properties/Rio de Janeiro.png", Texture.class);
		game.getAssetManager().load("Properties/Rome.png", Texture.class);
		game.getAssetManager().load("Properties/Shanghai.png", Texture.class);
		game.getAssetManager().load("Properties/Singapore.png", Texture.class);
		game.getAssetManager().load("Properties/Sydney.png", Texture.class);
		game.getAssetManager().load("Properties/Tokyo.png", Texture.class);
		
		game.getAssetManager().load("Properties/Dunedin Station.png", Texture.class);
		game.getAssetManager().load("Properties/Liege Guillemins.png", Texture.class);
		game.getAssetManager().load("Properties/Milano Centrale.png", Texture.class);
		game.getAssetManager().load("Properties/Sao Bento.png", Texture.class);
		
		game.getAssetManager().load("Properties/Eletricity.png", Texture.class);
		game.getAssetManager().load("Properties/Water.png", Texture.class);

		game.getAssetManager().load("Mortgaged.png", Texture.class);
		
	}
	
	private static void loadCChestCards() 
	{
		game.getAssetManager().load("CChestCards/Birthday.png",Texture.class);
		game.getAssetManager().load("CChestCards/CarToaded.png",Texture.class);
		game.getAssetManager().load("CChestCards/Eurovision.png",Texture.class);
		game.getAssetManager().load("CChestCards/GettingLate.png",Texture.class);
		game.getAssetManager().load("CChestCards/iPhone.png",Texture.class);
		game.getAssetManager().load("CChestCards/LateWork.png",Texture.class);
		game.getAssetManager().load("CChestCards/Lottery.png",Texture.class);
		game.getAssetManager().load("CChestCards/MSG.png",Texture.class);
		game.getAssetManager().load("CChestCards/OldLady.png",Texture.class);
		game.getAssetManager().load("CChestCards/PCS.png",Texture.class);
	} 

	private static void loadChanceCards()
	{
		game.getAssetManager().load("ChanceCards/AExam.png",Texture.class);
		game.getAssetManager().load("ChanceCards/Birthday.png",Texture.class); 
		game.getAssetManager().load("ChanceCards/CaughtStealing.png",Texture.class);
		game.getAssetManager().load("ChanceCards/FoundMoney.png",Texture.class);
		game.getAssetManager().load("ChanceCards/GoToMoscow.png",Texture.class);
		game.getAssetManager().load("ChanceCards/LostBet.png",Texture.class);
		game.getAssetManager().load("ChanceCards/RealEstateTaxes.png",Texture.class);
		game.getAssetManager().load("ChanceCards/SummerComing.png",Texture.class);
		game.getAssetManager().load("ChanceCards/WrongWay.png",Texture.class);
		game.getAssetManager().load("ChanceCards/LostCreditCard.png",Texture.class);
	}
	
	private static void loadDices()
	{
		game.getAssetManager().load ("Dice/Dice1.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice2.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice3.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice4.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice5.png", Texture.class); 
		game.getAssetManager().load ("Dice/Dice6.png", Texture.class);
	}
	
	@Override
	public void buildStage() {
		
		createRollDiceBtn();
		addActor(rollDiceBtn);
		
		createEndTurnBtn();
		addActor(endTurnBtn);
		
		createBuyPropertyBtn();
		addActor (buyPropertyBtn);
		
		createPropertyScreenBtn();
		addActor(propertyScreenBtn);
		
		createNegotiateBtn();
		addActor(negotiateBtn);
		
		addActor(createNoteBtn());
		
		if (btnState == 2)
		{
			rollDiceBtn.setTouchable(Touchable.disabled);
			rollDiceBtn.setColor(1,0,0,1);
			
			endTurnBtn.setTouchable(Touchable.enabled);
			endTurnBtn.setColor(0.9f, 0.9f, 0.9f, 1);
		}
		Texture board = game.getAssetManager().get("Board.png");
		ImageButton btnBoard = UIFactory.createButton(board);
		btnBoard.setSize(803, 803);
		btnBoard.setPosition(1.f, 197.f);
		
		/*btnBoard.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						System.out.println(x);
						System.out.println(y);
						return false;
						}
					});
		
		addActor(btnBoard);*/
		
		createSuccessfulBuyDialog();
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		
		game.getBatch().begin();
		drawEntities(delta);

		game.getBatch().end();
				
		super.act();
		super.draw();
	}
	
	private void drawEntities(float delta)
	{
		drawBoard();
		drawPlayerMenu();
		drawPlayers();
//		drawDice();
//		rollDiceAnimation(delta);
		drawPlayersPieces();
		drawCard();
		drawAHouse();
		drawAHotel();
		bankrupcyDraw();
	}
	
	private void drawPlayerMenu() {
		Texture board = game.getAssetManager().get("PlayersBackground.png", Texture.class);
		game.getBatch().draw(board, 803, 197, 295, 803);
	}

	private void drawPlayers() {
		font.setColor (0,0,0,1);
		font.getData().setScale(2);
		int i = 0;
		
		for (Player p : Game.getInstance().getPlayers())
		{
			font.draw (game.getBatch(), p.getName(), 820, 970 - i);
			font.draw (game.getBatch(), "Position: ", 820, 870 - i);
			font.setColor(0, 0.70f, 0, 1f);
			
			font.draw (game.getBatch(), Integer.toString(p.getMoney()), 820, 920 - i);
			font.setColor(1, 1, 1, 1);			
			
			font.draw (game.getBatch(), Game.getInstance().getBoard().getSquares().get(p.getPosition()).getName(), 820, 830 - i);
			font.setColor(0, 0, 0, 1);

			
			drawPiece (p.getBoardPiece(), 1000, 880 - i);
			 
			i += 195;	
		}
		
		font.draw (game.getBatch(), "Current Player", 400, 150);
		
		switch (Game.getInstance().getCurrentPlayer().getGameId())
		{
		case 1:
			font.setColor(0f, 0.64f, 0.91f, 1);
			break;
		case 2:
			font.setColor(0.93f, 0.11f, 0.14f, 1);
			break;
		case 3:
			font.setColor(1, 0.79f, 0.55f, 1);
			break;
		case 4:
			font.setColor(1, 0.5f, 0.15f, 1);
			break;
		}
		
		font.draw (game.getBatch(), Game.getInstance().getCurrentPlayer().getName(), 400, 100);
		
	}
	
	private void bankrupcyDraw() {
		if(Game.getInstance().getCurrentPlayer().getBankrupcyState()) {
			createbankRupcyDialog();
			addActor(bankruptPlayerDialog);
		}
		
		else {
			return;
		}
	}
	
	private void createNotValidPlayerDialog() {
		notValidPlayerDialog = new Dialog("Invalid Player!", skin) {
	
			protected void result(Object object) {
				if (object.equals(1L))
					notValidPlayerDialog.remove();

			};
		};
		
		notValidPlayerDialog.setPosition(340f, 600f);
		notValidPlayerDialog.setWidth(220f);
		notValidPlayerDialog.button("EXIT", 1L);
	}
	
	private void createbankRupcyDialog() {
		bankruptPlayerDialog = new Dialog("You are bankrupt!", skin) {
			
			protected void result(Object object) {
				if (object.equals(1L)) {
					Game.getInstance().getCurrentPlayer().setBankrupcyState(false);
					ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
					bankruptPlayerDialog.remove();
				}
			};
		};
		
		bankruptPlayerDialog.setPosition(340f, 600f);
		bankruptPlayerDialog.setWidth(220f);
		bankruptPlayerDialog.button("EXIT", 1L);
	}
	
	public class MyTextInputListener implements TextInputListener {
		   @Override
		   public void input (String text) {
			   //function for sorting through player array and finding him
			   Player p1 = GameController.getInstance().getPlayerByName(text);
			   String currentPlayerName = Game.getInstance().getCurrentPlayer().getName();
			   
			   if(p1 == null || p1.getName().equals(currentPlayerName)) {
				   createNotValidPlayerDialog();
				   addActor(notValidPlayerDialog);
				   firstClick = 0;
			   }
				  
			   else {
				   addListener(UIFactory.createListener(ScreenEnum.NEGOTIATION, p1));
			   }
		   }
		   
		   @Override
		   public void canceled () {
			   //nothing happens
		   }
	}

	private void rollDiceAnimation(float delta) {
			
		this.diceRollTime += delta;
		Random rand = new Random();
		Texture current;
		
		if (this.diceRollTime < 1)
		{
			current = (Texture) diceAnimation.getKeyFrames()[rand.nextInt(6)];
			game.getBatch().draw(current, 108.5f, 38.5f, 106.05f, 106.05f);
			
			current = (Texture) diceAnimation.getKeyFrames()[rand.nextInt(6)];
			game.getBatch().draw(current, 248.5f, 38.5f, 106.05f, 106.05f); 

		}
	}

	private void drawAnimation() {
			
		Array <Texture> array = new Array <Texture> (6);
		array.add((Texture) game.getAssetManager().get("Dice/Dice1.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice2.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice3.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice4.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice5.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice6.png"));
		
		diceAnimation = new Animation<>(0.1f, array);
		
	}
	
	private void drawBoard ()
	{
		Texture board = game.getAssetManager().get("Board.png",Texture.class); 
		game.getBatch().draw(board, 1, 197, 803, 803);
	}
	
	public void drawDice() {
		
		DiceView dice1 = new DiceView(game, diceValues.getValue1());
		DiceView dice2 = new DiceView(game, diceValues.getValue2());

		Sprite dice_1 = dice1.createSprite();
		Sprite dice_2 = dice2.createSprite();
		
		dice_1.scale(-0.65f);
		dice_2.scale(-0.65f);
		dice_1.setPosition(10, -60);
		dice_2.setPosition(150, -60);
				
		dice_1.draw(game.getBatch());
		dice_2.draw(game.getBatch());	
	}
	
	private void drawPlayersPieces()
	{
		for (Player p: Game.getInstance().getPlayers())
		{
			drawPiece (p.getBoardPiece(), p.getX(), p.getY());
		}
	}
	
	private void drawPiece(Piece p, int x, int y) {
		
		if(p.getType() == "Thimble") {
			drawThimble(x, y);
		}
		
		else if(p.getType() == "Car") {
			drawCar(x, y);
		}
		
		else if(p.getType() == "Hat") {
			drawHat(x, y);
		}
		
		else if(p.getType() == "Boot") {
			drawBoot(x, y);
		}
		
	}
	
	private void drawAHouse() {
		
		int nHouses = 0;
			
		for(int j = 0; j < Game.getInstance().getBoard().getSquares().size(); j++) {
			if(Game.getInstance().getBoard().getSquares().get(j).getType().equals("Property")) {
				Property s1 = (Property) Game.getInstance().getBoard().getSquares().get(j);
				nHouses = s1.getHouses();
				
				for(int i = 0; i < nHouses; i++) {
					HouseView houseView = new HouseView(game);
					
					Sprite house = houseView.createSprite();
					
					if(s1.getPosition() >= 0 && s1.getPosition() <= 10) {
						house.setPosition((s1.getX()+(19*i))-35, s1.getY()+60);
					}
					
					else if(s1.getPosition() >= 10 && s1.getPosition() <= 20) {
						house.setPosition(s1.getX()-70, (s1.getY()+60)+(19*i));
					}
					
					if(s1.getPosition() >= 20 && s1.getPosition() <= 30) {
						house.setPosition((s1.getX()+(19*i))-35, s1.getY()+60);
					}
					
					if(s1.getPosition() >= 30 && s1.getPosition() <= 39) {
						house.setPosition(s1.getX()+50, (s1.getY()+60)+(19*i));
					}
					
					house.setSize(20, 20);
					
					house.draw(game.getBatch());
				}
			}
		}
		
	}
	
	private void drawAHotel() {
		
		int nHotels = 0;
			
		for(int j = 0; j < Game.getInstance().getBoard().getSquares().size(); j++) {
			if(Game.getInstance().getBoard().getSquares().get(j).getType().equals("Property")) {
				Property s1 = (Property) Game.getInstance().getBoard().getSquares().get(j);
				nHotels = s1.getHotels();
				
				if(nHotels == 1) {
					
					HotelView hotelView = new HotelView(game);
						
					Sprite hotel = hotelView.createSprite();
					
					if(s1.getPosition() >= 10 && s1.getPosition() <= 20) {
						hotel.setPosition(s1.getX()-40, s1.getY()+66);
					} 
					
					else if(s1.getPosition() >= 30 && s1.getPosition() <= 39) {
						hotel.setPosition(s1.getX(), s1.getY()+66);
					}
					
					else {
						hotel.setPosition(s1.getX()+5, s1.getY()+20);
					}
						
					hotel.setSize(30, 30);
						
					hotel.draw(game.getBatch());
				}
			}
		}
	}

	public void drawCar(int x, int y) {
		CarView carView = new CarView(game);
		
		Sprite car = carView.createSprite();
		
		car.setPosition(x, y);
		car.setSize (40,40);
				
		car.draw(game.getBatch());
	}
	
	public void drawHat(int x, int y) {
		HatView hatView = new HatView(game);
		
		Sprite hat = hatView.createSprite();
		
		hat.setPosition(x, y);
		hat.setSize (40,40);
		
		hat.draw(game.getBatch());
	}
	
	public void drawBoot(int x, int y) {
		BootView bootView = new BootView(game);
		
		Sprite boot = bootView.createSprite();
		
		boot.setSize(40, 40);
		boot.setPosition(x, y);
		
		boot.draw(game.getBatch());
	}
	
	public void drawThimble(int x, int y) {
		ThimbleView thimbleView = new ThimbleView(game);
		
		Sprite thimble = thimbleView.createSprite();
		
		thimble.setSize(40,40);
		thimble.setPosition(x, y);
				
		thimble.draw(game.getBatch());
	}
	
	private void drawCard()
	{
		String res = Game.getInstance().inCardPosition(false);
		String cardType;
		int cardId;
		
		if (res == null)
		{
			if (closeBtn != null)
			{
				closeBtn.remove();
				closeBtn = null;
			}
			return;
		}
				
		EntityView cardView = null;
		cardType = res.substring(0, 2);
		cardId = Integer.valueOf(res.substring(3));

		if (cardType.equals("CH"))
			cardView = new ChanceView(game, cardId);

		else if (cardType.equals("CC"))
			cardView = new CChestView(game,cardId);

		Sprite card = cardView.createSprite();

		card.setSize(550, 400);
		card.setPosition(125, 400);

		if (closeBtn == null)
		{
			createCloseButton();
			addActor(closeBtn);
		}

		if (showCard)
			card.draw(game.getBatch());

		else if (closeBtn != null)
		{
			closeBtn.remove();
			closeBtn = null;
		}
	}
  	
	private void createRollDiceBtn() { 
        rollDiceBtn = new TextButton("Roll Dice", skin);
        rollDiceBtn.setPosition(800, 20);
        rollDiceBtn.setWidth(170);
        rollDiceBtn.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				
						diceRollTime = 0;
						showCard = true;
						
						diceValues = GameController.getInstance().doTurn();
						
						if(GameController.getInstance().tellViewToDisplayJailDialog() && diceValues.getValue1() != diceValues.getValue2()) {
							createJailDialog();
							addActor(jailDialog);
						
						} else {
							
							if(jailDialog != null) {
								jailDialog.remove();
							}
							
						}
							
						if (outputSound)
							chooseSound(); 
						
						rollDiceBtn.setTouchable(Touchable.disabled);
						rollDiceBtn.setColor(1,0,0,1);
						
						endTurnBtn.setTouchable(Touchable.enabled);
						endTurnBtn.setColor(0.9f, 0.9f, 0.9f, 1);
						
						btnState = 2;
						
						return false;
					}
				});
    }
	
	protected void chooseSound() {
		String name = Game.getInstance().getBoard().getSquares().get(Game.getInstance().getCurrentPlayer().getPosition()).getName();
		String file = "Sounds/" + name + ".mp3";
		
		sound = game.getAssetManager().get(file);
		long soundId = sound.play();
		sound.setVolume(soundId, 0.5f);
		
	}

	private void createBuyPropertyBtn() {

        buyPropertyBtn = new TextButton("Buy", skin);
        buyPropertyBtn.setPosition(600, 20);
        buyPropertyBtn.setWidth(170);
        buyPropertyBtn.setChecked(false);
        
        buyPropertyBtn.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						
						int res = GameController.getInstance().buyProperty();
						
						switch (res)
						{
						case -3:
							createNoMoneyDialog();
							addActor(noMoneyDialog);
							break;
						case -2:
							createAlreadyOwnedDialog();
							addActor(alreadyOwnedDialog);
							break;
						case -1:
							createNotBuyableDialog();
							addActor(notBuyableDialog);
							break;
						case 0:
							createSuccessfulBuyDialog();
							addActor(successfulBuyDialog);
							break;
						
						}
						
						return false;
					}
				});
        
	}
	
	private void createPropertyScreenBtn() {

        propertyScreenBtn = new TextButton("Properties", skin);
        propertyScreenBtn.setPosition(600, 130);
        propertyScreenBtn.setWidth(170);
        propertyScreenBtn.setChecked(false);
        
        propertyScreenBtn.addListener(UIFactory.createListener(ScreenEnum.PROPERTIES)); 
        
	}
	
	private void createNegotiateBtn() {
		
		negotiateBtn = new TextButton("Negotiate", skin);
		negotiateBtn.setPosition(600, 75);
		negotiateBtn.setWidth(170);
		negotiateBtn.setChecked(false);
		
		negotiateBtn.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						
						if(firstClick == 0) {
							MyTextInputListener listener = new MyTextInputListener();
							
							Gdx.input.getTextInput(listener, "Player Select", "", "Player?");
						}
						
						firstClick++;
						
						if(firstClick == 2) {
							firstClick = 0;
						}
						
						return false;
					}
				});
		
	}
	
	private void createEndTurnBtn()
	{
	        endTurnBtn = new TextButton("End Turn", skin);
	        endTurnBtn.setPosition(800, 110);
	        endTurnBtn.setWidth(170);
	        
			endTurnBtn.setTouchable(Touchable.disabled);
			endTurnBtn.setColor(1,0,0,1);
			
	        endTurnBtn.addListener(
					new InputListener() { 
						@Override
						public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { 
																		
							endTurnBtn.setTouchable(Touchable.disabled);
							endTurnBtn.setColor(1,0,0,1);
							
							rollDiceBtn.setTouchable(Touchable.enabled);
							rollDiceBtn.setColor(0.9f, 0.9f, 0.9f, 1);
							
							btnState = 1;
							
							Game.getInstance().endTurn();
							
							if (Game.getInstance().getCurrentPlayer().isBot())
								botTurnButtons();

							else 
								playerTurnButtons();
			
							return false;
						}
					});
	        
	        
	}
	 
	private ImageButton createNoteBtn()
	{
		Texture note = game.getAssetManager().get("Note.png");
		ImageButton noteBtn = UIFactory.createButton(note);
		noteBtn.setSize(50, 50); 
		noteBtn.setPosition(35, 40, Align.center);
		noteBtn.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { 
				
				if (outputSound)
					outputSound = false;
				else
					outputSound = true;
				
				return false;
			}
		});
		
		return noteBtn;
	}
	
	protected void playerTurnButtons() {
		rollDiceBtn.setText("Roll Dice");	
		endTurnBtn.setText("End Turn");
		
		buyPropertyBtn.setTouchable(Touchable.enabled);
		buyPropertyBtn.setColor(0.9f, 0.9f, 0.9f, 1);
		
		negotiateBtn.setTouchable(Touchable.enabled);
		negotiateBtn.setColor(0.9f, 0.9f, 0.9f, 1);
		
		propertyScreenBtn.setTouchable(Touchable.enabled);
		propertyScreenBtn.setColor(0.9f, 0.9f, 0.9f, 1);
	}

	protected void botTurnButtons() {
		rollDiceBtn.setText("Bot Turn");	
		endTurnBtn.setText("End Bot Turn");
		
		buyPropertyBtn.setTouchable(Touchable.disabled);
		buyPropertyBtn.setColor(0.4f, 0.4f, 0.4f, 1);
		
		negotiateBtn.setTouchable(Touchable.disabled);
		negotiateBtn.setColor(0.4f, 0.4f, 0.4f, 1);
		
		propertyScreenBtn.setTouchable(Touchable.disabled);
		propertyScreenBtn.setColor(0.4f, 0.4f, 0.4f, 1);
		
	}

	private void createCloseButton()
	{
	     	closeBtn = new TextButton("Close", skin);
	        closeBtn.setPosition(275f, 410);
	        closeBtn.setWidth(200);
	        
	        closeBtn.addListener(
					new InputListener() {
						@Override
						public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
							
							showCard = false;
								
							return false;
						}
					});
	        
	}
	
	public void createJailDialog() {
		
		jailDialog = new Dialog("Pay the fine or wait?", skin) {
            protected void result(Object object)
            {
            	
            	if(object.equals(1L)) {
            		GameController.getInstance().payJail();  
            		jailDialog.remove();
            	}
            	
            	if(object.equals(2L)) {
            		jailDialog.remove();
            	}
            	
            };
		};
		
		jailDialog.setPosition(340f, 600f);
		
		jailDialog.setMovable(false);
		
		jailDialog.setWidth(250f);
		jailDialog.button("PAY", 1L);
		jailDialog.button("WAIT", 2L);
	}
	
	public void createSuccessfulBuyDialog()
	{
		successfulBuyDialog = new Dialog("Purchase successful", skin){
        protected void result(Object object)
        {
        	if(object.equals(1L)) 
        	{
        		successfulBuyDialog.remove();
        	}
        };
		};
		successfulBuyDialog.setPosition(340f, 600f);
		
		successfulBuyDialog.setWidth(250f);
		successfulBuyDialog.button("EXIT", 1L);
		
	}
		
	public void createNotBuyableDialog()
	{
		notBuyableDialog = new Dialog("This square is not buyable", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					notBuyableDialog.remove();

			};
		};
		notBuyableDialog.setPosition(340f, 600f);

		notBuyableDialog.setWidth(300f);
		notBuyableDialog.button("EXIT", 1L);
	}

	public void createAlreadyOwnedDialog()
	{
		alreadyOwnedDialog = new Dialog("Property is already owned", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					alreadyOwnedDialog.remove();

			};
		};
		alreadyOwnedDialog.setPosition(340f, 600f);

		alreadyOwnedDialog.setWidth(320f);
		alreadyOwnedDialog.button("EXIT", 1L);
	}
		
	public void createNoMoneyDialog()
	{
		noMoneyDialog = new Dialog("You don't have enough money", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					noMoneyDialog.remove();
			};
		};
		noMoneyDialog.setPosition(340f, 600f);

		noMoneyDialog.setWidth(350f);
		noMoneyDialog.button("EXIT", 1L);
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void onWaitingStarted(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStarted(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameFinished(int code, boolean isRemote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameUpdateReceived(String message) {
		// TODO Auto-generated method stub
		
	}
	
}
