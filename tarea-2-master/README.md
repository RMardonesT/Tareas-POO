## Grupo 6 Paralelo 2

## Integrantes
     Tamara Carrera	201621010-5
     Felipe Contreras	201621002-4
     Ricardo Mardones 	201621036-9
     Nicolas Veneros 	201621024-5
## Organizacion en carpetas


<pre><code>

  |-- Stage4                                : Carpeta
      |-- Makefile                          : Archivo makefile de la etapa actual
      |-- configurationFile.txt             : Archivo con parámetros de configuración de la simulación
      |-- Stage4.java                       : Archivo contenedor de clase main de la ultima etapa
      |-- sources4                          : Carpeta contenedora de clases (package importado al main)
       	  |-- Comuna.java		             : Archivo contenedor de clase comuna
             |-- ComunaView.java                 : Archivo contenedor de clase comunaView
       	  |-- Pedestrian.java		         : Archivo contenedor de clase pedestrian
             |-- PedestrianView.java	         : Archivo contenedor de clase pedestrianView
             |-- settings.fxml                   : Archivo contenedor de configuraciones interfaz
             |-- settingsControl.java            : Archivo contenedor de la clase controlador de la interfaz
       	  |-- Simulator.java		          : Archivo contenedor de clase Simulator
             |-- SimulatorConfig.java		    : Archivo contenedor de clase simulatorConfig
             |-- SimulatorMenuBar.java           : Archivo contenedor de clase simulatorMenuBar
     	    |-- Vacunatorio.java		        : Archivo contenedor de clase vacunatorio
             |-- VacView.java		            : Archivo contenedor de clase vacView
             |-- oh_no_short.wav            : Archivo contenedor del sonido de contagio
             |-- virus_update.wav           : Archivo contenedor del sonido de actualización


</code></pre>

## Cómo compilar
	Para cualquier etapa, debe asegurarse de estar en la carpeta
    correspondiente. Por linea de comandos ingresar:
	>> make

## Cómo ejecutar
	Para cualquier etapa, debe asegurarse de estar en la carpeta
    correspondiente. Por linea de comandos ingresar:
	>> make run

## Cómo eliminar archivos .class
    Para cualquier etapa, debe asegurarse de estar en la carpeta
    correspondiente. Por linea de comandos ingresar:
    >> make clean


## Datos de simulación utilizados
    Los datos presentes en el archivo configurationFile.txt con los que se hicieron las pruebas son los siguientes

          1 0 5               |   <N>   <I>   <I_time [s]>
          1000 1000           |   <width[m]>   <length[m] >
          1.4 0.2 0.4         |   <Speed[m/s]>  <∆t[s]>  <∆θ [rad]>
          2 0.5 0.3 0.2 0.1   |   <d[m]>  <M>  <p0>  <p1> <p2>
          2 10 100            |   <NumVac> <VacSize> <VacTime [s] >


## Consideraciones
Se calibraron los parametros que presentan unidades de tiempo en segundo por factores de 10, valor obtenido mediante pruebas en simulación, con el fin de dejarlo en tiempo real.

Los infectados pasan de rojo a rosado para resaltar el cambio de color ya que con otras tonalidades de rojo no se notaba un cambio.

El tamaño de pedestrianView se modificó, agrandandolo a 20, para apreciar de forma clara el cambio entre las figuras de los estados presentes.

Se considera que para aplicar los cambios de la ventana settings se debe presionar el botón restart, que utiliza un método que reinicia la simulación, al igual que el start.

Se utilizó SceneBuilder para realizar la ventana de Settings donde los parámetros son modificados.

Optamos por el extra número 1.
