package com.tmdna.service;

import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {
    private static final String LATYN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String CYRILLIC_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзийіїйклмнопрстуфхцчшщьюя";
    private Integer key;

   public CaesarCipher(Integer key) {
        this.key = key;
    }

   public CaesarCipher() {}

    public List<String> encrypt(List<String> text) {
        return process(text, key);
    }

    public List<String> decrypt(List<String> text) {
        return process(text, -key);
    }

    private List<String> process(List<String> text, int shift) {
       final String ALPHABET = detectAlphabet(text);
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

    private String detectAlphabet(List<String> text) {
        for (String line : text) {
            for (char ch : line.toCharArray()) {
                Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);
                if (block == Character.UnicodeBlock.CYRILLIC ||
                        block == Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY ||
                        block == Character.UnicodeBlock.CYRILLIC_EXTENDED_A ||
                        block == Character.UnicodeBlock.CYRILLIC_EXTENDED_B) {
                    return CYRILLIC_ALPHABET;
                }
            }
        }
        return LATYN_ALPHABET;
    }

}
