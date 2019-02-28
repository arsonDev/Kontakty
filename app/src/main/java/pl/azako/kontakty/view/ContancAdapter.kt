package pl.azako.kontakty

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contanc_row.view.*

class ContancAdapter : RecyclerView.Adapter<ContancViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContancViewHolder {
        // tworzy z pliku xml obiekt
        val LayoutInflater = LayoutInflater.from(parent.context)
        // konwert na boiekt typu view
        val contactRow = LayoutInflater.inflate(R.layout.contanc_row,parent,false)
        return ContancViewHolder(contactRow)
    }

    override fun getItemCount(): Int {
        return 20;
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContancViewHolder, position: Int) {
        val name = holder.view.nazwa
        val number = holder.view.numer
        name.text = "Test $position"
        number.text = "Numer $position"
    }

}


class ContancViewHolder(val view : View) : RecyclerView.ViewHolder(view){

}