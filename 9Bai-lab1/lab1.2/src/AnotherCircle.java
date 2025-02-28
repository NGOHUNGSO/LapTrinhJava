public class AnotherCircle{
    private double radius;

    public AnotherCircle(){
            radius = 1.1;
    }

    public AnotherCircle(double ar){
            radius = ar;
    }

    public double getRadius(){
            return radius;
    }

    public void setRadius(double newAN){
            radius = newAN;
    }

    public double getArea(){
            return radius*radius*Math.PI;
    }

    public double getCircumference(){
            return 2*radius*Math.PI;
    }

    @Override
    public String toString(){
            return "Circle[radius = "+radius+"]";
    }

        }