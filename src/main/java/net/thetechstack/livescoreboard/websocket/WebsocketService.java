package net.thetechstack.livescoreboard.websocket;

import java.util.concurrent.ExecutionException;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import net.thetechstack.livescoreboard.score.Score;

@Service
public class WebsocketService {
    @Value("${server.port}") private int serverPort;
    @Autowired WebSocketStompClient webSocketStompClient;
    @Autowired StompSessionHandler stompSessionHandler;
    private StompSession stompSession = null;
    
    @PreDestroy
    private void preDestroy() throws InterruptedException, ExecutionException {
        if(this.getStompSession() != null)
            this.getStompSession().disconnect();
    }
    
    private StompSession getStompSession() throws InterruptedException, ExecutionException {
        if(this.stompSession == null) {
            this.stompSession = webSocketStompClient.connect("ws://localhost:"+serverPort+"/publish-score", stompSessionHandler).get();
            return this.stompSession;
        }
        else
            return stompSession;
    }
    public void publish(Score score) {
        try{
            this.getStompSession().send("/app/publish-score", score);
        }catch(Exception e) {
            System.out.println("Exception when sending message");
            System.out.println(e);
        }
    }
}
