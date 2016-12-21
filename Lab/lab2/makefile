#Christina Duran
#1323418
#cmps012b/m
#7/7/14
#Another makefile with macros

JAVASRC         = FileReverse.java
SOURCES         = README makefile ${JAVASRC}
MAINCLASS       = FileReverse
CLASSES         = FileReverse.class
JARFILE         = myFileReverse
JARCLASSES      = ${CLASSES}
SUBMIT          = submit cmps012b-pt.u14 lab2

all: ${JARFILE}

${JARFILE}: ${CLASSES}
        echo Main-class: ${MAINCLASS} > Manifest
        jar cvfm ${JARFILE} Manifest ${JARCLASSES}
        rm Manifest
        chmod +x ${JARFILE}

${CLASSES}: ${JAVASRC}
        javac -Xlint ${JAVASRC}

clean:
        rm ${CLASSES} ${JARFILE}

submit: ${SOURCES}
        ${SUBMIT} ${SOURCES}