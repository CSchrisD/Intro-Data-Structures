//Christina Duran
//1323418
//cmps012b/m
//7/30/14
//Dictionary adt for c
//Dictionary.c

//-----------------------------------------------------------------------------
//   Dictionary.c
//   Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Dictionary type
Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = x;
   N->value = y;
   N->next = NULL;
   return N;
}

//freeNode()
//destructor for the Dictionary type
void freeNode(Node* pN){
   if(pN !=NULL && *pN !=NULL){
        free(*pN);
        *pN = NULL;
    }
}

//DictioanryObj
typedef struct DictionaryObj{
        Node top;
        int numItems;
  }DictionaryObj;

// public functions -----------------------------------------------------------

//newDictionary()
//constructor for the Dictonary type
Dictionary newDictionary(void){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D != NULL);
    D->top = NULL;
    D->numItems = 0;
    return D;
}

//freeDictionary()
//destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
        if(pD !=NULL && *pD!=NULL){
                if(!isEmpty(*pD)){
                        makeEmpty(*pD);
                }
                free(*pD);
                *pD = NULL;
        }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr,"Stack Error: calling isEmpty() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   if(D -> numItems>0){
        return 0;
        }
   return 1;
}

//size()
//returns number of (key,value) pairs in D
//pre:none
int size(Dictionary D){
        return D->numItems;
}

//lookup()
//returns the value v such that (k, v) is in D, or returns NULL if no such value v exists.
//pre:none
char* lookup(Dictionary D, char* k){
        Node N = D->top;
        if(D == NULL){
                fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference \n");
                exit(EXIT_FAILURE);
        }
        while(N !=NULL){
        if(strcmp(N->key,k)==0){
                return N->value;
                }
                N=N->next;
        }
    return NULL;
}

// insert()
// insert new (key, value) pair into D
// pre: lookup(D,k)==NULL
void insert(Dictionary D, char* k, char* v){
  Node N = D->top;
   if(lookup(D,k) != NULL){
        fprintf(stderr, "error: key not found\n");
        exit(EXIT_FAILURE);
        }
   if(N == NULL){
        Node G = newNode(k,v);
        G->next = NULL;
        D->top = G;
        }
        else{
        while(N->next !=NULL){
                N = N->next;
        }
   Node K = N;
   K->next = newNode(k,v);
   Node C = K->next;
   N->next = C;
   }
   D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   Node N = D->top;
   if( lookup(D,k) == NULL ){
      fprintf(stderr, "errror: key not found\n");
      exit(EXIT_FAILURE);
   }
  if(strcmp(N->key,k)==0){
        Node P = D->top;
        Node G = P->next;
        D->top = G;
        freeNode(&P);
   }
     else{
   while(N !=NULL && N->next != NULL){
        if(strcmp(N->next->key, k)==0){
        Node G = N;
        Node C = N->next;
        G->next = C->next;
        N=G;
        freeNode(&C);
        }
        N = N->next;
    }
  }
  D->numItems--;
}

// makeEmpty()
// resets D to the empty state
// pre:none
void makeEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr,"Stack Error: calling popAll() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems==0 ){
      fprintf(stderr, "Stack Error: calling popAll() on empty Stack\n");
      exit(EXIT_FAILURE);
   }
   D->numItems = 0;
   D->top= NULL;
}

// printDictioanry()
// prints a text representation of D to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if( D==NULL ){
      fprintf(stderr, "error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->top; N!=NULL; N=N->next){
         fprintf(out,"%s %s\n", N->key, N->value);
   }
  //fprintf(out, "\n");
}