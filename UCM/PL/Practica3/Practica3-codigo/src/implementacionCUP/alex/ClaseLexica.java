package implementacionCUP.alex;

public enum ClaseLexica {
 ID,
 
 // Tipos de N�meros
 INT,  // N�mero Entero
 REAL, // N�mero Real
 EXE, // N�mero (Entero / Real) Exponencial
  
 
 // Operadores matem�ticos
 EQ, // Operador asignaci�n
 PLS, // Operador m�s
 MNS, // Operador menos
 MUL, // Operador multiplicar
 DIV, // Operador dividir
 
 PCE, // Par�ntesis cerrado
 PAO, //Par�ntesis abierto 

 // Operador binario de comparaci�n
 GT, // Comparador Mayor
 GE, // Comparador Mayor igual
 LT, // Comparador Menor
 LE, // Comparador Menor igual
 BE, // Comparador Igualdad
 NE, // Comparador Desigualdad
 
 // String reservados
 BOOL, // Booleanos
 NUM, // N�meros
 
 // Binario
 AND, // Operador Binario AND
 OR, // Operador Binario OR
 NOT, // Operador Unario TRUE, FALSE,
 
 // Separadores
 SEP, 
 PYC, 
 
 // Fin de fichero
 EOF
}
