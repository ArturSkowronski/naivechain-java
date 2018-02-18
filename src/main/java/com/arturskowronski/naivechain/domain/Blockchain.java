package com.arturskowronski.naivechain.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

import static com.arturskowronski.naivechain.domain.Block.GENESIS_BLOCK;

@Getter
@NoArgsConstructor
public class Blockchain extends LinkedList<Block> {

    public Blockchain(Block block) {
        this.add(block);
    }

    public boolean addBlock(Block block){
        return true;
    }

    public boolean isValidChain(){
        if (!this.getFirst().equals(GENESIS_BLOCK)){
            System.out.println("Genesis Block don't match!");
            return false;
        }

        for (int i = 1; i < this.size() - 1; i++) {
            if (!Block.isValidNewBlock(this.get(i - 1), this.get(i))){
                return false;
            }
        }
        return true;
    }

    public static Blockchain withGenesis(){
        Blockchain chain = new Blockchain();
        chain.add(GENESIS_BLOCK);
        return chain;
    };

}
