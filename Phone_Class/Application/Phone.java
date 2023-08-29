package Application;
import Application.InternetBrowser;
public class Phone {
    public static void main(String[] args) {

        InternetBrowser ib = new InternetBrowser(Site.Google);
        ib.openWebsite();

        MusicPlayer ms = new MusicPlayer();
        ms.playMusic("Post Malone");

        Telephone tl = new Telephone("Sara", 119823367);
        tl.allContacts();
    }

}
