//Dictionary.java
public class Dictionary implements DictionaryInterface{
  private Node head;
  private int numItems;

  //Dictionary
  //default constructor for the Dictionary class
  public Dictionary(){
    head= null;
    numItems = 0;
  }

  private class Node{
    String item;
    String valItem;
    Node next;

    Node(String x, String y){
      item=x;
      valItem=y;
      next=null;
    }
  }

  //isEmpty
  //pre: none
  //post: returns true if this Dictionary is empty, false otherwise
  public boolean isEmpty(){
    return (numItems == 0);
  }

  //size
  //pre:none
  //post: returns the number of elements in this Dictionary
  public int size(){
    return numItems;
  }

  //lookUp
  //pre: none
  //post:return if the desired item is in the dictionary
  public String lookup(String key){
    for(Node N=head; N != null; N=N.next){
      if(key == N.item){
        return N.valItem;
      }
          }
    return null;
  }

  //insert
  //pre:does not have duplicate keys
  //post: !isEmpty(), item is added to desired location
  public void insert(String key, String value) throws KeyCollisionException{
    Node N = head;
    if(lookup(key) != null){
      throw new KeyCollisionException("cannot insert duplicate keys");
    }
    if(N == null){
      Node G = new Node(key, value);
      G.next= head;
      head= G;
    }else{
      while(key.compareTo(N.item)>0 && N.next !=null){
        N=N.next;
      }
      Node P=N;
      Node C=P.next;
      P.next= new Node(key, value);
      P=P.next;
      P.next=C;
    }
    numItems++;
  }

//pre:!isEmpty
//post:get rid of the element at key
  public void delete(String key) throws KeyNotFoundException{
    Node N = head;
    if(lookup(key) == null){
      throw new KeyNotFoundException("cannot delete non-existent key");
    }
    if(key.compareTo(N.item)==0){
      Node P = head;
      head = head.next;
      P.next=head;
      numItems--;
    }else{
      while(N !=null && N.next != null){
        if(key.compareTo(N.next.item)==0){
          Node G = N;
          Node C = G.next;
          G.next=C.next;
          N=G;
        }
        N=N.next;
      }
      numItems--;
    }
  }

//everything that exists is gotten rid of
  public void makeEmpty(){
    head=null;
    numItems=0;
  }

  //print key and value
  public String toString(){
    int i;
    StringBuffer sb = new StringBuffer();
    for(Node N= head; N != null; N=N.next){
      sb.append(N.item).append(" ").append(N.valItem).append("\n");
    }
    return new String(sb);
  }
}