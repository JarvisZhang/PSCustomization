package cn.edu.buaa.jsi.psmanager;

import java.net.URL;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class XMLManager {
	
	private SOAPConnectionFactory scf;
	private SOAPMessage soapRequestMsg;

	public XMLManager(String srcIP, String destIP,
			long timeStart, long timeEnd){
		try{
			this.scf = SOAPConnectionFactory.newInstance();
			this.soapRequestMsg = MessageFactory.newInstance().createMessage();
			SOAPPart soapPart = this.soapRequestMsg.getSOAPPart();
			SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
			this.buildSOAPHeader(soapEnvelope);
			SOAPBody soapBody = soapEnvelope.getBody();
			Name bodyName = soapEnvelope.createName("nmwg:message");
			SOAPBodyElement msgElement = soapBody.addBodyElement(bodyName);
			this.buildBodyHeader(msgElement);
			this.buildIPElement(msgElement, srcIP, destIP);
			this.buildTimeElement(msgElement, timeStart, timeEnd);
			SOAPElement dataElement = msgElement.addChildElement("data",
					"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
			dataElement.setAttribute("id", "data2");
			dataElement.setAttribute("metadataIdRef", "meta2c");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public BwctlData sendRequest(String host){
		try {
			URL endpoint = new URL(host);
			SOAPConnection conn = this.scf.createConnection();
			SOAPMessage soapResponseMsg =
					conn.call(this.soapRequestMsg, endpoint);
			return new BwctlData(soapResponseMsg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void printSOAPResponse(SOAPMessage soapResponse){
        this.printSOAPXML(soapResponse);
    }
	
	public void printRequestXML(){
		this.printSOAPXML(this.soapRequestMsg);
	}
	
	private void printSOAPXML(SOAPMessage soapMessage){
		try{
			TransformerFactory transformerFactory =
	        		TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        Source sourceContent = soapMessage.getSOAPPart().getContent();
	        System.out.print("\nResponse SOAP Message = ");
	        StreamResult result = new StreamResult(System.out);
	        transformer.transform(sourceContent, result);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void buildSOAPHeader(SOAPEnvelope soapEnvelope){
		soapEnvelope.setAttribute("xmlns:SOAP-ENC",
	    		"http://schemas.xmlsoap.org/soap/encoding/");
	    soapEnvelope.setAttribute("xmlns:xsd",
	    		"http://www.w3.org/2001/XMLSchema");
	    soapEnvelope.setAttribute("xmlns:xsi",
	    		"http://www.w3.org/2001/XMLSchema-instance");
	    soapEnvelope.setAttribute("xmlns:SOAP-ENV",
	    		"http://schemas.xmlsoap.org/soap/envelope/");
	}
	
	private void buildBodyHeader(SOAPBodyElement msgElement){
		msgElement.setAttribute("type", "SetupDataRequest");
		msgElement.setAttribute("id", "setupDataRequest1");
		msgElement.setAttribute("xmlns:iperf",
				"http://ggf.org/ns/nmwg/tools/iperf/2.0/");
		msgElement.setAttribute("xmlns:nmwg",
				"http://ggf.org/ns/nmwg/base/2.0/");
		msgElement.setAttribute("xmlns:select",
				"http://ggf.org/ns/nmwg/ops/select/2.0/");
		msgElement.setAttribute("xmlns:nmwgt",
				"http://ggf.org/ns/nmwg/topology/2.0/");
		msgElement.setAttribute("xmlns:nmtm", "http://ggf.org/ns/nmwg/time/2.0/");
	}
	
	private void buildIPElement(SOAPBodyElement msgElement, String srcIP,
			String destIP) throws Exception{
		SOAPElement metaElement = msgElement.addChildElement("metadata", "nmwg",
				"http://ggf.org/ns/nmwg/base/2.0/");
		metaElement.setAttribute("id", "meta2");
		
		SOAPElement subElement = metaElement.addChildElement("subject", "nmwg",
				"http://ggf.org/ns/nmwg/base/2.0/");
		subElement.setAttribute("id", "sub1");
		
		SOAPElement endPointElement = subElement.addChildElement(
				"endPointPair", "nmwgt",
				"http://ggf.org/ns/nmwg/topology/2.0/");
		
		SOAPElement srcElement = endPointElement.addChildElement("src",
				"nmwgt", "http://ggf.org/ns/nmwg/topology/2.0/");
		srcElement.setAttribute("type", "ipv4");
		srcElement.setAttribute("value", srcIP);
		
		SOAPElement destElement = endPointElement.addChildElement("dst",
				"nmwgt", "http://ggf.org/ns/nmwg/topology/2.0/");
		destElement.setAttribute("type", "ipv4");
		destElement.setAttribute("value", destIP);
		
		SOAPElement eveElement = metaElement.addChildElement("eventType",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		eveElement.addTextNode("http://ggf.org/ns/nmwg/tools/iperf/2.0");
		
		SOAPElement paraElement = metaElement.addChildElement("parameters",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		paraElement.setAttribute("id", "parameters2");
		
		SOAPElement protoElement = paraElement.addChildElement("parameter",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		protoElement.setAttribute("name", "protocol");
		protoElement.addTextNode("TCP");
	}
	
	private void buildTimeElement(SOAPBodyElement msgElement, long timeStart,
			long timeEnd) throws Exception{
		SOAPElement metaElement = msgElement.addChildElement("metadata",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		metaElement.setAttribute("id", "meta2c");
		
		SOAPElement subElement = metaElement.addChildElement("subject",
				"select", "http://ggf.org/ns/nmwg/ops/select/2.0/");
		subElement.setAttribute("id", "sub2c");
		subElement.setAttribute("metadataIdRef", "meta2");
		
		SOAPElement paraElement = metaElement.addChildElement("parameters",
				"select", "http://ggf.org/ns/nmwg/ops/select/2.0/");
		paraElement.setAttribute("id", "param2c");
		
		SOAPElement startElement = paraElement.addChildElement("parameter",
				"nmwg", "http://ggf.org/ns/nmwg/ops/select/2.0/");
		startElement.setAttribute("name", "startTime");
		startElement.addTextNode(String.valueOf(timeStart));
		
		SOAPElement endElement = paraElement.addChildElement("parameter",
				"nmwg", "http://ggf.org/ns/nmwg/ops/select/2.0/");
		endElement.setAttribute("name", "endTime");
		endElement.addTextNode(String.valueOf(timeEnd));
		
		SOAPElement eveElement = metaElement.addChildElement("eventType",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		eveElement.addTextNode("http://ggf.org/ns/nmwg/ops/select/2.0");
	}
}
