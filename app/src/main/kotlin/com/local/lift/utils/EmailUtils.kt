// EmailUtils.kt
package com.local.lift.utils

import android.util.Log
import java.util.Properties
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailUtils {

    fun sendVerificationEmail(recipientEmail: String, verificationCode: String) {
        val username = "chansovanmonyyoeun03@gmail.com"
        val password = "gpatnlsvfxkvewsf"

        val properties = Properties().apply {
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
        }

        val session = Session.getInstance(properties, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        try {
            // Create the email message
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(username))  // Sender's email
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail))  // Recipient's email
                subject = "Password Reset Code"
                setText("Use the following code to reset your password: $verificationCode")
            }

            // Send the email
            Transport.send(message)
            Log.d("Email", "Email sent successfully!")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Email", "Error sending email: ${e.message}")
        }
    }
}
