fun main() {
    val name = readLine()!!
    printName(name)
}

fun printName(name: String) {
    println(if (name == "HIDDEN") {
        "Hello, secret user!"
    } else {
        "Hello, $name!"
    })
}
