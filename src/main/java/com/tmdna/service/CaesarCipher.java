package com.tmdna.service;

import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {
    private static final String LATIN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
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

    public List<String> bruteForceDecrypt(List<String> cipherText) {
        final String ALPHABET = detectAlphabet(cipherText);
        int maxShift = ALPHABET.length();

        List<String> bestResult = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        for (int key = 1; key < maxShift; key++) {
            List<String> attempt = process(cipherText, -key);
            double score = calculateFrequencyScore(attempt, ALPHABET);

            if (score > bestScore) {
                bestScore = score;
                bestResult = attempt;
            }
        }

        return bestResult;
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

    private double calculateFrequencyScore(List<String> text, String alphabet) {
        String joined = String.join("", text).toLowerCase();

        String commonLatin = "etaoinshrdlcumwfgypbvkjxqz";
        String commonCyrillic = "оаинетсрвлкмдпуяїєгбчйхжшюцщьфзґ";

        String common = alphabet.equals(CYRILLIC_ALPHABET) ? commonCyrillic : commonLatin;

        double score = 0.0;
        int totalLetters = 0;

        for (char c : joined.toCharArray()) {
            if (Character.isLetter(c)) {
                totalLetters++;
                int idx = common.indexOf(c);
                if (idx != -1) {
                    score += (common.length() - idx);
                }
            }
        }

        return totalLetters == 0 ? 0 : score / totalLetters;
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
        return LATIN_ALPHABET;
    }

}
