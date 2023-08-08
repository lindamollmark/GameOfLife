class Player(var cards: List<Card>, realPlayer: Boolean = false) {
    private var cardPositions = HashMap<Int, Int>()

    fun computerPlayCard(playedCard: Card) {
        if (cardsOnlyContainsLowerCardThanPlayed(playedCard)
        ) {
            return
        }

        var cardToCompare = playedCard
        val cardArray = cards.toTypedArray()
        for ((index, card) in cardArray.withIndex()) {

            cardPositions[index] = cardToCompare.cardValue()
            cardArray[index] = cardToCompare
            cardToCompare = card
            if (cardPositions.size == 4 && cardPositions.entries.stream()
                    .filter { it.key > index }
                    .filter { it.value > cardToCompare.cardValue() }
                    .toList()
                    .isEmpty()
            ) {
                break
            }
        }
        cards = cardArray.toList()
    }

 fun playerPlayCard(playedCard: Card) {
        var cardToCompare = playedCard
        val cardArray = cards.toTypedArray()
        for ((index, card) in cardArray.withIndex()) {

            cardPositions[index] = cardToCompare.cardValue()
            cardArray[index] = cardToCompare
            cardToCompare = card

            println("Position: ${index}")
            println(card.toString())
            if (index == 3) {
                break
            }
            println("Behåll eller kasta kort")
            if (readln().contentEquals("Kasta")) {
                break
            }
        }
        cards = cardArray.toList()
    }

    private fun cardsOnlyContainsLowerCardThanPlayed(playedCard: Card) = cardPositions.isNotEmpty() &&
            cardPositions.values.stream()
        .filter { it > playedCard.cardValue() }
        .toList()
        .isEmpty()

    fun getResult(): Int {
        return cards.stream()
            .map { c -> c.cardValue() }
            .reduce { sum, num -> sum + num }
            .orElse(0)

    }
}