package st.masoom.sabjimandi.Pages

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(), PaymentResultListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Checkout.preload(applicationContext)

        val amount = intent.getIntExtra("amount", 0)
        if (amount > 0) {
            // Start payment process if amount is valid
            startPayment(amount)
        } else {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show()
            finish() // Close activity if no valid amount is provided
        }
    }

    // Function to start the payment
    fun startPayment(amount: Int) {
        Checkout.preload(applicationContext)
        val checkout = Checkout()
        checkout.setKeyID("YOUR_RAZORPAY_KEY_ID") // Replace with your Razorpay API Key

        try {
            val options = JSONObject()
            options.put("name", "Your App Name")
            options.put("description", "Product Purchase")
            options.put("image", "https://your-logo-url.com/logo.png") // Add your logo URL
            options.put("currency", "INR")
            options.put("amount", amount * 100) // Amount is in paise (100 = Rs 1)

            val prefill = JSONObject()
            prefill.put("email", "user@example.com")
            prefill.put("contact", "9876543210")

            options.put("prefill", prefill)

            checkout.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
        }
    }

    // This method will be called on payment success
    override fun onPaymentSuccess(razorpayPaymentID: String?) {
        Toast.makeText(this, "Payment Successful: $razorpayPaymentID", Toast.LENGTH_LONG).show()
        finish() // Close the PaymentActivity

    }

    // This method will be called on payment failure
    override fun onPaymentError(code: Int, response: String?) {
        Toast.makeText(this, "Payment failed: $response", Toast.LENGTH_LONG).show()

        finish() // Close the PaymentActivity

    }
}
