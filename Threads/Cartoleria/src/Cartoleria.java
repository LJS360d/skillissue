public record Cartoleria() {
    public static int pencils = 100;
    public static int notebooks = 35;

    public static void printInventory(){
        System.out.println("La Cartoleria ha: "+ Cartoleria.pencils + " Matite");
        System.out.println("La Cartoleria ha: "+ Cartoleria.notebooks + " Quaderni ");
    }
}   
