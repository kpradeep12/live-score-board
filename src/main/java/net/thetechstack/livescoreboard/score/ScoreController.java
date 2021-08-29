package net.thetechstack.livescoreboard.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.thetechstack.livescoreboard.websocket.WebsocketService;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreService scoreService;
    @Autowired
    WebsocketService websocketService;

    @MessageMapping("/publish-score")
    @SendTo("/topic/score")
    public Score publishScore(Score score) {
        System.out.println("publish score " + score);
        return score;
    }

    @PutMapping("/increment/red")
    public void redIncrement() {
        System.out.println("red increment");
        this.websocketService.publish(this.scoreService.incrementRed());
    }

    @PutMapping("/increment/blue")
    public void blueIncrement() {
        System.out.println("blue increment");
        this.websocketService.publish(this.scoreService.incrementBlue());
    }

    @PutMapping("/reset")
    public void reset() {
        System.out.println("reset");
        this.websocketService.publish(this.scoreService.resetScore());
    }

    @PutMapping("/undo")
    public void undo() {
        System.out.println("undo");
        this.websocketService.publish(this.scoreService.undoLastScore());
    }

    @GetMapping
    public Score getScore() {
        System.out.println("get score");
        return this.scoreService.currentScore();
    }
}
