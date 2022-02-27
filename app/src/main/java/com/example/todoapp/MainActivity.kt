package com.example.todoapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager =LinearLayoutManager(this)

        fab.setOnClickListener {_ ->
            val alert= AlertDialog.Builder(this)
            alert.setTitle("Add To Do Items")
            alert.setMessage("Enter items to add")

            val editTasks=EditText(this)
            alert.setView(editTasks)

            alert.setPositiveButton("Add"){dialog,_ ->
                val edittext = editTasks.text.toString()
                if(edittext.isNotEmpty())
                {
                    val todo = Todo(edittext)
                    todoAdapter.todoAdd(todo)
                    Toast.makeText(this,"Task is added and saved",Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this,"Invalid input please try again",Toast.LENGTH_LONG).show()
                }
                editTasks.text.clear()
                dialog.dismiss()

            }
            alert.setNegativeButton("cancel"){dialog,_ ->
                Toast.makeText(this,"clicked cancel ",Toast.LENGTH_LONG).show()
                editTasks.text.clear()
                dialog.dismiss()
            }
            alert.show()
        }

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#5F5F5F")))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteAll ->{
                todoAdapter.deleteAll()
                true
            }
            R.id.deleteDone ->{
                todoAdapter.deleteDoneTodos()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }
}