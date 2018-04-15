package com.jp.market;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Calendar;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testWestern(){
        Date monday = DateUtils.createDate(2018, 01, 01);
        Date tuesday = DateUtils.createDate(2018, 01, 02);
        Date friday = DateUtils.createDate(2018, 01, 05);
        Date saturday = DateUtils.createDate(2018, 01, 06);
        Date sunday = DateUtils.createDate(2018, 01, 07);


        assertEquals(DateUtils.getDayOfWeek(monday), Calendar.MONDAY);
        assertEquals(DateUtils.getDayOfWeek(tuesday), Calendar.TUESDAY);
        assertEquals(DateUtils.getDayOfWeek(friday), Calendar.FRIDAY);
        assertEquals(DateUtils.getDayOfWeek(saturday), Calendar.SATURDAY);
        assertEquals(DateUtils.getDayOfWeek(sunday), Calendar.SUNDAY);

        WorkDayCalculator calc = new WorkDayCalculator("USD");
        Date _monday = calc.convertToWorkDay(monday);
        Date _tuesday = calc.convertToWorkDay(tuesday);
        Date _friday = calc.convertToWorkDay(friday);
        Date _saturday = calc.convertToWorkDay(saturday);
        Date _sunday = calc.convertToWorkDay(sunday);

        assertEquals(DateUtils.getDayOfWeek(_monday), Calendar.MONDAY);
        assertEquals(DateUtils.getDayOfWeek(_tuesday), Calendar.TUESDAY);
        assertEquals(DateUtils.getDayOfWeek(_friday), Calendar.FRIDAY);
        assertEquals(DateUtils.getDayOfWeek(_saturday), Calendar.MONDAY);
        assertEquals(DateUtils.getDayOfWeek(_sunday), Calendar.MONDAY);
    }

    public void testNonWestern(){
        Date monday = DateUtils.createDate(2018, 01, 01);
        Date tuesday = DateUtils.createDate(2018, 01, 02);
        Date friday = DateUtils.createDate(2018, 01, 05);
        Date saturday = DateUtils.createDate(2018, 01, 06);
        Date sunday = DateUtils.createDate(2018, 01, 07);


        assertEquals(DateUtils.getDayOfWeek(monday), Calendar.MONDAY);
        assertEquals(DateUtils.getDayOfWeek(tuesday), Calendar.TUESDAY);
        assertEquals(DateUtils.getDayOfWeek(friday), Calendar.FRIDAY);
        assertEquals(DateUtils.getDayOfWeek(saturday), Calendar.SATURDAY);
        assertEquals(DateUtils.getDayOfWeek(sunday), Calendar.SUNDAY);

        WorkDayCalculator calc = new WorkDayCalculator("AED");
        Date _monday = calc.convertToWorkDay(monday);
        Date _tuesday = calc.convertToWorkDay(tuesday);
        Date _friday = calc.convertToWorkDay(friday);
        Date _saturday = calc.convertToWorkDay(saturday);
        Date _sunday = calc.convertToWorkDay(sunday);

        assertEquals(DateUtils.getDayOfWeek(_monday), Calendar.MONDAY);
        assertEquals(DateUtils.getDayOfWeek(_tuesday), Calendar.TUESDAY);
        assertEquals(DateUtils.getDayOfWeek(_friday), Calendar.SUNDAY);
        assertEquals(DateUtils.getDayOfWeek(_saturday), Calendar.SUNDAY);
        assertEquals(DateUtils.getDayOfWeek(_sunday), Calendar.SUNDAY);
    }
    public void testApp()
    {
        Market market = Market.createInstance();
        market.sendInstruction(InstructionFactory.createBuyInstruction("foo",0.50, "SGP", DateUtils.createDate(2018, 01, 01), 5, 100.25));
        market.sendInstruction(InstructionFactory.createSellInstruction("bar",0.22, "AED", DateUtils.createDate(2018, 01, 01), 2, 110.5));
        market.sendInstruction(InstructionFactory.createSellInstruction("foo",1, "USD", DateUtils.createDate(2018, 01, 01), 4, 120.5));
        market.sendInstruction(InstructionFactory.createBuyInstruction("foo",1, "USD", DateUtils.createDate(2018, 01, 02), 1, 130.5));
        market.sendInstruction(InstructionFactory.createSellInstruction("foo",0.52, "USD", DateUtils.createDate(2018, 01, 02), 4, 140.5));
        market.sendInstruction(InstructionFactory.createSellInstruction("foo",0.22, "USD", DateUtils.createDate(2018, 01, 02), 3, 150.5));
        market.generateReport();
        ///assertEquals( true );
    }
}
