package eu.tutorials.dicegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import eu.tutorials.dicegame.databinding.FragmentDiceBinding
import eu.tutorials.dicegame.viewmodel.DiceViewModel

class DiceFragment : Fragment() {

    private val viewModel: DiceViewModel by activityViewModels()
    private lateinit var binding: FragmentDiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        if (!viewModel.canDiceRoll()) {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Dice Game")
                .setMessage("Congrats. You have completed the Game.\n Would you like to try again?")
                .setCancelable(false)
                .setNegativeButton(
                    R.string.try_again
                ) { dialogInterface, _ -> dialogInterface.dismiss() }
                .setPositiveButton(
                    R.string.next
                ) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    findNavController().navigate(R.id.action_diceFragment_to_resultsFragment)
                }
                .show()

            //val contextView = activity?.findViewById<View>(R.id.context_view)
            //Snackbar.make(contextView!!, " Game complete", Snackbar.LENGTH_SHORT).show()
        }
    }
}