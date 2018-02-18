package com.arturskowronski.naivechain;

import com.arturskowronski.naivechain.p2p.client.BlockchainStompSessionHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired
  private BlockchainStompSessionHandler sessionHandler;

  private static String URL = "ws://localhost:8080/blockchain";
  private static Logger logger = Logger.getLogger(BlockchainStompSessionHandler.class);

  /**
   * This event is executed as late as conceivably possible to indicate that 
   * the application is ready to service requests.
   */
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    List<Transport> transports = new ArrayList<>(1);
    transports.add(new WebSocketTransport( new StandardWebSocketClient()) );
    WebSocketClient transport = new SockJsClient(transports);
    WebSocketStompClient stompClient = new WebSocketStompClient(transport);
    stompClient.setMessageConverter(new MappingJackson2MessageConverter());

    ListenableFuture<StompSession> connect = stompClient.connect(URL, sessionHandler);
    connect.addCallback(
            result -> logger.info("Websockets Connection Established"),
            ex -> logger.error("Websockets Connection Error: " + ex.getMessage()));

  }
}