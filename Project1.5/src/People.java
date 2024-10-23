public class People {
    private final int age;
    private final String name;

    People(int age, String name){
        this.age = age;
        this.name = name;
    }
    public Integer getAge(){
        return age;
    }
    public String toString(){
        return this.age + " " + this.name;
    }


}
