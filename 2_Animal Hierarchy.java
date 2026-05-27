class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}

class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    
    @Override
    public void eat() {
        System.out.println(name + " the dog is eating dog food");
    }
    
    public void bark() {
        System.out.println(name + " is barking: Woof! Woof!");
    }
}

class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }
    
    @Override
    public void eat() {
        System.out.println(name + " the cat is eating fish");
    }
    
    public void meow() {
        System.out.println(name + " says: Meow!");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog("Max", 3, "Golden Retriever");
        Cat cat = new Cat("Whiskers", 2);
        
        dog.eat();
        dog.bark();
        cat.eat();
        cat.meow();
    }
}