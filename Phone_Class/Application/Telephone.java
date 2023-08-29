package Application;

import Src.ContactDatabase;

public class Telephone {


    public Contact contact;
    public ContactDatabase cd;

    public Telephone (String name, int number) {
        this.contact = new Contact(name, number);
        this.cd = new ContactDatabase();
    }

    public  Telephone (){}

    public void callNumber(int number){
        System.out.println("Call to number: " + number);
    }

    public void allContacts(){
        cd.allContacts();
        cd.close();
    }

    public void saveContact(){
        cd.saveContact(this.contact);
        cd.close();
    }

    public void UpdateContact(String name, int number) {
        cd.updateContactPhoneNumber(name, number);
        cd.close();
    }

    public void deleteContact(String name) {
        cd.deleteContactByName(name);
    }


}
