public class Go {
    public static void main(String[] args){
        Employee e1 = new Employee(8, "Peter", "Tan", 2500);
        System.out.println(e1);

        e1.setSalary(9999);
        System.out.println(e1);

        System.out.println("ID: "+e1.getId());
        System.out.println("First Name: "+e1.getFirstName());
        System.out.println("LastName: "+e1.getLastName());
        System.out.println("Name: "+e1.getName());
        System.out.println("Salary: "+e1.getSalary());
        System.out.println("Annual Salary: "+e1.getAnnualSalary());
        System.out.println("them 10% luong = "+e1.raiseSalary(10));
        System.out.println(e1);



    }
}
