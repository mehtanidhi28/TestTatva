package com.example.testtatva.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testtatva.R
import com.google.android.material.button.MaterialButton

class GridAdapter(private val gridSize: Int, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<GridAdapter.GridHolder>() {

    class GridHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        return GridHolder(view)
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        holder.apply {
            val button = itemView.findViewById<MaterialButton>(R.id.btnItemGrid)
            val context = button.context
            button.setOnClickListener {
                listener.invoke(position)
                it.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.holo_blue_dark
                    )
                )
            }
        }
    }

    fun enableTile(count: Int?) {
        print(count)
        val totalSize = gridSize * gridSize
    }

    override fun getItemCount(): Int = gridSize * gridSize
}