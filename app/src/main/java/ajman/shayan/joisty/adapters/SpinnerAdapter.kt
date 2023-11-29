package ajman.shayan.joisty.adapters

import ajman.shayan.joisty.services.getResourceIdForEnum
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SpinnerAdapter(
    context: Context,
    values: Array<out Enum<*>>,
) : ArrayAdapter<Enum<*>>(context, android.R.layout.simple_spinner_item, values) {

    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        replaceEnumResourceInView(position, view)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        replaceEnumResourceInView(position, view)
        return view
    }

    private fun replaceEnumResourceInView(position: Int, view: View) {
        val item = getItem(position)
        if (item is Enum<*>) {
            val stringResourceId = getResourceIdForEnum(item)
            (view as? TextView)?.text =
                if (stringResourceId != 0) context.getString(stringResourceId) else item.toString()
        }
    }
}