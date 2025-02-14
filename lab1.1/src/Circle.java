public class Circle {
    private double radius;
    private String color;

    public Circle(){
        radius = 1.0;
        color = "red";
    }

    public Circle(double r){
        this.radius = r;
        color = "blue";
    }

    public Circle(double r, String c){
        radius = r;
        color = c;
    }

    public double getRadius(){
        return radius;
    }

    public double getArea(){
        return radius*radius*Math.PI;
    }

    public String getColor(){return color;}

    public void setRadius(double newR){
        this.radius = newR;
    }

    public void setColor(String newC){
        color = newC;
    }

    public String toString(){
        return "Circle[radius="+radius+" color="+color+"]";
    }
}