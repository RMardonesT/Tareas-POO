FLAGS = -g #Allows debugging
JC = javac #Associates the compiler
JVM = java #The virtual machine call
CONF = configurationFile.txt
CSV = salida.csv
ETAPA_4 = Stage4 #Clase que contiene el main de la ETAPA_4


#It's defined what type or files we are using
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
Stage4.java \
sources4/*.java \




default: build #This compiles all the packages and the Stage#Test files


build:	$(CLASSES:.java=.class)

run:
	$(JVM) $(ETAPA_4) $(CONF)> $(CSV)



clean:
	rm *.class
	rm sources4/*.class
	rm *.csv
