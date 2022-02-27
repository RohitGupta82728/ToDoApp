package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*


class TodoAdapter(private val todos:MutableList<Todo>):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
       return TodoViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
       )
    }

    private fun toggleStrikeThrough(TodoContent : TextView,checked_status:Boolean)
    {
        if(checked_status)
        {
            TodoContent.paintFlags = TodoContent.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else
        {
            TodoContent.paintFlags = TodoContent.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            TodoContent.text=currentTodo.content
            chkBox.isChecked=currentTodo.checked_status
            toggleStrikeThrough(TodoContent,currentTodo.checked_status)
            chkBox.setOnCheckedChangeListener {_, checked_status ->
                toggleStrikeThrough(TodoContent,checked_status)
                currentTodo.checked_status =!currentTodo.checked_status

            }
        }
    }

    fun todoAdd(todo: Todo)
    {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    fun deleteAll(){
        todos.clear()
        notifyDataSetChanged()
    }
    fun deleteDoneTodos(){
        todos.removeAll{todo->
            todo.checked_status
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todos.size
    }


}
