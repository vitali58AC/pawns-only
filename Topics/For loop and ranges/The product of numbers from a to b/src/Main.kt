fun main() {
    val (a, b) = List(2) {
        readLine()!!.toLong()
    }
    var num = 1L
    for (i in a until b) {
        num *= i
    }
    println(num)
}
