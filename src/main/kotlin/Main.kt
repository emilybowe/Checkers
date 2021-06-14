fun main () {
    val game = Game()
    val aPieces = game.assignPieces(Player("A"), "black")
    val bPieces = game.assignPieces(Player("B"), "white")
    game.move(Pair(2,0), Pair(3,1), game.player1Token, Piece.Status.PAWN)
    game.displayBoard()

}
