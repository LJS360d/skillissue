public class Cliente extends Thread{
    private int wantPencil;
    private int wantNotebook;
    
    public Cliente(int wantPencil, int wantNotebook) {
        super();
        this.wantPencil = wantPencil;
        this.wantNotebook = wantNotebook;
    }
    @Override
    public void run() {
    if(!claimPencils())System.out.println(this.getName()+" ha sucato");
    if(!claimNotebooks())System.out.println(this.getName()+" ha sucato");
    }
    
        public boolean claimPencils(){
        System.out.println(this.getName()+"-Voglio "+wantPencil+" Matite");
        while(wantPencil > 0){
            synchronized(Cartoleria.class){
                if(Cartoleria.pencils <= 0){
                    System.out.println(this.getName()+" Mi mancano "+wantPencil+ " Matite");
                    wantPencil=-1;
                    break;
                }
                    Cartoleria.pencils--;
                    wantPencil--;
            }
        }  
        if (wantPencil == -1){
            return false;
        } return true;
        }

        public boolean claimNotebooks(){
        System.out.println(this.getName()+"-Voglio "+wantNotebook+" Quaderni");
        while(wantNotebook > 0){
            synchronized(Cartoleria.class){
                if(Cartoleria.notebooks<=0){
                    System.out.println(this.getName()+" Mi mancano "+wantNotebook+ " Quaderni");
                    wantNotebook=-1;
                    break;
                }
                Cartoleria.notebooks--;
                wantNotebook--;
            }    
        }
        if(wantNotebook == -1){
        return false;
        } 
        return true;
        }
}   
