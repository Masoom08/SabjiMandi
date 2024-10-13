package st.masoom.sabjimandi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import openMap

@Composable
fun ProfilePage() {

    var userName by remember { mutableStateOf("Masoom Singh") }
    var phoneNumber by remember { mutableStateOf("9454716083") }
    var emailId by remember { mutableStateOf("abc@gmail.com") }
    var pronouns by remember { mutableStateOf("she/her") }
    var gender by remember { mutableStateOf("Female") }

    var name by remember { mutableStateOf("") }
    val context = LocalContext.current // Get the current context here

    var isEditingProfile by remember { mutableStateOf(false) }

    if (isEditingProfile) {
        // Show the edit profile page
        EditProfilePage(
            initialName = userName,
            initialPhone = phoneNumber,
            initialEmail = emailId,
            initialPronouns = pronouns,
            onProfileUpdated = { updatedName, updatedPhone, updatedEmail, updatedPronouns, updatedGender ->
                // Update the profile data with the new values
                userName = updatedName
                phoneNumber = updatedPhone
                emailId = updatedEmail
                pronouns = updatedPronouns
                gender = updatedGender
                // Navigate back to profile page
                isEditingProfile = false
            }
        )
    } else {
        // Show the profile page
        val context = LocalContext.current


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color(0xFF006400))
                    .padding(16.dp)
            ) {
                if (name.isNotBlank()) {
                    Text(
                        text = "Hello, $name!",
                        style = TextStyle(
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    )
                } else {
                    Text(
                        text = "My Profile",
                        style = TextStyle(
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dp), // Replace with your image resource
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    IconButton(onClick = { isEditingProfile = true }) {
                        Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Text(text = "Edit Profile", style = MaterialTheme.typography.body1)
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Profile"
                                )
                            }
                            Column {

                                Text(
                                    text = "Phone No.: $phoneNumber",
                                    style = MaterialTheme.typography.body1
                                )
                                Text(
                                    text = "Email: $emailId",
                                    style = MaterialTheme.typography.body1
                                )
                                Text(
                                    text = "Pronouns: $pronouns",
                                    style = MaterialTheme.typography.body1
                                )
                                Text(
                                    text = "Gender: $gender",
                                    style = MaterialTheme.typography.body1
                                )

                            }
                        }

                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(text = "Enter your name:", style = MaterialTheme.typography.h6)
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Your Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Button(
                            onClick = { /* Handle Orders */ },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF006400),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Orders")
                        }

                        Spacer(modifier = Modifier.width(32.dp))

                        Button(
                            onClick = { /* Handle Favourite Seller */ },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF006400),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Favourite Seller")
                        }
                    }

                    Row {
                        Button(
                            onClick = { /* Handle Wishlist */ },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF006400),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Wishlist")
                        }

                        Spacer(modifier = Modifier.width(32.dp))

                        Button(
                            onClick = { /* Handle Cart */ },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF006400),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Cart")
                        }
                    }

                    Button(
                        onClick = { /* Handle Help Center */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF006400),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(text = "Help Center")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Track My Order Button (opens Google Maps)
                    Button(
                        onClick = {
                            // Use the context to call the openMap function
                            openMap(
                                context,
                                "28.6139",
                                "77.2090"
                            )  // Example coordinates (New Delhi)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF006400),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(text = "Track My Order")
                    }
                }
            }
        }
    }
}

