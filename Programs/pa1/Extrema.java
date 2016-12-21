//Christina Duran
//1323418
//cmps012b
//6/25/14
//this program finds and prints the maximum and minimum of an array recursively.
//Extrema.java

class Extrema{
  //maxArray
  //returns largest value in int array A
  static int maxArray(int[] A, int p, int r){
    int q;
    int max=A[p];

    if(p<r){
      q=(p+r)/2;
      int m1= maxArray(A, p, q);
      int m2= maxArray(A, q+1, r);
      if(m1>m2){
        max=m1;
      }else{
        max= m2;
      }
    }

    return max;
  }

  //minArray
  //returns the smallest value in int array A
  static int minArray(int[] A, int p, int r){
    int q;
    int min=A[p];

    if(p<r){
      q=(p+r)/2;
      int m1= minArray(A, p, q);
      int m2= minArray(A, q+1, r);
      if(m1<m2){
        min=m1;
      }else{
        min=m2;
      }
    }
    
    return min;
  }

  //main()
  public static void main(String[] args){
    int[] B= {-1,2,6,3,9,2,-3,-2,11,5,7};
    System.out.println("max= " + maxArray(B,0,B.length-1));//output max is 11
    System.out.println("min= " + minArray(B,0,B.length-1));//output max is -3
  }
}