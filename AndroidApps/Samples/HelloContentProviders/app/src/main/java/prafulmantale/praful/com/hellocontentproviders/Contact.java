package prafulmantale.praful.com.hellocontentproviders;

import java.util.ArrayList;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class Contact {
    public String id;
    public String name;
    public ArrayList<ContactEmail> emails;
    public ArrayList<ContactPhone> numbers;

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;
        this.emails = new ArrayList<ContactEmail>();
        this.numbers = new ArrayList<ContactPhone>();
    }

    public void addEmail(String address, String type) {
        emails.add(new ContactEmail(address, type));
    }

    public void addNumber(String number, String type) {
        numbers.add(new ContactPhone(number, type));
    }
}
