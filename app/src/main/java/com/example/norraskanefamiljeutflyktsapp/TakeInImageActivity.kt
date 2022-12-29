package com.example.norraskanefamiljeutflyktsapp

import android.app.ProgressDialog
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.norraskanefamiljeutflyktsapp.databinding.ActivityTakeInImageBinding
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class TakeInImageActivity : AppCompatActivity() {

    lateinit var binding : ActivityTakeInImageBinding
    lateinit var imageUri :Uri
    lateinit var imageFileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTakeInImageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.selectImageBtn.setOnClickListener {

            selectImage()
        }

        binding.uploadImageBtn.setOnClickListener {

            uploadImgae()
        }




    }

    private fun uploadImgae() {
      val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter= SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        imageFileName = fileName.toString()
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(imageUri).addOnSuccessListener {

            binding.firebaseImage.setImageURI(null)
            Toast.makeText(this@TakeInImageActivity, "Successfuly upladed", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()
            backtoAddActivity()
        }.addOnFailureListener {

            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@TakeInImageActivity, "failed", Toast.LENGTH_SHORT).show()

        }





    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)

    }

    fun backtoAddActivity(){
       // finish() hur skickar jag nåt med då?




        val intent = Intent(this, TestScrollDownAdd::class.java)
        intent.putExtra("imageName",imageFileName)
        //intent.flags = FLAG_ACTIVITY_PREVIOUS_IS_TOP
        intent.flags = FLAG_ACTIVITY_CLEAR_TOP

        startActivity(intent)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK ){

            imageUri = data?.data!!
            binding.firebaseImage.setImageURI(imageUri)


        }


    }

}