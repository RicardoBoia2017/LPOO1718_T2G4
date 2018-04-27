# Monopoly

## Intermediate Deliverables

### Architecture Design

#### UML Diagram

#### Behavioural Aspects

#### Expected Design Patterns

### GUI Design

#### Main Functionalities

- Load game
- Select the desired piece to play with
- Show each player's money and piece, as well as the current tax money (money to be awarded to the next player to end up in the Free Parking square.
- View all proporties and who owns each one
- Build houses/hotels
- Negotiate properties
- Mortgate proporties
- Save current game

#### GUI Mock-up

Board Screen

![boardscreen](https://user-images.githubusercontent.com/25772346/39356333-abcecc3c-4a07-11e8-86fd-45ded9980f84.png)

Property Screen

![propertyscreen](https://user-images.githubusercontent.com/25772346/39356395-d2152422-4a07-11e8-9415-609c6b48d4eb.png)

### Test Design

#### List of expected final test cases

- Create game (test if all the needed components are created correctly)
- Movement (test if the pieces are moving the correct amout of squares, according to the dice numbers and if it changes the direction of the movement when needed)
- Jail (test if the player goes to jail when needed, and test the exit possibilities (paying the bail, after a number of turns, etc.)
- Communuty Chest and Chance (test if the game is selecting the correct card when the player is in one of those squares and test if the action performed is coherent with the card)
- Taxes (test if the money payed from taxes (either by being in a tax square or by drawing a community chest or chance card with this instruction) is being saved correctly and if the player that moves to the Free Parking square gets the correct amount of money (tax money).
- Proporties buy and house/hotel building (test if proporties purchase goes as expected and if the player is only able to build houses and hotels in certain situation (if the player has all the proporties of a specific color for houses, or if the a certain proporty already had 4 houses before building a hotel)
- Stations and Companies rent growth (test if rent value goes up when the player possesses more than one card of a certain type)
- Go (test if the player collects 200€ when he passes the go square)
- Proporties negotiation (test if the negotiation between players goes as expected)
- Proporties mortgage (test if the player receives the right amount of money when he morgages a property and if the houses/hotels get collected)
- Bankruptcy (test if a player goes bankrupt when the amount he has to pay is bigger than the amount he has) 
