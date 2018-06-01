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

#### GUI Mock-up
 
Board Screen
 
![boardscreen](https://user-images.githubusercontent.com/25772346/39356333-abcecc3c-4a07-11e8-86fd-45ded9980f84.png)
 
 Property Screen
 
![propertyscreen](https://user-images.githubusercontent.com/25772346/39356395-d2152422-4a07-11e8-9415-609c6b48d4eb.png)
 
 Main Menu
 
![mainmenu](https://user-images.githubusercontent.com/36206773/39408729-b04d838a-4bd2-11e8-9a8f-13edb0b19e9b.png)

Piece Select Screen

![pieceselect](https://user-images.githubusercontent.com/36206773/39408735-cb2d082e-4bd2-11e8-9ed9-7f8f17860dd7.png) 

### Test Design
 
#### List of expected final test cases
 
- Create game (test if all the needed components are created correctly).
- Movement (test if the pieces are moving the correct amout of squares, according to the dice numbers and if it changes the direction of the movement when needed).
- Jail (test if the player goes to jail when needed, and test the exit possibilities (paying the bail, after a number of turns, etc.).
- Communuty Chest and Chance (test if the game is selecting the correct card when the player is in one of those squares and test if the action performed is coherent with the card).
- Taxes (test if the money payed from taxes (either by being in a tax square or by drawing a community chest or chance card with this instruction) is being saved correctly and if the player that moves to the Free Parking square gets the correct amount of money (tax money).
- Properties buy and house/hotel building (test if properties purchase goes as expected and if the player is only able to build houses and hotels in certain situation (if the player has all the properties of a specific color for houses, or if the a certain proporty already had 4 houses before building a hotel).
- Stations and Companies rent growth (test if rent value goes up when the player possesses more than one card of a certain type).
- Go (test if the player collects 200€ when he passes the go square).
- Properties negotiation (test if the negotiation between players goes as expected).
- Properties mortgage (test if the player receives the right amount of money when he morgages a property and if the houses/hotels get collected.
- Bankruptcy (test if a player goes bankrupt when the amount he has to pay is bigger than the amount he has).
