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
        System.out.println(this.getName()+"-Voglio "+wantPencil+" Matite");
        while(wantPencil > 0){
            synchronized(Cartoleria.class){
                if(Cartoleria.pencils > 0){
                    Cartoleria.pencils--;
                    wantPencil--;
                }
                else{
                    System.out.println(Thread.currentThread().getName()+" Mi mancano "+wantPencil+ " Matite");
                    wantPencil=-1;
                    System.err.println("Cartoleria- Puppa");
                    break;
                }
            }
        }  
        if (wantPencil > -1){
            System.out.println(this.getName()+" Poggers");
        } else System.out.println(this.getName()+" Per niente poggers");

        System.out.println(this.getName()+"-Voglio "+wantNotebook+" Quaderni");
        while(wantNotebook > 0){
            synchronized(Cartoleria.class){
                if(Cartoleria.notebooks>0){
                    Cartoleria.notebooks--;
                   wantNotebook--;
                }
                else{
                    System.out.println(Thread.currentThread().getName()+" Mi mancano "+wantNotebook+ " Quaderni");
                    System.err.println("Cartoleria- Puppa");
                    wantNotebook=-1;
                    break;
                }
            }    
        }
        if(wantNotebook > -1){
            System.out.println(this.getName()+" Molto Poggers"); 
        } else System.out.println(this.getName()+" Napoli");
               
        }


        
}
