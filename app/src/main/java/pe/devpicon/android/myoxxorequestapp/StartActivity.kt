package pe.devpicon.android.myoxxorequestapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.ResultCodes
import com.google.firebase.auth.FirebaseAuth

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.currentUser != null) {
            setContentView(R.layout.activity_start)
            showMessage(this@StartActivity, "Está logueado!")
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    listOf(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (data != null) {
                val idpResponse: IdpResponse? = IdpResponse.fromResultIntent(data)

                if (resultCode == ResultCodes.OK) {
                    startActivity(SignedActivity.createIntent(this, idpResponse))
                    finish()
                    return
                } else {
                    if (idpResponse == null) {
                        showMessage(this@StartActivity, getString(R.string.sign_in_cancelled))
                        return
                    }

                    if (idpResponse.errorCode == ErrorCodes.NO_NETWORK) {
                        showMessage(this@StartActivity, getString(R.string
                                .no_internet_connection))
                        return
                    }

                    if (idpResponse.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                        showMessage(this@StartActivity, getString(R.string
                                .unknown_error))
                        return
                    }



                    showMessage(this@StartActivity, getString(R.string.unknown_sign_in_response))
                }
            }

        }
    }


    companion object {
        val RC_SIGN_IN = 123
    }
}

