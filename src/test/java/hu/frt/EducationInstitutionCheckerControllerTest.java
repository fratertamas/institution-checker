package hu.frt;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EducationInstitutionCheckerControllerTest {

    @Test
    public void checker() {
        EducationInstitutionCheckerController controller = new EducationInstitutionCheckerController();
        assertFalse(controller.codeOfArea("AAAA"));
        assertTrue(controller.codeOfArea("42"));
    }
}
