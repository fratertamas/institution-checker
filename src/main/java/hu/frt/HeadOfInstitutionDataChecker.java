package hu.frt;

import java.util.ArrayList;
import java.util.List;

public class HeadOfInstitutionDataChecker {
    private EducationalInstitution educationalInstitution;

    public HeadOfInstitutionDataChecker(EducationalInstitution educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public List<Integer> getErrors() {
        return checkHeadOfInstitutionData(educationalInstitution);
    }

    private List<Integer> checkHeadOfInstitutionData(EducationalInstitution educationalInstitution) {
        List<Integer> errors = new ArrayList<>();
        String partOfName = "vezetéknév";
        errors.add(checkLeaderSurnameAndFirstName(educationalInstitution.getHeadOfInstitutionData().getSurname(), partOfName));
        partOfName = "keresztnév";
        errors.add(checkLeaderSurnameAndFirstName(educationalInstitution.getHeadOfInstitutionData().getFirstName(), partOfName));
        errors.add(checkLeaderPhoneNumber(educationalInstitution.getHeadOfInstitutionData().getPhoneNumber()));

        return errors;
    }

    private int checkLeaderPhoneNumber(String phoneNumber) {
        String pattern = "^([03]6)((20|30|31|70|1)(\\d{7})|(2[2-9]|3[2-7]|4[024-9]|5[234679]|6[23689]|7[2-9]|8[02-9]|9[92-69])(\\d{6}))$";

        if (!phoneNumber.matches(pattern))
            return 15;

        return 0;
    }

    protected int checkLeaderSurnameAndFirstName(String name, String partOfName) {
        if (name.isEmpty()) {
            if (partOfName == "vezetéknév")
                return 11;
            else
                return 13;
        }

        if (name.length() > 50) {
            if (partOfName == "vezetéknév")
                return 12;
            else
                return 14;
        }

        return 0;
    }
}
