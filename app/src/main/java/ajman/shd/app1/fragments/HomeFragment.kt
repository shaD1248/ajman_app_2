package ajman.shd.app1.fragments

import ajman.shd.app1.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ajman.shd.app1.databinding.FragmentHomeBinding
import ajman.shd.app1.entities.JoistDesign
import ajman.shd.app1.adapters.JoistDesignAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val joistDesigns = mutableListOf(
        JoistDesign(1000.0, 0.02),
        JoistDesign(600.0, 0.02),
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
        val recyclerView: RecyclerView = binding.root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = JoistDesignAdapter(joistDesigns) {
            loadJoistDetailFragment(it)
        }
    }

    private fun addListenerForCreateButton() {
        binding.root.findViewById<FloatingActionButton?>(R.id.fabAddJoist).setOnClickListener {
            val joistDesign = JoistDesign(600.0, 0.02)
            joistDesigns.add(0, joistDesign)
            loadJoistDetailFragment(joistDesign)
        }
    }

    private fun loadJoistDetailFragment(joistDesign: JoistDesign) {
        val joistDesignFragment = JoistDesignFragment()
        joistDesignFragment.arguments = Bundle().apply {
            putParcelable("joistDesign", joistDesign)
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