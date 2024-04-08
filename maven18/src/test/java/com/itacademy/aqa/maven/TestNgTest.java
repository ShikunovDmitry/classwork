package com.itacademy.aqa.maven;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;


public class TestNgTest {
    @Test
    public void divTest() {
        double a = 3;
        double b = 0;
        Calculator calculator = new Calculator();

        calculator.div(a, b);
    }

    @Test
    public void exceptionTest(){
        Calculator calculator = new Calculator();
        Assert.assertThrows(NumberFormatException.class,()-> calculator.div(1, 0));

    }

    @Test(expectedExceptions = NumberFormatException.class,expectedExceptionsMessageRegExp = ".*Attempt to divide by zero.*")
    public void exception2Test(){
        Calculator calculator = new Calculator();

        calculator.div(1, 0);

    }

    @Test
    public void exceptionInteruptionTest() throws InterruptedException {
        Thread.UncaughtExceptionHandler h = (th, ex) -> Assert.assertTrue(ex instanceof ArithmeticException);
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
    @Test(expectedExceptions = ArithmeticException.class)
    public void throwTest(){
        SecondThread secondThread = new SecondThread();
        secondThread.run();
    }
}
