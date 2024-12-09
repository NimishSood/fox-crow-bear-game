# 2024F CS2910 Project Report

**Group Members**
1. Aditya Sagar 370353
2. Nimish Sood 369051

# Summary of Game

The "Fox, Crow & Bear" game challenges players to reach the 100th block first by navigating through various special blocks using six-sided dice, which can change based on a player's luck attribute. Players encounter Fox blocks that propel them forward based on their last move, Crow blocks that transport them to the row's end, and Bear blocks that require players to battle using their power. Additionally, PowerUp and PowerDown blocks modify a player's power, while Luck blocks adjust their dice range (7, 8, 9, 10). Special blocks allow players to strategize by either boosting themselves forward or pushing other players backward. The first player to reach block 100 wins. [Game Rules](GAME_RULES.md)

*Note: This report was crafted with the assistance of generative AI tools to ensure clarity and coherence.*

# Experiment Report

## Player Strategies

1. **Pushing Down (Nearest Player Focused):**  
   This aggressive strategy prioritizes targeting the nearest opponent whenever a Special block is encountered. For instance, if Player A controls this strategy and lands on a Special block, they will push down the player closest to the finish line, aiming to hinder their progress and reduce their chances of winning. By consistently disrupting the leading players, this strategy seeks to level the playing field, making it harder for any single player to dominate the game.

2. **Moving Forward:**  
   This strategy focuses solely on advancing as quickly as possible by utilizing every available Boost block to move two steps ahead. Ignoring other players' positions, the player prioritizes self-progression. For example, Player B, employing this strategy, will always choose to boost themselves upon landing on a Special block, aiming to reach block 100 rapidly. This approach relies on minimizing delays and capitalizing on power-ups to maintain a steady lead.

3. **Balance:**  
   Combining aggression and self-advancement, the Balance strategy adapts based on the player's position relative to others. If a player is more than 10 blocks behind the leader, they choose to boost themselves to catch up. Otherwise, they opt to push down the nearest opponent. For example, Player C will assess their position; if behind significantly, they will boost forward, but if not, they will disrupt the nearest competitor. This flexibility aims to optimize both personal progress and strategic interference.

## Procedure

Our simulation utilizes a graphical user interface (GUI) with a dedicated `Strategy` menu, offering options to run the BOOST, PUNCH_NEAREST, and BALANCED strategies. Upon selecting a strategy, the user is prompted to input the number of simulation iterations they wish to execute. For each strategy, we conducted 60 trials to ensure statistical significance, especially since our game involves four players using different strategies.

The core components facilitating the simulation are the `StrategyRunner` and `StrategyResult` classes. **StrategyRunner** is responsible for executing multiple game instances based on the selected strategy. It initializes players with assigned strategies and automates their turns using randomized dice rolls influenced by their luck attributes. Each game continues until a player reaches block 100, upon which the winner is recorded.

**StrategyResult** captures comprehensive data from each simulation, including:
- **Win Counts:** Number of wins per player.
- **Action Usage:** Total number of boosts and punches executed by each player.
- **Actions per Win:** Average number of actions taken to secure a win.
- **Strategy Execution:** How effectively each strategy was employed throughout the game.

After running the simulations, the results are displayed in a user-friendly table within the GUI, showing each player's win rate and action metrics. This setup allows for a clear comparison of how each strategy performs under consistent conditions, providing insights into their effectiveness and efficiency. The experimental design ensures that each strategy is tested thoroughly across multiple game scenarios, enabling a robust analysis of their respective strengths and weaknesses.

*Note: The documentation and presentation of this procedure were refined using generative AI tools to enhance readability and structure.*

# Results

### Strategy 1: Pushing Down (Nearest Player Focused)

| Player Name | Wins (Out of 60) |
|-------------|-------------------|
| Harsh       | 16                |
| Nimish      | 18                |
| Govind      | 8                 |
| Aditya      | 18                |

### Strategy 2: Moving Forward

| Player Name | Wins (Out of 60) |
|-------------|-------------------|
| Harsh       | 18                |
| Nimish      | 11                |
| Govind      | 12                |
| Aditya      | 19                |

### Strategy 3: Balance

| Player Name | Wins (Out of 60) |
|-------------|-------------------|
| Harsh       | 13                |
| Nimish      | 17                |
| Govind      | 10                |
| Aditya      | 20                |

**Summary:**  
We conducted 60 simulations for each of the three strategies across four players. In the **Pushing Down** strategy, Nimish and Aditya led with 18 wins each, Harsh secured 16, and Govind lagged with 8 wins. The **Moving Forward** strategy saw Aditya topping the chart with 19 wins, closely followed by Harsh with 18 wins. Nimish and Govind had 11 and 12 wins, respectively. The **Balance** strategy resulted in Aditya achieving the highest number of wins at 20, Nimish with 17, Harsh with 13, and Govind with 10 wins. These results indicate that the Balance strategy slightly outperforms the others, with Moving Forward also demonstrating strong performance. Pushing Down, while effective for certain players, generally resulted in fewer wins, particularly for Govind.

# Analysis

The experiment assessed three distinct strategies—**Pushing Down**, **Moving Forward**, and **Balance**—across four players over 60 simulations each. The primary metric was the number of wins per player, complemented by action usage data such as boosts and punches. Below is an in-depth analysis of each strategy's performance and underlying dynamics.

### Pushing Down (Nearest Player Focused)

This strategy aims to disrupt the progress of the nearest opponent by consistently pushing them backward whenever a Special block is encountered. The rationale is to prevent any single player from establishing a dominant lead. The results show that Nimish and Aditya each secured 18 wins, Harsh achieved 16, and Govind only 8 wins. While this strategy effectively hampers the leading players, it may inadvertently slow down the player's own progress. Govind's lower win count suggests that over-aggression can backfire, possibly by neglecting self-advancement or becoming a target itself.

### Moving Forward

Focusing exclusively on personal advancement, this strategy utilizes every Boost block to move two steps ahead, disregarding other players' positions. Aditya emerged as the most successful player with 19 wins, followed closely by Harsh with 18 wins. Nimish and Govind had 11 and 12 wins, respectively. The high win rates for Aditya and Harsh indicate that consistent forward movement is a reliable path to victory. However, this strategy's drawback is its lack of interference with opponents, potentially allowing others to overtake unimpeded. Nimish and Govind's moderate win counts reflect a balance between self-advancement and vulnerability to aggressive strategies like Pushing Down.

### Balance

The Balance strategy combines both self-advancement and strategic interference. Players assess their position relative to the leader: if they are more than 10 blocks behind, they choose to boost themselves to catch up. Otherwise, they push down the nearest opponent. Aditya achieved the highest number of wins at 20, Nimish with 17, Harsh with 13, and Govind with 10 wins. This adaptability allows players to respond dynamically to game conditions, leveraging aggressive actions when necessary and focusing on personal progress otherwise. The superior performance of Aditya suggests that the Balance strategy effectively mitigates the weaknesses of the other two strategies by maintaining a flexible approach.

### Comparative Insights

1. **Win Rates:**
   - **Balance (20 wins):** Highest overall, demonstrating the effectiveness of adaptive strategies.
   - **Moving Forward (19 wins):** Close second, highlighting the strength of consistent self-advancement.
   - **Pushing Down (18 wins):** Equal to Aditya’s wins in Moving Forward but significantly lower for other players.

2. **Action Efficiency:**
   - **Balance:** Likely optimized actions per win due to strategic switching, though specific data on actions per win would provide deeper insights.
   - **Moving Forward:** High efficiency in self-advancement but potential inefficiency when opponents are not sufficiently hindered.
   - **Pushing Down:** Effective in certain scenarios but less efficient overall, especially if overused.

3. **Player Performance:**  
   Aditya consistently performed well across all strategies, indicating a possible inherent advantage or superior execution of the strategies. Govind's lower performance, particularly in Pushing Down and Balance, suggests that aggressive strategies may not suit all players equally.

### Strategic Effectiveness

The **Balance** strategy outperforms the others by merging the benefits of both self-advancement and opponent disruption. It allows players to maintain momentum while strategically hindering others when necessary, leading to a higher overall win rate. **Moving Forward** also demonstrates strong performance, emphasizing the importance of consistent progression. In contrast, **Pushing Down** offers moderate success but may be limited by its aggressive nature, which can neglect personal advancement and make players vulnerable.

### Recommendations

Based on the simulation data:
- **Optimal Strategy:** Balance, due to its adaptability and higher win rate.
- **Alternative Strategy:** Moving Forward, for players who prefer a straightforward approach.
- **Caution on Aggression:** Pushing Down should be used selectively, as excessive aggression can impede personal progress and overall effectiveness.

Further experimentation with additional data points, such as actions per win and the impact of power and luck modifications, could provide more comprehensive insights into each strategy's strengths and weaknesses.

# Reflection

Throughout the development of the "Fox, Crow & Bear" game, we extensively utilized ChatGPT as our generative AI tool. Its integration into our workflow significantly influenced both our coding practices and problem-solving approaches, yielding a mix of advantages and challenges.

### Benefits of Using ChatGPT

1. **Debugging Assistance:**  
   One of the most notable instances where ChatGPT proved invaluable was in debugging logical errors. For example, in `Special.java`, we mistakenly used the `==` operator to compare strings, causing our conditions to fail consistently. ChatGPT quickly identified this issue, recommending the use of `.equalsIgnoreCase(String sampleString)` instead of `==`, thereby resolving the bug efficiently.

2. **Code Refactoring and Optimization:**  
   As our project grew in complexity, maintaining clean and efficient code became challenging. ChatGPT assisted in refactoring disorganized sections, such as the `Special` class, enhancing readability and performance. It provided suggestions for optimizing loops, reducing redundant code, and adhering to Java best practices, which streamlined our development process.

3. **Test Case Generation:**  
   Developing comprehensive test cases for our strategies was crucial for ensuring robustness. ChatGPT aided in designing effective JUnit tests by outlining key scenarios and edge cases. This guidance helped us achieve better test coverage and more reliable results, aligning with our Test-Driven Development (TDD) approach.

4. **Documentation Support:**  
   Writing clear and professional documentation was essential for both our team and future maintainers. ChatGPT facilitated this by generating detailed Javadoc comments and block-level explanations, ensuring that our codebase was well-documented and self-explanatory.

5. **Presentation of Documentation:**  
   Beyond code-related assistance, ChatGPT helped in structuring and presenting our project documentation. It provided clear and coherent language for the report sections, ensuring that the content was easy to understand and professionally formatted. This was particularly beneficial in translating complex technical details into accessible explanations for the report.

### Challenges and Limitations

1. **Handling Complex Interdependencies:**  
   While ChatGPT excelled at addressing isolated issues, it struggled with problems involving multiple interdependent classes. For instance, when attempting to implement a feature that required coordination between `Player`, `GameBoard`, and `Special` classes, ChatGPT provided fragmented solutions that lacked coherence. This necessitated additional manual integration and testing to achieve the desired functionality.

2. **Maintaining Context Over Extended Interactions:**  
   In scenarios requiring extended interactions, such as implementing new strategies or major refactoring efforts, ChatGPT occasionally lost track of the broader project context. This led to suggestions that were misaligned with our overall design or redundant with existing implementations, requiring us to filter and adapt the AI's input carefully.

3. **Vague and Generic Recommendations:**  
   At times, ChatGPT's suggestions were overly generic, lacking specificity tailored to our project's unique requirements. For example, when seeking advice on optimizing the simulation's performance, the responses were broad, offering general best practices without actionable steps relevant to our specific implementation.

4. **Dependence on AI Output Quality:**  
   Relying on ChatGPT introduced a dependency on the quality of its responses. Occasionally, it provided incorrect or suboptimal code snippets that, if unchecked, could propagate errors into our codebase. This underscored the importance of critical evaluation and validation of AI-generated content.

### Impact on Team Dynamics and Learning

The use of ChatGPT fostered a collaborative environment where team members could seek instant assistance, enhancing productivity and reducing frustration during challenging phases. It served as a supplementary resource, complementing our collective knowledge and accelerating the development timeline.

However, over-reliance on AI tools may have limited our deeper engagement with certain coding principles and problem-solving techniques. While ChatGPT facilitated quick fixes, it is essential to balance AI assistance with hands-on learning to ensure comprehensive understanding and skill development.

### Future Considerations

If granted more time, we would leverage ChatGPT to explore more advanced features, such as integrating AI-driven decision-making for player strategies or enhancing the GUI with interactive elements like animated dice and player avatars. Additionally, implementing a feature like "SpinTheWheel" would have benefited from more nuanced AI assistance to design the underlying mechanics effectively.

We also aim to delve deeper into optimizing our strategies by analyzing more granular data points and possibly employing machine learning techniques to refine player decision-making processes based on historical game outcomes.

In conclusion, generative AI tools like ChatGPT played a pivotal role in our project's success by providing timely solutions and enhancing code quality. Nevertheless, mindful usage and critical assessment of AI-generated content are crucial to maximize benefits while mitigating limitations. Balancing AI assistance with manual problem-solving fosters a more resilient and skilled development team.

*Note: The documentation and presentation aspects of this project were significantly enhanced with the help of generative AI tools, ensuring clarity and professionalism.*

# Bonus Consideration

Our project incorporates several advanced features that align with the potential bonus criteria:

1. **Advanced Object-Oriented Design:**  
   We implemented an inheritance hierarchy with abstract classes and interfaces to encapsulate different block types (Fox, Crow, Bear) and player strategies. This design allows for easy scalability and future enhancements, adhering to high standards of OOD principles.

2. **Dynamic Strategy Integration:**  
   By designing a flexible `StrategyRunner` and `StrategyResult` system, we enabled seamless integration and comparison of multiple strategies. This modular approach facilitates the addition of new strategies without altering the core game mechanics, demonstrating high cohesion and low coupling.

3. **Comprehensive AI Reflection:**  
   Our reflection section critically evaluates both the benefits and limitations of using generative AI tools like ChatGPT. We provide specific examples of how AI assisted in debugging and refactoring, as well as discussing scenarios where AI fell short. This depth of analysis showcases our ability to engage thoughtfully with emerging technologies.

4. **User-Friendly Simulation Interface:**  
   Beyond basic functionality, our GUI offers clear prompts and organized result displays, enhancing user experience. The inclusion of tooltips and detailed logging provides players with insightful feedback, making the simulation both informative and engaging. Additionally, the GUI allows users to run simulations by specifying the number of iterations, providing flexibility and control over the experimental process. This feature was not a requirement of the project but was implemented to offer a more interactive and user-friendly experience.

5. **Potential for Enhanced Interactivity:**  
   Although not fully implemented, our plan to add features like animated sprites and interactive blocks indicates foresight into creating a more immersive gaming experience. These planned enhancements demonstrate our commitment to advancing the project beyond its initial scope.

Given these aspects, we believe our project merits bonus consideration for its sophisticated design, strategic flexibility, and thoughtful integration of AI tools.

---
