public abstract class Theatre {
    public final static int MAX_SEATS = 8;
    public static int seats = MAX_SEATS;
    
    public static void printUnoccupiedSeats(){
        System.out.println("Posti rimasti: "+Theatre.seats);
    }
    public static void main(String[] args) throws InterruptedException {
     printUnoccupiedSeats();
        Container g = new Container("Combriccola");
        Thread polacco = new Spectator("Polacco");

        g.insert(
            polacco,polacco,
            new Spectator("Buti"),
            new Spectator("Birraiolo"),
            new Spectator("Onorevole"),
            new Spectator("Svedese"),
            new Spectator("Stupido"),
            new Spectator("Napolitano"),
            new Spectator("Salvini"),
            new Spectator("SalviniRobot"),
            new Spectator("Bot-Gene")
        );

        g.startAll();
        g.joinAll();
        printUnoccupiedSeats();
            
    }
}
