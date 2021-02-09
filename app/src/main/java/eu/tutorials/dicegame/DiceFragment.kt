package eu.tutorials.dicegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import eu.tutorials.dicegame.databinding.FragmentDiceBinding
import eu.tutorials.dicegame.model.DiceViewModel

class DiceFragment : Fragment() {

    private val viewModel: DiceViewModel by viewModels()
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
            diceViewModel = viewModel
        }
    }

    fun onRoll() {
        if(!viewModel.canDiceRoll()) {
            val contextView = activity?.findViewById<View>(R.id.context_view)
            Snackbar.make(contextView!!, " Game complete", Snackbar.LENGTH_SHORT).show()
        }
    }
}