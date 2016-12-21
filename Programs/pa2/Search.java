//Christina Duran
//1323418
//cmps012b/m
//7/9/14
//sorts a file, and searches if an inputed argument is in the file
//Search.java
import java.util.Scanner;
import java.io.*;

class Search{
  //main method
  public static void main(String[] args) throws IOException{
    Scanner in = new Scanner(new File(args[0]));
    int lineCount=0;
    in.useDelimiter("\n");
    while(in.hasNext()){
      lineCount++;
      in.next();
    }
    in.close();

    Scanner inNext = new Scanner(new File(args[0]));
    String[] word= new String[lineCount];
    int[] lineNum = new int[lineCount];
    inNext.useDelimiter("\n");
    //put file words into a String array and line numbers
    for(int i=0;i<lineCount;i++){
      word[i]=inNext.next();
      lineNum[i]=i+1;
    }

    inNext.close();
    mergeSort(word,lineNum,0,word.length-1);//alphabatise, and change corresponding line numbers

    int j = 1;
    int n = args.length;
    String token="";
    //print if the argument is in the file or not
    while(j<n){
      token=args[j];
      System.out.println(wordSearch(word,lineNum,0,word.length-1,token));
      j++;
    }
  }

  //looks through array recursively for target word
  public static String wordSearch(String[] A, int[] lineNumber, int p, int r, String target){
    int q;
    if(p<=r){
      q=(p+r)/2;
      if(target.compareTo(A[q])==0){
        return target+ " found on line "+ lineNumber[q];
      }
      else if(target.compareTo(A[q])<0){
        return wordSearch(A,lineNumber,p,q-1,target);
      }
      else{
        return wordSearch(A,lineNumber, q+1, r, target);
      }
    }
    else{
      return "not found";
    }
  }

  //recursivly alphebatize words in file array
  public static void mergeSort(String[] word,int[] lineNumber, int p, int r){
    int q;
    if(p<r){
      q=(p+r)/2;
      mergeSort(word,lineNumber, p, q);
      mergeSort(word,lineNumber,q+1, r);
      merge(word,lineNumber, p, q, r);
    }
  }

  public static void merge(String[] word,int[] lineNumber, int p, int q, int r){
    int n1= q-p+1;
    int n2= r-q;
    String[] L=new String[n1];
    String[] R= new String[n2];
    int[] Lint= new int[n1];
    int[] Rint= new int[n2];

    int i,j,k;
    for(i=0; i<n1; i++){
      L[i]=word[p+i];
      Lint[i]=lineNumber[p+i];
      }
    for(j=0; j<n2; j++){
      R[j]= word[q+j+1];
      Rint[j]=lineNumber[q+j+1];
    }
    i=0;
    j=0;
    for(k=p; k<=r; k++){
      if(i<n1 && j<n2){
        if(L[i].compareTo(R[j])<0){
          word[k]=L[i];
          lineNumber[k]=Lint[i];
          i++;
        }
        else{
          word[k]=R[j];
          lineNumber[k]=Rint[j];
          j++;
        }
      }
      else if(i<n1){
        word[k]=L[i];
        lineNumber[k]=Lint[i];
        i++;
      }
      else{
        word[k]=R[j];
        lineNumber[k]=Rint[j];
        j++;
      }
    }
  }
}