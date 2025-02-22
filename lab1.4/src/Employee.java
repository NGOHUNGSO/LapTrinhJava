public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    public Employee(int id, String fn, String ln, int sl){
        this.id = id;
        firstName=fn;
        lastName=ln;
        salary=sl;
    }

    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getName(){
        return firstName+" "+lastName;
    }
    public int getSalary(){
        return salary;
    }

    public void setSalary(int sl){
        salary=sl;
    }

    public int getAnnualSalary(){
        return salary*12;
    }

    public int raiseSalary(int percent){
        salary+=salary*percent/100;
        return salary;
    }

    @Override
    public String toString(){
        return "Employee[id="+id+" name="+firstName+lastName+" salary="+salary+"]";
    }
}