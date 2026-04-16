package Day4.Solutions;

public class InheritanceDemo {
    public static void main(String[] args) {
        Student student1 = new Student("Alice", 20, "1234");
        Student student2 = new Student("Bob", 20, "4321");
        Teacher teacher = new Teacher("Mr. Smith", 40, "Mathematics");

        // Display information and demonstrate method overriding
        System.out.println("Student1 Information:");
        student1.display();
        student1.birthday();

        System.out.println("\nTeacher Information:");
        teacher.display();
        teacher.birthday();

        // Pass by reference demonstration
        Student student3 = student2;
        student2.display();
        student3.display();

        student3.birthday();

        student2.display();
        student3.display();

        // Private variables
        // student2.age = 25;
        // Can't change someone's birthday without announcing it to them!
        // must use the birthday method to change the age of a person

        Person[] people = { student1, student2, teacher };
        for (int i = 0; i < people.length; i++) {
            System.out.println("\nPerson " + (i + 1) + " Information:");
            people[i].display();
        }

        for (Person person : people) {
            System.out.println("\nPerson Information:");
            person.display();
        }
    }

    static class Person {
        // Start without private
        private String name;
        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        void display() {
            System.out.println("Name: " + name + ", Age: " + age);
        }

        void birthday() {
            age++;
            System.out.println("Happy Birthday! " + name + " is now " + age + " years old.");
        }
    }

    static class Student extends Person {
        private String studentId;

        Student(String name, int age, String studentId) {
            super(name, age);
            this.studentId = studentId;
        }

        @Override
        void display() {
            // Start off by recreating the display method of the parent class
            super.display(); // Then show reuse of the parent class method
            System.out.println("Student ID: " + studentId);
        }
    }

    static class Teacher extends Person {
        private String subject;

        Teacher(String name, int age, String subject) {
            super(name, age);
            this.subject = subject;
        }

        @Override
        void display() {
            super.display();
            System.out.println("Subject: " + subject);
        }
    }
}
