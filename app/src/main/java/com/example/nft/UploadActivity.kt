package com.example.nft

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.Manifest
import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class UploadActivity : AppCompatActivity() {
    private val GALLERY = 100
    private val GALLERY_PERMISSION_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_upload)


        val image = findViewById<ImageView>(R.id.imageupload)
        val tiName : TextInputLayout = findViewById(R.id.ti_name)
        val etName : EditText = findViewById(R.id.et_name)
        val tiDesc : TextInputLayout = findViewById(R.id.ti_desc)
        val etDesc : EditText = findViewById(R.id.et_desc)
        val tiSize : TextInputLayout = findViewById(R.id.ti_size)
        val etSize : EditText = findViewById(R.id.et_size)
        val tiPrice : TextInputLayout = findViewById(R.id.ti_price)
        val etPrice : EditText = findViewById(R.id.et_price)
        val uploadBtn : Button = findViewById(R.id.uploadbtn)

        uploadBtn.isEnabled = false
        etName.doOnTextChanged { text, start, before, count ->
            if(text!!.length < 3){
                tiName.error = "Please Enter your Full name"

            }else{
                tiName.error = null
                uploadBtn.isEnabled = etDesc.text.length >= 25 && etSize.text.isNotEmpty() && etPrice.text.isNotEmpty()


            }
        }

        etDesc.doOnTextChanged { text, start, before, count ->
            if(text!!.length < 25){
                tiDesc.error = "Please Enter at least five word"

            }else{
                tiDesc.error = null
                uploadBtn.isEnabled = etName.text.length>=3 &&  etSize.text.isNotEmpty() && etPrice.text.isNotEmpty()

            }
        }

        etSize.doOnTextChanged { text, start, before, count ->
            if(text!!.isEmpty()){
                tiSize.error = "Please Enter the image size"

            }else{
                tiSize.error = null

                uploadBtn.isEnabled = etName.text.length>=3 && etDesc.text.length >= 25  && etPrice.text.isNotEmpty()

            }
        }
        etPrice.doOnTextChanged { text, start, before, count ->
            if(text!!.isEmpty()){
                tiPrice.error = "Please Enter the Price"

            }else{
                tiPrice.error = null
                uploadBtn.isEnabled = etName.text.length>=3 && etDesc.text.length >= 25 && etSize.text.isNotEmpty()


            }
        }
        uploadBtn.setOnClickListener {
            val intent = Intent(this,PreviewActivity::class.java)
            startActivity(intent)
        }

        image.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
                    //permission denied
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permission, GALLERY_PERMISSION_CODE)
                }
                else
                {
                    //permission already granted
                    pickupImageFromGallery()
                }
            }
            else
            {
                //system os is < Marshmallow
                pickupImageFromGallery()
            }
        }

    }
    fun pickupImageFromGallery()
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            GALLERY_PERMISSION_CODE ->
            {
                if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //permission from popup granted
                    pickupImageFromGallery()
                }
                else
                {
                    //permission from popup denied
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val image = findViewById<ImageView>(R.id.imageupload)
        if (resultCode == Activity.RESULT_OK && requestCode == GALLERY)
        {
            image.setImageURI(data?.data)
        }
    }

}