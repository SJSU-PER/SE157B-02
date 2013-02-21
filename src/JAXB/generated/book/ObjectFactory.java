//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.20 at 08:38:32 PM PST 
//


package jaxb.generated.book;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.generated.book package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Book_QNAME = new QName("", "book");
    private final static QName _BookInfo_QNAME = new QName("", "book_info");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.generated.book
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookRoot }
     * 
     */
    public BookRoot createBookRoot() {
        return new BookRoot();
    }

    /**
     * Create an instance of {@link BookType }
     * 
     */
    public BookType createBookType() {
        return new BookType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookRoot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "book")
    public JAXBElement<BookRoot> createBook(BookRoot value) {
        return new JAXBElement<BookRoot>(_Book_QNAME, BookRoot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "book_info")
    public JAXBElement<BookType> createBookInfo(BookType value) {
        return new JAXBElement<BookType>(_BookInfo_QNAME, BookType.class, null, value);
    }

}