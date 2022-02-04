FLAGS = -g #Allows debugging
JC = javac #Associates the compiler
JVM = java #The virtual machine call
CONF = configurationFile.txt
CSV = salida.csv
ETAPA_3 = Stage3 #Clase que contiene el main de la ETAPA_3


#It's defined what type or files we are using
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
Stage3.java \
sources3/*.java \




default: build #This compiles all the packages and the Stage#Test files


build:	$(CLASSES:.java=.class)

run:
	$(JVM) $(ETAPA_3) $(CONF)> $(CSV)



clean:
	rm *.class
	rm sources3/*.class
	rm *.csv
