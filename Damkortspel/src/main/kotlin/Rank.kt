enum class Rank(val rankNum: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9),
    TEN(10), JACK(11), QUEEN(0), KING(13), ACE(1);

    companion object {
        fun ranks(): List<Rank> {
return enumValues<Rank>().asList()
        }
    }
}