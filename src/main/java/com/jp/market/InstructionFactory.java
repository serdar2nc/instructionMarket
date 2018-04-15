package com.jp.market;

import java.util.Date;

public class InstructionFactory {
    public static Instruction createSellInstruction(String entity, double agreedFx, String currency, Date settlementDate, int units, double pricePerUnit) {
        return new Instruction(entity, "S", agreedFx, currency, settlementDate, units, pricePerUnit);
    }

    public static Instruction createBuyInstruction(String entity, double agreedFx, String currency, Date settlementDate, int units, double pricePerUnit) {
        return new Instruction(entity, "B", agreedFx, currency, settlementDate, units, pricePerUnit);
    }
}