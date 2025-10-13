package com.tmdna.service;

import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    // TODO: Implement ability to handle UA text
//    private static final String UA_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзийіїйклмнопрстуфхцчшщьюя";
    private Integer key;

   public CaesarCipher(Integer key) {
        this.key = key;
    }

    CaesarCipher() {

    }

    public List<List<String>> bruteForceDecrypt(List<String> text) {
        List<List<String>> allResults = new ArrayList<>();

        for (int possibleKey = 1; possibleKey < ALPHABET.length(); possibleKey++) {
            List<String> decryptedText = process(text, -possibleKey);
            allResults.add(decryptedText);
        }

        return allResults;
    }

    public List<String> encrypt(List<String> text) {
        return process(text, key);
    }

    public List<String> decrypt(List<String> text) {
        return process(text, -key);
    }

    private List<String> process(List<String> text, int shift) {
        List<String> result = new ArrayList<>(text.size());

        for (String line : text) {
            StringBuilder sb = new StringBuilder();

            for (char c : line.toCharArray()) {
                int index = ALPHABET.indexOf(c);
                if (index != -1) {
                    int newIndex = (index + shift) % ALPHABET.length();
                    if (newIndex < 0) {
                        newIndex += ALPHABET.length();
                    }
                    sb.append(ALPHABET.charAt(newIndex));
                } else {
                    sb.append(c);
                }
            }

            result.add(sb.toString());
        }

        return result;
    }

}
