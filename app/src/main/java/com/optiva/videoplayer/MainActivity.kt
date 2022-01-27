package com.optiva.videoplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.optiva.videoplayer.data.CategoriesEntity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataviewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        dataviewModel.getAllData()
        val list: ListView = findViewById(R.id.catListid) as ListView

        dataviewModel.categories.observe(this, Observer { categories->
            if(categories.size>0){
                val adapter = ListCategoriesAdapter(applicationContext, categories)
                list.adapter=adapter
                list.onItemClickListener = object : AdapterView.OnItemClickListener{
                    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        var category:CategoriesEntity = categories.get(p2)
                        val intent = Intent(applicationContext,VideoActivity::class.java)
                            intent.putExtra("cat",category.id)
                            intent.putExtra("type",category.type)
                            startActivity(intent)
                    }
                }
            }
        })
    }
}