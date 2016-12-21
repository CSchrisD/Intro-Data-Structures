//Christina Duran
//1323418
//cmps012b/m
//8/6/14
//test file for List.java commands
//ListTest.java

class ListTest{
  public static void main(String[] args){
    List<String> A = new List<String>();
    System.out.println(A.isEmpty());
    System.out.println(A.size());

    A.add(1, "one");
    A.add(2, "two");
    A.add(3, "three");

    System.out.println(A.isEmpty());
    System.out.println(A.size());
    System.out.println(A);
    System.out.println(A.get(1));

    A.remove(1);
    A.remove(2);

    System.out.println(A.isEmpty());
    System.out.println(A.size());
    System.out.println(A);

    A.removeAll();

    System.out.println(A.isEmpty());
    System.out.println(A.size());
    System.out.println(A);
  }
}
