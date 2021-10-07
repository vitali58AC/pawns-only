fun main() {
    val n = readLine()!!.toInt()
    val numberList = List(n) {
        readLine()!!.toInt()
    }
    if (numberList.sorted() == numberList) {
        println("YES")
    } else {
        println("NO")
    }
}