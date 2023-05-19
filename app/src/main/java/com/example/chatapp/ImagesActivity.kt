package com.example.chatapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ImagesActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ImageAdapter

    private lateinit var mProgressCircle: ProgressBar

    private lateinit var mDatabaseRef: DatabaseReference
    private lateinit var mUploads: MutableList<Upload>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mProgressCircle = findViewById(R.id.progress_circle)

        mUploads = mutableListOf()

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads")

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val upload = postSnapshot.getValue(Upload::class.java)
                    mUploads.add(upload!!)
                }

                mAdapter = ImageAdapter(this@ImagesActivity, mUploads)

                mRecyclerView.adapter = mAdapter
                mProgressCircle.visibility = View.INVISIBLE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ImagesActivity, databaseError.message, Toast.LENGTH_SHORT).show()
                mProgressCircle.visibility = View.INVISIBLE
            }
        }

        mDatabaseRef.addValueEventListener(valueEventListener)
    }
}
