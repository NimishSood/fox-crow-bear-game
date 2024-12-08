package upei.project;

import java.util.Map;

public class StrategyResult {
    private final Map<String,Integer> wins;
    private final Map<String,Integer> usage;
    private final String strategy;
    private final int runs;

    public StrategyResult(String strategy, int runs, Map<String,Integer> wins, Map<String,Integer> usage) {
        this.strategy = strategy;
        this.runs = runs;
        this.wins = wins;
        this.usage = usage;
    }

    public Map<String,Integer> getWins() { return wins; }
    public Map<String,Integer> getUsage() { return usage; }
    public String getStrategy() { return strategy; }
    public int getRuns() { return runs; }
}
