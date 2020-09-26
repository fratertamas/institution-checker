package hu.frt;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TaxNumberCheckerTest {
    private TaxNumberChecker taxNumberChecker = new TaxNumberChecker();

    @Test
    public void educationalInstitutionTaxNumberCodeOfAreaWithRightDataTest(){
        assertTrue(taxNumberChecker.codeOfArea("42"));
        assertTrue(taxNumberChecker.codeOfArea("22"));
        assertTrue(taxNumberChecker.codeOfArea("06"));
        assertTrue(taxNumberChecker.codeOfArea("16"));
    }

    @Test
    public void educationalInstitutionTaxNumberCodeOfAreaWithWrongDataTest(){
        assertFalse(taxNumberChecker.codeOfArea("77"));
        assertFalse(taxNumberChecker.codeOfArea("AAAA"));
        assertFalse(taxNumberChecker.codeOfArea("8"));
    }

}
