enum class Suit {
    HEARTS,
    SPADES,
    CLUBS,
    DIAMONDS;

    companion object {
        fun suits(): List<Suit> {
            return listOf(HEARTS, SPADES, CLUBS, DIAMONDS)
        }
    }
}
