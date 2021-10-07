fun main() {
    val n = readLine()!!.toInt()
    val numbers = List(n) {
        readLine()!!.toInt()
    }.sortedDescending()
    if (n > 1) {
        var max = 0
        for (i in 1..numbers.lastIndex) {
            val temp = numbers[i] * numbers[0]
            if (temp > max) {
                max = temp
            }
        }
        println(max)
    } else {
        if (n > 0) println(numbers[0])
    }
}
