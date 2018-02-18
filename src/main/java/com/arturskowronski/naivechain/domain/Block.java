package com.arturskowronski.naivechain.domain;

import lombok.*;

import java.util.Date;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Block {

    protected int index;
    protected String hash;
    protected String previousHash;
    protected long timestamp;
    protected Object data;
    protected int nounce;

    public static boolean isValidNewBlock(Block previousBlock, Block newBlock) {
        if (previousBlock.getIndex() + 1 != newBlock.getIndex()) {
            return false;
        }

        if (!previousBlock.getHash().equals(newBlock.getHash())) {
            return false;
        }

        if (calculateHashForBlock(newBlock).equals(newBlock.getHash())) {
            return false;
        }

        return true;
    }

    public static Block nextBlock(Block previousBlock, Object blockData) {
        int nextIndex = previousBlock.index + 1;
        long nextTimestamp = new Date().getTime();
        HashPair nextHash = calculateHash(nextIndex, previousBlock.getHash(), nextTimestamp, blockData, 0);
        return new Block(nextIndex, previousBlock.getHash(), nextHash.getHash(), nextTimestamp, blockData, nextHash.getNounce());
    }

    private static HashPair calculateHash(int index, String previousHash, long timestamp, Object blockData, int nounce) {
        while (true) {
            nounce++;
            String value = sha256Hex(index + previousHash + timestamp + blockData.hashCode() + nounce);
            if (value.startsWith("0")) {
                System.out.println("Found Nounce: " + nounce + " for hash " + value);
                return new HashPair(nounce, value);
            }
        }
    }

    private static String calculateHashForBlock(Block block) {
        return calculateHash(block.getIndex(), block.getPreviousHash(), block.getTimestamp(), block.getData(), block.getNounce()).getHash();
    }

    public static final Block GENESIS_BLOCK = new Block(
            0,
            "816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7",
            "0",
            1465154705,
            "Pozdro Bielsko-Biala!",
            0
    );

}
