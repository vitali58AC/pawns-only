package chess

import chess.Chessboard.chessboard
import chess.Chessboard.printCurrentBoard
import chess.Const.black
import chess.Const.empty
import chess.Const.white
import chess.Player.Side.*
import kotlin.math.abs


lateinit var playerOne: Player
lateinit var playerTwo: Player
private var onPassage = ""
private var count = 0
private var gameState = GameState.Run

fun main() {
    println("Pawns-Only Chess")
    println("First Player's name:")
    playerOne = Player(readLine()!!, White)
    println("Second Player's name:")
    playerTwo = Player(readLine()!!, Black)

    printCurrentBoard()
    while (true) {
        checkState(playerOne)
        if (checkTurn(playerOne)) {
            break
        }
        checkState(playerTwo)
        if (checkTurn(playerTwo)) {
            break
        }
    }
    gameState.printState()
}

fun checkTurn(player: Player): Boolean {
    val regex = "[a-h][1-8][a-h][1-8]".toRegex()
    var input: String
    while (true) {
        if (gameState != GameState.Run) return true
        //println("$name's turn:")
        println("${player.name}'s turn:")
        if (count > 2) {
            onPassage = ""
            count = 0
        }
        input = readLine()!!
        if (input == "exit") {
            return true
        }
        if (regex.matches(input) && playerTurn(input, player)) {
            ++count
            return false
        }
        if (!regex.matches(input)) {
            println("Invalid Input")
        }
    }
}


fun advanceCheck(input: String, player: Player): Boolean {
    val y = input[3].toString().toInt() - input[1].toString().toInt()
    return when {
        player.side == White && input[1] == '2' && y in 1..2 -> {
            if (y == 2) {
                onPassage = input
            }
            true
        }
        player.side == Black && input[1] == '7' && y in -2..-1 -> {
            if (y == -2) {
                onPassage = input
            }
            true
        }
        player.side == White && y == 1 -> {
            true
        }
        else -> player.side == Black && y == -1
    }
}

fun capture(input: String): Boolean {
    val y = abs(input[3].toString().toInt() - input[1].toString().toInt()) == 1
    val captureBorder = input[2] + 1 == input[0] || input[2] - 1 == input[0]
    return when {
        y && captureBorder -> {
            true
        }
        else -> false
    }
}

fun playerTurn(input: String, player: Player): Boolean {
    val index = transformToIndex(input)
    val start = chessboard[index[0]][index[1]]
    val end = chessboard[index[2]][index[3]]
    val line = input[0] == input[2]
    val checkWhite = start != empty && player.side == White && start == white
    val checkBlack = start != empty && player.side == Black && start == black
    return when {
        advanceCheck(input, player) && checkWhite && end != black && line -> {
            chessboard[index[2]][index[3]] = white
            chessboard[index[0]][index[1]] = empty
            printCurrentBoard()
            true
        }
        capture(input) && checkWhite -> {
            if (end == black) {
                chessboard[index[2]][index[3]] = white
                chessboard[index[0]][index[1]] = empty
                printCurrentBoard()
                true
            } else if (onPassage.isNotEmpty() && end == empty) {
                if (input[2] == onPassage[2] && input[3] == onPassage[3] + 1) {
                    val passIndex = transformToIndex(onPassage)
                    chessboard[index[2]][index[3]] = white
                    chessboard[index[0]][index[1]] = empty
                    chessboard[passIndex[2]][passIndex[3]] = empty
                    printCurrentBoard()
                    true
                } else {
                    println("Invalid Input")
                    false
                }
            } else {
                println("Invalid Input")
                false
            }
        }
        start == empty && player.side == White || start == black && player.side == White -> {
            println("No white pawn at ${input.substring(0, 2)}")
            false
        }
        advanceCheck(input, player) && checkBlack && end != white && line -> {
            chessboard[index[2]][index[3]] = black
            chessboard[index[0]][index[1]] = empty
            printCurrentBoard()
            true
        }
        capture(input) && checkBlack -> {
            if (end == white) {
                chessboard[index[2]][index[3]] = black
                chessboard[index[0]][index[1]] = empty
                printCurrentBoard()
                true
            } else if (onPassage.isNotEmpty() && end == empty) {
                if (input[2] == onPassage[2] && input[3] == onPassage[3] - 1) {
                    val passIndex = transformToIndex(onPassage)
                    chessboard[index[2]][index[3]] = white
                    chessboard[index[0]][index[1]] = empty
                    chessboard[passIndex[2]][passIndex[3]] = empty
                    printCurrentBoard()
                    true
                } else {
                    println("Invalid Input")
                    false
                }
            } else {
                println("Invalid Input")
                false
            }
        }
        start == empty && player.side == Black || start == white && player.side == Black -> {
            println("No black pawn at ${input.substring(0, 2)}")
            false
        }
        else -> {
            println("Invalid Input")
            false
        }
    }
}

fun transformToIndex(input: String): List<Int> {
    val list = mutableListOf<Int>()
    list.add(abs(input[1].toString().toInt() - 8))
    list.add(abs(input[0].code - 96))
    list.add(abs(input[3].toString().toInt() - 8))
    list.add(abs(input[2].code - 96))
    return list
}


fun checkState(player: Player) {
    var count = 0
    var stalemateCount = 0
    if (chessboard[0].contains(white)) {
        gameState = GameState.WhiteWin
        return
    }
    if (chessboard[7].contains(black)) {
        gameState = GameState.BlackWin
        return
    }
    for (list in chessboard) {
        for (field in list) {
            if (player.side == White) {
                if (field.contains(white)) {
                    if (checkPossibilityMoveWhite(list.indexOf(field), chessboard.indexOf(list), player)) {
                        ++stalemateCount
                    }
                    ++count
                }
            }
            if (player.side == Black) {
                if (field.contains(black)) {
                    if (checkPossibilityMoveBlack(list.indexOf(field), chessboard.indexOf(list), player)) {
                        ++stalemateCount
                    }
                    ++count
                }
            }
        }
    }
    if (count == 0 && player.side == White) {
        gameState = GameState.BlackWin
        return
    }
    if (count == 0 && player.side == Black) {
        gameState = GameState.WhiteWin
        return
    }
    if (stalemateCount == 0) {
        gameState = GameState.Stalemate
    }
}

fun checkPossibilityMoveWhite(x: Int, y: Int, player: Player): Boolean {
    val onPassageWhite = if (onPassage.isNotEmpty()) {
        (y == 3 && onPassage[3].toString().toInt() == x + 1) || (y == 3 && onPassage[3].toString().toInt() == x - 1)
    } else false
    val canCaptureWhite =
        chessboard[y - 1][x - 1] == black || chessboard[y - 1][x + 1] == black || onPassageWhite
    val firstTurnCheck = if (y == 6) {
        chessboard[y - 2][x] == empty
    } else {
        false
    }
    if (player.side == White && canCaptureWhite || firstTurnCheck || (chessboard[y - 1][x] == empty)) {
        return true
    }
    return false
}

fun checkPossibilityMoveBlack(x: Int, y: Int, player: Player): Boolean {
    val onPassageBlack = if (onPassage.isNotEmpty()) {
        (y == 4 && onPassage[3].toString().toInt() == x + 1) || (y == 4 && onPassage[3].toString().toInt() == x - 1)
    } else false
    val canCaptureBlack =
        chessboard[y + 1][x - 1] == white || chessboard[y + 1][x + 1] == white || onPassageBlack
    val firstTurnCheck = if (y == 1) {
        chessboard[y + 2][x] == empty
    } else {
        false
    }
    if (player.side == Black && canCaptureBlack || firstTurnCheck || (chessboard[y + 1][x] == empty)) {
        return true
    }
    return false
}



