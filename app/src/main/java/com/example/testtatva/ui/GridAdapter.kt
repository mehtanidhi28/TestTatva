package com.example.testtatva.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testtatva.R
import com.google.android.material.button.MaterialButton

class GridAdapter(private val gridSize: Int, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<GridAdapter.GridHolder>() {

    class GridHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var button: MaterialButton? = null
    private var context: Context? = null
    private var viewList = ArrayList<MaterialButton>()
    private var currentIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        return GridHolder(view)
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        holder.apply {
            button = itemView.findViewById(R.id.btnItemGrid)
            context = button?.context
            viewList.add(button as MaterialButton)
            button?.setOnClickListener {
                if (currentIndex == position) {
                    listener.invoke(position)
                    it.apply {
                        setBackgroundColor(
                            ContextCompat.getColor(
                                context!!,
                                android.R.color.holo_blue_light
                            )
                        )
                        isClickable = false
                    }
                }
            }
        }
    }

    fun enableTile(index: Int) {
        currentIndex = index
        viewList[index].apply {
            setBackgroundColor(
                ContextCompat.getColor(
                    context!!,
                    android.R.color.holo_red_light
                )
            )
            isClickable = true
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = gridSize * gridSize
}