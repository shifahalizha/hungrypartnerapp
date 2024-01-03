package com.example.hungrypartner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.GridLayout
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hungrypartner.databinding.ActivityMain2Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var dataList: ArrayList<DataClass2>
    private lateinit var adapter: MyAdapter
    var databaseReference:DatabaseReference? = null
    var eventListener:ValueEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this@MainActivity2, 1)
        binding.recylerView.layoutManager = gridLayoutManager

        val builder = AlertDialog.Builder(this@MainActivity2)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        dataList = ArrayList()
        adapter = MyAdapter(this@MainActivity2,dataList)
        binding.recylerView.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("hungrypartner")
        dialog.show()

        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass2 = itemSnapshot.getValue(DataClass2::class.java)
                    if(dataClass2 != null){
                        dataList.add(dataClass2)
                    }

                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }

        })

        binding.fab.setOnClickListener{
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }

        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchList(newText)
                return true
            }

        })


    }
    //After ON CREATE
    fun searchList(text: String){
        val searchList = ArrayList<DataClass2>()
        for (dataClass in dataList){
            if(dataClass.dataTitle?.lowercase()?.contains(text.lowercase()) == true){
                searchList.add(dataClass)
            }
        }
        adapter.searchDataList(searchList)
    }
}