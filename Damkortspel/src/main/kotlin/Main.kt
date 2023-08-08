fun main() {
    val cardDeck: CardDeck = createCardDeck()

    val player = dealCardToPlayer(cardDeck)
    val player2 = dealCardToPlayer(cardDeck)
        val player3 = dealCardToPlayer(cardDeck)
    val realPlayer = dealCardToPlayer(cardDeck, true)
    while (cardDeck.cardDeck.isNotEmpty()) {
        computerPlayGame(player, cardDeck)
        computerPlayGame(player2, cardDeck)
        computerPlayGame(player3, cardDeck)
        playerPlayGame(realPlayer, cardDeck)
    }
    println("Player1 result: ${player.getResult()}")
    println("Player2 result: ${player2.getResult()}")
    println("Player3 result: ${player3.getResult()}")
    println("Manuell spelares result: ${realPlayer.getResult()}")
}

fun dealCardToPlayer(cardDeck: CardDeck, realPlayer: Boolean = false): Player {
    val cards = IntRange(1, 4).map { cardDeck.fetchAndRemoveCard() }
        .toList()
    return Player(cards, realPlayer)
}

fun createCardDeck(): CardDeck {
    val cards = Suit.suits().stream()
        .flatMap { c -> Rank.ranks().stream().map { Card(c, it) } }
        .toList()

    return CardDeck(cards)
}

fun computerPlayGame(player: Player, cardDeck: CardDeck) {
    if (cardDeck.cardDeck.isNotEmpty()) {
        val playedCard = cardDeck.fetchAndRemoveCard()
        if (playedCard.cardValue() < 5) {
            player.computerPlayCard(playedCard)
        }
    }
}

fun playerPlayGame(player: Player, cardDeck: CardDeck) {
    if (cardDeck.cardDeck.isNotEmpty()) {
        val playedCard = cardDeck.fetchAndRemoveCard()
        println("${playedCard.suit} ${playedCard.rank}")
        println("Behåll eller kasta kort")
        if (readln().contentEquals("Behåll")) {
            player.playerPlayCard(playedCard)
            }
    }
}