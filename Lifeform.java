public abstract class Lifeform {
    private final int deathAge;
    private int nutrient;
    private int age;

    public Lifeform(int deathAge, int nutrient){
        this.deathAge = deathAge;
        this.nutrient = nutrient;
    }

    public int getDeathAge(){
        return deathAge;
    }

    public int getAge(){
        return age;
    }

    public void increAge(){
        /* Increase the age of this Lifeform by 1 */
        age++;
    }

    public int getNutrient(){
        return nutrient;
    }

    public abstract boolean isDead();
        /* Returns true if this Lifeform is dead*/

}
