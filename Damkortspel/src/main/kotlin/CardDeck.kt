class CardDeck(var cardDeck: List<Card>) {

    fun fetchAndRemoveCard(): Card {
        val card = cardDeck.random()
        cardDeck = cardDeck.minus(card)
        return card
    }
}