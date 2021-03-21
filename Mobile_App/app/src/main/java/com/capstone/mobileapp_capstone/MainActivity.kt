package com.capstone.mobileapp_capstone

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuthMultiFactorException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        //setProgressBar(binding.progressBar)

        // Initialize Firebase Auth
        auth = Firebase.auth
        binding.signInButton.setOnClickListener(this)
        binding.signOutButton.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI(auth.currentUser)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
            // Sign in succeeded
                updateUI(auth.currentUser)
            } else {
            // Sign in failed
                Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }
public fun getCurrentUser(){
    val user = Firebase.auth.currentUser
    user?.let {
        // Name, email address, and profile photo Url
        val name = user.displayName
        val email = user.email
        val photoUrl = user.photoUrl

        // Check if user's email is verified
        val emailVerified = user.isEmailVerified

        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getToken() instead.
        val uid = user.uid
    }
}
private fun updateUI(user: FirebaseUser?) {
    if (user != null) {
        // Signed in
        binding.status.text = getString(R.string.firebaseui_status_fmt, user.email)
        binding.detail.text = getString(R.string.id_fmt, user.uid)

        binding.signInButton.visibility = View.GONE
        binding.signOutButton.visibility = View.VISIBLE
    } else {
        // Signed out
        binding.status.setText(R.string.signed_out)
        binding.detail.text = null

        binding.signInButton.visibility = View.VISIBLE
        binding.signOutButton.visibility = View.GONE
    }
}
    private fun createAccount(email: String, password: String) {
        Log.d(FragmentActivity.TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }
        showProgressBar()

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                        // [START_EXCLUDE]
                        hideProgressBar()
                        // [END_EXCLUDE]
                    }
                })
        // [END create_user_with_email]
    }
}