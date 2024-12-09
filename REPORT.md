
# 2024F CS2910 Project Report  
**Group Members**  
1. Aditya Sagar 370353 
2. Nimish Sood
  
# Summary of Game  

This this game has Fox, Crow and Bear for giving players a boost or hindering the race to 100th block. 6 numbered dices are used by players which change according to luck, obtain steps to move forward and complete the race. One who reaches 100 first wins the game.

There are also some miscellaneous block, such as Power block, Luck block and Special block which help the player increase power to fight the bear, increase luck to increase the maximum possible outcome obtained in dice, and Special block which gives player the ability to strategize in if they want to move forward themselves or they want to push other players down.
  
# Experiment Report  

## Player Strategies  

1. **Pushing down (nearest player focused)**: This strategy by player focuses on using the aggressive approach to other players every time the user gets the opportunity to use the special block, it will push down the **nearest player**. It will target the **current winning player** for that move and keep pushing it down. It makes sense to push down the nearest player in the graph to reduce the chance of them winning. For example, we have 3 players p1,p2 and p3. P1 is at the nearest position, now suppose we are controlling p2, whenever we get a chance to get a boost block, we push down p1, if p3 is in the nearest position now, we would **push p3 down** as it has a higher chance of winning.

2. **Moving Forward**:  This strategy mainly focuses on utilizing every boost block to move 2 steps ahead. This would help move the player the fastest way possible if we disregard other players pushing the player down or the player getting attacked by the bear. This strategy may help the player win if he gains enough power blocks to fight the bear or is not pushed down by other players via Special block. For example, we have 3 players p1 p2 p3 and and we are controlling p1. Whenever p1 is at Special block, we decide to use move 2 steps ahead.

3. **Balance**: This strategy is a mixture of the previous two strategies, if the player is behind the leading opponent more than 10 blocks, the player chooses to move forward, otherwise the player punches other players down. This strategy allows player to not fixate his moves for the whole game and adapt to the given conditions. For example, we have 3 players p1 p2 p3 and and we are controlling p1. Whenever p1 is at boost block, we decide to use move 2 steps ahead. For example, we have 3 players p1 p2 p3 and and we are controlling p1. Whenever p1 is at Special block, we look at other player's position to decide if we want to move forward or punch other players.
  
## Procedure  

In our GUI, there is a menu named `Strategy` which has options such as:  

`Run BOOST strategy`  
`Run PUNCH_NEAREST strategy`
`Run Balance strategy`

Whenever these menu items are clicked, the user is prompted with the number of simulation iterations they want to have. The motive of the simulation is to track how many times each player performed the strategy action. We are greeted with a table then which shows the win rate (number of wins upon total number of simulations).
Under the hood, we have two classes that make this work: `StrategyResult` and `StrategyRunner`. **StrategyRunner** is a class that helps in running multiple strategy games and returns a `StrategyResult` with win and usage status. The method `runStrategyGames` helps store the amount of wins a player has using a strategy with the help of a HashMap. `runSingleGamePlayer` is the method that helps in iterating through players and making them take turns automatically using random dice functions. This function is called to run **one** game and retrieve data from it .  **StrategyResult** is a simple class that helps in storing the number of simulations, wins, usage, and the strategies used in the simulation. Together, these components allow the user to experiment with and analyze different strategies conveniently, giving a clear and detailed overview of performance outcomes through the GUI.
  
## Results  

### Strategy 1 Results 

| Player Name	 | Wins                        |  
| -------------- | --------------------------- |  
| Harsh          | 16 wins out of 60                           |  
| Nimish         | 18 wins out of 60                       |  
| Govind         | 8  wins out of 60                        |  
| Aditya         | 18 wins out of 60                


### Strategy 2 Results 

 | Player Name	 | Wins                        |  
| -------------- | --------------------------- |  
| Harsh          | 18 wins out of 60                          |  
| Nimish         | 11 wins out of 60                           |  
| Govind         | 12 wins out of 60                          |  
| Aditya         | 19  wins out of 60                          |   

  
  ### Strategy 3 Results
   | Player Name	 | Wins                        |  
| -------------- | --------------------------- |  
| Harsh          | 13 wins out of 60                          |  
| Nimish         | 17 wins out of 60                           |  
| Govind         | 10 wins out of 60                          |  
| Aditya         | 20  wins out of 60                          |   
  
# Analysis

The experiment analyzes three distinct strategies used by players in a competitive gaming the setting: Balance, Pushing Down (Nearest Player Focused), and Moving Forward. The win rates of four players (Harsh, Nimish, Govind, and Aditya) across 60 games per strategy serve as the basis for each strategy's analysis. The  pushing down strategy tries to reduce the possibility of any one player holding a lead by consistently pushing down the nearest opponent. Both Nimish and Aditya achieved 18 wins, indicating that the strategy allowed them to win effectively. Harsh followed with 16 wins, suggesting a competitive performance. No player achieved over 30% of the wins, suggesting that this strategy levels the game by keeping all players from gaining a significant lead.  This strategy prevents runaway leads by constantly disrupting the nearest player. One disadvantage of this strategy is The focus on pushing others down may come at the expense of the player’s moving forward in the game. The moving forward approach places a high priority on moving quickly by taking advantage of every chance to get ahead by two steps. It is entirely focused on the player itself and ignores other players' positions in an effort to maintain a lead. It enables rapid movement, increasing the chances of avoiding attacks or obstacles like the bear. Aditya and Harsh performed well, with 19 and 18 wins, respectively. Their ability to maintain a steady pace may have contributed to their high success rate. This strategy might look good, but it ignores the influence of other players, potentially allowing competitors to gain advantages unchallenged and makes players vulnerable to aggressive strategies like pushing down, as seen in the moderate win rates for some players. The balanced approach adapts to game conditions by switching between moving forward and pushing down. Aditya achieved 20 wins, the highest across all strategies, highlighting the effectiveness of adaptive play for maintaining competitiveness, combining the advantages of strategic change with active movement. Overall, the Balance strategy works best because it combines flexibility with an ability to overcome particular obstacles presented by various game conditions. It helps players like Aditya and Nimish to succeed and lessens the drawbacks of solely aggressive or forward-focused strategies, even though it might not produce the highest average win rates among all players.  In conclusion, the Balance strategy is recommended for players who can effectively adapt to dynamic conditions, while simpler strategies like Moving Forward may be better for those who prefer a straightforward approach. 


# Reflection 

- We used ChatGPT as our generative AI helping tool. It greatly helped in solving some errors which were hard to detect. For example, in ``Special.java`` keyboard input was compared with a string using ``==`` sign, always making our conditions false and not running the code, ChatGPT was able to detect this error by recommending to use ``.equalsIgnoreCase(String sampleString)`` instead of   ``==``. We also used this tool to refactor our code in a more presentable and efficient manner. It was a challenge on deciding how to test the ``GameBoard.java`` class, we took inspiration from ChatGPT's standards of testing the class. While working on a big project, we also struggled in writing clean and readable code. The tool helped us in refactoring and documenting code in a professional manner. For example, our code was very disorganized for ``Special`` class, but ChatGPT helped to refactor and document our code for further studying of it. In our experience, ChatGPT has been really good in solving small pieces of problems, like refactoring code, writing tests for a specific class, on correcting some basic syntax errors, but when it comes to solving bigger problems involving two or more classes, ChatGPT loses it's track and is unable to solve the solution in a simple manner. It was observed that the tool easily deviated from the given goal and wasn't able to process big chunks of code to refactor. It also wasn't able to hold the information from the previous prompt properly and started giving vague and incorrect responses. If we had more time, we could have implemented a GUI  sprites of dices, players, animals and blocks to make the experience more interactive and appealing. With the help of GUI 's  we would have removed the ``power`` attribute of ``Player`` class and allowed the players to fight ``Bear`` with constant input from mouse and keyboard actions, making the player rely more on their gaming skills instead of their power earned from blocks. We also wanted to add ``SpinTheWheel`` block in the game, to give player multiple choices on the block to choose and earn different rewards or powers. Spin the wheel would have required a really different mechanism so we archived it for later, this would have surely made the game more fun. This feature would have added more unpredictability and strategy to the game. AI Also helped us pick us some things that we didn't consider while analyzing our datasets. For example, the tool helped showing the disadvantages and advantages of ths strategies implemented.  



  
# Bonus Consideration:  
If you have aspects of your project you would like considered for the available bonus.
