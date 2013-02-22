package JAXB.unmarshallers;

import java.io.File;
import java.util.List;
import javax.xml.bind.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import jaxb.generated.isbn.IsbnRoot;
import jaxb.generated.isbn.IsbnType;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Adapted from Pro XML Development with Java Technology
// by Ajay Vohra and Deepak Vohra
// Apress, 2006
@Entity(name = "isbn")
public class ISBN
{
   private String ISBNNumber;
   private long id;

   public ISBN()
   {
   }

   public ISBN(String ISBNNumber)
   {
      this.ISBNNumber = ISBNNumber;
   }

   @Column(name="isbn_number")
   public String getISBNNumber()
   {
      return ISBNNumber;
   }
   public void setISBNNumber(String ISBNNumber)
   {
      this.ISBNNumber = ISBNNumber;
   }

   @Id
   @GeneratedValue
   @Column(name="id")
   public long getId()
   {
      return id;
   }
   public void setId(long id)
   {
      this.id = id;
   }

   public String toString()
   {
      return ISBNNumber;
   }
}
