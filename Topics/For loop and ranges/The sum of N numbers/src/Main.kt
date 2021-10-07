fun main() {
    val n = readLine()!!.toInt()
    val list = List(n) {
        readLine()!!.toInt()
    }
    println(list.sum())
}
