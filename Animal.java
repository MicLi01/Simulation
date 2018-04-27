public abstract class Animal extends Lifeform{
    protected static final int maxEnergy = 100;
    private int energy;
    public Animal(int deathAge, int energy){
        super(deathAge, 30);
        this.energy = energy;
    }

    @Override
    public boolean isDead(){
        return getAge() == getDeathAge() || getEnergy() == 0;
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }

    public int getEnergy(){
        return energy;
    }

    public void decreEnergy(){
        /* Decreases energy of this Animal */
        setEnergy(getEnergy() - 1);
    }

    public boolean canReproduce(){
        return getEnergy() > maxEnergy / 2 && (getAge() > getDeathAge()/4 && getAge() < getDeathAge()*3/4);
    }

    public void eat(Lifeform food){
        /* Adds the food's nutrients to this Animal's energy up to a maximum of maxEnergy. */
        if(getEnergy() + food.getNutrient() > maxEnergy){
            setEnergy(maxEnergy);
        }
        else{
            setEnergy(getEnergy() + food.getNutrient());
        }
    }

    public boolean isFull(){
        /* Returns true if this Animal's energy exceeds a certain energy amount. */
        return getEnergy() >= maxEnergy * 8/10;
    }

    public int[] move(Lifeform[][] board, int i, int j){
        /* Returns a int array containing the position of the food where
           index 0 represents the row and index 1 represents the column.
         */
        int[] newLoc;
        newLoc = foodLoc(board, i, j);
        return newLoc;
    }

    public abstract boolean isFood(Lifeform[][] board, int i, int j);
        /* Returns true if there is food and false otherwise */

    public abstract int[] foodLoc(Lifeform[][] board, int i, int j);
        /* Precondition: isFood returns true.
           Finds the location of the food.
         */

    public abstract void reproduce(Lifeform[][] newBoard, int i, int j);

}
