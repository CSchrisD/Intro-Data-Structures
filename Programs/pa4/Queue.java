//Christina Duran
//1323418
//cmps012b/m
//7/30/14
//Queue ADT for test and simulation
//Queue.java

import java.io.*;
import java.util.Scanner;

public class Queue implements QueueInterface{
  private Node head;
  private int time;
  private int numPeople;
  private Job item;
  private Node tail;

  public Queue(){
    head=null;
    tail=null;
    time=0;
  }

  private class Node{
    Job arriveTime;
    Node next;

    Node(Job x){
      arriveTime=x;
      next=null;
    }
  }

  public boolean isEmpty(){
    return (numPeople==0);
  }

  public int length(){
    return numPeople;
  }

  public void enqueue(Job a){
    Node T = tail;
    Node N = head;

    if(N == null){
      Node G = new Node(a);
      G.next = head;
      head = G;
      tail = head;
    }
    else if(T !=null){
      Node C= new Node(a);
      tail.next=C;
      tail=C;
    }
    numPeople++;
  }

  public Object dequeue() throws QueueEmptyException{
    Node G= head;
    Node N=head;
    if(isEmpty()){
      throw new QueueEmptyException("cannot dequeue non existant item t front of queue");
    }
    else{
      N=head.next;
      head=N;
    }
    numPeople--;
    return G.arriveTime;
  }

  public Object peek() throws QueueEmptyException{
    Node N = head;
    if(N==null){
      throw new QueueEmptyException("cannot see non existant item from front of queue");
    }else{
      return N.arriveTime;
    }
  }

  public void dequeueAll() throws QueueEmptyException{
    if(isEmpty()){
      throw new QueueEmptyException("cannot dequeue empty queue");
    }
    else{
      head=null;
      tail=null;
      numPeople=0;
    }
  }

  public String toString(){
    StringBuffer sb = new StringBuffer();
    for(Node N= head; N != null; N=N.next){
      sb.append(N.arriveTime);
    }
    return new String(sb);
  }
}