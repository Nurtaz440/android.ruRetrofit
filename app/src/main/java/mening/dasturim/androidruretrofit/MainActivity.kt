package mening.dasturim.androidruretrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mening.dasturim.androidruretrofit.data.adapter.MoviesAdapter
import mening.dasturim.androidruretrofit.retrofit.MoviApiClient

class MainActivity : AppCompatActivity() {


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Вызываем метод getTopRatedMovies()
        // Получаем Single
        val getTopRatedMovies = MoviApiClient.apiClient.getTopRatedMovies("ee1f67b218538f137d2b6c2892761ff9")
        getTopRatedMovies
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val movies = it.results
                    // Передаем результат в adapter и отображаем элементы
                    recyclerView.adapter = MoviesAdapter(movies, R.layout.list_item_movie)
                },
                { error ->
                    // Логируем ошибку
                    Log.e(TAG, error.toString())
                }
            )
    }
    companion object {
        private val TAG = MainActivity::class.java.simpleName
        // TODO - insert your themoviedb.org API KEY here
        private val API_KEY = ""
    }
}