//Christina Duran
//1323418
//cmps012b/m
//8/6/14
//List ADT for any kind of object
//List.java

public class List<T> implements ListInterface<T>{
  private Node head;
  private int numItems;

  List(){
    head = null;
    numItems = 0;
  }

  private class Node{
    T item;
    Node next;

    Node(T z){
      item = z;
      next = null;
    }
  }

   private Node find(int index){
      Node N = head;
      for(int i=1; i<index; i++) N = N.next;
      return N;
   }

   public boolean isEmpty(){
    return (numItems==0);
  }

   public int size(){
     return numItems;
   }

   public T get(int index) throws ListIndexOutOfBoundsException{
     if( index<1 || index>numItems){
       throw new ListIndexOutOfBoundsException("List Error: get() called on invalid index: "+index);
     }
     Node N = find(index);
     return N.item;
   }

   public void add(int index, T newItem) throws ListIndexOutOfBoundsException{
     if( index< 0 || index>(numItems)+1){
       throw new ListIndexOutOfBoundsException("List Error: add() called on invalid index: " + index);
     }
     if( index == 1){
       Node N = new Node(newItem);
       N.next = head;
       head = N;
     }else{
       Node P= find(index-1);
       Node C=P.next;
       P.next= new Node(newItem);
       P=P.next;
       P.next=C;
     }
     numItems++;
   }

  public void remove(int index) throws ListIndexOutOfBoundsException{
    if(index<1 || index>numItems){
      throw new ListIndexOutOfBoundsException("List Error: remove() called on invalid index: " + index);
    }
    if(index==1){
      Node N = head;
      head = head.next;
      N.next = null;
    }else{
      Node P = find(index-1);
      Node N = P.next;
      P.next = N.next;
      N.next = null;
    }
    numItems--;
  }

  public void removeAll(){
    head = null;
    numItems = 0;
  }

  public String toString(){
    String s = "";
    for(Node N=head; N!=null; N=N.next){
      s = s + N.item + " ";
    }
    return s;
  }
}
