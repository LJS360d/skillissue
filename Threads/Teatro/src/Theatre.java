public record Theatre() {
    public final static int MAX_SEATS = 3;
    public static int seats = MAX_SEATS;
    
    public static void printUnoccupiedSeats(){
        System.out.println("Posti rimasti: "+seats);
    }
    public static void main(String[] args) {
     printUnoccupiedSeats();
        Container g = new Container("Combriccola");
        g.insert(
            new Spectator("Buti"),
            new Spectator("Birraiolo"),
            new Spectator("Onorevole"),
            new Spectator("Polacco"),
            new Spectator("Stupido"),
            new Spectator("Napolitano"),
            new Spectator("Salvini"),
            new Spectator("SalviniRobot"),
            new Spectator("Bot-Gene")
        );
        g.startAll();
        
        while(seats<=0){
            printUnoccupiedSeats();
            break;
        }
            
    }
}
