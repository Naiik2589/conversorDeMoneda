Currency Converter

Descripción
Este proyecto es un conversor de monedas que permite a los usuarios convertir entre varias monedas utilizando tasas de cambio actualizadas en tiempo real. Utiliza la API de ExchangeRate para obtener los datos de conversión y está desarrollado en Java.

Funcionalidades
Selección de monedas: Permite seleccionar la moneda de origen y destino de una lista de opciones.
Conversión precisa: Realiza la conversión de montos en tiempo real utilizando tasas de cambio actualizadas.
Entradas flexibles: Acepta tanto números enteros como decimales para los montos a convertir.
Formato de salida personalizado: Muestra los resultados en un formato claro y fácil de leer, por ejemplo, EUR a COP '10.5' = 48,483.65 COP.
Manejo de errores: Incluye validaciones para entradas no válidas y asegura que los usuarios reciban mensajes claros sobre cómo corregir sus entradas.
Interfaz de consola amigable: Proporciona un menú de selección claro y fácil de usar.
Instalación
Clona el repositorio en tu máquina local.
Asegúrate de tener Java y Maven instalados.
Abre el proyecto en tu IDE favorito (se recomienda IntelliJ IDEA).
Ejecuta el archivo CurrencyConverter.java.
Ejemplo de Uso
Aquí hay un ejemplo de cómo se ve la interacción con el usuario:

markdown
Copiar código
Seleccione la moneda origen:
1. USD
2. EUR
3. COP
4. GBP
5. JPY
6. AUD
7. CAD
8. CHF
1
Seleccione la moneda destino:
1. USD
2. EUR
3. COP
4. GBP
5. JPY
6. AUD
7. CAD
8. CHF
2
Ingrese el monto (formato numérico, por ejemplo, 1.6): 
10.5
   
Monto convertido: EUR a COP '10.5' = 48,483.65 COP

¿Desea hacer otra conversión? (s/n)


