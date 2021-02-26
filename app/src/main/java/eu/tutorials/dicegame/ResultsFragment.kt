package eu.tutorials.dicegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import eu.tutorials.dicegame.adapter.DiceAdapter
import eu.tutorials.dicegame.databinding.FragmentResultsBinding
import eu.tutorials.dicegame.model.DiceViewModel

class ResultsFragment : Fragment() {

    private val viewModel: DiceViewModel by activityViewModels()
    private var binding: FragmentResultsBinding? = null
    private val adapter = DiceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            resultsFragment = this@ResultsFragment
            resultsViewModel = viewModel
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }

        viewModel.games.observe(viewLifecycleOwner){
            adapter.games = it
        }
    }

}