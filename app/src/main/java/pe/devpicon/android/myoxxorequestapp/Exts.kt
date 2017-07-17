package pe.devpicon.android.myoxxorequestapp

import android.content.Context
import android.widget.Toast

/**
 * Created by armando on 7/16/17.
 */
fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast
            .LENGTH_SHORT).show()
}