package com.tecsup.dmarcoapp

import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.item_plato.*
import kotlinx.android.synthetic.main.item_plato_2.*


class PlatosActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platos)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        title = "Platos D'Marco"
        bebida_2.setOnClickListener {
            showPopup(it)
        }
        bebida_1.setOnClickListener {
            showPopup(it)
        }
    }
    private fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.popup_menu, popup.menu)
        popup.show()
    }

}