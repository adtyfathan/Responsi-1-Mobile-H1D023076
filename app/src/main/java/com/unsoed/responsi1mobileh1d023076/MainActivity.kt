package com.unsoed.responsi1mobileh1d023076

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.unsoed.responsi1mobileh1d023076.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, ClubHistoryActivity::class.java))
        }

        binding.btnCoach.setOnClickListener {
            startActivity(Intent(this, HeadCoachActivity::class.java))
        }

        binding.btnSquad.setOnClickListener {
            startActivity(Intent(this, TeamSquadActivity::class.java))
        }
    }
}