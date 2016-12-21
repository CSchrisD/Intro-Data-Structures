#Christina Duran
#1323418
#cmps012b
#A makefile for Extrema.java
#A makefile with macros
JAVASRC    = Extrema.java
SOURCES    = README makefile ${JAVASRC}
MAINCLASS  = Extrema
CLASSES    = Extrema.class
JARFILE    = Extrema
JARCLASSES = ${CLASSES}
SUBMIT     = submit cmps012b-pt.u14 pa1

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