package pl.azako.kontakty.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import pl.azako.kontakty.ContactAdapter
import pl.azako.kontakty.R
import pl.azako.kontakty.models.Contact


class MainActivity : AppCompatActivity() {

    companion object {
        val contactList : MutableList<Contact> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAPK","Utowrzenie na nowo activity")
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val contentResolver = contentResolver

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                   0)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {


            val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null, null
            )
            try {
                while (cursor.moveToNext()) {
                    var contact = Contact(
                        cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)),
                        "",
                        cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    )
                    if (contact.hasNumber > 0){
                        val queryPhone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf<String>(contact._id.toString()),null)
                        if (queryPhone.moveToNext())
                            contact.number = queryPhone.getString(queryPhone.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))

                    }
                    contactList.add(contact)
                }

            } finally {
                cursor.close()
            }
        }
        recyclerView.adapter = ContactAdapter()
    }
}
