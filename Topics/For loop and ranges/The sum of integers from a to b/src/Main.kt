fun main() {
    val (a, b) = List(2) {
        readLine()!!.toInt()
    }
    var num = 0
    for (i in a..b) {
        num += i
    }
    println(num)
}
