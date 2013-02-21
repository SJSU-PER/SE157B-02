package JAXB.marshallers;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.*;
import jaxb.generated.publisher.ObjectFactory;
import jaxb.generated.publisher.PublisherRoot;
import jaxb.generated.publisher.PublisherType;


// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006

public class PublisherJAXBMarshaller
{
	public void generateXMLDocument()
    {
		try {
			JAXBContext jaxbContext =
                    JAXBContext.newInstance("jaxb.generated.publisher");
			Marshaller marshaller = jaxbContext.createMarshaller();
			ObjectFactory factory = new ObjectFactory();

         PublisherRoot publisher = factory.createPublisherRoot();
         List<PublisherType> publisherList = publisher.getPubInfo();

         PublisherType publisherInfo = factory.createPublisherType();
         publisherInfo.setPubId(new BigInteger("1"));
         publisherInfo.setPubName("Pocket Books");
         publisherList.add(publisherInfo);

         publisherInfo = factory.createPublisherType();
         publisherInfo.setPubId(new BigInteger("2"));
         publisherInfo.setPubName("Pearson");
         publisherList.add(publisherInfo);

         publisherInfo = factory.createPublisherType();
         publisherInfo.setPubId(new BigInteger("3"));
         publisherInfo.setPubName("Prentice Hall");
         publisherList.add(publisherInfo);

         publisherInfo = factory.createPublisherType();
         publisherInfo.setPubId(new BigInteger("4"));
         publisherInfo.setPubName("Gallery Books");
         publisherList.add(publisherInfo);

         publisherInfo = factory.createPublisherType();
         publisherInfo.setPubId(new BigInteger("5"));
         publisherInfo.setPubName("Dales Large Print Books");
         publisherList.add(publisherInfo);

         publisherInfo = factory.createPublisherType();
         publisherInfo.setPubId(new BigInteger("6"));
         publisherInfo.setPubName("Random House Digital, Inc");
         publisherList.add(publisherInfo);

			JAXBElement<PublisherRoot> authorElement =
                    factory.createPublisher(publisher);
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			marshaller.marshal(authorElement, System.out);
		}
        catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] argv)
    {
		PublisherJAXBMarshaller jaxbMarshaller = new PublisherJAXBMarshaller();
		jaxbMarshaller.generateXMLDocument();
	}
}
