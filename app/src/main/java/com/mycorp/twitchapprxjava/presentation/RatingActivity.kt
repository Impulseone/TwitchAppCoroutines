package com.mycorp.twitchapprxjava.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import com.mycorp.twitchapprxjava.databinding.ActivityRatingBinding

class RatingActivity : AppCompatActivity() {
    private lateinit var ratingActivityBinding: ActivityRatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratingActivityBinding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(ratingActivityBinding.root)

        ratingActivityBinding.ratingBarDefault.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                Toast.makeText(
                    this@RatingActivity, "рейтинг: $rating",
                    Toast.LENGTH_LONG
                ).show()
            }

        ratingActivityBinding.sendReportBtn.setOnClickListener{startActivity(
            Intent(this,
            MainActivity::class.java)
        )}
        ratingActivityBinding.backBtn.setOnClickListener{startActivity(Intent(this, MainActivity::class.java))}
    }
}