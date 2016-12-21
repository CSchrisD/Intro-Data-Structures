//Christina Duran
//1323418
//cmps012b/m
//8/13/14
//Translated Binary tree ADT from C into java. creates new Dictionary functions.
//Dictionary.java

//Dictionary.java
public class Dictionary implements DictionaryInterface{
  private Node root;
  private int numItems;

  //Dictionary
  //default constructor for the Dictionary class
  public Dictionary(){
    root = null;
    numItems = 0;
  }

  private class Node{
    String item;
    String valItem;
    Node left;
    Node right;

    Node(String x, String y){
      item = x;
      valItem = y;
      left = null;
      right = null;
    }
  }
  // findKey()
// returns the Node containing key k in the subtree rooted at R, or returns
// NULL if no such Node exists
  Node findKey(Node R, String k){
    if( R==null || k.compareTo(R.item)==0)
      return R;
    if(k.compareTo(R.item)<0 )
      return findKey(R.left, k);
    else // strcmp(k, R->key)>0
      return findKey(R.right, k);
  }


// findParent()
// returns the parent of N in the subtree rooted at R, or returns NULL 
// if N is equal to R. (pre: R != NULL)
  Node findParent(Node N, Node R){
    Node P=null;
    if( N!=R ){
      P = R;
      while( P.left!=N && P.right!=N ){
        if(N.item.compareTo(P.item)<0)
          P = P.left;
        else
          P = P.right;
      }
    }
    return P;
  }

// findLeftmost()
// returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
  Node findLeftmost(Node R){
    Node L = R;
    if( L!=null ) {
      for( ; L.left!=null; L=L.left) ;
    }
    return L;
  }

  // printInOrder()
  // prints the (key, value) pairs belonging to the subtree rooted at R in order
  // of increasing keys to file pointed to by out.
  void printInOrder(Node R){
    if( R!=null ){
      printInOrder(R.left);
      System.out.println(R.item+" " + R.valItem);
      printInOrder(R.right);
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
    Node N = findKey(root, key);
    return(N==null ? null : N.valItem);
  }

  //insert
  //pre: does not have duplicate keys
  //post: !isEmpty(), item is added to desired location
  public void insert(String key, String value) throws KeyCollisionException{

    if(findKey(root, key) != null){
      throw new KeyCollisionException("cannot insert duplicate key");
    }

    Node N = new Node(key, value);
    Node B = null;
    Node A = root;

    while( A != null){
      B = A;
      if(key.compareTo(A.item)<0) A = A.left;
      else A = A.right;
    }

    if( B==null ){
      root = N;
    }else if(key.compareTo(B.item)<0){
      B.left = N;
    }
    else{
      B.right = N;
    }
    numItems++;
  }

  //delete
  //pre:!isEmpty
  //Post:remove the key and value at key
  public void delete(String key) throws KeyNotFoundException{
    Node N = findKey(root, key);
    Node P;
    Node S;

    if( lookup(key) == null){
      throw new KeyNotFoundException("cannot delete non-existant key");
    }

    if(N.left == null && N.right == null){
      if(N==root){
        root=null;
      }else{
        P= findParent(N, root);
        if(P.right==N) P.right = null;
        else P.left = null;
      }
    }else if(N.right==null){
      if(N==root){
        root = N.left;
      }else{
        P = findParent(N, root);
        if(P.right==N) P.right = N.right;
        else P.left = N.right;
      }
    }else if( N.left==null){
      if(N == root){
        root = N.right;
      }else{
        P = findParent(N, root);
        if(P.right==N) P.right = N.right;
        else P.left = N.right;
      }
    }else{
      S = findLeftmost( N.right );
      N.item = S.item;
      N.valItem = S.valItem;
      P = findParent(S, N);
      if(P.right==S) P.right = S.right;
      else P.left = S.right;
    }
   numItems--;
  }

  //makeEmpty
  //pre:!isEmpty
  //post: remove everything in list
  public void makeEmpty(){
    root=null;
    numItems=0;
  }

  //toString
  //pre: none
  //post: print the key and value
  public String toString(){
    Node N = root;
    StringBuffer sb = new StringBuffer();
    printInOrder(N);
    return new String(sb);
  }
}