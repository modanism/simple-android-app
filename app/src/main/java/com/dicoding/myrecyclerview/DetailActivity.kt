package com.dicoding.myrecyclerview

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity(), View.OnClickListener {

//    private lateinit var listHero : ArrayList<Hero>
//
    companion object {
        const val KEY_HERO = "key_hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_HERO, Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_HERO)
        }

        val abDetailName : TextView = findViewById(R.id.ab_detail_name)
        val tvDetailName : TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription : TextView = findViewById(R.id.tv_detail_description)
        val tvDetailPhoto : ImageView = findViewById(R.id.tv_detail_photo)
        val tvDetailRealName : TextView = findViewById(R.id.tv_detail_real_name)
        val action_share : ImageButton = findViewById(R.id.action_share)
        val backButton : ImageButton = findViewById(R.id.back_button)

        abDetailName.text = dataHero?.name?.lowercase()
        tvDetailName.text = dataHero?.name
        tvDetailDescription.text = dataHero?.description
        if (dataHero != null) {
            tvDetailPhoto.setImageResource(dataHero.photo)
        }
        tvDetailRealName.text = dataHero?.real_name

        action_share.setOnClickListener(this)
        backButton.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back_button -> {
                finish()
            }
            R.id.action_share -> {
                val desc : TextView = findViewById(R.id.tv_detail_description)
                val real_name : TextView = findViewById(R.id.tv_detail_real_name)
                val shareText = real_name.text.toString() + " " + desc.text.toString()

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}