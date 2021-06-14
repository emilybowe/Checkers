fun main () {
    val game = Game()
    val aPieces = game.assignPieces(Player("A"), "black")
    val bPieces = game.assignPieces(Player("B"), "white")
    game.displayBoard()
    println()
    game.move(Pair(2,0), Pair(3, 1), game.player1Token)
    game.displayBoard()

}
