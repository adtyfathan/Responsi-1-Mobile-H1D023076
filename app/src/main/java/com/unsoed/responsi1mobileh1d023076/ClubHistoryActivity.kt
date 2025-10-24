package com.unsoed.responsi1mobileh1d023076

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.unsoed.responsi1mobileh1d023076.databinding.ActivityClubHistoryBinding
import android.text.Html

class ClubHistoryActivity : ComponentActivity() {
    private lateinit var binding: ActivityClubHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = "63. Fulham FC's History"
        binding.tvContent.text = Html.fromHtml(
            getString(R.string.club_history_text),
            Html.FROM_HTML_MODE_LEGACY
        )
    }
}