package test;

public class TCPWorker implements Runnable{
    String out;
    public TCPWorker(String s) {
        this.out=s;
    }

    public void run() {
        System.out.println(out);
    }
}
