/**
 * Available at
 * {@link https://github.com/LJS360d/skillissue/tree/master/Threads}
 * 
 * 
 * @author Luca Lencioni
 */
public class Campana extends Thread {
    private final static int DEFAULT_TIMES = 4;
    private final static String[] Sounds = {"Din","Don","Dan"};
    private static int index = -1;
    private int times;

    public Campana() {
        this.times = DEFAULT_TIMES;
    }

    public Campana(int times) {
        this.times = times;
    }

    @Override
    public void run() {
       for (int i = 0; i < times; i++) {
           synchronized(Sounds){
               index++;
               if(index>=3){
                index = 0;
               }
               System.out.println(Sounds[index]);
            }
    }
    

        
}
}
        