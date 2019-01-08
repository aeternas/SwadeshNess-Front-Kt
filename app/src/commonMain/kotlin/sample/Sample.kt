package sample

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

class Language(val fullName: String, val code: String)
class LanguageGroup(val name: String, val languages: Array<Language>)

fun hello(): String = "Hello from ${Platform.name}"

class Proxy {
    fun proxyHello() = hello()
}

fun main(args: Array<String>) {
    println(hello())
}