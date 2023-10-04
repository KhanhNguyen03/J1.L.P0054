
package dataAccess;

import common.ScannerFactory;
import java.util.ArrayList;
import java.util.Formatter;
import model.Contact;
import repository.ContactManager;
import repository.IContactManager;

public class ContactDao {

    private static ContactDao instance = null;
    IContactManager cm;
    ScannerFactory sc;
    private int count = 0;

    public ContactDao() {
        cm = new ContactManager();
        sc = new ScannerFactory();
    }

    public static ContactDao Instance() {
        if (instance == null) {
            synchronized (ContactDao.class) {
                if (instance == null) {
                    instance = new ContactDao();
                }
            }
        }
        return instance;
    }

    public void createContact(ArrayList<Contact> contactList) {
        System.out.println("-----Add Contact-----");
        System.out.println("Enter Name: ");
        String fullname = sc.getString();
        System.out.println("Enter First Name");
        String firstName =sc.getString();
        System.out.println("Enter Last Name:");
        String lastName =sc.getString();
        System.out.println("Enter Group: ");
        String group = sc.getString();
        System.out.println("Enter Address");
        String address = sc.getString();
        System.out.println("Enter Phone");
        String phone = sc.getPhone();
        String[] name = fullname.split(" ", 2);
        if (name.length == 1) {
            Contact contact = new Contact(fullname, firstName, lastName, group, address, phone);
            contactList.add(contact);
            contact.setId(contactList.indexOf(contact) + 1);
        } else {
            Contact contact = new Contact(  fullname, firstName, lastName, group, address, phone);
            contactList.add(contact);
            contact.setId(contactList.indexOf(contact) + 1);
        }
    }

    public void deleteContact(ArrayList<Contact> contactList) {
        showAll(contactList);
        System.out.println("-----Delete a Contact-----");
        System.out.println("Enter ID");
        int id = sc.getInt();
        boolean removed = contactList.removeIf(contact -> contact.getId() == id);
        if (removed) {
            System.out.println("Successful");
        } else {
            System.out.println("ID not found");
        }
    }

    public void showAll(ArrayList<Contact> contactList) {
        Formatter ft = new Formatter();
        System.out.println("-----Display all Contact-----");
        System.out.printf("%2s %10s %15s %15s %10s %11s %11s \n", "ID", "Name", "First Name", "Last Name", "Group", "Address", "Phone");
        for (Contact contact : contactList) {
            ft.format("%-4s %-20s %-15s %-15s %-10s %-10s %-12s s \n",
                    contact.getId(),
                    contact.getFullName(),
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getGroup(),
                    contact.getAddress(),
                    contact.getPhone());
        }
        System.out.println(ft);
        System.out.println("");
    }

}
