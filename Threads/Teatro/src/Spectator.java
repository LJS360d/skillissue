public class Spectator extends Thread{
    
    public Spectator(String name) {
        super(name);
        
    }

    @Override
    public void run() {
        synchronized(Theatre.class){
        if(Theatre.seats > 0){
                Theatre.seats--;
                System.out.println(this.getName()+": Vado a sedermi");
            }
        
        else{
            System.out.println("Siamo Pieni, Puppa: "+this.getName());
            }
        }
    }
    
}