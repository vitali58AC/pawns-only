fun main() {
    val n = readLine()!!.toInt()
    val list = mutableListOf<Int>()
    repeat(n) {
        list.add(readLine()!!.toInt())
    }
    list.sort()
    println(list[0])
}
