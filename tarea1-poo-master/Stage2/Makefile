FLAGS = -g #Allows debugging
JC = javac #Associates the compiler
JVM = java #The virtual machine call
CONF = configurationFile.txt
CSV = salida.csv
ETAPA_2 = Stage2 #Clase que contiene el main de la ETAPA_2


#It's defined what type or files we are using
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
Stage2.java \
sources2/*.java \




default: build #This compiles all the packages and the Stage#Test files


build:	$(CLASSES:.java=.class)

run:
	$(JVM) $(ETAPA_2) $(CONF)> $(CSV)



clean:
	rm *.class
	rm sources2/*.class
	rm *.csv
