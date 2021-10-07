package chess

object Chessboard {
    private val border = "  " + "+---".repeat(8).plus("+")
    val chessboard = mutableListOf(
        mutableListOf("8 ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|"),
        mutableListOf("7 ", "| B ", "| B ", "| B ", "| B ", "| B ", "| B ", "| B ", "| B ", "|"),
        mutableListOf("6 ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|"),
        mutableListOf("5 ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|"),
        mutableListOf("4 ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|"),
        mutableListOf("3 ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|"),
        mutableListOf("2 ", "| W ", "| W ", "| W ", "| W ", "| W ", "| W ", "| W ", "| W ", "|"),
        mutableListOf("1 ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|   ", "|"),
        mutableListOf("    a", "   b", "   c", "   d", "   e", "   f", "   g", "   h")
    )

    fun printCurrentBoard() {
        val currentBoard = chessboard.map {
            it.joinToString("")
        }
        println(currentBoard.joinToString("\n$border\n", "$border\n"))
    }

}