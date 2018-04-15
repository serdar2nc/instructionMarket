package com.jp.market;

public enum BuySellFlag {
    B("Buy"),
    S("Sell")
            ;

    private final String text;

    BuySellFlag(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }
}
