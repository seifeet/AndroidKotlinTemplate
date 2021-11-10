package work.monkey.kotlintemplate.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import java.util.*

interface ModelDisplayName {
    val displayName: String
}

class KeyValueType(val id: Long, override val displayName: String) : ModelDisplayName

class KeyValueFilterableArrayAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val values: List<KeyValueType>):
        ArrayAdapter<KeyValueType>(context, layoutResource, values),
        Filterable {
    private var mValues: List<KeyValueType> = values

    override fun getCount(): Int {
        return mValues.size
    }

    override fun getItem(position: Int): KeyValueType {
        return mValues[position]
    }

    override fun getItemId(position: Int): Long {
        // Or just return p0
        return mValues[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView as TextView? ?: LayoutInflater.from(context)
                .inflate(layoutResource, parent, false) as TextView
        view.text = mValues[position].displayName
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                @Suppress("UNCHECKED_CAST")
                mValues = filterResults.values as List<KeyValueType>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.lowercase(Locale.getDefault())

                val filterResults = FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty()) {
                    values
                } else {
                    values.filter {
                        it.displayName.lowercase(Locale.getDefault()).contains(queryString)
                    }
                }
                return filterResults
            }
        }
    }
}
