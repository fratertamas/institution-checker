package hu.frt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InstitutionDataCheckerTest {
    private EducationalInstitution educationalInstitution;
    private InstitutionDataChecker institutionDataChecker;
    private InstitutionData institutionData;

    @Before
    public void init(){
        educationalInstitution = new EducationalInstitution();
        institutionData = new InstitutionData();
        institutionDataChecker = new InstitutionDataChecker(educationalInstitution);
    }

    @Test
    public void institutionNameMinimumLengthTest(){
        institutionData.setName("Kör");
        assertEquals(2, institutionDataChecker.checkInstitutionName(institutionData.getName()));
        assertTrue( institutionData.getName().length() < 5);
    }

    @Test
    public void institutionNameMaximumLengthTest(){
        institutionData.setName("Záhonyi Kandó Kálmán Közlekedési Szakközépiskola, Gimnázium és Dr. Béres József Kollégium");
        assertEquals(3, institutionDataChecker.checkInstitutionName(institutionData.getName()));
        assertTrue(institutionData.getName().length() > 50);
    }
}
