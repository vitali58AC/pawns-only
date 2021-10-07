fun main() {
    val input = List(3) {
        readLine()!!
    }
    val separator = readLine()!!
    val string = input.joinToString(separator).replace("NO SEPARATOR", " ")
    println(string)
}