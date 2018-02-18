package com.arturskowronski.naivechain.p2p.server;

import com.arturskowronski.naivechain.domain.Peer;
import com.arturskowronski.naivechain.p2p.messages.BlockchainMessage;
import com.arturskowronski.naivechain.p2p.messages.QueryAllMessage;
import com.arturskowronski.naivechain.p2p.messages.QueryLatestMessage;
import com.arturskowronski.naivechain.services.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class P2PServer {

    @Autowired
    BlockchainService blockchainService;

    private final SimpMessagingTemplate template;

    public List<Peer> listPeers() {
        return Collections.emptyList();
    }

    public void broadcast(BlockchainMessage blockchainMessage) {

    }

    @Autowired
    P2PServer(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/blockchain")
    @SendTo("/topic/public")
    public BlockchainMessage onReceivedBlockchainMessage(@Payload BlockchainMessage message){
        this.template.convertAndSend("/topic/public", message);
        return message;
    }

    @MessageMapping("/blockchain.queryAll")
    @SendTo("/topic/public")
    public QueryAllMessage onReceivedQueryAllMessage(@Payload QueryAllMessage message){
        this.template.convertAndSend("/topic/public", message);

        return message;
    }

    @MessageMapping("/blockchain.queryLatest")
    @SendTo("/topic/public")
    public QueryLatestMessage onReceivedQueryLatestMessage(@Payload QueryLatestMessage message){
        this.template.convertAndSend("/topic/public", message);
        return message;
    }


}
