# Monopoly

### Instalation/Setup

### Architecture Design
 
#### UML Diagram

Model part

![controllerdiagram](https://user-images.githubusercontent.com/36206773/39408765-2de92da8-4bd3-11e8-995f-4aaebe9af602.png)

View Part

![viewdiagram](https://user-images.githubusercontent.com/36206773/39408773-5c0a9212-4bd3-11e8-855c-989d934400b2.png)

Controller Part

![modeldiagram](https://user-images.githubusercontent.com/36206773/39408777-66d39018-4bd3-11e8-91c6-143527278d5f.png)
 
#### Behavioural Aspects

Link to pdf

[Basic Sequence Diagram.pdf](https://github.com/adobe/brackets/files/1958945/Basic.Sequence.Diagram.pdf)

Image

![basic sequence diagram-1](https://user-images.githubusercontent.com/36206773/39408808-fe662df0-4bd3-11e8-9e2b-cfc22558f7fb.png)
 
#### Design Patterns
 
- MVC - Model, View and Controller.
 
  **Model** - stores and manages all the data from the game. "Game" stores the players in game, the board, queues with chance cards and community chest cards, etc. There is also a package of entities that include all types of squares, with each of them having a specific action that are applied to the players, the player class, with a bot subclass that handles the bot behaviour, and the board pieces class that move the player according to his piece.
 
  **View** - responsible for the output of the information, always updating the screen according to the data stores in the game model, like each player's money, the piece position, the current player, etc. . Each screen has buttons that allows the user to navigate through screen has he wishe.
 
  **Controller** - used as a bridge between the view and model. Every time the user presses a button, the controller receives and handles that information and converts it in instructions for the model. For example, when the player rolls the dice, the controller calls the model methods to roll the dice, move the player and for the square to do his action. If the player is a bot it will also call his behavioural methods.
  
  **Model** and **Controller** have two packages created for them: one for the *Singleton* instance of each one, and another for all the entities (board, player, cards, etc..).
  
  **View** also has two packages: one for all the screens that are going to be showed during the game (Main Menu, Piece Selection, Game Screen, etc.) and another for all the entities (board, player, cards, etc..).
  
  This pattern is used to separate internal information in different areas, allowing us to develop the code in parallel.
  
- Singleton - used to ensure there is only one instance of a class, keeping a global point of access to all classes related to the singleton.
 
  There are three classes in the game that use this pattern: Monopoly.java, GameController.java and Game.java.
 
### GUI Design
 
#### Main Functionalities
 
- Select number of human players
- Adding bots
- Select the desired piece to play with.
- Show each player's name, money, piece and current position
- Sounds for each square
- Option toggle sound on or off
- Message with who is the current player
- View each player's properties
- Build houses/hotels.
- Negotiate properties.
- Mortgate properties.
 
#### Major dificulties

A major dificulty during the development of the project was understand what exactly was the responsibilities of each component of the MVC design pattern, to the point that, in the beggining, we had the concepts of Model and Controller confused. With the help of the teachers we eventually understood the "job" of each component.

We also had some difficulties with how to work with libgdx since it was the first time both of us interacted with the software, and we had to spend a lot of hours browsing the internet to find solutions to our problems (like how to only show the dialogs in specific situations).

Finally, we had problems with time, since we didn't had enough to invest in the porject as we would like, since , as mentioned above, we didn't know how to work with libgdx and there was a lot of logic to implement in the model.

#### Lessons learned

We think the major thing we learned was how to work with libgdx, since it is a very useful tool to have in our repertoire. It also allows us to embilish the interface in a way that tools like Java Swing doesn't, and use very useful methods that are not available without theis software.

Also the MVC model design pattern is very usefull to keep the logic and the user interface separated and despite being a little hard to understand it in the beginning, its going to be very usefull in our future. 

#### Overall time spent

It is very hard to give an exact number of hours used developing the game, but at least 100 hours were for sure used among both of us.

#### Distribution of the work

Luis Mendes: 

- Initial structure of project
- Piece Selection screen (pre- multiplayer)
- Player movement
- Jail logic
- Rent calculation
- Start square gic
- House and hotel building
- Negotiation between two players

Ricardo Boia 

- Bots implementation
- All custom drawings used for the game (Board, Chance and Community Chest cards, Dice, Properties, etc.)
- Implementation of sounds
- Properties Screen, Number of players selection screen
- Local multiplayer implementation
- Purchase of properties, 
- Mortgage
- Chance and Community Chest cards logic
- Taxes logic
- Documentation

Each of us created the tests to the logic implemented by us.

#### User manual
 
When game starts the user goes to a screen where he can start a game or exir the application

![main menu](https://user-images.githubusercontent.com/25772346/40852461-6e560114-65c2-11e8-8665-6e1129bd1ab0.png)

If the user chooses to start a game he will then choose the number of human players, from 1 to 4

![number of players](https://user-images.githubusercontent.com/25772346/40852459-6e18b05c-65c2-11e8-8b6d-7767c53293d2.png)

Then each player will choose a piece for himself

![piece selection](https://user-images.githubusercontent.com/25772346/40852471-7426783a-65c2-11e8-8ba2-273770b33124.png)

The rest of the pieces will be taken by bots until the number of players is 4

Then the game starts.

In this screen the user has information about :
- Each player's money, board piece, name and current position,
- The current player

Plus there is also the value of last dice roll and a musical note on the left lower corner that allows the user to toggle the sounds on and off 

![game screen](https://user-images.githubusercontent.com/25772346/40852472-7451168a-65c2-11e8-888f-7f6832711a0e.png)

The player1 will roll the dice by pressing ROLL DICE and complete his turn by pressing END TURN (this buttons will never be active at the same time)

When its the bot turn, the user will only have one button available at the time. First BOT TURN, and then END BOT TURN.

![bot turn](https://user-images.githubusercontent.com/25772346/40852473-7478e624-65c2-11e8-9cc9-bd7e024cd2eb.png)

When its a player turn, he will have access to three other buttons:

- BUY PROPERTY, which, if possible, will purchase the square where the player is. If not possible, an error message will show.
- PROPERTIES, which opens a screen where the player can check all his properties. Plus he can build houses and hotels, in addition to mortgage (and then rebuy) the property
![properties screen](https://user-images.githubusercontent.com/25772346/40852474-74b5b14e-65c2-11e8-81a6-207d3fb406f4.png)
- NEGOTIATE, which allows the player to purchase other player's properties by entering their name and buy the selected property
![negotiations screen](https://user-images.githubusercontent.com/25772346/40852469-73f0d3e2-65c2-11e8-9548-c63b9067c2ce.png)
