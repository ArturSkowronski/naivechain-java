package com.arturskowronski.naivechain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HashPair {
    private int nounce;
    private String hash;
}
