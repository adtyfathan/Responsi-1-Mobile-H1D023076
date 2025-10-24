# Responsi 1 Pemrograman Mobile

Nama        : Aditya Fathan Naufaldi<br>
NIM         : H1D023076<br>
Shift Lama  : F<br>
Shift Baru  : B

## Video Demo Aplikasi
https://github.com/user-attachments/assets/8847b2ba-3a83-4ebc-b8c2-4c63080ffd01

## Alur Aplikasi
Aplikasi ini menggunakan arsitektur MVVM dengan dukungan Retrofit, LiveData, dan ViewBinding untuk mengelola alur data secara terstruktur.

Berikut penjelasan alur datanya:

1️⃣ Pemanggilan Data dari API
- Aplikasi menggunakan Football-Data.org API untuk mengambil informasi tentang klub sepak bola, termasuk detail tim, pelatih, dan skuad pemain.
- Endpoint API diakses melalui interface Api yang didefinisikan di RetrofitInstance menggunakan Retrofit.
```
@GET("teams/{id}")
fun getTeamDetail(
    @Path("id") teamId: Int,
    @Header("X-Auth-Token") token: String
): Call<SearchResponse>
```
2️⃣ Pengelolaan Data di Repository
- TeamRepository bertugas untuk melakukan request asynchronous ke API.
- Jika respon berhasil, data JSON dari API akan otomatis diubah menjadi objek Kotlin SearchResponse menggunakan Gson Converter.
- Repository menyimpan hasilnya dalam MutableLiveData<SearchResponse> agar bisa diobservasi oleh ViewModel.
```
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
    }
)
```
3️⃣ Distribusi Data ke ViewModel
- TeamViewModel bertindak sebagai penghubung antara UI dan repository.
- ViewModel menerima data LiveData dari repository dan meneruskannya ke komponen UI.
- Karena LiveData bersifat reaktif, setiap kali data dari API berubah, ViewModel otomatis memberi notifikasi ke Activity atau Fragment yang mengamatinya.
```
class TeamViewModel : ViewModel() {
    private val repository = TeamRepository()

    val teamData: LiveData<SearchResponse> = repository.teamData
    val error: LiveData<String> = repository.error

    fun loadTeam(teamId: Int, token: String) {
        repository.getTeamDetail(teamId, token)
    }
}
```
4️⃣ Penyajian Data di Layar
- Setiap Activity mengamati perubahan dari LiveData di ViewModel.
- HeadCoachActivity menampilkan nama, tanggal lahir, dan kebangsaan pelatih, serta gambar pelatih dari resource lokal menggunakan Glide.
- TeamSquadActivity menampilkan daftar pemain dalam RecyclerView, di mana setiap kartu diberi warna berbeda sesuai posisi (Goalkeeper, Defence, Midfield, Offence).
- Ketika pengguna menekan salah satu pemain, detail pemain muncul di bagian bawah layar menggunakan BottomSheetDialogFragment.
