package lesson_3;

import static java.lang.Thread.sleep;

public class PingPong {

    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        pingPong.start();
    }

    private void start(){
        Thread ping = new Thread(new Word(), "ping");
        ping.start();
        Thread pong = new Thread(new Word(), "pong");
        pong.start();
    }

    protected class Word implements Runnable{

        public void run() {
            while (true){
                try {
                    printWord();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected synchronized void printWord() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        sleep(300);
        notify();
        wait();
    }

}
