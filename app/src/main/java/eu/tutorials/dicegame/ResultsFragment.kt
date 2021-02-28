package eu.tutorials.dicegame

import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import eu.tutorials.dicegame.adapters.DiceAdapter
import eu.tutorials.dicegame.databinding.FragmentResultsBinding
import eu.tutorials.dicegame.viewmodel.DiceViewModel

class ResultsFragment : Fragment() {

    private val viewModel: DiceViewModel by activityViewModels()
    private var binding: FragmentResultsBinding? = null
    private val adapter = DiceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share_item -> share()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun share() {
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setSubject(getString(R.string.share_subject))
            .setText(getString(R.string.share_text))
            .setType("text/plain")
            .intent

        try {
            startActivity(shareIntent)
        }
        catch (ex: Exception) {
            Snackbar.make(binding!!.root, "Error, Please try again!", Snackbar.LENGTH_SHORT).show()
        }
    }
}