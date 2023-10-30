package ajman.shayan.joisty.adapters

import ajman.shayan.joisty.R
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.models.structure.m
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JoistDesignAdapter(
    private val designs: List<JoistDesign>,
    private val onItemClick: (JoistDesign) -> Unit
) : RecyclerView.Adapter<JoistDesignViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoistDesignViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_joist_design, parent, false)
        return JoistDesignViewHolder(view)
    }

    override fun onBindViewHolder(holder: JoistDesignViewHolder, position: Int) {
        val design = designs[position]
        holder.bind(design)
        holder.itemView.setOnClickListener { onItemClick(design) }
    }

    override fun getItemCount(): Int = designs.size
}

class JoistDesignViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(joistDesign: JoistDesign) {
        itemView.findViewById<TextView>(R.id.textProjectName).text = joistDesign.projectName
        itemView.findViewById<TextView>(R.id.textJoistInfo).text = (joistDesign.L / m).toString() + "m - " + joistDesign.occupancy
//        itemView.findViewById<TextView>(R.id.textSteelJoistDetails).text = "Joist Details"
    }
}
