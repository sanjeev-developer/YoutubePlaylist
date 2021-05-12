package com.youtubeplaylist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.youtubeplaylist.Interface.DataTransfer
import com.youtubeplaylist.viewModel.GoogleLogin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, DataTransfer{

    lateinit var gso:GoogleSignInOptions;
    lateinit var mGoogleSignInClient:GoogleSignInClient
    var RC_SIGN_IN:Int = 1
    lateinit var vObj: GoogleLogin;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        bt_googleLogin.setOnClickListener(this);

        //sanjeev println("new change here")

        println("pushing master code to branchone testing")


        vObj = ViewModelProvider(this@MainActivity).get(GoogleLogin::class.java)
        vObj.context =this@MainActivity
        vObj.dataTransfer=this@MainActivity
    }

    fun abc()
    {
        println("this is the code for branch conflict test third conflict")
    }

    override fun onClick(v: View?) {
        signIn()
        vObj.showToast()
        //Test.testing()
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID", googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e("failed code=", e.statusCode.toString())
        }
    }

    fun showToastInActivity()
    {
        Toast.makeText(this@MainActivity,"this is toast message is in Activity", Toast.LENGTH_SHORT).show()
    }

    override fun datasend(s: String) {
        Toast.makeText(this@MainActivity,"this is toast message is in interface", Toast.LENGTH_SHORT).show()
    }
}