package eu.tutorials.dicegame.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.tutorials.dicegame.R

class DiceViewModel : ViewModel() {
    private val _side = MutableLiveData<Int>()
            val side: LiveData<Int>
                get() = _side

    init { setDiceSide() }

    fun setDiceSide() {
        val number = ((1).rangeTo(6)).toList().random()

        _side.value = when(number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}