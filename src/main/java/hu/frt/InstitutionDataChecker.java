package hu.frt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.frt.model.CodeOfTaxNumberArea;
import hu.frt.model.TaxNumberArea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstitutionDataChecker {


    private EducationalInstitution educationalInstitution;

    public InstitutionDataChecker(EducationalInstitution educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public String getErrors() {
        return checkInstitutionData(educationalInstitution);
    }

    private String  checkInstitutionData(EducationalInstitution educationalInstitution) {
        String errorMessage = "";
        errorMessage += checkInstitutionType(educationalInstitution.getInstitutionData().getType());
        errorMessage += checkInstitutionName(educationalInstitution.getInstitutionData().getName());
        errorMessage += checkInstitutionOmIdentificationNumber(educationalInstitution.getInstitutionData().getOmIdentificationNumber());
        errorMessage += checkInstitutionStatus(educationalInstitution.getInstitutionData().getStatus());
        errorMessage += checkInstitutionNumberOfTaskLocation(educationalInstitution.getInstitutionData().getNumberOfTaskLocation());
        errorMessage += checkInstitutionTaxNumber(educationalInstitution.getInstitutionData().getTaxNumber());

        return errorMessage;
    }

    private String checkInstitutionTaxNumber(String taxNumber) {
        String pattern = "\\d{8}-[1-5]-\\d{2}";

        if (!taxNumber.matches(pattern))
            return "Az adószám helyes formátuma: 12345678-1-23 \r\n" +
                    "Ahol az első 8 számjegy az adótörzsszámn.\r\n" +
                    "A 9. számjegy 1-5. számjegy közül valamelyik.\r\n" +
                    "A 10.-11. számjegy, azaz az utolsó két karakter az adózó illetékes területi adóhatóságának kódja.\r\n";

        if(!isOkTaxBase(taxNumber.substring(0,8)))
            return "Ellenőrizze az adószám első 8 számjegyének helyességét! \r\n";

        if (!codeOfArea(taxNumber.substring(11,13)))
            return "Ellenőrizze az adószám utolsó 2 számjegyének a helyeségét!\r\n";

        return "";
    }

    protected boolean codeOfArea(String codeOfArea) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CodeOfTaxNumberArea readValue = mapper.readValue(EducationInstitutionCheckerController.class.getResourceAsStream("/sourceFile/adokodokszotar_terulet.json"), CodeOfTaxNumberArea.class);
            boolean isFound = false;
            for (TaxNumberArea code: readValue.getRows()
            ) {
                if (code.getKod().equals(codeOfArea)){
                    isFound = true;
                }
            }
            return isFound;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isOkTaxBase(String taxBase) {
        int number1 = Integer.parseInt(taxBase.substring(0,1));
        int number2 = Integer.parseInt(taxBase.substring(1,2));
        int number3 = Integer.parseInt(taxBase.substring(2,3));
        int number4 = Integer.parseInt(taxBase.substring(3,4));
        int number5 = Integer.parseInt(taxBase.substring(4,5));
        int number6 = Integer.parseInt(taxBase.substring(5,6));
        int number7 = Integer.parseInt(taxBase.substring(6,7));
        int number8 = Integer.parseInt(taxBase.substring(7,8));
        int number = 10 - ((9*number1 + 7*number2 + 3*number3 + number4 + 9*number5 + 7*number6 + 3*number7) % 10);

        if (number8 == number)
            return true;

        return false;
    }

    private String checkInstitutionNumberOfTaskLocation(int numberOfTaskLocation) {
        if (numberOfTaskLocation < 0)
            return "A feladatellátási helyek száma nem lehet 0-nál kisebb! \r\n";

        return "";
    }

    private String checkInstitutionStatus(String status) {
        List<String> statuses = new ArrayList<>();
        statuses.add("AKTÍV");
        statuses.add("MEGSZŰNT");
        statuses.add("SZÜNETEL");

        if (!statuses.contains(status.toUpperCase()))
            return "A megadott intézményi státusz hibás! \r\n";
        return "";
    }

    private String checkInstitutionOmIdentificationNumber(String omIdentificationNumber) {
        if (omIdentificationNumber.length() != 6)
            return "Az OM azonosító 6 számjegyből kell álljon! \r\n";

        String pattern = "\\d{6}";
        if (!omIdentificationNumber.matches(pattern))
            return "Nem számjegyeket adott meg! \r\n";

        return "";
    }

    private String checkInstitutionType(String type) {
        List<String> types = new ArrayList<String>();
        types.add("ÓVODA");
        types.add("ÁLTALÁNOS ISKOLA");
        types.add("KÖZÉPISKOLA");

        if (!types.contains(type.toUpperCase()))
            return "A megadott intézményi típus hibás! \r\n";
        return "";
    }

    private String checkInstitutionName(String name) {
        if (name.length() < 5)
            return "A megadott név túl rövid (minimum 5 karakter)!\r\n";

        if (name.length() > 50)
            return "A megadott név túl hosszú (maximum 50 karakter)! \r\n";

        return "";
    }
}
