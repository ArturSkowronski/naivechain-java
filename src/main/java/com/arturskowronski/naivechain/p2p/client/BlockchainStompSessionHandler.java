package com.arturskowronski.naivechain.p2p.client;

import com.arturskowronski.naivechain.p2p.messages.BlockchainMessage;
import com.arturskowronski.naivechain.services.BlockchainService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class BlockchainStompSessionHandler extends StompSessionHandlerAdapter {

    private Logger logger = Logger.getLogger(BlockchainStompSessionHandler.class);

    @Autowired
    BlockchainService blockchainService;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/public", this);
        logger.info("Subscribed to /topic/public");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return BlockchainMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        BlockchainMessage msg = (BlockchainMessage) payload;
        logger.info("Received : " + msg);
    }
}