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
       	  |-- Comuna.java		    : Archivo contenedor de clase comuna
       	  |-- Individuo.java		    : Archivo contenedor de clase individuo
       	  |-- Simulador.java		    : Archivo contenedor de clase simulador
       	  |-- Vacunatorio.java		    : Archivo contenedor de clase vacunatorio


</code></pre>

## Cómo compilar
	Para cualquier etapa, debe asegurarse de estar en la carpeta
    correspondiente. Por linea de comandos ingresar:
	>> make

## Cómo ejecutar
	Para cualquier etapa, debe asegurarse de estar en la carpeta
    correspondiente. Por linea de comandos ingresar:
	>> make run

## Cómo eliminar archivos .class y .csv
    Para cualquier etapa, debe asegurarse de estar en la carpeta
    correspondiente. Por linea de comandos ingresar:
    >> make clean


  ## Datos de simulación utilizados
    Los datos presentes en el archivo configurationFile.txt con los que se hicieron las pruebas son los siguientes

          360 100 30 40       |   <T[s]>   <N>   <I>   <I_time [s]>
          50 100              |   <width[m]>   <length[m] >
          5 1 0.4             |   <Speed[m/s]>  <∆t[s]>  <∆θ [rad]>
          2 0.5 0.5 0.3 0.1   |   <d[m]>  <M>  <p0>  <p1> <p2>
          2 10 60             |   <NumVac> <VacSize> <VacTime [s] >


## Consideraciones sobre el tiempo


El tiempo  de la simulación avanza en unidades cercanas a un
segundo hasta  completar el 80% de la duración de la simulación, luego comienza a avanzar en unidades equivalentes 5% del tiempo de total de  simulación.
