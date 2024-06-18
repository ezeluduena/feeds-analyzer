package utils;

public class Config {
    private boolean printFeed = false;
    private boolean computeNamedEntities = false;
    private String feedKey;
    private String heuristic;
    private String statsFormat;

    public Config(boolean printFeed, boolean computeNamedEntities, String feedKey, String heuristic,
            String statsFormat) {
        this.computeNamedEntities = computeNamedEntities;
        this.feedKey = feedKey;
        this.heuristic = heuristic;
        this.statsFormat = statsFormat;

        this.setFeedKey(feedKey);
        this.setPrintFeed(printFeed);
        this.setStatsFormat(computeNamedEntities, statsFormat);

    }

    private void setStatsFormat(boolean computeNamedEntities, String statsFormat) {
        if (statsFormat == null && computeNamedEntities) {
            this.statsFormat = "cat";
        }
    }

    private void setFeedKey(String feedKey) {
        if (feedKey == null) {
            this.feedKey = "all";
        }
    }

    private void setPrintFeed(boolean printFeed) {
        if (!this.computeNamedEntities) {
            this.printFeed = true;
        } else {
            this.printFeed = printFeed;
        }
    }

    public boolean getPrintFeed() {
        return printFeed;
    }

    public boolean getComputeNamedEntities() {
        return computeNamedEntities;
    }

    public String getFeedKey() {
        return feedKey;
    }

    public String getHeuristic() {
        return heuristic;
    }

    public String getStatsFormat() {
        return statsFormat;
    }

}
