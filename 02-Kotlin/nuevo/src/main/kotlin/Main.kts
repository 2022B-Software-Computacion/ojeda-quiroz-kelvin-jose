import java.util.*

// Main.kt
fun main() {
    println("hola mundo")
    //Tipos de variables
//
//    // Inmutable (No se puede reasignar)
//    val inmutable: String = "Kelvin";
//    //inmutable = "Jose" // no se puede reasignar
//
//    // mutables (Se puede reasignar los valores)
//    var mutable: String = "Kelvin";
//    mutable = "Jose";
//
//    //Se prefiere var respecto de val, es decir, var > val
//    // No es necesario el ;
//
//
//    // Duck typing
//
//    val ejemploVariable = "Ejemplo"
//    val edadEjemplo = 12
//    ejemploVariable.trim()
//
//    // variables prmitivas
//    val sueldo: Double = 1.2
//    val estadoCivil: Char = 'S'
//    val mayorEdad: Boolean = true
//
//    //Clase de Java
//    val fechaNacimiento: Date = Date()
//
//
//    //if else
//    if (true) {
//
//    } else {
//
//    }
//
//    // Switch no existe
//    val estadoCivilWhen = "S"
//    when (estadoCivilWhen) {
//        ("S") -> {
//            println("acercarse")
//        }
//
//        ("C") -> {
//            println("Alejarse")
//        }
//
//        "UN" -> println("hablar")
//        else -> print("No reconocido")
//
//    }
//    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"


    // Declaración de las isntancias de la clase
    val sumaUno = Suma(1, 1);
    val sumaDos = Suma(null, 1);
    val sumaTres = Suma(1, null);
    val sumaCuatro = Suma(null, null);
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    Suma.pi
    Suma.elevarAlCuadrado(2)
    Suma.historialSumas


    // Tipos de arreglos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    // Arreglos dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    print(arregloDinamico)

}


fun imprimirNombre(nombre: String): Unit { // Unit es semejante a void
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial: Double? = null, //Opcional (Null)-> nullable, es decir que  el ? significa que puede tomar valores nulos
): Double {
    // String -> String?
    // Int -> Int?
    // Date -> Date?
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) * bonoEspecial
    }
}


//Clases dentro de Kotlin
abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ) { //Bloque código constructor
        this.numeroUno = uno;
        this.numeroDos = dos;
        println("Inicializado")
    }
}

abstract class Numero( //Constructor primario
    // uno: Int, //Parametro
    //public var uno: Int, // Semejante a utilizar var uno: Int
    protected val numeroUno: Int,
    protected val numeroDos: Int
) {

    init {//Constructor primario
        // this.uno
        this.numeroUno
        numeroUno
        this.numeroDos
        numeroDos
        println("Inicializado")

    }

}

abstract class NumeroEjm( //Constructor primario
    protected val numeroUno: Int,
    private val numeroDos: Int
) {
    init {//Constructor primario
        numeroUno
        numeroDos
        println("Inicializado")
    }

}

class Suma(//Constructor Primario Suma
    uno: Int, //Parametro
    dos: Int  //Parametro
) : Numero(uno, dos) {
    init { // Bloque contructor primario
        this.numeroUno
        this.numeroDos
        //uno
        //dos
    }

    constructor( //Segundo contructor
        uno: Int?, //Parametros
        dos: Int    //Parametros
    ) : this( // Llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    )

    constructor( //Segundo contructor
        uno: Int, //Parametros
        dos: Int?    //Parametros
    ) : this( // Llamada constructor primario
        if (dos == null) 0 else dos,
        uno
    )

    constructor( //Segundo contructor
        uno: Int?, //Parametros
        dos: Int?    //Parametros
    ) : this( // Llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos
    )

    public fun sumar(): Int {
        //return numeroUno + numeroDos
        val total = numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }

    companion object { //Atributos o metodos compartidos dentre las instancias
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNumeroSuma: Int) {
            historialSumas.add(valorNumeroSuma)
            print(historialSumas)
        }
    }
}
