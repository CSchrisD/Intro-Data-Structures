//Christina Duran
//1323418
//cmps012b/m
//7/23/14
//finds number of certain characters and puts them in appropriate arrays with the same characters
//charType.c

#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0, k=0,l=0,m=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
        if(isalpha(s[i])){
          a[m++]=s[i];
        }
     else if( isdigit(s[i])){
         d[j++] = s[i];
        }
     else if( ispunct(s[i]) ){
        p[k++]=s[i];
        }
     else  if(isspace(s[i])){
        w[l++]=s[i];
        }
      i++;
   }
   a[m] = '\0';
   d[j] = '\0';
   p[k] = '\0';
   w[l] = '\0';
}

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* aNum;      // string holding alphabetic
   char* dNum;      // string holding digets
   char* pNum;      // string holding punctuation
   char* wNum;      // string holding white space
   int lineNum=1;   // number of in file lines

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( ( in = fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   aNum = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   dNum = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   pNum = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   wNum = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   assert( line!=NULL && aNum !=NULL && dNum !=NULL && pNum!=NULL && wNum!=NULL );

   // read each line in input file, extract alpha-numeric characters 
    while( fgets(line, MAX_STRING_LENGTH+1, in) != NULL ){
      extract_chars(line,aNum,dNum,pNum,wNum);
        int a=strlen(aNum);
        int b=strlen(dNum);
        int c=strlen(pNum);
        int d=strlen(wNum);

      fprintf(out, "\nline %d contains:\n",lineNum);
      fprintf(out, "%d alphabetic characters: %s\n",a,aNum);
      fprintf(out, "%d numeric characters: %s\n",b,dNum);
      fprintf(out, "%d punctuation characters: %s\n",c,pNum);
      fprintf(out, "%d whitespace characters: %s\n",d,wNum);
      lineNum++;
   }

   // free heap memory 
   free(line);
   free(aNum);
   free(dNum);
   free(pNum);
   free(wNum);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}