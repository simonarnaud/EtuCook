package sa.com.etucook.recycler_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sa.com.etucook.R
import sa.com.etucook.model.Meal

class MealRecyclerAdapter(meals: ArrayList<Meal>, listener: OnItemClickListener): RecyclerView.Adapter<MealRecyclerAdapter.RecyclerViewHolder>() {

    private var meals: List<Meal> = meals
    private var listenerMeal: OnItemClickListener = listener

    interface OnItemClickListener  {
        fun onItemClick(meal: Meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealRecyclerAdapter.RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentMeal: Meal = meals[position]
        val name = currentMeal.mealName.capitalize()
        val cost = currentMeal.mealCost.toString() + " €"

        holder.mName.text = name
        holder.mCost.text = cost

        holder.bind(currentMeal, listenerMeal)
    }

    fun addMeals(listMeals: List<Meal>) {
        meals = listMeals
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var mName = itemView.findViewById<TextView>(R.id.name_meal)
        var mCost = itemView.findViewById<TextView>(R.id.cost_meal)

        fun bind(meal: Meal, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(meal) }
        }
    }
}