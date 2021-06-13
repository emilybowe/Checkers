class Game(private val rows: Int = 8, private val col: Int = 8, private val piecePercentage: Double = 0.1875) {
    val board = Board(rows, col)

    fun displayBoard () {
        board.printBoard()
    }
    private fun calculatePieces() :Int {
        return ((rows) * (col) * piecePercentage).toInt()
    }
    private fun getPlayerPieces(): Array<Piece> {
        return Array(calculatePieces()) { Piece() }
    }
    fun assignPieces(player: Player, colour:String) : Array<Piece> {
        val colouredPieces = getPlayerPieces()
        when(colour){
            "Black".toUpperCase() -> for (i in colouredPieces) {Piece.Colour.BLACK}
            else ->  {Piece.Colour.WHITE}
        }
        return colouredPieces
    }

    fun applyPieces() {
        val numberOfPieces = calculatePieces()
        var y = 0
        var x = 0
        for(i in 0 until numberOfPieces) {
            for(j in 0 until board.array.size) {
                board.array[x][y] = 3
            }
            if(y == (board.array.size - 2)) { //0,6
                x = 1
                y = 1
            } else if (y == (board.array.size - 1)) { //1,7
                x = 2
                y = 0
            } else {
                y +=2
            }
        }
    }
}

    fun move() {

    }
