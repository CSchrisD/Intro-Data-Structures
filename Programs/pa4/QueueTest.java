import java.io.*;
import java.util.Scanner;

class  QueueTest{
  public static void main(String[] args){
    Queue A = new Queue();
    Queue B = new Queue();

    System.out.println(B.isEmpty());
    System.out.println(B.length());
    //System.out.println(B.peek());
    Job C = new Job(1,2);
    Job D = new Job(1,3);
    Job E = new Job(1,4);

    A.enqueue(C);

    System.out.println(A.isEmpty());
    System.out.println(A.length());
    System.out.println(A.peek());
    System.out.println(A);

    A.enqueue(D);
    System.out.println(A.isEmpty());
    System.out.println(A.length());
    System.out.println(A);

    A.enqueue(E);
    A.enqueue(E);
    System.out.println(A.isEmpty());
    System.out.println(A.length());
    System.out.println(A);

    A.dequeue();
    System.out.println(A.length());
    System.out.println(A.peek());
    System.out.println(A);

    A.dequeueAll();
    System.out.println(A);

    A.enqueue(C);
    A.enqueue(D);
    A.enqueue(E);
    System.out.println(A);
  }
}