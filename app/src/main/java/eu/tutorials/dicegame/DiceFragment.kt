package eu.tutorials.dicegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import eu.tutorials.dicegame.databinding.FragmentDiceBinding
import eu.tutorials.dicegame.model.DiceViewModel

class DiceFragment : Fragment() {

    private val diceViewModel: DiceViewModel by viewModels()
    private lateinit var binding: FragmentDiceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dice, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            diceFragment = this@DiceFragment
            viewModel = diceViewModel
        }
    }

    fun onRoll() {
        diceViewModel.setDiceSide()
    }
}