package com.arturskowronski.naivechain.p2p.messages;

import com.arturskowronski.naivechain.domain.Blockchain;
import lombok.Getter;

@Getter
public class BlockchainMessage {
    private Blockchain data;

    public BlockchainMessage(Blockchain data) {
        this.data = data;
    }
}
