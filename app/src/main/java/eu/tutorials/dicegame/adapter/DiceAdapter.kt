package eu.tutorials.dicegame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.dicegame.R
import eu.tutorials.dicegame.model.Game

class DiceAdapter : RecyclerView.Adapter<DiceViewHolder>() {

    var games = listOf<Game>()
        set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiceViewHolder, position: Int) {
        holder.item_date.text = games[position].date
        holder.item_score.text = games[position].score.toString()
    }

    override fun getItemCount() = games.size
}

class DiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val item_image: ImageView = view.findViewById(R.id.item_image)
    val item_date: TextView = view.findViewById(R.id.item_date)
    val item_score: TextView = view.findViewById(R.id.item_score)
}