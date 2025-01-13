package st.masoom.sabjimandi

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

@Composable
fun UploadImagePage(onBack: () -> Unit) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isImageUploaded by remember { mutableStateOf(false) } // Track upload status
    val context = LocalContext.current
    val storageRef = FirebaseStorage.getInstance().reference // Initialize Firebase Storage reference
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = it // Set the selected image URI
            isImageUploaded = false // Reset upload status if a new image is selected
        }
    }

    val fileRef = storageRef.child("your-file-path-in-storage")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Upload Profile Image", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Button to select an image from the gallery
        Button(onClick = { launcher.launch("image/*")}
            ,colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF006401),
            contentColor = Color.White
            ), ) {
            Text(text = "Choose Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the selected image if available and not yet uploaded
        imageUri?.let {
            if (!isImageUploaded) {
                Image(
                    painter = rememberImagePainter(it), // Use Coil to load the selected image
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Upload button
        Button(onClick = {
            imageUri?.let { uri ->
                val fileName = uri.lastPathSegment ?: UUID.randomUUID().toString() + ".jpg"
                val fileRef = storageRef.child("uploads/$fileName") // Create a reference for the file in Firebase

                // Upload the file to Firebase
                fileRef.putFile(uri)
                    .addOnSuccessListener {
                        // On success, display a toast message
                        Toast.makeText(context, "Uploaded Successfully", Toast.LENGTH_SHORT).show()
                        imageUri = null // Clear the selected image
                        isImageUploaded = true // Update the upload status
                    }
                    .addOnFailureListener {
                        // On failure, display an error message
                        Toast.makeText(context, "Upload Failed", Toast.LENGTH_SHORT).show()
                    }
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF006401),
                contentColor = Color.White
            ),) {
            Text(text = "Upload")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back button to return to the profile screen
        Button(onClick = onBack,colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF006401),
            contentColor = Color.White
        ),) {
            Text(text = "Back to Profile")
        }
    }
}
