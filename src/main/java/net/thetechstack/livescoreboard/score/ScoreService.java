package net.thetechstack.livescoreboard.score;

import java.util.Stack;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    private Stack<Score> scoreStack = new Stack<>();

    @PostConstruct
    private void PostConstruct() {
        scoreStack.push(new Score(0,0));
    }
    
    public Score currentScore() {
        return scoreStack.peek();
    }

    public Score incrementRed() {
        Score score = scoreStack.peek().incrementRedScore();
        scoreStack.push(score);
        return scoreStack.peek();
    }

    public Score incrementBlue() {
        Score score = scoreStack.peek().incrementBlueScore();
        scoreStack.push(score);
        return scoreStack.peek();
    }

    public Score resetScore() {
        scoreStack.clear();
        scoreStack.push(new Score(0, 0));
        return scoreStack.peek();
    }

    public Score undoLastScore(){
        if(scoreStack.size() > 1)
            scoreStack.pop();
        return scoreStack.peek();
    }
}
