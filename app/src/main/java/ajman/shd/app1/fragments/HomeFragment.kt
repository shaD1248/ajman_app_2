package ajman.shd.app1.fragments

import ajman.shd.app1.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ajman.shd.app1.databinding.FragmentHomeBinding
import ajman.shd.app1.entities.JoistDesign
import ajman.shd.app1.adapters.JoistDesignAdapter
import ajman.shd.app1.ui.home.HomeViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        showJoistDesigns(root)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    private fun showJoistDesigns(root: View) {
        val joistDesigns = listOf(
            JoistDesign(1000.0, 0.02),
            JoistDesign(600.0, 0.02),
        )

        val recyclerView: RecyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = JoistDesignAdapter(joistDesigns) { selectedDesign ->
            val bundle = Bundle().apply {
                putParcelable("joistDesign", selectedDesign)
            }

            val joistDesignFragment = JoistDesignFragment()
            joistDesignFragment.arguments = bundle

            // Perform fragment transaction to show JoistDesignFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, joistDesignFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}