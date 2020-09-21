package hu.frt;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ExampleProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        String errors = "";
        InstitutionDataChecker institutionDataChecker = new InstitutionDataChecker(educationalInstitution);
        HeadOfInstitutionDataChecker headOfInstitutionDataChecker = new HeadOfInstitutionDataChecker(educationalInstitution);
        errors += institutionDataChecker.getErrors();
        errors += headOfInstitutionDataChecker.getErrors();

        return errors;
    }
}
