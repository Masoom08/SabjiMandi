import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun openMap(context: Context, latitude: String, longitude: String) {
    val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")

    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    }else {
        // Show a message if no app can handle the intent
        Toast.makeText(context, "No app available to open maps", Toast.LENGTH_SHORT).show()
    }
}