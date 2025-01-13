package st.masoom.sabjimandi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import openMap
import androidx.compose.material3.*
import androidx.navigation.NavController
import st.masoom.sabjimandi.Pages.Cart
import st.masoom.sabjimandi.Login.signOut


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(navController: NavController) {
    var imageResource by remember { mutableStateOf(R.drawable.farmer) }
    var userName by remember { mutableStateOf("Rahul Kumar") }
    var phoneNumber by remember { mutableStateOf("945477777") }
    var emailId by remember { mutableStateOf("abc@gmail.com") }
    var pronouns by remember { mutableStateOf("he/his") }
    var gender by remember { mutableStateOf("Male") }

    val context = LocalContext.current // Get the current context here


    var isEditingProfile by remember { mutableStateOf(false) }
    var isUploadingImage by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var isCartPageVisible by remember { mutableStateOf(false) }

    if (isUploadingImage) {
        // Render the UploadImagePage
        UploadImagePage(
            onBack = { isUploadingImage = false } // Set isUploadingImage to false when back is pressed
        )
    }
    else if (isEditingProfile) {
        EditProfilePage(
            initialName = userName,
            initialPhone = phoneNumber,
            initialEmail = emailId,
            initialPronouns = pronouns,
            onProfileUpdated = { updatedName, updatedPhone, updatedEmail, updatedPronouns, updatedGender ->
                userName = updatedName
                phoneNumber = updatedPhone
                emailId = updatedEmail
                pronouns = updatedPronouns
                gender = updatedGender
                isEditingProfile = false
            }
        )
    } else if (isCartPageVisible) {
        // Show Cart composable
        Cart(navController = navController)
    }else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "My Profile",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 32.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            )

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
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                            .clickable {
                                showBottomSheet = true
                            },
                        contentAlignment = Alignment.TopStart
                    ) {
                        Image(
                            painter = painterResource(id = imageResource),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Edit Profile",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            IconButton(onClick = { isEditingProfile = true }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Profile"
                                )
                            }
                        }
                        Text(text = "Name: $userName", style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = "Phone No.: $phoneNumber",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(text = "Email: $emailId", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Pronouns: $pronouns", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Gender: $gender", style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))


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
                                containerColor = Color(0xFF006401),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Orders",color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(32.dp))

                        Button(
                            onClick = { /* Handle Favourite Seller */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006401),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Favourite Seller",color = Color.White)
                        }
                    }

                    Row {
                        Button(
                            onClick = { /* Handle Wishlist */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006401),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Wishlist",color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(32.dp))

                        Button(
                            onClick = {isCartPageVisible = true
                                      },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006401),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Cart",color = Color.White)
                        }
                    }
                    Row {
                        Button(
                            onClick = { /* Handle Help Center */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006401),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Help Center",color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(32.dp))

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
                                containerColor = Color(0xFF006401),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Track My Order",color = Color.White)
                        }
                    }
                    Row {
                        Button(
                            onClick = {

                                signOut(navController) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006401),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Sign Out",color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(32.dp))

                        Button(
                            onClick = { isUploadingImage = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006401),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = "Upload Image",color = Color.White)
                        }
                    }
                }
            }
        }

    }


    // Modal Bottom Sheet for Profile Picture Options
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Edit Profile Picture",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Divider()
                Spacer(modifier = Modifier.height(8.dp))

                BottomSheetOption(
                    icon = Icons.Default.Face,
                    text = "New profile picture",
                    onClick = {
                        showBottomSheet = false
                        // Handle new picture action
                    }
                )

                BottomSheetOption(
                    icon = Icons.Default.Delete,
                    text = "Remove current picture",
                    textColor = Color.Red,
                    onClick = {
                        imageResource = R.drawable.default_image // Reset to default
                        showBottomSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun BottomSheetOption(
    icon: ImageVector,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
    }
}
