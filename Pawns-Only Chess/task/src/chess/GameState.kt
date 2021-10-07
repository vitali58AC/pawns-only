package chess

enum class GameState(private val state: String) {
    Run("Run"),
    WhiteWin("White Wins!"),
    BlackWin("Black Wins!"),
    Stalemate("Stalemate!");

    fun printState() {
        if (state != "Run") {
            println(state)
        }
        println("Bye!")
    }
}