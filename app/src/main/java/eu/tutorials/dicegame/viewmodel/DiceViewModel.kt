package eu.tutorials.dicegame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.tutorials.dicegame.R
import eu.tutorials.dicegame.data.Game
import java.text.SimpleDateFormat
import java.util.*

class DiceViewModel : ViewModel() {
    private lateinit var date: String
    private var rolls = 0
    private var score = 0

    private var _side = MutableLiveData<Int>()
    val side: LiveData<Int> = _side

    private var _games = MutableLiveData<MutableList<Game>>(mutableListOf())
    val games: LiveData<MutableList<Game>> = _games

    init {
        reset()
    }

    private fun setDate() {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault())
        date = formatter.format(calendar.time)
    }

    private fun reset() {
        setDate()
        rolls = 0
        score = 0
        canDiceRoll()
    }

    fun canDiceRoll(): Boolean {
        if (rolls < 10) {
            rolls++
            val number = ((1).rangeTo(6)).toList().random()

            _side.value = when (number) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

            val luckyNumber = ((1).rangeTo(6)).toList().random()
            if (number == luckyNumber) {
                score++
            }
            return true
        }
        else {
            _games.value?.add(Game(date = date, score = score))
            reset()
            return false
        }
    }
}