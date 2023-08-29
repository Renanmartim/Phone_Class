package Application;

public enum Site {
    Google("https://www.google.com.br/"),
    Facebook("https://pt-br.facebook.com/");

    private final String url;

    Site(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
