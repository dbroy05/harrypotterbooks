package service

import io.reactivex.Observable
import model.Book
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Service to get the list of books
 */

interface BookListService {
    @GET("/books.json")
    fun getBookList() : Observable<List<Book>>

    companion object Factory {
        private val SERVER_URL = "http://de-coding-test.s3.amazonaws.com"

        fun create() : BookListService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(SERVER_URL)
                    .build()

            return retrofit.create(BookListService::class.java)
        }
    }
}
