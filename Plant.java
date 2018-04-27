public class Plant extends Lifeform{
    public Plant(){
           super(5, 20);
    }

    @Override
    public String toString(){
        return "P";
    }

    @Override
    public boolean isDead(){
        return getAge() == getDeathAge();
    }

}
