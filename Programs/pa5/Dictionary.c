//Christina Duran
//1323418
//cmps012b/m
//8/12/14
//Dictionary ADT for c
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
  #define MAX_LEN 180
  const int tableSize = 101;

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

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
   unsigned int result = 0xBAE86554;
   while (*input) {
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

// public functions -----------------------------------------------------------

//DictioanryObj
typedef struct DictionaryObj{
  Node* top;
  int totalItems;
}DictionaryObj;

//newDictionary() 
//constructor for the Dictonary type
Dictionary newDictionary(void){
  Dictionary D = malloc(sizeof(DictionaryObj));
  assert(D != NULL);
  D->totalItems = 0;
  D->top = calloc(tableSize,sizeof(NodeObj));

  for(int i=0; i<tableSize; i++){
    D->top[i] = NULL;
  }
  return D;
}

//freeDictionary()
//destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
  if(pD != NULL && *pD!=NULL){
     if(isEmpty(*pD)==0){
       makeEmpty(*pD);
    }
    free(*pD);
    *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
    int isEmpty(Dictionary D){
    if(D -> totalItems>0){
      return 0;
    }
    return 1;
  }

//size() 
//returns number of (key,value) pairs in D
//pre:none
  int size(Dictionary D){
    return D->totalItems;
  }

//lookup()
//returns the value v such that (k, v) is in D, or returns NULL if no such value v exists.
//pre:none
  char* lookup(Dictionary D, char* k){
    Node A = D->top[hash(k)];
    while(A != NULL){
      if(strcmp(A->key,k)==0){
        return A->value;
      }
      A=A->next;
    }
    return NULL;
  }

// insert()
// insert new (key, value) pair into D
// pre: lookup(D,k)==NULL
  void insert(Dictionary D, char* k, char* v){
    Node A = D->top[hash(k)];
    if(lookup(D,k) != NULL){
      fprintf(stderr, "error: key not found\n");
      exit(EXIT_FAILURE);
    }
    if(A == NULL){
      Node G = newNode(k,v);
      G->next = NULL;
      D->top[hash(k)] = G;
    }
    else{
      Node K = newNode(k,v);
      K->next = A;
    }
    D->totalItems++;
  }

// delete() 
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
  void delete(Dictionary D, char* k){
    Node A = D->top[hash(k)];
    if( lookup(D,k) == NULL ){
      fprintf(stderr, "error: key not found\n");
      exit(EXIT_FAILURE);
    }
    if(strcmp(A->key,k)==0){
      Node P = A;
      Node G = P->next;
      D->top[hash(k)] = G;
      freeNode(&P);
    }else{
      while(A != NULL && A->next != NULL){
        if(strcmp(A->next->key, k)==0){
          Node G = A;
          Node C = A->next;
          G->next = C->next;
          A=G;
          freeNode(&C);
        }
        A = A->next;
      }
    }
    D->totalItems--;
  }

// makeEmpty()   
// resets D to the empty state
// pre:none
  void makeEmpty(Dictionary D){
    for(int i = 0; i<tableSize; i++){
    Node N =  D->top[i];
    Node P = D->top[i];
    while(N != NULL){
      if(N->next == NULL){
        N = NULL;
        freeNode(&N);
        N = P;
        }
        N = N->next;
    }
   freeNode(&P);
  }
    D->totalItems = 0;
}

// printDictioanry()
// prints a text representation of D to the file pointed to by out
// pre: none
  void printDictionary(FILE* out, Dictionary D){
    for(int i=0; i<tableSize;i++){
    Node A = D->top[i];
      while(A != NULL){
      fprintf(out,"%s %s\n", A->key, A->value);
      A = A->next;
      }
    }
  }