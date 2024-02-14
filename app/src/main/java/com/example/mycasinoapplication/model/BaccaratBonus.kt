package com.example.mycasinoapplication.model

enum class BaccaratBonus(
    name: String,
    desc: String = "",
    odds: Float = 1.0f,
) {
    NINE_ONE("9/1", "", 150f),
    NINE_SEVEN("9/7", "", 50f),
    EIGHT_SIX("8/6", "", 50f),
    PANDA("Panda", "", 25f),
    TIE("Tie", "", 25f),
    DRAGON("Dragon", "", 40f),
    NO_BONUS("None", "", 0f);

    fun hasBonus(player: Int, banker: Int, playerCount: Int, bankerCount: Int): BaccaratBonus {
        return when{
            player.equals(9) && banker.equals(1)
                    || player.equals(1) && banker.equals(9) -> BaccaratBonus.NINE_ONE
            else -> BaccaratBonus.NO_BONUS
        }
    }
}
