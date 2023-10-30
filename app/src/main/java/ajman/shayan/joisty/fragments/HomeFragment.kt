package ajman.shayan.joisty.fragments

import ajman.shayan.joisty.JoistyApplication
import ajman.shayan.joisty.view_models.JoistDesignViewModel
import ajman.shayan.joisty.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ajman.shayan.joisty.databinding.FragmentHomeBinding
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.adapters.JoistDesignAdapter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var joistDesigns = mutableListOf(
        JoistDesign(1000.0),
        JoistDesign(600.0),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        showJoistDesigns()
        addListenerForCreateButton()
        return binding.root
    }

    private fun showJoistDesigns() {
        val joistyApplication = requireActivity().application as JoistyApplication
        val dao = joistyApplication.database.joistDesignDao()
        val viewModel = JoistDesignViewModel(dao)
        joistDesigns = viewModel.allJoistDesigns.value?.toMutableList() ?: joistDesigns

        val recyclerView: RecyclerView = binding.root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = JoistDesignAdapter(joistDesigns) {
            loadJoistDetailFragment(it)
        }
    }

    private fun addListenerForCreateButton() {
        binding.root.findViewById<FloatingActionButton?>(R.id.fabAddJoist).setOnClickListener {
            val joistDesign = JoistDesign(600.0)
            joistDesigns.add(0, joistDesign)
            loadJoistDetailFragment(joistDesign)
        }
    }

    private fun loadJoistDetailFragment(joistDesign: JoistDesign) {
        val joistDesignFragment = JoistDesignFragment()
        joistDesignFragment.arguments = Bundle().apply {
            putParcelable("joistDesignParcelable",
                ajman.shayan.joisty.models.JoistDesignParcelable(joistDesign)
            )
        }

        // Perform fragment transaction to show JoistDesignFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, joistDesignFragment)
            .addToBackStack(null) // Optional: Add to back stack for navigation
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}