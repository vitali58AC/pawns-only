fun main() {
    val (a, b, c, d) = List(4) {
        readLine()!!.toInt()
    }
    for (i in 0..1000) {
        if (a * i * i * i + b * i * i + c * i + d == 0) {
            println(i)
        }
    }
}
