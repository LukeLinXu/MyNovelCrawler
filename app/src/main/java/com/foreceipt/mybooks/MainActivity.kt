package com.foreceipt.mybooks

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.realm.Realm
import org.jsoup.Jsoup
import org.jsoup.nodes.TextNode


class MainActivity : AppCompatActivity() {
    val base_url = "https://www.qu.la"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?): Unit {
                val doc = Jsoup.connect("$base_url/book/55970/").get()
                for (item in doc.select("a[href]")){
//                    Log.d("Luke", (item.childNode(0) as TextNode).wholeText)
                    val numFromName = Utils.getNumFromName((item.childNode(0) as TextNode).wholeText)
//                    Log.d("Luke", numFromName.toString())
//                    Log.d("Luke", item.attr("href"))
                    if(numFromName > 0){
                        val singleNovel = SingleNovel()
                        singleNovel.id = numFromName
                        singleNovel.url = item.attr("href")
                        Utils.addOrUpdate(singleNovel)
                    }
                }

                for (item in Utils.getNotDownloadNovelList()){
//                    Log.d("Luke", item.id.toString()+","+item.url)
                    item.content = getContent(item)
                    Utils.addOrUpdate(item)
                    Log.d("Luke", item.id.toString() + "Done")
                }

            }
        }.execute()

    }

    fun getContent(item: SingleNovel): String{
        val url = item.url
//        val url = "/book/55970/3049012.html"
        val doc = Jsoup.connect(base_url + url).get()
        return (doc.getElementById("content").childNode(0) as TextNode).wholeText
    }

}
