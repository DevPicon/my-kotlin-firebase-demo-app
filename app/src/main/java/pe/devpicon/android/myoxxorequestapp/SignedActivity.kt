package pe.devpicon.android.myoxxorequestapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.IdpResponse

class SignedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signed)
    }

    companion object {
        fun createIntent(context: Context, idpResponse: IdpResponse?): Intent = Intent(context,
                SignedActivity::class.java).apply { putExtra("idpResponse", idpResponse) }
    }

}
