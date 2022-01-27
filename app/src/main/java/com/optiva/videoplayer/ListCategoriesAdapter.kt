package com.optiva.videoplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.optiva.videoplayer.data.CategoriesEntity

class ListCategoriesAdapter(val context:Context, val list: List<CategoriesEntity>) : BaseAdapter(){

    private val inflator: LayoutInflater
    init{
        this.inflator = LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view:View = View.inflate(context,R.layout.categori_list_item,null)
        val text:TextView = view.findViewById(R.id.listTitle)

        var item:CategoriesEntity = list.get(p0)
        text.text = item.title

        return view
    }
}