package com.mtsmda.xml;

import com.mtsmda.helper.ObjectHelper;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by dminzat on 11/11/2016.
 */
public class ValidatorXML {

    public static boolean validate(String xsdOrDtdPath, String xmlPath) {
        if (StringUtils.isBlank(xsdOrDtdPath) || StringUtils.isBlank(xmlPath)) {
            throw new RuntimeException("xsd file path or xml file path is null or empty");
        }
        if (!xsdOrDtdPath.endsWith(".xsd")) {
            throw new RuntimeException("file should be 'xsd' extension");
        }
        if (!xmlPath.endsWith(".xml")) {
            throw new RuntimeException("file should be 'xml' extension");
        }
        return validate(new File(xsdOrDtdPath), new File(xmlPath));
    }

    public static boolean validate(File xsdOrDtdPath, File xmlPath) {
        if (ObjectHelper.objectIsNull(xsdOrDtdPath) || ObjectHelper.objectIsNull(xmlPath)) {
            throw new RuntimeException("xsd file or xml path is null!");
        }
        if (!xsdOrDtdPath.exists()) {
            throw new RuntimeException("XSD file not exists!");
        }
        if (!xmlPath.exists()) {
            throw new RuntimeException("XML file not exists!");
        }
        if (!xsdOrDtdPath.isFile() || !xmlPath.isFile()) {
            throw new RuntimeException("xsd(dtd) or xml file is not file!");
        }
        try {
            SchemaFactory schemaFactory = null;
            schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdOrDtdPath);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlPath));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }


    // validate using DOM (DTD as defined in the XML)
    public static boolean validateWithDTDUsingDOM(String xml)
            throws ParserConfigurationException, IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.setErrorHandler(
                    new ErrorHandler() {
                        public void warning(SAXParseException e) throws SAXException {
                            System.out.println("WARNING : " + e.getMessage()); // do nothing
                        }

                        public void error(SAXParseException e) throws SAXException {
                            System.out.println("ERROR : " + e.getMessage());
                            throw e;
                        }

                        public void fatalError(SAXParseException e) throws SAXException {
                            System.out.println("FATAL : " + e.getMessage());
                            throw e;
                        }
                    }
            );
            builder.parse(new InputSource(xml));
            return true;
        } catch (ParserConfigurationException pce) {
            throw pce;
        } catch (IOException io) {
            throw io;
        } catch (SAXException se) {
            return false;
        }
    }

    // validate using SAX (DTD as defined in the XML)
    public static boolean validateWithDTDUsingSAX(String xml)
            throws ParserConfigurationException, IOException {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            SAXParser parser = factory.newSAXParser();

            XMLReader reader = parser.getXMLReader();
            reader.setErrorHandler(new MyErrorHandler()/*
                    new ErrorHandler() {
                        public void warning(SAXParseException e) throws SAXException {
                            System.out.println("WARNING : " + e.getMessage()); // do nothing
                        }

                        public void error(SAXParseException e) throws SAXException {
                            System.out.println("ERROR : " + e.getMessage());
                            throw e;
                        }

                        public void fatalError(SAXParseException e) throws SAXException {
                            System.out.println("FATAL : " + e.getMessage());
                            throw e;
                        }
                    }
            */);
            reader.parse(new InputSource(xml));
            return true;
        } catch (ParserConfigurationException pce) {
            throw pce;
        } catch (IOException io) {
            throw io;
        } catch (SAXException se) {
            return false;
        }
    }

    private static class MyErrorHandler implements ErrorHandler {
        public void warning(SAXParseException e) throws SAXException {
            System.out.println("Warning: ");
            printInfo(e);
        }

        public void error(SAXParseException e) throws SAXException {
            System.out.println("Error: ");
            printInfo(e);
        }

        public void fatalError(SAXParseException e)
                throws SAXException {
            System.out.println("Fattal error: ");
            printInfo(e);
        }

        private void printInfo(SAXParseException e) {
            System.out.println("   Public ID: " + e.getPublicId());
            System.out.println("   System ID: " + e.getSystemId());
            System.out.println("   Line number: " + e.getLineNumber());
            System.out.println("   Column number: " + e.getColumnNumber());
            System.out.println("   Message: " + e.getMessage());
        }
    }

}