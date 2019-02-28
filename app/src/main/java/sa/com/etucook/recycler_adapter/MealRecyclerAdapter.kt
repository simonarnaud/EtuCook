package sa.com.etucook.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import sa.com.etucook.databinding.MealItemListBinding
import sa.com.etucook.model.Meal

class MealRecyclerAdapter(private val listener: OnItemClickListener): ListAdapter<Meal, MealRecyclerAdapter.RecyclerViewHolder>(MealDiff()) {

    interface OnItemClickListener {
        fun onItemClick(mealId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerViewHolder(sa.com.etucook.databinding.MealItemListBinding.inflate(LayoutInflater.from(parent.context)), listener)

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) = holder.bind(getItem(position))

    class RecyclerViewHolder(private val binding: MealItemListBinding, listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { binding.meal?.let { listener.onItemClick(it.id)} }
        }

        val meal get() = binding.meal

        fun bind(meal: Meal) {
            binding.meal = meal
            binding.executePendingBindings()
        }
    }

    //Class pour tester les différences entre les objets de la liste
    private class MealDiff : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
    }

}
