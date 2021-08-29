package net.thetechstack.livescoreboard.score;

public class Score {
    private Integer redScore;
    private Integer blueScore;

    public Score(Integer redScore, Integer blueScore) {
        this.redScore = redScore;
        this.blueScore = blueScore;
    }

    public Integer getRedScore() {return redScore;}
    public Integer getBlueScore() {return blueScore;}
    public Score incrementRedScore() {
        return new Score(redScore + 1, blueScore);
    }
    public Score incrementBlueScore() {
        return new Score(redScore, blueScore + 1);
    }
    public String toString() {
        return "red: " + redScore + " blue: " + blueScore;
    }
}
