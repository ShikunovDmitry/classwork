package com.itacademy.aqa.maven;

public class SecondThread implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("Sleep for 10 seconds");
        }catch (InterruptedException ex){
            throw new ArithmeticException("Sleep Exception");
        }
    }
}
