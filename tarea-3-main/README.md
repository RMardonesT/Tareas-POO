## Grupo 6 Paralelo 2

## Integrantes
     Gustavo Matamala	201730020-5
     Felipe Contreras	201621002-4
     Ricardo Mardones 	201621036-9
     Nicolas Veneros 	201621024-5
## Organizacion en carpetas


<pre><code>

  |-- Stage4				: Carpeta
      |-- Stage4.pro			: Archivo .pro de la etapa 4
      |-- config.txt			: Archivo con parámetros de configuración de la simulación
      |-- Stage4.cpp			: Archivo contenedor de clase Stage4
      |-- Comuna.cpp			: Archivo contenedor de clase Comuna
      |-- Comuna.h			: Archivo de cabecera de la clase Comuna
      |-- Pedestrian.cpp		: Archivo contenedor de clase Pedestrian
      |-- Pedestrian.h			: Archivo de cabecera de la clase Pedestrian
      |-- Simulator.cpp			: Archivo contenedor de clase Simulator
      |-- Simulator.h			: Archivo de cabecera de la clase Simulator
      |-- vacunatorio.cpp		: Archivo contenedor de clase vacunatorio
      |-- vacunatorio.h			: Archivo de cabecera de la clase vacunatorio
      |-- graph.cpp			: Archivo contenedor de clase graph
      |-- graph.h			: Archivo de cabecera de la clase graph
      |-- graph.ui			: Archivo de descripción de interfaz grafica graph
      |-- settings.cpp			: Archivo contenedor de clase settings
      |-- settings.h			: Archivo de cabecera de la clase settings
      `-- settings.ui			: Archivo de descripción de interfaz grafica settings
      

</code></pre>

## Cómo ejecutar
Se ejecuta a través de Qt creator al abrir el archivo Stage4.pro
Se debe agregar en la pestaña project el path al archivo config.txt

## Datos de simulación utilizados
    Los datos presentes en el archivo configurationFile.txt con los que se hicieron las pruebas son los siguientes

          1000 600 5          |   <N>   <I>   <I_time [s]>
          800 600	      |   <width[m]>   <length[m] >
          1.4 0.2 0.4         |   <Speed[m/s]>  <∆t[s]>  <∆θ [rad]>
          2 0.5 0.8 0.5 0.3   |   <d[m]>  <M>  <p0>  <p1> <p2>
          5 500 5             |   <NumVac> <VacSize> <VacTime [s] >


## Consideraciones
No se opta por ninguna de las actividades extra.


