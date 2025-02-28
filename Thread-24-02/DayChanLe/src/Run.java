public class Run {
    public static void main(String[] args) {
        OddThread ot = new OddThread();
        EvenThread et = new EvenThread();
        Thread t1 = new Thread(et);
        Thread t2 = new Thread(ot);
        t2.start();
        t1.start();
    }
}
