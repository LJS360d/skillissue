public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        Campana c = new Campana("Bing");
        Campana b = new Campana("Bong");
        Campana t = new Campana("Bang");
        Campana u = new Campana("Beng");
        c.start();
        t.start();
        b.start();
        u.start();

    }
}
