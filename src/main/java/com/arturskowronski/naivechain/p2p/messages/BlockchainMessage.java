package com.arturskowronski.naivechain.p2p.messages;

import com.arturskowronski.naivechain.domain.Blockchain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlockchainMessage {
    private Blockchain data;

    public BlockchainMessage(Blockchain data) {
        this.data = data;
    }
}
