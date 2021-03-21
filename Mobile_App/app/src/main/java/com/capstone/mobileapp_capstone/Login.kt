package com.capstone.mobileapp_capstone

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.mobileapp_capstone.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityLoginBinding

    companion object {
        private const val TAG = "Login"
        private const val RC_MULTI_FACTOR = 9005
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init buttons
        binding.emailSignInButton.setOnClickListener(this)
        binding.emailCreateAccountButton.setOnClickListener(this)
        binding.signOutButton.setOnClickListener(this)
        binding.reloadButton.setOnClickListener(this)

        //init firebase auth
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.emailCreateAccountButton -> {
                createAccount(binding.fieldEmail.text.toString(), binding.fieldPassword.text.toString())
            }
            R.id.emailSignInButton -> signIn(binding.fieldEmail.text.toString(), binding.fieldPassword.text.toString())
            R.id.signOutButton -> signOut()

            R.id.reloadButton -> reload()
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }
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

                    // ...
                }
    }
    private fun signIn(email: String, password: String){
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                        // ...
                    }

                    // ...
                }
    }
    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }


    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            binding.status.text = getString(R.string.login_status_fmt,
                    user.email, user.isEmailVerified)
            binding.detail.text = getString(R.string.firebase_status_fmt, user.uid)

            binding.emailPasswordButtons.visibility = View.GONE
            binding.emailPasswordFields.visibility = View.GONE
            binding.signedInButtons.visibility = View.VISIBLE


        } else {
            binding.status.setText(R.string.signed_out)
            binding.detail.text = null

            binding.emailPasswordButtons.visibility = View.VISIBLE
            binding.emailPasswordFields.visibility = View.VISIBLE
            binding.signedInButtons.visibility = View.GONE
        }
    }
    private fun reload() {
        auth.currentUser!!.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateUI(auth.currentUser)
                Toast.makeText(this@Login,
                        "Reload successful!",
                        Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "reload", task.exception)
                Toast.makeText(this@Login,
                        "Failed to reload user.",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validateForm(): Boolean {
        var valid = true

        val email = binding.fieldEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.fieldEmail.error = "Required."
            valid = false
        } else {
            binding.fieldEmail.error = null
        }

        val password = binding.fieldPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.fieldPassword.error = "Required."
            valid = false
        } else {
            binding.fieldPassword.error = null
        }

        return valid
    }
}