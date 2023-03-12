import java.io.Serializable;

public class BattleshipGrid implements Serializable{
    private Byte[][] grid = new Byte[10][10];
    static final long serialVersionUID = 42L;

    public Byte[][] getGrid() {
        return grid;
    }

    
    public BattleshipGrid() {
        clearGrid(grid);
    }
    public void clearGrid(Byte[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j]=0;
            }
        }
    }
    public void printGrid() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid.length; j++) {
                if(grid[i][j] == 'a')//There is a boat
                System.out.print("o");
                else 
                System.out.print("x");
            }
            System.out.println();
        } 
    }


    public Byte at(int x, int y,boolean setToShip){
        this.grid[x][y] = (byte) ((setToShip) ? 'a':'b');
        return this.grid[x][y];
    }

    public Byte at(int x, int y){
        return this.grid[x][y];
    }
}
