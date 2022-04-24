package com.example.nft

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.content.SharedPreferences
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext


class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnupdate = findViewById(R.id.btnUpdate) as ImageView

        val btnCopy = findViewById(R.id.btncopy) as ImageView
        val btnlogout = findViewById(R.id.btnlogout) as ImageView

        btnupdate!!.setOnClickListener{
            val goToUpdatePage = Intent(this, UpdateProfile::class.java)
            startActivity(goToUpdatePage)
        }
        btnlogout!!.setOnClickListener{

                val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
                dialogBuilder.setMessage("Are you sure you want to logout ?")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Yes", DialogInterface.OnClickListener {
                            dialog, id ->
                        getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply()
                        val mainIntent = Intent(this@ProfileActivity, LoginActivity::class.java)
                        startActivity(mainIntent)
                        finish()
                    })
                    // negative button text and action
                    .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Logout")
                // show alert dialog
                alert.show()

        }


    }
    private fun copyTextToClipboard() {
        val textToCopy = etTextToCopy.text
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }

}