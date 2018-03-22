package com.example.manuel.testfragmentflicker

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private var showing1: Boolean =false

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                showing1 = !showing1
                showFragment(if (showing1) BlankFragment() else MainActivityFragment(),"TAG",this,true,R.id.container)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showFragment(fragment: Fragment,
                             tag: String,
                             activity: FragmentActivity,
                             addToBackStack: Boolean,
                             layout: Int): Boolean {
//        Logger.i("Show fragment", fragment.toString() + " - url:"
//                + (if (fragment.arguments != null) fragment.arguments.getString(WebFragment.KEY_URL, "N/A") else "")
//                + " - fragment tag:" + tag + " - add to back stack:" + addToBackStack)

        val ft = activity.supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
        try {
            ft.replace(layout, fragment, tag)
            if (addToBackStack) ft.addToBackStack(null)
            ft.commit()
        } catch (e: IllegalStateException) {
//            Logger.e("FragmentChange", "Could not replace fragment " + tag + ":\n" + e.message)
            e.printStackTrace()
        }
        return true
    }
}
