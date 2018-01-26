package fragment

import adapter.BookListAdapter
import android.app.Fragment
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.roydibyandroidcodingchallenge.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import model.Book
import service.BookListService


/**
 * Main fragment to show list of books.
 */

class BookListFragment : Fragment() {
    internal lateinit var bookList: List<Book>
    var dialog : ProgressDialog? = null
    var recyclerView : RecyclerView? = null

    /**
     * When created, it calls the service to get the book list to show
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.book_list, container, false)
        dialog = ProgressDialog(activity)
        recyclerView = view.findViewById(R.id.book_list_view) as RecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(activity)

        return view
    }

    override fun onResume() {
        super.onResume()
        //Preparing service to get the list of books
        val service = BookListService

        //Show the progress dialog to begin the service call
        dialog?.setTitle("Loading...")
        dialog?.show()

        //Calling the service factory to get the book list and observes for result to come back
        service.create().getBookList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result -> showResult(result)}, //Success, show the result
                        { error -> error.printStackTrace()} //Failure, print the error for now
                )

    }

    /**
     * Assigns the result to adaptor to show the list
     */
    private fun showResult(result: List<Book>?) {
        //Dismiss the dialog to end the service call
        dialog?.dismiss()
        bookList = result!!
        //Once result retrieved, is assigned to adaptor to show.
        val listAdapter = BookListAdapter(activity, bookList)

        recyclerView?.adapter = listAdapter
    }


}
