#Christina Duran
#1323418
#cmps012b/m
#7/23/14
#makefile for c

FLAGS =	-std=c99 -Wall
SOURCES =	charType.c
OBJECTS =	charType.o
EXEBIN =	charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean:
	rm $(EXEBIN) $(OBJECTS)
