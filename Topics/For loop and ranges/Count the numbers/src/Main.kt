fun main() {
    val r = readLine()!!.toInt()..readLine()!!.toInt()
    val n = readLine()!!.toInt()
    var count = 0
    for (i in r) {
        if (i % n == 0) ++count
    }
    println(count)
}
