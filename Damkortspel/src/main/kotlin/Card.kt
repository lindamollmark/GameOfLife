class Card(val suit: Suit, val rank: Rank) {

    fun cardValue(): Int {
        return if (suit == Suit.HEARTS && rank == Rank.QUEEN) {
            -2
        } else if (suit == Suit.SPADES && rank == Rank.TWO ||
            suit == Suit.DIAMONDS && rank == Rank.TEN) {
            -1
        } else {
            rank.rankNum
        }

    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (suit != other.suit) return false
        if (rank != other.rank) return false

        return true
    }

    override fun hashCode(): Int {
        var result = suit.hashCode()
        result = 31 * result + rank.hashCode()
        return result
    }

    override fun toString(): String {
        return "${suit} ${rank}"
    }


}