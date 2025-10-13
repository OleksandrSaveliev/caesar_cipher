package com.tmdna.model;

public enum FileSuffix {
    ENCRYPTED,
    DECRYPTED;

    @Override
    public String toString() {
        return "[" + name() + "]";
    }
}