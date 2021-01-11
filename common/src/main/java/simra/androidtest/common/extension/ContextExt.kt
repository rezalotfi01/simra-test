package simra.androidtest.common.extension

import android.content.Context
import android.content.Intent

fun Context.shareText(text: String){
    val txtIntent = Intent(Intent.ACTION_SEND)
    txtIntent.type = "text/plain"
    txtIntent.putExtra(Intent.EXTRA_SUBJECT, "Content")
    txtIntent.putExtra(Intent.EXTRA_TEXT, text)
    startActivity(Intent.createChooser(txtIntent, "Share"))
}