package com.example.mycasinoapplication.model

enum class BaccaratBets(
    val bonusName: String,
    val desc: String = "",
    val odds: Int = 1,
    val isBonus: Boolean = true,
    val min10: Int = 1,
    val max10: Int = 100,
    val increment10: Int = 1,
    val min25: Int = 5,
    val max25: Int = 100,
    val increment25: Int = 5,
    val manualAdd: Boolean = false,
) {
    NINE_ONE("9/1", "", 150, isBonus = true, max10 = 25, max25 = 25),
    NINE_SEVEN("9/7", "", 50),
    EIGHT_SIX("8/6", "", 50),
    PANDA("Panda", "", 25),
    TIE("Tie", "", 25),
    DRAGON("Dragon", "", 40),

//    NO_BONUS("None", "", 0, false),
    PLAYER_WINS("Player", "", 1, false, manualAdd = true),
    BANKER_WINS("Banker", "", 1, false, manualAdd = true),
}
