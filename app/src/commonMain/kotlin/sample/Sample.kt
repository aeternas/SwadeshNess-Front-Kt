package sample

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

data class Language(var fullName: String, var code: String)

fun hello(): String = "Hello from ${Platform.name}"

class Proxy {
    fun proxyHello() = hello()
}

fun main(args: Array<String>) {
    println(hello())
}