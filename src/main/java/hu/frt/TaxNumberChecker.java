package hu.frt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.frt.model.CodeOfTaxNumberArea;
import hu.frt.model.TaxNumberArea;

import java.io.IOException;

public class TaxNumberChecker{
    protected int checkInstitutionTaxNumber(String taxNumber) {
        String pattern = "\\d{8}-[1-5]-\\d{2}";

        if (!taxNumber.matches(pattern))
            return 8;

        if(!isOkTaxBase(taxNumber.substring(0,8)))
            return 9;

        if (!codeOfArea(taxNumber.substring(11,13)))
            return 10;

        return 0;
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

    protected boolean isOkTaxBase(String taxBase) {
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
}
