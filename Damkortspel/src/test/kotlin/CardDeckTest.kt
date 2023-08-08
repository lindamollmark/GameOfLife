import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

internal class CardDeckTest {
    @Test
    fun shouldRemoveOneCardFromDeck() {
        val cards = Suit.suits().stream()
            .flatMap { c -> Rank.ranks().stream().map { Card(c, it) } }
            .toList()
        val cardDeck = CardDeck(cards)

        cardDeck.fetchAndRemoveCard()
        assertEquals(51, cardDeck.cardDeck.size)
    }
}