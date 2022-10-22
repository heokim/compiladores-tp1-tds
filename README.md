# compiladores-tp1-tds
Trabajo Practico de Compiladores, implementación de un TDS
</br>
Tema: Dado una lista de precios, se pide obtener el valor correspondiente al precio promedio y la cantidad de precios que se tomaron en consideración (elementos en la lista).
</br>
Inicialmente planteamos que la lista de números está separado por comas ( , ) y que los precios son números enteros.</br>
</br>
Ejemplo de entrada de la lista de precios valida:</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10000 , 40000 , 5000</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Serian 3 precios con un promedio de 18333</br>
</br>
Empezamos el análisis de como vamos a leer la estructura de lista de precios con su separador la coma ( , ).</br>
</br>
Tenemos un BNF inicial para leer lista de precios:</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LISTA -> PRECIO , LISTA</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LISTA -> PRECIO</br>

y el BNF de precio seria un numero entero, basicamente una cadena consecutiva de digitos:</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PRECIO -> DIGITO PRECIO</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PRECIO -> DIGITO</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DIGITO -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9</br>
</br>
Escribimos todo el BNF en una columna:</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TDS -> LISTA</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LISTA -> PRECIO , LISTA | PRECIO</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PRECIO -> DIGITO PRECIO | DIGITO</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DIGITO -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9</br>
</br>
Verificamos que no tenga recursión por la izq., en este caso no tenemos.</br>
Verificamos factores comunes: en este caso tenemos en “lista” y en “precio”</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LISTA -> PRECIO , LISTA | PRECIO LISTA -> PRECIO R1</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R1 -> , LISTA | ε</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PRECIO -> DIGITO PRECIO | DIGITO PRECIO -> DIGITO R2</br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R2 -> PRECIO | ε</br>
