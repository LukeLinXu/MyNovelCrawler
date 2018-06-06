package com.foreceipt.mybooks

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_content.*

/**
 * Created by lukelin on 2018-06-06.
 */
class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        val index = intent.getIntExtra("data", -1)
        val novel = Utils.getNovel(index)
        content.text = novel.id.toString() + "\n" + novel.content
        next.setOnClickListener{
            val intent = Intent(this@ContentActivity, ContentActivity::class.java)
            intent.putExtra("data", index + 1)
            startActivity(intent)
            finish()
        }
    }
}