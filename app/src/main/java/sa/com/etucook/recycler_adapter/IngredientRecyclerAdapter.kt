package sa.com.etucook.recycler_adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sa.com.etucook.model.Ingredient
import androidx.recyclerview.widget.ListAdapter

import sa.com.etucook.databinding.IngredientItemListBinding

class IngredientRecyclerAdapter(private val listener: OnItemClickListener): ListAdapter<Ingredient, IngredientRecyclerAdapter.RecyclerViewHolder>(IngredientDiff()) {

    interface OnItemClickListener {
        fun onItemClick(ingredientId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerViewHolder(IngredientItemListBinding.inflate(LayoutInflater.from(parent.context)), listener)

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) = holder.bind(getItem(position))

    class RecyclerViewHolder(private val binding: IngredientItemListBinding, listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { binding.ingredient?.let { listener.onItemClick(it.id)} }
        }

        val ingredient get() = binding.ingredient

        fun bind(ingredient: Ingredient) {
            binding.ingredient = ingredient
            binding.executePendingBindings()
        }
    }

    //Class pour tester les différences entre les objets de la liste
    private class IngredientDiff : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient) = oldItem == newItem
    }

}
