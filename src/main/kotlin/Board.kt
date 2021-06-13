class Board(rows: Int, col: Int) {
    var array = Array(rows) { IntArray(col) {1} }

    fun printBoard() {
        for (array in array) {
            println()
            for(value in array){
                print(value)
            }
        }
    }
}
