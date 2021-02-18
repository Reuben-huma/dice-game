package eu.tutorials.dicegame

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import eu.tutorials.dicegame.adapter.DiceAdapter
import eu.tutorials.dicegame.databinding.FragmentResultsBinding
import eu.tutorials.dicegame.model.DiceViewModel

class ResultsFragment : Fragment() {

    private var binding: FragmentResultsBinding? = null
    private val viewModel: DiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("DiceFragment", "onCreateView()")
        binding = FragmentResultsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("DiceFragment", "onViewCreated(side) ${viewModel.side.value}")
        Log.d("DiceFragment", "onViewCreated(game) ${viewModel.games.value}")

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            resultsFragment = this@ResultsFragment
            resultsViewModel = viewModel
            recyclerView.adapter = DiceAdapter(viewModel.games.value!!)
            recyclerView.setHasFixedSize(true)
        }
    }

}