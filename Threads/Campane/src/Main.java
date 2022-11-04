public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        Campana c = new Campana(3);
        Campana b = new Campana(3);
        Campana t = new Campana(3);
        c.start();
        t.start();
        b.start();


    }
}
