package eu.tutorials.dicegame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.dicegame.R
import eu.tutorials.dicegame.data.Game

class DiceAdapter : RecyclerView.Adapter<DiceViewHolder>() {

    var games = listOf<Game>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DiceViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: DiceViewHolder, position: Int) {
        holder.itemDate.text = games[position].date
        holder.itemScore.text = holder.context.getString(R.string.score, games[position].score)
    }

    override fun getItemCount() = games.size
}

class DiceViewHolder(val context: Context, view: View) : RecyclerView.ViewHolder(view) {
    val itemDate: TextView = view.findViewById(R.id.item_date)
    val itemScore: TextView = view.findViewById(R.id.item_score)
}