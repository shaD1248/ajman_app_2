package ajman.shayan.joisty.fragments

import ajman.shayan.joisty.JoistyApplication
import ajman.shayan.joisty.R
import ajman.shayan.joisty.activities.JoistDesignActivity
import ajman.shayan.joisty.adapters.JoistDesignAdapter
import ajman.shayan.joisty.adapters.JoistDesignViewHolder
import ajman.shayan.joisty.databinding.FragmentHomeBinding
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.view_models.JoistDesignViewModel
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var repo: JoistDesignViewModel
    private var recyclerView: RecyclerView? = null
    private var updateView = false
    private var deleteAction: MenuItem? = null

    var joistDesigns = mutableListOf<JoistDesign>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        postDelayed()
        addListenerForCreateButton()
        return binding.root
    }

    private fun postDelayed() {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (updateView) {
                    recyclerView = binding.root.findViewById(R.id.recyclerView)
                    val adapter = recyclerView?.adapter as JoistDesignAdapter?
                    if (adapter == null) {
                        recyclerView?.adapter =
                            JoistDesignAdapter(joistDesigns, getOnItemClick(), getOnItemLongClick())
                    } else {
                        adapter.updateJoistDesigns(joistDesigns)
                    }
                    updateView = false
                }
                mainHandler.postDelayed(this, 1000)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setJoistDesignAdapter()
    }

    private fun setJoistDesignAdapter() {
        val application = requireActivity().application as JoistyApplication
        repo = application.repo
        val updateRecyclerView = {joistDesigns: MutableList<JoistDesign> ->
            this.joistDesigns = joistDesigns
            updateView = true
        }

        repo.loadAllJoists(updateRecyclerView)
    }

    private fun getOnItemClick() = { joistDesign: JoistDesign, holder: JoistDesignViewHolder ->
        if (joistDesign.selected) {
            joistDesign.selected = false
            onItemSelectionChanged(joistDesign, holder)
        } else {
            loadJoistDesignActivity(joistDesign)
        }
    }

    private fun loadJoistDesignActivity(joistDesign: JoistDesign) {
        val intent = Intent(requireContext(), JoistDesignActivity::class.java)
        intent.putExtra("joistDesignId", joistDesign.id)
        intent.putExtra("projectName", joistDesign.projectName)
        startActivity(intent)
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

    private fun addListenerForCreateButton() {
        binding.root.findViewById<FloatingActionButton?>(R.id.fabAddJoist).setOnClickListener {
            val joistDesign = JoistDesign(600.0)
            joistDesign.projectName = getString(R.string.default_project_name)
            joistDesigns.add(0, joistDesign)
            loadJoistDesignActivity(joistDesign)
            recyclerView?.adapter?.notifyDataSetChanged()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                if (menu.findItem(R.id.action_delete) == null) {
                    menuInflater.inflate(R.menu.main, menu)
                }
                deleteAction = menu.findItem(R.id.action_delete)
                updateDeleteActionVisibility()
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                var changed = false
                joistDesigns.removeIf {
                    changed = changed or it.selected
                    if (it.selected) repo.delete(it)
                    it.selected
                }
                if (changed) {
                    updateDeleteActionVisibility()
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
                return true
            }
        }, this, Lifecycle.State.RESUMED)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updateDeleteActionVisibility() {
        deleteAction?.isVisible = joistDesigns.count { it.selected } > 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}