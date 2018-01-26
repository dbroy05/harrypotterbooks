package activity

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.roydibyandroidcodingchallenge.R

import fragment.BookListFragment

/*
Main activity to handle all fragments to show list of books
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //It calls first fragment to load and handle the list view
        val fragment = BookListFragment()
        val fragmentManager = fragmentManager
        fragmentManager.beginTransaction()
                .add(R.id.book_list_fragment, fragment).commit()
    }
}
