package com.example.recyclerviewappintent

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(
        private var list: List<Todo>,
        private val onClick:(index: Int) -> Unit,
        private val onClickDel:(index: Int, indexDb:Int) -> Unit,
    ):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //Создает View и кладет его во ViewHolder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // Обновлдяет свое содержимое для элемента, который оказался на экране
        holder.textView.text = list[position].content

        holder.button.setOnClickListener {
            onClick(holder.adapterPosition)
        }

        holder.buttonDel.setOnClickListener {
            if(holder.adapterPosition>-1){
                onClickDel(holder.adapterPosition, list[holder.adapterPosition].id)
            }
        }

    }


    override fun getItemCount(): Int = list.size // Общее число элементов

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { //  Класс для доступа к элеменету списка
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val button = itemView.findViewById<Button>(R.id.buttonGo)
        val buttonDel = itemView.findViewById<Button>(R.id.buttonDel)
    }
}
