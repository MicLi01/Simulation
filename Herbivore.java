public class Herbivore extends Animal {

    public Herbivore(){
        super(20, 5);
    }

    @Override
    public String toString(){
        return "H";
    }

    @Override
    public int[] foodLoc(Lifeform[][] board, int i, int j){
        if (i != 0 && board[i-1][j] instanceof Plant){
            return new int[] {i-1,j};
        }
        else if (j != 0 && board[i][j-1] instanceof Plant){
            return new int[] {i,j-1};
        }
        else if (i != board.length-1 && board[i+1][j] instanceof Plant){
            return new int[] {i+1,j};
        }
        else{
            return new int[] {i,j+1};
        }
    }

    @Override
    public boolean isFood(Lifeform[][] board, int i, int j){
        boolean food = false;
        if(i != 0 && board[i-1][j] != null && board[i-1][j] instanceof Plant){
            food = true;
        }
        else if(j != 0 && board[i][j-1] != null && board[i][j-1] instanceof Plant){
            food = true;
        }
        else if(i != board.length-1 && board[i+1][j] != null && board[i+1][j] instanceof Plant){
            food = true;
        }
        else if(j != board[i].length-1 && board[i][j+1] != null && board[i][j+1] instanceof Plant){
            food = true;
        }
        return food;
    }

    @Override
    public void reproduce(Lifeform[][] newBoard, int i, int j){
        /* Halves the energy of the current Animal and creates a new Herbivore and adds to newBoard.*/
        setEnergy(getEnergy() / 2);
        newBoard[i][j] = new Herbivore();
    }
}
