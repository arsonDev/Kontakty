package pl.azako.kontakty.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import pl.azako.kontakty.ContancAdapter
import pl.azako.kontakty.R
import pl.azako.kontakty.view.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAPK","Utowrzenie na nowo activity")
        setContentView(R.layout.activity_main)
        val model = ViewModelProviders.of(this).get(MainViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ContancAdapter()

    }
}
