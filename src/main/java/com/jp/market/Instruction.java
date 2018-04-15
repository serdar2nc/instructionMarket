package com.jp.market;

import java.util.Date;

/**
 * An instruction to buy or sell
 */
public class Instruction {
    /**
     * A financial entity whose shares are to be bought or sold
     */
    private String entity;

    /**
     * B – Buy – outgoing
     * S – Sell – incoming
     */
    private String buySellFlag;

    /**
     *  the foreign exchange rate with respect to USD that was agreed
     */
    private double agreedFx;

    /**
     *
     */
    private String currency;

    /**
     * Date on which the instruction was sent to JP Morgan by various clients
     */
    private Date instructionDate;

    /**
     * The date on which the client wished for the instruction to be settled with respect
     * to InstructionFactory Date
     */
    private Date settlementDate;

    /**
     * Number of shares to be bought or sold
     */
    private int units;

    private double pricePerUnit;


    /* GETTERS / SETTERS*/

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getBuySellFlag() {
        return buySellFlag;
    }

    public void setBuySellFlag(String buySellFlag) {
        this.buySellFlag = buySellFlag;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(double agreedFx) {
        this.agreedFx = agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(Date instructionDate) {
        this.instructionDate = instructionDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getUSDTradeAmount(){
        return this.pricePerUnit * this.units * ("USD".equals(currency) ? 1 : this.getAgreedFx());
    }
    /* GETTERS / SETTERS*/

    public Instruction(String entity, String buySellFlag, double agreedFx, String currency, Date settlementDate, int units, double pricePerUnit) {
        this.entity = entity;
        this.buySellFlag = buySellFlag;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.instructionDate = new Date();
    }

    @Override
    public String toString() {
        String flag = "B".equals(buySellFlag) ? "Outgoing" : "Incoming";
        return String.format("%s #%d of %s for $%.2f", flag, units, entity, getUSDTradeAmount());
    }

    public boolean isIncoming() {
        return buySellFlag.equals("S");
    }
    public boolean isOutgoing() {
        return buySellFlag.equals("B");
    }
}
