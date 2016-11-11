package com.mtsmda.xml;

import com.mtsmda.helper.ListHelper;
import org.testng.annotations.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.mtsmda.xml.ValidatorXML.*;
import static org.testng.Assert.*;

/**
 * Created by dminzat on 11/11/2016.
 */
public class ValidatorXMLTest {

    private String xsd;
    private String xmlValid;
    private String xmlError;
    private String xmlError2;
    private String dtd;
    private String xmlValidDtd;

    public ValidatorXMLTest() {
        xsd = getClass().getClassLoader().getResource("xml_xsd/students.xsd").getFile();
        xmlValid = getClass().getClassLoader().getResource("xml_xsd/students_valid.xml").getFile();
        xmlError = getClass().getClassLoader().getResource("xml_xsd/students_error.xml").getFile();
        xmlError2 = getClass().getClassLoader().getResource("xml_xsd/students_error2.xml").getFile();
        dtd = getClass().getClassLoader().getResource("xml_xsd/students.dtd").getFile();
        xmlValidDtd = getClass().getClassLoader().getResource("xml_xsd/students_valid_dtd.xml").getFile();
    }


    @Test
    public void validateTestError() {
        ListHelper.getListWithData(new ResultTest("", "", "xsd file path or xml file path is null or empty"),
                new ResultTest(null, "", "xsd file path or xml file path is null or empty"),
                new ResultTest("   ", "", "xsd file path or xml file path is null or empty"),
                new ResultTest("file", "file", "file should be 'xsd' extension"),
                new ResultTest("file.xsd1", "file", "file should be 'xsd' extension"),
                new ResultTest("file.xsd", "file", "file should be 'xml' extension"),
                new ResultTest("file.xsd", "file.xml1", "file should be 'xml' extension"),
                new ResultTest("file.xsd", "file.exe", "file should be 'xml' extension"),
                new ResultTest("Sstudents.xsd", "Sstudents.xml", "XSD file not exists!"),
                new ResultTest(xsd, "students.xml", "XML file not exists!")).forEach(resultTest -> {
            String returnErrorMessage = returnErrorMessage(resultTest.getXsdFile(), resultTest.getXsmFile());
            assertNotNull(returnErrorMessage);
            assertEquals(returnErrorMessage, resultTest.getResult());
        });

        try {
            System.out.println(validateWithDTDUsingDOM(xmlValid));
        }  catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(validateWithDTDUsingSAX(xmlValid));
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateTestValid() {
        ListHelper.getListWithData(
                new ResultTest("student.xsd", xmlValid, "XSD file not exists!"),
                new ResultTest(xsd, "students_valid.xml", "XML file not exists!"),
                new ResultTest(xsd, xmlValid, "true"),
                new ResultTest(xsd, xmlError, "cvc-complex-type.3.2.2: Attribute 'id' is not allowed to appear in element 'student'."),
                new ResultTest(xsd, xmlError2, "cvc-complex-type.3.2.2: Attribute 'id' is not allowed to appear in element 'student'.")).forEach(resultTest -> {
            String returnErrorMessage = returnErrorMessage(resultTest.getXsdFile(), resultTest.getXsmFile());
            assertNotNull(returnErrorMessage);
            assertEquals(returnErrorMessage, resultTest.getResult());
        });
    }

    private String returnErrorMessage(String xsdPath, String xmlPath) {
        String returnErrorMessage = null;
        try {
            boolean validate = validate(xsdPath, xmlPath);
            returnErrorMessage = validate + "";
        } catch (RuntimeException e) {
            returnErrorMessage = e.getMessage();
        }
        return returnErrorMessage;
    }

    private class ResultTest {
        private String xsdFile;
        private String xsmFile;
        private String result;

        public ResultTest(String xsdFile, String xsmFile, String result) {
            this.xsdFile = xsdFile;
            this.xsmFile = xsmFile;
            this.result = result;
        }

        public String getXsdFile() {
            return xsdFile;
        }

        public String getXsmFile() {
            return xsmFile;
        }

        public String getResult() {
            return result;
        }
    }

}