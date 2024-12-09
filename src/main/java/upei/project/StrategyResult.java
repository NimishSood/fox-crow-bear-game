package upei.project;

import java.util.Map;

/**
 * Holds the results of multiple strategy runs, including
 * wins per player, usage counts for total actions,
 * usageBoost and usagePunch to differentiate actions for Balanced strategy.
 */
public class StrategyResult {
    private final Map<String,Integer> wins;
    private final Map<String,Integer> usage;       // total usage if single-action strategy
    private final Map<String,Integer> usageBoost;  // For strategies that have boost (BALANCED or BOOST)
    private final Map<String,Integer> usagePunch;  // For strategies that have punch (BALANCED or PUNCH_NEAREST)
    private final String strategy;
    private final int runs;

    public StrategyResult(String strategy, int runs,
                          Map<String,Integer> wins,
                          Map<String,Integer> usage,
                          Map<String,Integer> usageBoost,
                          Map<String,Integer> usagePunch) {
        this.strategy = strategy;
        this.runs = runs;
        this.wins = wins;
        this.usage = usage;
        this.usageBoost = usageBoost;
        this.usagePunch = usagePunch;
    }

    public Map<String,Integer> getWins() { return wins; }
    public Map<String,Integer> getUsage() { return usage; }
    public Map<String,Integer> getUsageBoost() { return usageBoost; }
    public Map<String,Integer> getUsagePunch() { return usagePunch; }
    public String getStrategy() { return strategy; }
    public int getRuns() { return runs; }
}
