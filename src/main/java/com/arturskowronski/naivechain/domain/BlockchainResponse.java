package com.arturskowronski.naivechain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlockchainResponse {
    public enum ResponseResult {
        RESPONSE_BLOCKCHAIN,
        QUERY_ALL,
        NO_ACTION,
    }
    ResponseResult result;
    Blockchain data;

    public BlockchainResponse(ResponseResult result) {
        this.result = result;
    }
}
