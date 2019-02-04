package sa.com.etucook.recycler_adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sa.com.etucook.R
import sa.com.etucook.model.Ingredient


class IngredientRecyclerAdapter(ingredients: ArrayList<Ingredient>, listener: OnItemClickListener): RecyclerView.Adapter<IngredientRecyclerAdapter.RecyclerViewHolder>() {

    private var ingredients: List<Ingredient> = ingredients
    private var listenerIngredient: OnItemClickListener  = listener

    interface OnItemClickListener {
        fun onItemClick(ingredient: Ingredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientRecyclerAdapter.RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: IngredientRecyclerAdapter.RecyclerViewHolder, position: Int) {
        val currentIngredient: Ingredient = ingredients[position]
        val name = currentIngredient.ingredientName.capitalize()
        val cost = currentIngredient.ingredientCost.toString() + " €"

        holder.mName.text = name
        holder.mCost.text = cost

        holder.bind(currentIngredient, listenerIngredient)
    }

    fun addIngredients(listIngredients: List<Ingredient>) {
        ingredients = listIngredients
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var mName = itemView.findViewById<TextView>(R.id.name_ingredient)
        var mCost = itemView.findViewById<TextView>(R.id.cost_ingredient)

        fun bind(ingredient: Ingredient, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(ingredient)
            }
        }
    }
}
