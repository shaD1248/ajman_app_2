package ajman.shayan.joisty.adapters

import ajman.shayan.joisty.R
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.models.structure.m
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class JoistDesignAdapter(
    private val joistDesigns: List<JoistDesign>,
    private val onItemClick: (JoistDesign, JoistDesignViewHolder) -> Unit,
    private val onItemLongClick: (JoistDesign, JoistDesignViewHolder) -> Unit
) : RecyclerView.Adapter<JoistDesignViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoistDesignViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_joist_design, parent, false)
        return JoistDesignViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: JoistDesignViewHolder, position: Int) {
        val joistDesign = joistDesigns[position]
        holder.bind(joistDesign)
        holder.itemView.setOnClickListener { onItemClick(joistDesign, holder) }
        holder.itemView.setOnLongClickListener {
            onItemLongClick(joistDesign, holder)
            notifyItemChanged(position)
            true
        }
    }

    override fun getItemCount(): Int = joistDesigns.size
}

class JoistDesignViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val checkIcon: ImageView = itemView.findViewById(R.id.checkIcon)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(joistDesign: JoistDesign) {
        itemView.findViewById<TextView>(R.id.textProjectName).text = joistDesign.projectName
        itemView.findViewById<TextView>(R.id.textJoistInfo).text = (joistDesign.L / m).toString() + "m - " + joistDesign.occupancy
        itemView.findViewById<TextView>(R.id.textSteelJoistDetails).text = joistDesign.joistArrangement.toString() + ", " + joistDesign.steelSectionDetails
        checkIcon.visibility = if (joistDesign.selected) View.VISIBLE else View.GONE
    }
}
