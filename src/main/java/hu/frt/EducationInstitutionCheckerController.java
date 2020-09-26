package hu.frt;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ExampleProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class EducationInstitutionCheckerController {
    @ApiOperation(value = "Oktatási intézmény adatainak ellenőrzése")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "contents",
                    dataTypeClass = EducationalInstitution.class,
                    examples = @io.swagger.annotations.Example(
                            value = {
                                    @ExampleProperty(value = "{\n" +
                                            "  \"headOfInstitutionData\": {\n" +
                                            "    \"firstName\": \"Kiss\",\n" +
                                            "    \"phoneNumber\": \"06203920511\",\n" +
                                            "    \"surname\": \"Balázs\"\n" +
                                            "  },\n" +
                                            "  \"institutionData\": {\n" +
                                            "    \"name\": \"Piroska Ovi\",\n" +
                                            "    \"numberOfTaskLocation\": 10,\n" +
                                            "    \"omIdentificationNumber\": \"101101\",\n" +
                                            "    \"status\": \"aktív\",\n" +
                                            "    \"taxNumber\": \"18299425-1-43\",\n" +
                                            "    \"type\": \"óvoda\"\n" +
                                            "  }\n" +
                                            "}", mediaType = "application/json")
                            })
            )
    })
    @PostMapping("institution-check")
    public String checker(@RequestBody EducationalInstitution educationalInstitution){
        HashMap<Integer, String> errorMap = createCodeOfErrors();
        String errors = "";
        InstitutionDataChecker institutionDataChecker = new InstitutionDataChecker(educationalInstitution);
        HeadOfInstitutionDataChecker headOfInstitutionDataChecker = new HeadOfInstitutionDataChecker(educationalInstitution);

        headOfInstitutionDataChecker.getErrors();

        for (Integer code: institutionDataChecker.getErrors()) {
            if (code != 0)
                errors += errorMap.get(code)+"\r\n";
        }
        for (Integer code: headOfInstitutionDataChecker.getErrors()) {
            if (code != 0)
                errors += errorMap.get(code)+"\r\n";
        }

        return errors;
    }

    private HashMap<Integer, String> createCodeOfErrors() {
        HashMap<Integer,String> errorCodes = new HashMap<>();
        errorCodes.put(1,"A megadott intézményi típus hibás!");
        errorCodes.put(2,"A megadott név túl rövid (minimum 5 karakter)!");
        errorCodes.put(3,"A megadott név túl hosszú (maximum 50 karakter)!");
        errorCodes.put(4,"Az OM azonosító 6 számjegyből kell álljon!");
        errorCodes.put(5,"Nem számjegyeket adott meg!");
        errorCodes.put(6,"A megadott intézményi státusz hibás!");
        errorCodes.put(7,"A feladatellátási helyek száma nem lehet 0-nál kisebb!");
        errorCodes.put(8,"Az adószám helyes formátuma: 12345678-1-23 \r\n" +
                         "Ahol az első 8 számjegy az adótörzsszámn.\r\n" +
                         "A 9. számjegy 1-5. számjegy közül valamelyik.\r\n" +
                         "A 10.-11. számjegy, azaz az utolsó két karakter az adózó illetékes területi adóhatóságának kódja.");
        errorCodes.put(9,"Ellenőrizze az adószám első 8 számjegyének helyességét!");
        errorCodes.put(10,"Ellenőrizze az adószám utolsó 2 számjegyének a helyeségét!");
        errorCodes.put(11,"A vezetéknév nem lehet üres!");
        errorCodes.put(12,"A vezetéknév nem lehet 50 karakternél több!");
        errorCodes.put(13,"A keresztnév nem lehet üres!");
        errorCodes.put(14,"A keresztnév nem lehet 50 karakternél több!");
        errorCodes.put(15,"Kérem ellenőrizze a megadott telefonszámot!\r\n" +
                          "Helyes formátum pl: 06201112222");

        return errorCodes;
    }
}
