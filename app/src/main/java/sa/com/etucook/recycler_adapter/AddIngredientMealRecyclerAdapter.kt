package sa.com.etucook.recycler_adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sa.com.etucook.model.Ingredient
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.add_ingredient_meal_item_list.view.*

class AddIngredientMealRecyclerAdapter(private val listener: OnItemClickListener): ListAdapter<Ingredient, AddIngredientMealRecyclerAdapter.RecyclerViewHolder>(IngredientDiff()) {

    interface OnItemClickListener {
        fun onItemClick(ingredientId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerViewHolder(sa.com.etucook.databinding.AddIngredientMealItemListBinding.inflate(LayoutInflater.from(parent.context)), listener)

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) = holder.bind(getItem(position))

    class RecyclerViewHolder(private val binding: sa.com.etucook.databinding.AddIngredientMealItemListBinding, listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.checkbox.setOnClickListener { binding.ingredient?.let { listener.onItemClick(it.id)} }
            /*for(ingredient in ingredientIds) {
                itemView.checkbox.isChecked = ingredient.id.equals(it.id)
            }*/
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
