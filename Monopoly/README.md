# Monopoly
 
## Intermediate Deliverables
 
### Architecture Design
 
#### UML Diagram
 
#### Behavioural Aspects
 
#### Expected Design Patterns
 
- MVC - Model, View and Controller.
 
  **Controller** - Handles the data in an abstract form (AKA, for example, sees the game Board as an ArrayList of Squares, a Square being another class used to represent each one of the Board's spaces) setting in motion the game logic according with the inputs.
 
  **View** - responsible for the output of the information, handling the drawing of all of the necessary elements be they UI or game related. It is also in charge of swapping between screens.
 
  **Model** - processes the changes made at an abstract level by the controller receiving inputs from that element, and informs View of the object to be displayed to match these.
  
  **Model** and **Controller** have two packages created for them: one for the *Singleton* instance of each one, and another for all the entities (board, player, cards, etc..).
  
  **View** also has two packages: one for all the screens that are going to be showed during the game (Main Menu, Piece Selection, Board Screen, etc.) and another for all the entities (board, player, cards, etc..).
  
  This pattern is used to separate internal information in different areas, allowing us to develop the code in parallel.
  
- Singleton - used to ensure there is only one instance of a class, keeping a global point of access to all classes related to the singleton.
 
  There are three classes that use this pattern: Monopoly.java, GameController.java and GameModel.java.
    
- Factory - defines an interface for creating an object, but lets the subclasses decide which class to instantiate.
 
  This pattern is used to "delegate" responsabilities away from the main classes into the *Entityview* subclasses.
 
### GUI Design
 
#### Main Functionalities
 
- Load game.
- Select the desired piece to play with.
- Show each player's money and piece, as well as the current tax money (money to be awarded to the next player to end up in the Free Parking square.
- View all properties and who owns each one.
- Build houses/hotels.
- Negotiate properties.
- Mortgate properties.
- Save current game.
 
#### GUI Mock-up
 
Board Screen
 
![boardscreen](https://user-images.githubusercontent.com/25772346/39356333-abcecc3c-4a07-11e8-86fd-45ded9980f84.png)
 
Property Screen
 
![propertyscreen](https://user-images.githubusercontent.com/25772346/39356395-d2152422-4a07-11e8-9415-609c6b48d4eb.png)
 
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
