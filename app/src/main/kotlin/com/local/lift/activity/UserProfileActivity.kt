package com.local.lift.activity

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.local.locallift.R
import com.local.locallift.databinding.UserProfileBinding
//import com.google.firebase.auth.firebaseAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.local.lift.model.User

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: UserProfileBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: DatabaseReference
    private lateinit var dialog: Dialog
    private lateinit var user: User
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        // Find the TextView in the layout
        val textView: TextView = findViewById(R.id.welcomeUser)

        // Set text to display
        val userName = "John Doe" // Example data
        textView.text = "Welcome, $userName"

        // Log the data for debugging
        Log.d("UserProfileActivity", "UserName: $userName")
    }
}