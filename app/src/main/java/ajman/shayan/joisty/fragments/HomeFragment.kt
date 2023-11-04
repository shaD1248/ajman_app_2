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
import ajman.shayan.joisty.adapters.JoistDesignViewHolder
import android.os.Build
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var recyclerView: RecyclerView? = null
    private var deleteAction: MenuItem? = null

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
        addMenuProvider()
        setJoistDesignAdapter()
        addListenerForCreateButton()
        return binding.root
    }

    private fun addMenuProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                if (menu.findItem(R.id.action_delete) == null) {
                    menuInflater.inflate(R.menu.main, menu)
                }
                deleteAction = menu.findItem(R.id.action_delete)
                updateDeleteActionVisibility()
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                joistDesigns.removeIf { it.selected }
                updateDeleteActionVisibility()
                recyclerView?.adapter?.notifyDataSetChanged()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setJoistDesignAdapter() {
        val application = requireActivity().application as JoistyApplication
        val dao = application.database.joistDesignDao()
        val viewModel = JoistDesignViewModel(dao)
        joistDesigns = viewModel.allJoistDesigns.value?.toMutableList() ?: joistDesigns

        recyclerView = binding.root.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter =
            JoistDesignAdapter(joistDesigns, getOnItemClick(), getOnItemLongClick())
    }

    private fun getOnItemClick() = { joistDesign: JoistDesign, holder: JoistDesignViewHolder ->
        if (joistDesign.selected) {
            joistDesign.selected = false
            onItemSelectionChanged(joistDesign, holder)
        } else {
            loadJoistDetailFragment(joistDesign)
        }
    }

    private fun getOnItemLongClick() = { joistDesign: JoistDesign, holder: JoistDesignViewHolder ->
        if (!joistDesign.selected) {
            joistDesign.selected = true
            onItemSelectionChanged(joistDesign, holder)
        }
    }

    private fun onItemSelectionChanged(joistDesign: JoistDesign, holder: JoistDesignViewHolder) {
        holder.checkIcon.visibility = if (joistDesign.selected) View.VISIBLE else View.GONE
        updateDeleteActionVisibility()
    }

    private fun updateDeleteActionVisibility() {
        deleteAction?.isVisible = joistDesigns.count { it.selected } > 0
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
        onDestroyView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}