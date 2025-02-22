public class Rectangle{
    private float width;
    private float length;

    public Rectangle(){
        width = 1.0f;
        length = 1.0f;
    }

    public Rectangle(float l, float w){
        width = w;
        length = l;
    }

    public float getLength(){
        return length;
    }

    public float getWidth() {
        return width;
    }

    public void setLength(float le){
        length=le;
    }

    public void setWidth(float wi){
        width=wi;
    }

    public float getArea(){
        return width*length;
    }

    public float getPerimeter(){
        return (width+length)*2;
    }

    @Override
    public String toString(){
        return "Rectangle[length="+length+",width="+width+"]";
    }
}