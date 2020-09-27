package hu.frt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaxNumberCheckerTest {
    private TaxNumberChecker taxNumberChecker;

    @Before
    public void init(){
        taxNumberChecker = new TaxNumberChecker();
    }

    @Test
    public void taxNumberCodeOfAreaWithDataTest(){
        assertTrue(taxNumberChecker.codeOfArea("42"));
        assertTrue(taxNumberChecker.codeOfArea("22"));
        assertTrue(taxNumberChecker.codeOfArea("06"));
        assertTrue(taxNumberChecker.codeOfArea("16"));
    }

    @Test
    public void taxNumberCodeOfAreaWrongDataTest(){
        assertFalse(taxNumberChecker.codeOfArea("77"));
        assertFalse(taxNumberChecker.codeOfArea("AAAA"));
        assertFalse(taxNumberChecker.codeOfArea("8"));
    }

    @Test
    public void taxNumberBaseRightDataTest(){
        assertTrue(taxNumberChecker.isOkTaxBase("18234237"));
        assertTrue(taxNumberChecker.isOkTaxBase("15835231"));
    }

    @Test
    public void taxNumberBaseWrongDataTest(){
        assertFalse(taxNumberChecker.isOkTaxBase("18234236"));
        assertFalse(taxNumberChecker.isOkTaxBase("18234238"));
    }

    @Test
    public void taxNumberIncorrectPatternTest(){
        assertEquals(8, taxNumberChecker.checkInstitutionTaxNumber("15835231-8-15"));
        assertEquals(8, taxNumberChecker.checkInstitutionTaxNumber("15835h31-2-15"));
        assertEquals(8, taxNumberChecker.checkInstitutionTaxNumber("1583531-3-15"));
        assertEquals(8, taxNumberChecker.checkInstitutionTaxNumber("158352331-215"));
        assertEquals(8, taxNumberChecker.checkInstitutionTaxNumber("15835231215"));

    }

    @Test
    public void correctTaxNumberTest(){
        assertEquals(0, taxNumberChecker.checkInstitutionTaxNumber("15835231-2-15"));
        assertEquals(0, taxNumberChecker.checkInstitutionTaxNumber("18234237-1-43"));
    }
}
