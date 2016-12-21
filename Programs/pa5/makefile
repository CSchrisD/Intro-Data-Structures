#Christina Duran
#1323418
#cmps012b/m
#8/11/14
#makefile

FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c
OBJECTS = Dictionary.o DictionaryClient.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
        gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
        gcc -c $(FLAGS) $(SOURCES)

clean:
        rm -f $(EXEBIN) $(OBJECTS)

check:
        valgrind --leak-check=full $(EXEBIN)