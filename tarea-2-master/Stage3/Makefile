FLAGS = -g #Allows debugging
JAVAFXPATH = --module-path "C:\Program Files\Java\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml,javafx.media
JC = javac  #Associates the compiler
JVM = java  #The virtual machine call
CONF = configurationFile.txt
ETAPA_3 = Stage3 #Clase que contiene el main de la ETAPA_3


#It's defined what type or files we are using
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JAVAFXPATH) $(JFLAGS) $*.java
CLASSES = \
Stage3.java \
src/*.java \



default: build #This compiles all the packages and the Stage#Test files


build:	$(CLASSES:.java=.class)

run:
	$(JVM) $(JAVAFXPATH) $(ETAPA_3) $(CONF)



clean:
	rm *.class
	rm src/*.class
	