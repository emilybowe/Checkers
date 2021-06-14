import java.util.*

class Game(private val rows: Int = 8, private val col: Int = 8, private val piecePercentage: Double = 0.1875) {
    val empty = 1
    val player1Token = 3
    val player2Token = 4
    val board = Board(rows, col)
    val lastMarker = board.array.size-1
    val secondLastMarker = board.array.size-2



    fun displayBoard () {
        board.printBoard()
    }
    private fun calculatePieces() :Int {
        return ((rows) * (col) * piecePercentage).toInt()
    }
    private fun getPlayerPieces(): Array<Piece> {
        return Array(calculatePieces()) {Piece()}
    }
    fun assignPieces(player: Player, colour:String) : Array<Piece> {
        val colouredPieces = getPlayerPieces()
        when(colour){
            "Black".toUpperCase() -> for (i in colouredPieces) {Piece.Colour.BLACK}
            else ->  {Piece.Colour.WHITE}
        }
        for (i in colouredPieces) {Piece.Status.PAWN}
        applyPiecesP1()
        applyPiecesP2()
        return colouredPieces
    }

    fun applyPiecesP1() {
        val numberOfPieces = calculatePieces()
        var y = 0
        var x = 0
        for (i in 0 until numberOfPieces) {
            for (j in 0 until board.array.size) {
                board.array[x][y] = player1Token
            }
            if (y == (secondLastMarker)) { //0,6
                x = 1
                y = 1
            } else if (y == (lastMarker)) { //1,7
                x = 2
                y = 0
            } else {
                y += 2
            }
        }
    }

        fun applyPiecesP2() {
            val numberOfPieces = calculatePieces()
            var y = lastMarker
            var x = lastMarker
            for (i in 0 until numberOfPieces) {
                for (j in 0 until board.array.size) {
                    board.array[x][y] = player2Token
                }
                if (y == 1) { //7,1
                    --x
                    y = secondLastMarker
                } else if (y == 0) { //6,0
                    --x
                    y = lastMarker
                } else {
                    y -= 2
                }
            }
        }
    fun checkMoveLegal(moveFrom: Pair<Int, Int>, moveTo: Pair<Int, Int>,playerToken: Int) : Boolean{
        if(board.array[moveTo.first][moveTo.second] != empty) {
            "Space is Occupied!"; return false
        } else if (board.array[moveFrom.first][moveFrom.second] == empty) {
            "No piece there!"; return false
        } else if(board.array[moveFrom.first][moveFrom.second] != playerToken) {
            "That's not your piece!" ; return false
        }
        return true
    }

    fun checkMoveLegalWithDiagonalAndBackwards(moveFrom: Pair<Int, Int>, moveTo: Pair<Int, Int>, playerToken: Int, piece: Piece.Status) : Boolean{

        var moveFromSecond = moveFrom.copy(second = moveFrom.second)
        var moveFromFirst = moveFrom.copy(first = moveFrom.first)
        var moveToFirstMinus1 = moveTo.copy(first = moveTo.first-1)
        var moveToFirstPlus1 = moveTo.copy(first = moveTo.first+1)
        var moveToSecondMinus1 = moveTo.copy(second = moveTo.second-1)
        var moveToSecondPlus1 = moveTo.copy(second = moveTo.second+1)

        if(moveTo.first == board.array.size || moveTo.first == -1) {
            return false
        }
        if(moveTo.second == col || moveTo.second == -1) {
            return false
        }
        if(playerToken == player1Token) {
            //00
            if((moveFromSecond == moveToSecondMinus1) || (moveFromSecond == moveToSecondPlus1 )) { //check y
                if((piece == Piece.Status.KING) || (moveFromFirst == moveToFirstPlus1 )){ //check x
                    return true
                }
            }
        } else if (playerToken == player2Token) {
            //last
            if((moveFromSecond == moveToSecondMinus1) && (moveFromSecond == moveToSecondPlus1)) {//check y
                if((piece == Piece.Status.KING) || (moveFromFirst == moveToFirstMinus1)){ //check x
                    return true
                }
            }
        }
        return false
    }

    fun move(moveFrom: Pair<Int, Int>, moveTo: Pair<Int, Int>, playerToken: Int, piece: Piece.Status) {
        if(checkMoveLegal(moveFrom, moveTo, playerToken)) {
            if (checkMoveLegalWithDiagonalAndBackwards(moveFrom,moveTo, playerToken, piece)) {
                board.array[moveFrom.first][moveFrom.second] = empty
                board.array[moveTo.first][moveTo.second] = playerToken
                if ((playerToken == player1Token) && (board.array[moveTo.first] contentEquals board.array[0])) {
                    "Piece Promoted"
                } else if ((playerToken == player1Token) && (board.array[moveTo.first] contentEquals board.array[lastMarker])) {
                    "Piece Promoted"
                } else "Piece Moved"
            } else "Move not valid"
        }else "Move not valid"
    }
}


