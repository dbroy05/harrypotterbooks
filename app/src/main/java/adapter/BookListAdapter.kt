package adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.roydibyandroidcodingchallenge.R
import com.squareup.picasso.Picasso

import model.Book

/**
 * Adaptor for the reclycler view to show the book list
 */

class BookListAdapter(private val mContext: Context, private val mBookList: List<Book>) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    /**
     * Initializes the ViewHolder to hold the each row of the list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_list_item, parent, false)
        return ViewHolder(view)
    }

    /**
     * Binds the holder for each row the list
     */
    override fun onBindViewHolder(holder: BookListAdapter.ViewHolder, position: Int) {
        val book = mBookList[position]
        holder.bindItem(book)
    }

    /**
     * Returns number of rows in list
     */
    override fun getItemCount(): Int {
        return mBookList.size
    }

    /**
     * Main ViewHolder class the hold the value of each row in list
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var description: TextView
        var author: TextView
        var bookIcon: ImageView

        init {
            description = itemView.findViewById(R.id.book_description) as TextView
            author = itemView.findViewById(R.id.book_author) as TextView
            bookIcon = itemView.findViewById(R.id.book_icon) as ImageView
        }

        fun bindItem(book: Book) {
            description.text = book.title
            if (book.author != null) {
                author.text = "Author: " + book.author
            }

            Picasso.with(mContext).load(book.imageURL).into(bookIcon)
        }
    }
}
