package com.unsoed.responsi1mobileh1d023076.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023076.data.model.Player
import com.unsoed.responsi1mobileh1d023076.R

class SquadAdapter(
    private val players: List<Player>,
    private val onClick: (Player) -> Unit
) : RecyclerView.Adapter<SquadAdapter.SquadViewHolder>() {
    inner class SquadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvPlayerName)
        val tvNationality: TextView = itemView.findViewById(R.id.tvPlayerNationality)
        val card: LinearLayout = itemView.findViewById(R.id.playerCard) ?: itemView as LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return SquadViewHolder(view)
    }

    override fun onBindViewHolder(holder: SquadViewHolder, position: Int) {
        val player = players[position]
        holder.tvName.text = player.name
        holder.tvNationality.text = player.nationality

        val color = when {
            player.position?.contains("Goalkeeper", true) == true -> Color.YELLOW
            player.position?.contains("Back", true) == true -> Color.BLUE
            player.position?.contains("Midfield", true) == true -> Color.GREEN
            player.position?.contains("Winger", true) == true || player.position?.contains("Forward", true) == true || player.position?.contains("Offence", true) == true -> Color.RED
            else -> Color.LTGRAY
        }
        holder.card.setBackgroundColor(color)

        holder.itemView.setOnClickListener { onClick(player) }
    }

    override fun getItemCount() = players.size
}