import java.util.*

// Main.kt
fun main() {
    println("hola mundo")
    //Tipos de variables

    // Inmutable (No se puede reasignar)
    val inmutable: String = "Kelvin";
    //inmutable = "Jose" // no se puede reasignar

    // mutables (Se puede reasignar los valores)
    var mutable: String = "Kelvin";
    mutable = "Jose";

    //Se prefiere var respecto de val, es decir, var > val
    // No es necesario el ;


    // Duck typing

    val ejemploVariable = "Ejemplo"
    val edadEjemplo = 12
    ejemploVariable.trim()

    // variables prmitivas
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true

    //Clase de Java
    val fechaNacimiento: Date = Date()


    //if else
    if (true) {

    } else {

    }

    // Switch no existe
    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("acercarse")
        }

        ("C") -> {
            println("Alejarse")
        }

        "UN" -> println("hablar")
        else -> print("No reconocido")

    }
    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"
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
