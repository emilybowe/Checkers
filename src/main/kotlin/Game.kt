import java.util.*

class Game(private val rows: Int = 8, private val col: Int = 8, private val piecePercentage: Double = 0.1875) {
    val empty = 1
    val player1Token = 3
    val player2Token = 4

    val board = Board(rows, col)

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
            if (y == (board.array.size - 2)) { //0,6
                x = 1
                y = 1
            } else if (y == (board.array.size - 1)) { //1,7
                x = 2
                y = 0
            } else {
                y += 2
            }
        }
    }

        fun applyPiecesP2() {
            val numberOfPieces = calculatePieces()
            var y = board.array.size -1
            var x = board.array.size -1
            for (i in 0 until numberOfPieces) {
                for (j in 0 until board.array.size) {
                    board.array[x][y] = player2Token
                }
                if (y == 1) { //7,1
                    --x
                    y = board.array.size - 2
                } else if (y == 0) { //6,0
                    --x
                    y = board.array.size - 1
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
        } else {
            return true
        }
        return true
    }

    fun checkMoveLegalWithDiagonal(moveFrom: Pair<Int, Int>, moveTo: Pair<Int, Int>,playerToken: Int, piece: Piece.Status) : Boolean{
        if(playerToken == player1Token) {
            //00
            if((moveFrom.second == moveTo.second -1 || moveFrom.second == moveTo.second +1 )) { //check y
                if(piece == Piece.Status.KING || (moveFrom.first == moveTo.first +1)){ //check x
                    return true
                }
            }
        } else if (playerToken == player2Token) {
            //77
            if((moveFrom.first == moveTo.first -1) && (moveFrom.second == moveTo.second +1)) {//check y
                if(piece == Piece.Status.KING || (moveFrom.first == moveTo.first -1)){
                    return true
                }
            }

        }
    }

    fun move(moveFrom: Pair<Int, Int>, moveTo: Pair<Int, Int>, playerToken: Int) {
        //board.array[piece.position.first][piece.position.second] = 1
        if(checkMoveLegal(moveFrom, moveTo, playerToken)) {
            if (checkMoveLegalWithDiagonal()) {
                board.array[moveFrom.first][moveFrom.second] = empty
                board.array[moveTo.first][moveTo.second] = playerToken
            }
        }
    }
}


