public class Student {
    // Thuoc Tinh
    String name;
    int id;
    String birthdate;
    String classroom;

    // Ham Tao
    public Student(){
        name = "John";
        id = 1;
        birthdate = "28/03/86";
        classroom = "KTPM";
    }
    public Student(String name, int id
                , String birthdate, String classroom){
        this.name = name;
        this.id = id;
        this.birthdate=birthdate;
        this.classroom=classroom;
    }

    // Phuong Thuc
    public void eat() {
        System.out.println("Eating...");
    }

    public void learn() {
        System.out.println("Learning...");
    }

}
