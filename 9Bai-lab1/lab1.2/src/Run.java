public class Run {
    public static void main(String[] args){
        AnotherCircle an1 = new AnotherCircle();
        System.out.println("Cau 1");
        System.out.println(an1);
        System.out.println("================================");

        AnotherCircle an2 = new AnotherCircle(2.2);
        System.out.println("Cau 2");
        System.out.println("Với radius "+an2.toString()+" thì diện tích là "+an2.getArea()+" và chu vi là "+an2.getCircumference());
        System.out.println("================================");

        AnotherCircle an3 = new AnotherCircle();
        System.out.println("Cau 3");
        System.out.println("Xài toString: "+an3.toString());
        System.out.println("================================");
    }
}
