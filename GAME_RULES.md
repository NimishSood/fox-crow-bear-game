# Fox, Crow & Bear Game Rules

## Objective

Be the first player to reach block 100 by navigating through a board filled with various special blocks that can either propel you forward, hinder your progress, or offer strategic choices.

## Components

- **Game Board:** A linear board consisting of 100 blocks, numbered from 1 to 100.
- **Players:** Each player starts at block 1.
- **Dice:** Six-sided dice used to determine movement. The range of dice outcomes can change based on the player's luck attribute.
- **Special Blocks:** These blocks have unique effects when landed upon.

## Block Types

1. **Regular Block:**
    - **Effect:** No special action. Simply allows the player to stay or continue based on dice roll.

2. **Fox Block:**
    - **Effect:** Propels the player forward based on their last dice roll. For example, if the player rolled a 4 previously, landing on a Fox block moves them forward 4 additional blocks.

3. **Crow Block:**
    - **Effect:** Transports the player to the end of the current row. Assuming the board is visualized as a 10x10 grid, this moves the player to the rightmost block of their current row.

4. **Bear Block:**
    - **Effect:** Challenges the player's power. The player must engage in a battle using their power attribute. Success may allow them to move forward or gain other benefits, while failure could result in being pushed back or losing power.

5. **PowerUp Block:**
    - **Effect:** Increases the player's power attribute, enhancing their ability to battle bears and execute powerful actions like pushing other players.

6. **PowerDown Block:**
    - **Effect:** Decreases the player's power attribute, weakening their abilities and making battles more challenging.

7. **Luck Block:**
    - **Effect:** Improves the player's luck attribute, which increases the maximum possible outcome on their dice rolls (e.g., from a standard 6 to a 7, 8, 9, or 10).

8. **Special Block:**
    - **Effect:** Offers players unique strategic choices. Upon landing, players can choose to either:
        - **Boost Forward:** Propel themselves forward, gaining additional blocks based on their power.
        - **Push Opponent:** Push another player backward, hindering their progress.

## Gameplay

1. **Setup:**
    - Each player starts at block 1.
    - Players are assigned attributes:
        - **Power:** Determines the strength in battles and effectiveness in actions.
        - **Luck:** Influences the dice roll outcome range.

2. **Turn Order:**
    - Players take turns in a predetermined order.
    - On a player's turn:
        - **Roll Dice:** The player rolls the dice, resulting in a movement value based on their current luck attribute.
        - **Move Forward:** The player moves forward the number of blocks indicated by the dice roll.
        - **Resolve Block Effect:** The player lands on a block and resolves its effect based on the block type.

3. **Special Blocks:**
    - **Fox, Crow, Bear, PowerUp, PowerDown, Luck:** These blocks automatically trigger their respective effects upon landing.
    - **Special Blocks:** Players choose their action when landing on these blocks, deciding between boosting forward or pushing an opponent.

4. **Battles:**
    - When landing on a Bear block, players engage in a battle using their power attribute.
    - The outcome of the battle affects the player's position and power based on success or failure.

5. **Winning the Game:**
    - The first player to reach or surpass block 100 wins the game.

## Strategy Tips

- **Balance Progress and Aggression:** While pushing opponents can hinder their progress, it's essential to maintain your own advancement to reach block 100 first.
- **Utilize Power and Luck:** Enhance your power to effectively battle bears and push opponents, and improve your luck to maximize your dice rolls.
- **Adapt to Game State:** Adjust your strategy based on your position relative to other players. If you're lagging, prioritize boosting forward; if you're in the lead, consider disrupting others.

## Additional Rules

- **Dice Modification:** Players with higher luck attributes may have dice that allow for outcomes beyond the standard 6, such as 7, 8, 9, or 10, enabling longer jumps on the board.
- **Battle Mechanics:** Specifics of battles (e.g., how power is compared, what constitutes success or failure) should be defined clearly in the implementation.
- **Ties:** In cases where multiple players reach block 100 in the same round, additional rules can determine the winner, such as who reached it first within the round.

## Example Scenario

- **Player A** rolls a 5 and moves to block 5.
- **Block 5** is a Fox block. Player A moves forward an additional 5 blocks to block 10.
- **Block 10** is a Special block. Player A chooses to push the nearest player, Player B, back 3 blocks.
- **Player B** is now on block 7 instead of block 10.

## Conclusion

The "Fox, Crow & Bear" game combines strategic movement with interactive block effects, offering players multiple avenues to outmaneuver their opponents. Balancing aggression with personal advancement and leveraging attributes like power and luck are key to mastering the game and securing victory.
