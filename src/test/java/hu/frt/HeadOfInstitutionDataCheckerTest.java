package hu.frt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeadOfInstitutionDataCheckerTest {
    private HeadOfInstitutionDataChecker headOfInstitutionDataChecker;

    @Before
    public void init(){
        EducationalInstitution educationalInstitution = new EducationalInstitution();
        headOfInstitutionDataChecker = new HeadOfInstitutionDataChecker(educationalInstitution);
    }

    @Test
    public void surnameAndFirstNameEmptyTest(){
        assertEquals(11, headOfInstitutionDataChecker.checkLeaderSurnameAndFirstName("", "vezetéknév"));
        assertEquals(13, headOfInstitutionDataChecker.checkLeaderSurnameAndFirstName("","keresztnév"));
    }

    @Test
    public void surnameAndFirstNameStringLengthBiggerThen50Test(){
        String name = "dr. Kovács-Nagy A. Bence Máténé dr. Tóth-Horváth B.";
        assertEquals(12, headOfInstitutionDataChecker.checkLeaderSurnameAndFirstName(name, "vezetéknév"));
    }

    @Test
    public void correctSurnameAndFristNameTest(){
        String surname = "dr. Albertovics-Kovács";
        String firstName = "Tivadar Sándor";
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderSurnameAndFirstName(surname,"vezetéknév"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderSurnameAndFirstName(firstName,"keresztnév"));
    }

    @Test
    public void phoneNumberMatchesPatternWithIncorrectDataTest(){
        assertEquals(15, headOfInstitutionDataChecker.checkLeaderPhoneNumber("+36305556666"));
        assertEquals(15, headOfInstitutionDataChecker.checkLeaderPhoneNumber("3630556666"));
        assertEquals(15, headOfInstitutionDataChecker.checkLeaderPhoneNumber("0630555666"));
        assertEquals(15, headOfInstitutionDataChecker.checkLeaderPhoneNumber("06-30-555-6666"));
        assertEquals(15, headOfInstitutionDataChecker.checkLeaderPhoneNumber("06/20-555-6666"));
        assertEquals(15, headOfInstitutionDataChecker.checkLeaderPhoneNumber("06-1-555-666"));
    }

    @Test
    public void correctPhoneNumberTest(){
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("06305556666"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("06205556666"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("06705556666"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("0614820224"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("0642435482"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("36305556666"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("36205556666"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("36705556666"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("3614820224"));
        assertEquals(0, headOfInstitutionDataChecker.checkLeaderPhoneNumber("3642435482"));
    }
}
