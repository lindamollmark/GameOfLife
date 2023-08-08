import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PlayerTest {

    @Test
    fun shouldUpdateIfNewCardIsQueen() {
        val playerStartCards = listOf(
            Card(Suit.SPADES, Rank.FIVE),
            Card(Suit.HEARTS, Rank.FIVE),
            Card(Suit.DIAMONDS, Rank.FIVE),
            Card(Suit.CLUBS, Rank.FIVE)
        )
        val player = Player(playerStartCards)

        val queenCard = Card(Suit.SPADES, Rank.QUEEN)
        player.computerPlayCard(queenCard)

        assertTrue(player.cards.contains(queenCard))
    }

    @Test
    fun shouldNotUpdateIfNewCardHigherTanHighestInDeck() {
        val playerStartCards = listOf(
            Card(Suit.SPADES, Rank.THREE),
            Card(Suit.HEARTS, Rank.QUEEN),
            Card(Suit.DIAMONDS, Rank.THREE),
            Card(Suit.CLUBS, Rank.ACE)
        )
        val player = Player(playerStartCards)

        val firstUpdatedCard = Card(Suit.SPADES, Rank.TWO)
        player.computerPlayCard(firstUpdatedCard)
        val highestCard = Card(Suit.SPADES, Rank.FOUR)
        player.computerPlayCard(highestCard)

        assertFalse(player.cards.contains(highestCard))
    }

    @Test
    fun shouldStopUpdateCardsIfNoHigherInHigherPositions() {
        val playerStartCards = listOf(
            Card(Suit.SPADES, Rank.THREE),
            Card(Suit.HEARTS, Rank.QUEEN),
            Card(Suit.DIAMONDS, Rank.THREE),
            Card(Suit.CLUBS, Rank.JACK)
        )
        val player = Player(playerStartCards)

        val firstUpdatedCard = Card(Suit.SPADES, Rank.TWO)
        player.computerPlayCard(firstUpdatedCard)
        val highestCard = Card(Suit.SPADES, Rank.FOUR)
        player.computerPlayCard(highestCard)
        val equalCard = Card(Suit.SPADES, Rank.ACE)
        player.computerPlayCard(equalCard)
        val equalCard2 = Card(Suit.HEARTS, Rank.ACE)
        player.computerPlayCard(equalCard2)
        val expected = listOf(
            Card(Suit.HEARTS, Rank.ACE),
            Card(Suit.SPADES, Rank.ACE),
            Card(Suit.SPADES, Rank.TWO),
            Card(Suit.HEARTS, Rank.QUEEN),
        )

        assertEquals(expected, player.cards)
    }

    @Test
    fun shouldStopUpdateCardsIfNoHigherInHigherPositions2() {
        val playerStartCards = listOf(
            Card(Suit.SPADES, Rank.TEN),
            Card(Suit.HEARTS, Rank.QUEEN),
            Card(Suit.DIAMONDS, Rank.FIVE),
            Card(Suit.CLUBS, Rank.QUEEN)
        )
        val player = Player(playerStartCards)
        val card1 = Card(Suit.SPADES, Rank.THREE)
        player.computerPlayCard(card1)
        val card2 = Card(Suit.SPADES, Rank.ACE)
        player.computerPlayCard(card2)
        val card3 = Card(Suit.SPADES, Rank.THREE)
        player.computerPlayCard(card3)
        val card4 = Card(Suit.HEARTS, Rank.TWO)
        player.computerPlayCard(card4)
        val card5 = Card(Suit.HEARTS, Rank.FOUR)
        player.computerPlayCard(card5)
        val card6 = Card(Suit.HEARTS, Rank.FOUR)
        player.computerPlayCard(card6)
        val card7 = Card(Suit.HEARTS, Rank.ACE)
        player.computerPlayCard(card7)
        val card8 = Card(Suit.HEARTS, Rank.TWO)
        player.computerPlayCard(card8)
        val card9 = Card(Suit.HEARTS, Rank.TWO)
        player.computerPlayCard(card9)
        val card10 = Card(Suit.HEARTS, Rank.FOUR)
        player.computerPlayCard(card10)
        val card11 = Card(Suit.HEARTS, Rank.ACE)
        player.computerPlayCard(card11)
        val card12 = Card(Suit.HEARTS, Rank.THREE)
        player.computerPlayCard(card12)
        val card13 = Card(Suit.HEARTS, Rank.THREE)
        player.computerPlayCard(card13)
        val card14 = Card(Suit.HEARTS, Rank.ACE)
        player.computerPlayCard(card14)
        val card15 = Card(Suit.HEARTS, Rank.FOUR)
        player.computerPlayCard(card15)
        val card16 = Card(Suit.HEARTS, Rank.TWO)
        player.computerPlayCard(card16)
        val card17 = Card(Suit.HEARTS, Rank.QUEEN)
        player.computerPlayCard(card17)
        val card18 = Card(Suit.HEARTS, Rank.QUEEN)
        player.computerPlayCard(card18)
        val expected = listOf(
            Card(Suit.HEARTS, Rank.QUEEN),
            Card(Suit.HEARTS, Rank.QUEEN),
            Card(Suit.SPADES, Rank.ACE),
            Card(Suit.HEARTS, Rank.QUEEN),
        )

        assertEquals(expected, player.cards)
    }

    @Test
    fun shouldGetZeroAsResult() {
        val playerStartCards = listOf(
            Card(Suit.SPADES, Rank.FIVE),
            Card(Suit.HEARTS, Rank.KING),
            Card(Suit.DIAMONDS, Rank.THREE),
            Card(Suit.CLUBS, Rank.ACE)
        )
        val player = Player(playerStartCards)

        val firstUpdate = Card(Suit.HEARTS, Rank.QUEEN)
        player.computerPlayCard(firstUpdate)
        val secondUpdate = Card(Suit.SPADES, Rank.QUEEN)
        player.computerPlayCard(secondUpdate)
        val thirdUpdate = Card(Suit.DIAMONDS, Rank.QUEEN)
        player.computerPlayCard(thirdUpdate)
        val forthUpdate = Card(Suit.CLUBS, Rank.QUEEN)
        player.computerPlayCard(forthUpdate)

        assertTrue(player.getResult() == 0)
    }
}