FLAGS = -g #Allows debugging
JC = javac #Associates the compiler
JVM = java #The virtual machine call
CONF = configurationFile.txt
CSV = salida.csv
ETAPA_1 = Stage1 #Clase que contiene el main de la ETAPA_1


#It's defined what type or files we are using
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
Stage1.java \
sources1/*.java \




default: build #This compiles all the packages and the Stage#Test files


build:	$(CLASSES:.java=.class)

run:
	$(JVM) $(ETAPA_1) $(CONF)> $(CSV)



clean:
	rm *.class
	rm sources1/*.class
	rm *.csv
