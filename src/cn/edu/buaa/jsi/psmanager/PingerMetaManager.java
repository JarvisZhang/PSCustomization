package cn.edu.buaa.jsi.psmanager;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PingerMetaManager extends XMLManager {

	private String host;

	public PingerMetaManager(String host) {
		try {
			this.host = host;
			super.init();
			SOAPPart soapPart = super.getSOAPRequestMsg().getSOAPPart();
			SOAPEnvelope soapEnvelope;
			soapEnvelope = soapPart.getEnvelope();
			this.buildSOAPHeader(soapEnvelope);
			SOAPBody soapBody = soapEnvelope.getBody();
			Name bodyName = soapEnvelope.createName("nmwg:message");
			SOAPBodyElement msgElement = soapBody.addBodyElement(bodyName);
			this.buildBodyHeader(msgElement);
			SOAPElement metaElement = this.buildMetaElement(msgElement);
			this.buildDataElement(msgElement, metaElement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getMetadataKey(String srcIP, String destIP) {
		try {
			SOAPMessage soapResponse = this.sendRequest(this.host);
			SOAPBody soapBody = soapResponse.getSOAPBody();
			Element message = (Element) soapBody.getElementsByTagName(
					"nmwg:message").item(0);
			NodeList metadataItems = message.getElementsByTagName("nmwg:metadata");
			for (int i = 0; i < metadataItems.getLength(); i++) {
				Element metadataItem = (Element) metadataItems.item(i); 
				if (haveEndpointPair(metadataItem, srcIP, destIP)) {
					Element keyElement =
							(Element) metadataItem.getElementsByTagName(
									"nmwg:key").item(0);
					return keyElement.getAttribute("id");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new String("");
		}
		return new String("");
	}

	protected SOAPBodyElement buildBodyHeader(SOAPBodyElement msgElement) {
		msgElement.setAttribute("type", "MetadataKeyRequest");
		msgElement.setAttribute("id", "message1");
		msgElement.setAttribute("xmlns:pinger",
				"http://ggf.org/ns/nmwg/tools/pinger/2.0/");
		msgElement.setAttribute("xmlns:nmwg",
				"http://ggf.org/ns/nmwg/base/2.0/");
		return msgElement;
	}

	protected SOAPElement buildMetaElement(SOAPBodyElement msgElement)
			throws SOAPException {
		SOAPElement metaElement = msgElement.addChildElement("metadata",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		metaElement.setAttribute("id", "meta1");

		SOAPElement subElement = metaElement.addChildElement("subject",
				"pinger", "http://ggf.org/ns/nmwg/tools/pinger/2.0/");
		subElement.setAttribute("id", "sub1");

		SOAPElement eveElement = metaElement.addChildElement("eventType",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		eveElement.addTextNode("http://ggf.org/ns/nmwg/tools/pinger/2.0/");
		return metaElement;
	}

		
	private static boolean haveEndpointPair(Element metaElement, String srcIP,
			String destIP) throws Exception {
		Element subElement = (Element) metaElement.getElementsByTagName(
				"pinger:subject").item(0);
		Element endPointElement = (Element) subElement.getElementsByTagName(
				"nmwgt:endPointPair").item(0);
		Element srcElement = (Element) endPointElement.getElementsByTagName(
				"nmwgt:src").item(0);
		Element destElement = (Element) endPointElement.getElementsByTagName(
				"nmwgt:dst").item(0);
		String metaSrcIP = srcElement.getAttribute("value");
		String metaDestIP = destElement.getAttribute("value");
		return ((metaSrcIP.equals(srcIP) && metaDestIP.equals(destIP)) || 
				(metaSrcIP.equals(destIP) && metaDestIP.equals(srcIP)));
	}
}
