<h1>OOP Challenge</h1>

<h2>challenge posed by: https://github.com/glysns in a course of DIO. Database connection via jdbc was used.</h2>

<p>Modeling and diagramming of the representation in UML and Code with regard to the iPhone component.</p>

<p>Based on the iPhone launch video as per the link below, create the class and interface diagram using a UML
        tool of your preference to represent the roles of the iPhone as: Music Player, Phone Device, and Internet
        Browser. Then, create the classes and interfaces in .java file format.</p>

<p>iPhone 2007 Launch</p>
<p>Relevant minutes from 00:15 to 00:55</p>

<h2>Expected Behaviors:</h2>
<ul>
    <li>Music Player: play, pause, selectMusic</li>
    <li>Phone Device: dial, answer, startVoicemail</li>
    <li>Internet Browser: displayPage, addNewTab, updatePage</li>
</ul>

```mermaid

classDiagram
  class iPhone {
    +playMusic()
    +makePhoneCall()
    +browseInternet()
  }

  class MusicPlayer {
    +playMusic()
  }

  class Phone {
    +makePhoneCall()
  }

  class InternetBrowser {
    +browseInternet(Site: Site)
  }

  class Contact {
    + String name;
    + int number;
  }

  class ContactDatabase {
    + Connection connection
    + createTableIfNotExists()
    + saveContact(Contact contact)
    - contactExists(String name)
    + allContacts()
    + updateContactPhoneNumber(String contactName, Integer newPhoneNumber)
    + deleteContactByName(String contactName)
    + close()
  }

  class Telephone {
    + Contact contact
    + ContactDatabase cd
    + callNumber(int number)
    + void allContacts()
    + void saveContact()
    + void UpdateContact(String name, int number)
    + void deleteContact(String name)
  }

  class Site {
    GOOGLE
    FACEBOOK
  }

  iPhone * ..> MusicPlayer
  iPhone * ..> Phone
  iPhone * ..> InternetBrowser
  Phone ..> Telephone
  Telephone ..> ContactDatabase
  ContactDatabase ..> Contact

```

