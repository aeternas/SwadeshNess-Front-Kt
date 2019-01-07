package sample

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

data class Language(var fullName: String, var code: String)
data class LanguageGroup(var name: String, var languages: Array<Language>)

fun hello(): String = "Hello from ${Platform.name}"

class Proxy {
    fun proxyHello() = hello()
}

fun main(args: Array<String>) {
    println(hello())
}