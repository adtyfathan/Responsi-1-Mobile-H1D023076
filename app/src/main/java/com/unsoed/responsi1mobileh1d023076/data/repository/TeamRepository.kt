package com.unsoed.responsi1mobileh1d023076.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unsoed.responsi1mobileh1d023076.data.model.SearchResponse
import com.unsoed.responsi1mobileh1d023076.data.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamRepository {
    private val _teamData = MutableLiveData<SearchResponse>()
    val teamData: LiveData<SearchResponse> get() = _teamData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getTeamDetail(teamId: Int, token: String) {
        RetrofitInstance.api.getTeamDetail(teamId, token)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _teamData.postValue(response.body())
                    } else {
                        _error.postValue("Gagal memuat data: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    _error.postValue(t.message ?: "Terjadi kesalahan jaringan")
                }
            })
    }
}