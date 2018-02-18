package com.arturskowronski.naivechain.rest;

import com.arturskowronski.naivechain.domain.*;
import com.arturskowronski.naivechain.p2p.client.P2PClient;
import com.arturskowronski.naivechain.p2p.server.P2PServer;
import com.arturskowronski.naivechain.p2p.messages.BlockchainMessage;
import com.arturskowronski.naivechain.services.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiningController {

    @Autowired
    private BlockchainService blockchainService;

    @Autowired
    private P2PServer p2pServer;


    @Autowired
    private P2PClient p2pClient;

    @RequestMapping(method = RequestMethod.GET, path = "/blockchain")
    Blockchain getBlockchain() {
        return blockchainService.getChain();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/mineBlock")
    Block mineBlock(@RequestBody Object data) {
        Block block = blockchainService.mineBlock(data);
        p2pServer.broadcast(new BlockchainMessage(new Blockchain(block)));
        return block;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addPeer")
    ResponseEntity addPeer(@RequestBody Peer peer) {
        p2pClient.connectToPeers(peer);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/handleBlockchain")
    BlockchainResponse handleBlockchain(@RequestBody Blockchain newBlockchain) {
        return blockchainService.handleBlockchainResponse(newBlockchain);
    }

}