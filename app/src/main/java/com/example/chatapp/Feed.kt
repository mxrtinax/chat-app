package com.example.chatapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.R
import com.squareup.picasso.Picasso

class Feed : AppCompatActivity() {

    private companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    private lateinit var mButtonChooseImage: Button
    private lateinit var mButtonUpload: Button
    private lateinit var mTextViewShowUploads: TextView
    private lateinit var mEditTextFileName: EditText
    private lateinit var mImageView: ImageView
    private lateinit var mProgressBar: ProgressBar

    private var mImageUri: Uri? = null

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null && result.data?.data != null) {
                mImageUri = result.data?.data

                Picasso.with(this).load(mImageUri).into(mImageView)
            }
        }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed)

        mButtonChooseImage = findViewById(R.id.button_choose_image)
        mButtonUpload = findViewById(R.id.button_upload)
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads)
        mEditTextFileName = findViewById(R.id.edit_text_file_name)
        mImageView = findViewById(R.id.image_view)
        mProgressBar = findViewById(R.id.progress_bar)

        mButtonChooseImage.setOnClickListener {
            openFileChooser()
        }

        mButtonUpload.setOnClickListener {
            // Add your upload logic here
        }

        mTextViewShowUploads.setOnClickListener {
            // Add your show uploads logic here
        }
    }

    private fun openFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        pickImageLauncher.launch(Intent.createChooser(intent, "Select Image"))
    }
}
