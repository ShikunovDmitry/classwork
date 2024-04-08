package com.itacademy.aqa.maven;


import com.epam.tat.module4.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class AppTest
{
    @Test
    public void calculatorTest(){
        Calculator calculator = new Calculator();
    }
    @Test
    public void exceptionInteruptionTest() throws InterruptedException {
        Thread.UncaughtExceptionHandler h = (th, ex) -> Assert.assertTrue(ex instanceof IndexOutOfBoundsException);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    System.out.println("Sleep for 10 seconds");
                }catch (InterruptedException ex){
                    throw new ArithmeticException("Sleep Exception");
                }
            }
        });
        thread.setUncaughtExceptionHandler(h);
        thread.start();
        thread.interrupt();
        thread.join();
    }

}
