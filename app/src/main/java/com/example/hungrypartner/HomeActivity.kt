package com.example.hungrypartner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    lateinit var TextView: TextView


    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: AdapterClass
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        TextView =findViewById(R.id.txt_view)
        TextView.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        TextView =findViewById(R.id.textview10)
        TextView.setOnClickListener {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                }




        imageList = arrayOf(
            R.drawable.jenkhugs,
            R.drawable.love_mochi__you_can_make_your_own_at_home_with_these_3_easy_and_delicious_recipes,
            R.drawable.homemade_shoyu_ramen_noodles__soy_sauce_flavored_chuka_soba____sudachi
        )

        titleList = arrayOf(
            "Cromboloni",
            "Mochi",
            "Shoyu Ramen"
        )

        descList = arrayOf(
            getString(R.string.Mochi),
            getString(R.string.Ramen),
            getString(R.string.Mochi)
        )

        detailImageList = arrayOf(
            R.drawable.jenkhugs,
            R.drawable.love_mochi__you_can_make_your_own_at_home_with_these_3_easy_and_delicious_recipes,
            R.drawable.homemade_shoyu_ramen_noodles__soy_sauce_flavored_chuka_soba____sudachi
        )


        recyclerView = findViewById(R.id.recylerView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        searchList = arrayListOf<DataClass>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataList.forEach {
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)){
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false

            }
        })
        myAdapter = AdapterClass(searchList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], detailImageList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(dataList)
    }
}