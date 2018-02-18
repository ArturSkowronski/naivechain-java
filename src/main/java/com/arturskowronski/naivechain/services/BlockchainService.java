package com.arturskowronski.naivechain.services;

import com.arturskowronski.naivechain.domain.Block;
import com.arturskowronski.naivechain.domain.Blockchain;
import com.arturskowronski.naivechain.domain.BlockchainResponse;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;

import static com.arturskowronski.naivechain.domain.Block.GENESIS_BLOCK;
import static com.arturskowronski.naivechain.domain.Block.nextBlock;
import static com.arturskowronski.naivechain.domain.BlockchainResponse.ResponseResult.*;

@Component
@Scope("singleton")
@Getter
public class BlockchainService {

    private Blockchain chain = new Blockchain(GENESIS_BLOCK);

    public BlockchainResponse handleBlockchainResponse(Blockchain newChain) {
        newChain.sort(Comparator.comparingInt(Block::getIndex));
        Block latestBlockReceived = newChain.getLast();

        if (latestBlockReceived.getIndex() > chain.getLast().getIndex()) {
            if (chain.getLast().getHash().equals(latestBlockReceived.getPreviousHash()))  {
                System.out.println("Verification failed: case 1");
                chain.add(latestBlockReceived);
                return new BlockchainResponse(RESPONSE_BLOCKCHAIN, new Blockchain(chain.getLast()));
            }

            if (newChain.size() == 1) {
                System.out.println("Verification Query Laters");
                return new BlockchainResponse(QUERY_ALL);
            }

            return replaceChain(newChain);
        }

        return new BlockchainResponse(NO_ACTION);
    }

    public Block mineBlock(Object data){
        Block newBlock = nextBlock(chain.getLast(), data);
        chain.add(newBlock);
        return chain.getLast();
    }

    private BlockchainResponse replaceChain(Blockchain newBlockchain) {
        if (newBlockchain.isValidChain() && newBlockchain.size() > chain.size()) {
            System.out.println("Verification success");
            chain = newBlockchain;
            return new BlockchainResponse(RESPONSE_BLOCKCHAIN, new Blockchain(chain.getLast()));
        } else {
            System.out.println("Verification invalid Blockchain");
            return new BlockchainResponse(NO_ACTION);
        }
    }
}
