import java.util.ArrayList;

public class Environment {
    private Lifeform[][] board;

    public Environment(){
        board = new Lifeform[30][30];
        generateBoard();
    }

    private void generateBoard(){
        /* Creates an initial board */
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++){
                int rand = (int)(Math.random() * 20);
                if(rand == 0){
                    board[i][j] = new Carnivore();
                }
                else if(rand < 7){
                    board[i][j] = new Herbivore();
                }
                else if(rand < 10){
                    board[i][j] = new Plant();
                }
            }
        }
    }

    private void printBoard(){
        /* Displays the current board */
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == null){
                    System.out.print("_ ");
                }
                else{
                    System.out.print(board[i][j].toString() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void run(int iterations){
        /* Runs the Simulation for a number of times given by the variable iterations. */
        printBoard();
        for(int i = 0; i < iterations; i++){
            iteration();
            age();
            useEnergy();
            plantGrow();
            printBoard();
        }
    }

    private void plantGrow(){
        /* On each empty spot on the board, there is a set chance that a plant
           will grow.
         */
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                int rand = (int)(Math.random() * 100);
                if(board[i][j] == null && rand < 15){
                    board[i][j] = new Plant();
                }
            }
        }
    }

    private void age(){
        /* Increases the age of each Lifeform on the board.
           If the lifeform is dead, then it is removed.
         */
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] != null){
                    board[i][j].increAge();
                    if(board[i][j].isDead()){
                        board[i][j] = null;
                    }
                }
            }
        }
    }

    private void useEnergy(){
        /* Decrease the energy for each Animal in the board.
           If the Animal is dead, then it is removed.
         */
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] != null && board[i][j] instanceof Animal){
                    ((Animal)board[i][j]).decreEnergy();
                    if(board[i][j].isDead()){
                        board[i][j] = null;
                    }
                }
            }
        }
    }

    private void iteration(){
        /* Makes one iteration for the board.
           Sets the old board equal to the new board.
         */
        Lifeform[][] newBoard = new Lifeform[30][30];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                animalMove(newBoard, i, j);
            }
        }
        board = newBoard;
    }

    private void animalMove(Lifeform[][] newBoard, int i, int j){
        /* If the Lifeform at row i and column j is an Animal, then it will either eat
           if it is hungry and there exist food next to it or move randomly if there is space.
           Otherwise, the Animal does not move.
           Plants never move.
         */
        if(board[i][j] instanceof Animal && !((Animal)board[i][j]).isFull() && ((Animal)board[i][j]).isFood(board, i, j)){
            int[] newLoc = ((Animal)board[i][j]).move(board, i, j);
            if(board[newLoc[0]][newLoc[1]] != null){
                ((Animal)board[i][j]).eat(board[newLoc[0]][newLoc[1]]);
            }
            addToNewBoard(newBoard, newLoc, i, j);
        }
        else if(board[i][j] instanceof Animal && checkSpace(newBoard, i, j) != null){
            int[] newLoc = checkSpace(newBoard, i, j);
            addToNewBoard(newBoard, newLoc, i, j);
        }
        else if(newBoard[i][j] == null){
            newBoard[i][j] = board[i][j];
        }
    }

    private void addToNewBoard(Lifeform[][] newBoard, int[] newLoc, int i, int j){
        /* Moves the Animal at row i and column j to the new location on newBoard.
           The target is either food or an empty space.
           Set both the target and the Animal moving to null on the old board.
         */
        newBoard[newLoc[0]][newLoc[1]] = board[i][j];
        if(((Animal)board[i][j]).canReproduce()){
            ((Animal)board[i][j]).reproduce(newBoard, i, j);
        }
        board[newLoc[0]][newLoc[1]] = null;
        board[i][j] = null;
    }

    private int[] checkSpace(Lifeform[][] newBoard, int i, int j){
        /* Method that either returns a int array which is the location of an empty space around the
           Lifeform at row i and column j or returns null when there is no empty space.
         */
        ArrayList<int[]> space = new ArrayList<>();
        if(i != 0 && board[i-1][j] == null && newBoard[i-1][j] == null){
            space.add(new int[] {i-1,j});
        }
        else if(j != 0 && board[i][j-1] == null && newBoard[i][j-1] == null){
            space.add(new int[] {i,j-1});
        }
        else if(i != board.length-1 && board[i+1][j] == null && newBoard[i+1][j] == null){
            space.add(new int[] {i+1,j});
        }
        else if(j != board[i].length-1 && board[i][j+1] == null && newBoard[i][j+1] == null){
            space.add(new int[] {i,j+1});
        }
        if(space.size() == 0){
            return null;
        }
        return space.get(((int)(Math.random() * space.size())));
    }

}
