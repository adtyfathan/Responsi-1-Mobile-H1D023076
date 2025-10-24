package com.unsoed.responsi1mobileh1d023076

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.responsi1mobileh1d023076.databinding.ActivityTeamSquadBinding
import com.unsoed.responsi1mobileh1d023076.ui.adapter.SquadAdapter
import com.unsoed.responsi1mobileh1d023076.ui.fragment.PlayerDetailFragment
import com.unsoed.responsi1mobileh1d023076.viewmodel.TeamViewModel

class TeamSquadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamSquadBinding
    private lateinit var viewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamSquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        val teamId = 63
        viewModel.loadTeam(teamId, "5d363938c2ea47d1b40b050dc0055840")

        binding.rvSquad.layoutManager = LinearLayoutManager(this)

        viewModel.teamData.observe(this) { team ->
            val adapter = SquadAdapter(team.squad) { player ->
                PlayerDetailFragment(
                    player.name,
                    player.dateOfBirth ?: "-",
                    player.nationality,
                    player.position ?: "-"
                ).show(
                    supportFragmentManager,
                    PlayerDetailFragment::class.java.simpleName
                )
            }
            binding.rvSquad.adapter = adapter
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}