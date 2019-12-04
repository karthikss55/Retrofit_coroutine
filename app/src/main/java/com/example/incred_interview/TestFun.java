package com.example.incred_interview;


public class TestFun {

   static class Check{
        public static void printData () {
            System.out.println("inner static");
        }
    }

}

class runFun{
    public static void main(String[] args) {
        TestFun.Check.printData();
    }
}
