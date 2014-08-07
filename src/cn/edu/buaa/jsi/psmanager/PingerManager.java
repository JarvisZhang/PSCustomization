package cn.edu.buaa.jsi.psmanager;

import java.util.List;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPPart;

public class PingerManager extends XMLManager {
	
	public PingerManager(String metaKey, long timeStart,
			long timeEnd) {
		try {
			super.init();
			SOAPPart soapPart = super.getSOAPRequestMsg().getSOAPPart();
			SOAPEnvelope soapEnvelope;
			soapEnvelope = soapPart.getEnvelope();
			this.buildSOAPHeader(soapEnvelope);
			SOAPBody soapBody = soapEnvelope.getBody();
			Name bodyName = soapEnvelope.createName("nmwg:message");
			SOAPBodyElement msgElement = soapBody.addBodyElement(bodyName);
			this.buildBodyHeader(msgElement);
			SOAPElement metaElement =
					this.buildMetaElement(msgElement, timeStart, timeEnd);
			this.addKeyElement(metaElement, metaKey);
			this.buildDataElement(msgElement, metaElement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<PingerPoint> getResult(String host) {
		PingerData pingerData = new PingerData(this.sendRequest(host));
		return pingerData.getPointData();
	}
	
	protected SOAPBodyElement buildBodyHeader(SOAPBodyElement msgElement) {
		msgElement.setAttribute("type", "SetupDataRequest");
		msgElement.setAttribute("id", "message2");
		msgElement.setAttribute("xmlns:pinger",
				"http://ggf.org/ns/nmwg/tools/pinger/2.0/");
		msgElement.setAttribute("xmlns:nmwg",
				"http://ggf.org/ns/nmwg/base/2.0/");
		return msgElement;
	}
	
	protected SOAPElement buildMetaElement(SOAPBodyElement msgElement,
			long timeStart, long timeEnd) throws SOAPException {
		SOAPElement metaElement = msgElement.addChildElement("metadata",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		metaElement.setAttribute("id", "meta2");
		
		SOAPElement paraElement = metaElement.addChildElement("parameters",
				"pinger", "http://ggf.org/ns/nmwg/tools/pinger/2.0/");
		paraElement.setAttribute("id", "param2");
		
		SOAPElement startElement = paraElement.addChildElement("parameter",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		startElement.setAttribute("name", "startTime");
		startElement.addTextNode(String.valueOf(timeStart));
		SOAPElement endElement = paraElement.addChildElement("parameter",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		endElement.setAttribute("name", "endTime");
		endElement.addTextNode(String.valueOf(timeEnd));
		
		SOAPElement eveElement = metaElement.addChildElement("eventType",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		eveElement.addTextNode("http://ggf.org/ns/nmwg/tools/pinger/2.0/");
		
		return metaElement;
	}
	
	protected SOAPElement addKeyElement(SOAPElement metaElement, String key)
			throws SOAPException {
		SOAPElement keyElement = metaElement.addChildElement("key",
				"nmwg", "http://ggf.org/ns/nmwg/base/2.0/");
		keyElement.setAttribute("id", key);
		return keyElement;
	}
}
