package com.unsoed.responsi1mobileh1d023076

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.unsoed.responsi1mobileh1d023076.databinding.ActivityHeadCoachBinding
import com.unsoed.responsi1mobileh1d023076.viewmodel.TeamViewModel

class HeadCoachActivity : ComponentActivity() {
    private lateinit var binding: ActivityHeadCoachBinding
    private lateinit var viewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        binding.layoutCoachInfo.visibility = View.GONE

        val teamId = 63
        viewModel.loadTeam(teamId, "5d363938c2ea47d1b40b050dc0055840")

        viewModel.teamData.observe(this) { team ->
            val coach = team.coach
            binding.tvCoachName.text = coach.name
            binding.tvCoachBirth.text = coach.dateOfBirth ?: "-"
            binding.tvCoachNationality.text = coach.nationality

            Glide.with(this)
                .load(R.drawable.coach)
                .into(binding.imgCoach)

            binding.layoutCoachInfo.visibility = View.VISIBLE
        }

        viewModel.error.observe(this) { error ->
            binding.tvCoachName.text = error
        }
    }
}