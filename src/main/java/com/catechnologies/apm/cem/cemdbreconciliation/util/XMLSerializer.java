package com.catechnologies.apm.cem.cemdbreconciliation.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import org.w3c.dom.Document;
import java.io.StringReader;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * JAXB XML serializer/deserializer. Converts JPA entities to/from XML
 * @author bezad01
 *
 */
public class XMLSerializer {

	private static final String XML_UNMARSHAL_TROUBLESHOOT =	"xml.deserialization.troubleshooting";	
	
	/**
	 * 
	 * @param source
	 * @param type
	 * @return
	 */
    public static String objectToXML(Object source, Class<?> type) {
        
    	String result;
        StringWriter sw = new StringWriter();
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(source, sw);
            result = sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    
    /**
     * 
     * @param xml
     * @param type
     * @return
     */
    public static Object xmlToObject(String xml, Class<?> type) {
       
    	Object result;
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            
            if(ResourceUtil.getApplicationProperty(XML_UNMARSHAL_TROUBLESHOOT).equals("yes"))
            	unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
            
            result = unmarshaller.unmarshal(new StringReader(xml));
            
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return result;
    }	
    
    
    /**
     * 
     * @param xml
     * @param xpathExpression
     * @return
     */
    public static String extractValue(String xml, String xpathExpression) {
        
    	String value;
        
    	try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            documentBuilderFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();

            byte[] bytes = xml.getBytes("UTF-8");
            InputStream inputStream = new ByteArrayInputStream(bytes);
            Document doc = docBuilder.parse(inputStream);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            value = xpath.evaluate(xpathExpression, doc, XPathConstants.STRING).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return value;
    }    
    
	
}
