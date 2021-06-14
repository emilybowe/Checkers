fun main () {
    val game = Game()
    //game.displayBoard()
    println()
    val players = arrayOf(Player("A"), Player("B"))
    val aPieces = game.assignPieces(players[0], "black")
    val bPieces = game.assignPieces(players[1], "white")
    game.applyPieces()
    game.displayBoard()
    println()
    game.applyPieces1()
    game.displayBoard()
}
