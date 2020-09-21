package hu.frt;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class InstitutionDataCheckerTest {
    private EducationalInstitution educationalInstitution;
    private InstitutionDataChecker institutionData = new InstitutionDataChecker(educationalInstitution);

    @Test
    public void educationalInstitutionTaxNumberCodeOfAreaWithRightDataTest(){
        assertTrue(institutionData.codeOfArea("42"));
        assertTrue(institutionData.codeOfArea("06"));
        assertTrue(institutionData.codeOfArea("16"));
    }

    @Test
    public void educationalInstitutionTaxNumberCodeOfAreaWithWrongDataTest(){
        assertFalse(institutionData.codeOfArea("77"));
        assertFalse(institutionData.codeOfArea("AAAA"));
        assertFalse(institutionData.codeOfArea("8"));
    }

}
