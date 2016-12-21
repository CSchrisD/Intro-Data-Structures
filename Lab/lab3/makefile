#Christina Duran
#1323418
#cmps012b/m
#7/16/14
#makefile for FileReverse.c with Macros

FLAGS = -std=c99 -Wall
SOURCES = FileReverse.c
OBJECTS = FileReverse.o
EXEBIN = FileReverse

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
        gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
        gcc -c $(FLAGS) $(SOURCES)

clean :
        rm -f $(EXEBIN) $(OBJECTS)