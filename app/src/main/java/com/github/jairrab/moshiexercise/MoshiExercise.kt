package com.github.jairrab.moshiexercise

import androidx.annotation.Keep
import com.squareup.moshi.*


fun main() {
    val blackjackHand = BlackjackHand(
        hiddenCard = Card('6', Suit.SPADES),
        visibleCards = listOf(
            Card('4', Suit.CLUBS),
            Card('A', Suit.HEARTS)
        )
    )

    val json = objToJson(blackjackHand)
    val obj = jsonToObj<BlackjackHand>(json!!)

    val cards = listOf(
        Card('4', Suit.CLUBS),
        Card('A', Suit.HEARTS)
    )

    val cardsJson = objToJson(cards)
    val cardsObj = jsonToList<Card>(cardsJson!!)

    println(json)
}

inline fun <reified T> objToJson(obj: T): String? {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(T::class.java)
    return jsonAdapter.toJson(obj)
}

inline fun <reified T> jsonToObj(json: String): T? {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(T::class.java)
    return jsonAdapter.fromJson(json)
}

inline fun <reified T> jsonToList(json: String): List<T>? {
    val type = Types.newParameterizedType(List::class.java, T::class.java)
    val moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<List<T>> = moshi.adapter(type)
    return adapter.fromJson(json)
}

@JsonClass(generateAdapter = true)
data class BlackjackHand(
    val hiddenCard: Card,
    val visibleCards: List<Card>,
)

@JsonClass(generateAdapter = true)
data class Card(
    @Json(name = "rank_name") val rank: Char,
    @Json(name = "suit_name") val suit: Suit,
)

@Keep
enum class Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES;
}