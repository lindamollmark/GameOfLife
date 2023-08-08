import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MainKtTest {

    @Test
    fun shouldCreateCardDeckWith52Cards() {
        val cardDeck: CardDeck = createCardDeck()

        assertEquals(52, cardDeck.cardDeck.size)
    }

    @Test
    fun shouldDeal4CardsToPlayer() {
        val cardDeck: CardDeck = createCardDeck()
        val playerStartCards = dealCardToPlayer(cardDeck, realPlayer)
        assertEquals(4, playerStartCards.cards.stream().distinct().toList().size)
    }
}