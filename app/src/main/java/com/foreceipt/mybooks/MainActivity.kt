package com.foreceipt.mybooks

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.TextNode


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?): Unit {
                val doc = Jsoup.connect("https://www.qu.la/book/55970/").get()
                Log.d("Luke", doc.title())
                for (item in doc.select("a[href]")){
                    Log.d("Luke", (item.childNode(0) as TextNode).wholeText)
                }

            }
        }.execute()

    }


}
