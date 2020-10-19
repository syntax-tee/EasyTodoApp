package com.app.taiye.todocleanarchitecture.ui.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.taiye.todocleanarchitecture.R
import com.app.taiye.todocleanarchitecture.data.Priority
import com.app.taiye.todocleanarchitecture.data.model.ToDoData
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    var dataList = emptyList<ToDoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemView.title_txt.text = dataList[position].title
        holder.itemView.description_txt.text = dataList[position].description
        holder.itemView.row_background.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }
        when(dataList[position].priority){
            Priority.HIGH -> holder.itemView.priority_indicator.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.red))
            Priority.MEDIUM -> holder.itemView.priority_indicator.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.yellow))
            Priority.LOW -> holder.itemView.priority_indicator.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.green))
        }
    }



    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    fun setData(toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }

}