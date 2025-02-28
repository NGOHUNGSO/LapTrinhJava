public class OddThread extends Thread {
    @Override
    public void run(){
        for(int i=0; i<10; i++){
            if(i%2 != 0){
                System.out.println("Số lẻ: "+i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                }
            }
        }
    }
    }