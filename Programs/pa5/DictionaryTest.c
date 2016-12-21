//Christina Duran
//1323418
//cmps012b/m
//8/12/14
//test file for dictionary
//DictionaryTest.c

  #include<stdio.h>
  #include<stdlib.h>
  #include<string.h>
  #include"Dictionary.h"
  #define MAX_LEN 180

  int main(int argc, char* argv[]){
  Dictionary A = newDictionary();
  char* k;
  char* v;
  char* word1[] = {"one", "two", "three", "four", "five"};
  char* word2[] = {"foo", "bar", "blah", "galuph", "happy"};
  int i;

  insert(A,"six","gum");
  printDictionary(stdout,A);
  printf("%s\n",(isEmpty(A)?"true":"false"));
  printf("%d\n",size(A));
  insert(A,"seven","word");
  insert(A,"eight","car");
  printf("value = %s\n",lookup(A, "seven"));
  printDictionary(stdout,A);
  delete(A,"six");
  printDictionary(stdout,A);
  makeEmpty(A);

  for(i=0; i<5; i++){
    insert(A,word1[i],word2[i]);
  }

  //insert(A,"five", "goo"); // key collision error

  printDictionary(stdout,A);
  printf("%s\n",(isEmpty(A)?"true":"false"));
  printf("%d\n",size(A));

  for(i=0; i<5; i++){
    k=word1[i];
    v=lookup(A,k);
    printf("key=\"%s\" %s\"%s\"\n",k,(v==NULL?"not found ":"value="),v);
  }

  delete(A,"one");
  delete(A,"three");
  delete(A,"five");

  printDictionary(stdout, A);

  for(i=0; i<5; i++){
    k = word1[i];
    v = lookup(A, k);
    printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
  }

  printf("%s\n", (isEmpty(A)?"true":"false"));
  printf("%d\n", size(A));
  makeEmpty(A);
  printf("%s\n", (isEmpty(A)?"true":"false"));

  freeDictionary(&A);
  return(EXIT_SUCCESS);
}