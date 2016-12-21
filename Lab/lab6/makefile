#Christina Duran
#1323418
#cmps012b/m
#8/6/14
#makefile

#Variables
SOURCES = List.java ListInterface.java ListClient.java\
          ListIndexOutOfBoundsException.java
MAINCLASS       = ListClient
CLASSES = List.class ListInterface.class ListClient.class List\$$Node.class\
          ListIndexOutOfBoundsException.class
JARFILE = ListClient

#Build Targets

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint:all,-serial $(SOURCES)

clean:
	rm -f $(CLASSES) $(JARFILE)
