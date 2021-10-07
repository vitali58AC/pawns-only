package chess

data class Player(val name: String, val side: Side) {
    enum class Side {
        White,
        Black
    }
}