package com.arturskowronski.naivechain.p2p.client;

import com.arturskowronski.naivechain.domain.Peer;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

@Service
public class P2PClient {
    private static String URL = "ws://localhost:8080/spring-mvc-java/chat";

    static WebSocketClient client = new StandardWebSocketClient();
    static WebSocketStompClient stompClient = new WebSocketStompClient(client);


    public void connectToPeers(Peer peer) {

    }
}
