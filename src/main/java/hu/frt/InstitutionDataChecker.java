package hu.frt;

import java.util.ArrayList;
import java.util.List;

public class InstitutionDataChecker {
    private EducationalInstitution educationalInstitution;

    public InstitutionDataChecker(EducationalInstitution educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public List<Integer> getErrors() {
        return checkInstitutionData(educationalInstitution);
    }

    private List<Integer>  checkInstitutionData(EducationalInstitution educationalInstitution) {
        List<Integer> errors = new ArrayList<>();
        TaxNumberChecker taxNumberChecker = new TaxNumberChecker();
        errors.add(checkInstitutionType(educationalInstitution.getInstitutionData().getType()));
        errors.add(checkInstitutionName(educationalInstitution.getInstitutionData().getName()));
        errors.add(checkInstitutionOmIdentificationNumber(educationalInstitution.getInstitutionData().getOmIdentificationNumber()));
        errors.add(checkInstitutionStatus(educationalInstitution.getInstitutionData().getStatus()));
        errors.add(checkInstitutionNumberOfTaskLocation(educationalInstitution.getInstitutionData().getNumberOfTaskLocation()));
        errors.add(taxNumberChecker.checkInstitutionTaxNumber(educationalInstitution.getInstitutionData().getTaxNumber()));

        return errors;
    }

    private int checkInstitutionNumberOfTaskLocation(int numberOfTaskLocation) {
        if (numberOfTaskLocation < 0)
            return 7;

        return 0;
    }

    private int checkInstitutionStatus(String status) {
        List<String> statuses = new ArrayList<>();
        statuses.add("AKTÍV");
        statuses.add("MEGSZŰNT");
        statuses.add("SZÜNETEL");

        if (!statuses.contains(status.toUpperCase()))
            return 6;
        return 0;
    }

    private int checkInstitutionOmIdentificationNumber(String omIdentificationNumber) {
        if (omIdentificationNumber.length() != 6)
            return 4;

        String pattern = "\\d{6}";
        if (!omIdentificationNumber.matches(pattern))
            return 5;

        return 0;
    }

    private int checkInstitutionType(String type) {
        List<String> types = new ArrayList<>();
        types.add("ÓVODA");
        types.add("ÁLTALÁNOS ISKOLA");
        types.add("KÖZÉPISKOLA");

        if (!types.contains(type.toUpperCase()))
            return 1;
        return 0;
    }

    protected int checkInstitutionName(String name) {
        if (name.length() < 5)
            return 2;

        if (name.length() > 50)
            return 3;

        return 0;
    }
}
