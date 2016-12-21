//Christina Duran
//1323418
//cmps012b/m
//7/7/14
//this program takes in a file of strings and reverses their order
// and prints them on individual lines.
//FileCopy.java
//Illustrates file IO

import java.io.*;
import java.util.Scanner;

public class FileReverse{
  public static void main(String[] args) throws IOException{
    Scanner in= null;
    PrintWriter out= null;
    String line= null;
    String[] token= null;
    int i, n, lineNumber=0;

    if(args.length<2){
      System.out.println("Usage: FileCopy infile outfile");
      System.exit(1);
    }

    in= new Scanner(new File(args[0]));
    out= new PrintWriter(new FileWriter(args[1]));

    in.useDelimiter("\n");

    while(in.hasNext()){
      line = in.next();
      token = line.split("\\s+"); //split line around white space
      n = token.length;
      for(i=0; i<n;i++){
        if(line.length() != 0){
          String A= stringReverse(token[i], token[i].length());
          out.println(A);
        }
      }
    }
    in.close();
    out.close();
  }

  //recursive function that reverses the word
  public static String stringReverse(String s, int n){
    char[] B = {s.charAt(n-1)};
    String rev="";
    String B_str=new String(B);
    //if n==0 do nothing
    if(n==1){
      return B_str;
    }
    else{
      rev=B_str+stringReverse(s,n-1);
      return rev;
    }
  }
}