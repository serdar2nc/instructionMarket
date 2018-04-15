package com.jp.market;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Market {
    private List<Instruction> memory = new ArrayList<>();

    public static Market createInstance(){
        return new Market();
    }

    public void sendInstruction(Instruction instruction){
        WorkDayCalculator calc = new WorkDayCalculator(instruction.getCurrency());
        Date workDay = calc.convertToWorkDay(instruction.getSettlementDate());
        instruction.setSettlementDate(workDay);
        memory.add(instruction);
    }

    public void generateReport() {
        generateReport(memory.stream(), "Instructions Daily");
    }

    private static double calcAmount(Stream<Instruction> stream){
        return stream.mapToDouble(i -> i.getUSDTradeAmount()).reduce(0.0, (x, y)-> x + y);
    }

    private static int sortByOutgoingDesc(Instruction i1, Instruction i2) {
        double amount1 = i1.isOutgoing() ? i1.getUSDTradeAmount() : 0;
        double amount2 = i2.isOutgoing() ? i2.getUSDTradeAmount() : 0;
        return Double.compare(amount2, amount1);
    }

    private void generateReport(Stream<Instruction> stream, String name) {
        System.out.println("\n*** "+name+" REPORT ***");

        stream
        .collect(Collectors.groupingBy(s -> s.getSettlementDate()))
        .entrySet().stream().forEach(Market::displayPerDay);
    }

    private static void displayPerDay(Map.Entry<Date, List<Instruction>> grp) {
        List<Instruction> instructionsPerDay = grp.getValue();
        double totalIncoming = calcAmount(instructionsPerDay.stream().filter(i-> i.isIncoming()));
        double totalOutgoing = calcAmount(instructionsPerDay.stream().filter(i-> i.isOutgoing()));
        System.out.println(String.format("%s: %d settlements found: Total Outgoing: $%.2f, Total Incoming: $%.2f", DateUtils.format(grp.getKey()), instructionsPerDay.size(), totalOutgoing, totalIncoming));

        instructionsPerDay
        .stream()
        .sorted(Market::sortByOutgoingDesc)
        .forEach(item -> {
            System.out.println("\t" + item);
        });
    }
}
