public class RunCircle {
    public static void main(String[] args){
        Circle c1 = new Circle();
        System.out.println("Cau 1");
        System.out.println("The circle has radius of " + c1.getRadius() + " and area of "+ c1.getArea());

        Circle c2 = new Circle(2.0);
        System.out.println("Cau 2");
        System.out.println("The circle has radius of "+c2.getRadius()+ " and area of "+ c2.getArea());

        Circle c3 = new Circle(3.0, "yelow");
        System.out.println("Cau 3");
        System.out.println("The circle has radius "+c3.getRadius() + " and the color has value "+c3.getColor());

        Circle c4 = new Circle();
        System.out.println("Cau 4");
        c4.setRadius(5.5);
        System.out.println("radius is "+c4.getRadius());
        c4.setColor("Purple");
        System.out.println("Color is "+c4.getColor());

        Circle c5 = new Circle(6.6);
        System.out.println("Cau 5");
        System.out.println(c5.toString());
        System.out.println(c5);
        System.out.println("Operator '+' invokes toString() too"+c5);

    }
}
