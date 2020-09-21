package hu.frt;

public class HeadOfInstitutionDataChecker {

    private EducationalInstitution educationalInstitution;

    public HeadOfInstitutionDataChecker(EducationalInstitution educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public String getErrors() {
        return checkHeadOfInstitutionData(educationalInstitution);
    }

    private String checkHeadOfInstitutionData(EducationalInstitution educationalInstitution) {
        String errorMessage = "";
        String partOfName = "vezetéknév";
        errorMessage += checkLeaderSurnameAndFirstName(educationalInstitution.getHeadOfInstitutionData().getSurname(), partOfName);

        partOfName = "keresztnév";
        errorMessage += checkLeaderSurnameAndFirstName(educationalInstitution.getHeadOfInstitutionData().getFirstName(), partOfName);

        errorMessage += checkLeaderPhoneNumber(educationalInstitution.getHeadOfInstitutionData().getPhoneNumber());

        return errorMessage;
    }

    private String checkLeaderPhoneNumber(String phoneNumber) {
        String pattern = "^([03]6)((20|30|31|70|1)(\\d{7})|(2[2-9]|3[2-7]|4[024-9]|5[234679]|6[23689]|7[2-9]|8[02-9]|9[92-69])(\\d{6}))$";

        if (!phoneNumber.matches(pattern))
            return "Kérem ellenőrizze a megadott telefonszámot!\r\n";

        return "";
    }

    private String checkLeaderSurnameAndFirstName(String surname, String partOfName) {
        if (surname.isEmpty())
            return "A "+partOfName+" nem lehet üres!\r\n";

        if (surname.length() > 50)
            return "A "+partOfName+" nem lehet 50 karakternél több!\r\n";

        return "";
    }
}
