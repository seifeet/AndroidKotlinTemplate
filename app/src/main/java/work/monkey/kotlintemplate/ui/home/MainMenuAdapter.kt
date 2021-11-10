package work.monkey.kotlintemplate.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import work.monkey.kotlintemplate.R
import work.monkey.kotlintemplate.databinding.RowItemHeaderMainMenuBinding
import work.monkey.kotlintemplate.databinding.RowItemMainMenuBinding

class MainMenuAdapter : ListAdapter<MainMenuType, MainMenuAdapter.ViewHolder>(
    MainMenuDiffCallback()
) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val menuViewType = MainMenuViewType.from(viewType)
        return ViewHolder.from(parent, menuViewType)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item.viewType.id
    }

    class ViewHolder private constructor(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: MainMenuType) {
            if (binding is RowItemMainMenuBinding) {
                binding.itemTextView.text = item.text
                binding.itemIcon.setImageResource(item.resourceId)
                binding.executePendingBindings()
            } else if (binding is RowItemHeaderMainMenuBinding) {
                binding.itemTextView.text = item.text
                binding.executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup, viewType: MainMenuViewType): ViewHolder {

                var layoutId = R.layout.row_item_header_main_menu
                if (viewType == MainMenuViewType.ITEM) {
                    layoutId = R.layout.row_item_main_menu
                }

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding : ViewDataBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    layoutId,
                    parent,
                    false
                )
                return ViewHolder(binding)
            }

        }
    }
}

class MainMenuDiffCallback : DiffUtil.ItemCallback<MainMenuType>() {

    override fun areItemsTheSame(oldItem: MainMenuType, newItem: MainMenuType): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: MainMenuType, newItem: MainMenuType): Boolean {
        return oldItem == newItem
    }
}