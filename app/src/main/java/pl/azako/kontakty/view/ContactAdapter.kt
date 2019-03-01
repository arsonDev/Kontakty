package pl.azako.kontakty

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contanc_row.view.*
import pl.azako.kontakty.view.MainActivity

class ContactAdapter : RecyclerView.Adapter<ContancViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContancViewHolder {
        // tworzy z pliku xml obiekt
        val layoutInflater = LayoutInflater.from(parent.context)
        // konwert na boiekt typu view
        val contactRow = layoutInflater.inflate(R.layout.contanc_row,parent,false)
        return ContancViewHolder(contactRow)
    }

    override fun getItemCount(): Int {
        return MainActivity.contactList.size
    }

    override fun onBindViewHolder(holder: ContancViewHolder, position: Int) {

        var contact = MainActivity.contactList.get(position)
        val name = holder.view.nazwa
        val number = holder.view.numer
        val button = holder.view.callButton
        button.setOnClickListener {
            val intent = Intent()
            intent.data = Uri.parse("tel:$contact.number")
            intent.action = Intent.ACTION_DIAL
            ContextCompat.startActivities(holder.view.context, arrayOf(intent),null)
        }
        name.text = contact.name
        number.text = contact.number
    }
}

class ContancViewHolder(val view : View) : RecyclerView.ViewHolder(view)

