package st.masoom.sabjimandi.Pages

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import st.masoom.sabjimandi.ProductImages
import st.masoom.sabjimandi.Products
import st.masoom.sabjimandi.RetailerCard
import st.masoom.sabjimandi.getRetailers
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import st.masoom.sabjimandi.ChatPage
import st.masoom.sabjimandi.ChatViewModel
import st.masoom.sabjimandi.MainActivity
import st.masoom.sabjimandi.MarketCard
import st.masoom.sabjimandi.ProductDetailPage
import st.masoom.sabjimandi.getMarkets


@Composable
fun HomePage( userName: String, chatViewModel: ChatViewModel, context: Context) {
    var showChat by remember { mutableStateOf(false) }
    val navController = rememberNavController()


    //Text(text = "Home", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
    Scaffold(
        floatingActionButton = {
            if (!showChat) {
                FloatingActionButton(
                    onClick = {
                        showChat = !showChat // Show the chat page when FAB is clicked
                    },
                    modifier = Modifier.padding(bottom = 56.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Chatbot"
                    ) // Chatbot icon
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End // Align to the bottom-right
    ) { innerPadding ->
        if(showChat){
            ChatPage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                viewModel = chatViewModel
            )
        }else {
        NavHost(navController = navController, startDestination = "home"){

        // Home Page content
        composable("home") {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, start = 16.dp, end = 16.dp, bottom = 56.dp)
            ) {
                item {
                    Row {

                        Text(
                            text = "नमस्कार  ",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "$userName !",
                            style = TextStyle(
                                color = Color(0xFF234E09),
                                fontSize = 32.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Your Personalized Path to Direct access to market",
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Explore Product",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                item {
                    // Call the horizontally scrollable product images
                    ProductImages(Products(), navController)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Nearest Market",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                items(getMarkets()) { market ->
                    MarketCard(market = market)
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Explore More",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                // Add retailer list inside the LazyColumn
                items(getRetailers()) { retailer ->
                    RetailerCard(retailer = retailer)
                }
            }
        }
        // Product Detail Page
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val context = LocalContext.current
            ProductDetailPage(productId = productId , context= context)
        }
        }

        }
    }
}




