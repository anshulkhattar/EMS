package com.example.ems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import androidx.core.view.isVisible
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class EntryDetailsActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var phn: EditText
    lateinit var checkin: Button
    lateinit var checkoutButton: Button
    lateinit var logout: Button
    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_details)

        name = this.findViewById(R.id.nameEditText)
        phn = findViewById(R.id.phoneEditText)
        checkin = findViewById(R.id.checkInButton)
        checkoutButton=findViewById(R.id.checkOutButton)
        logout=findViewById(R.id.logoutButton)

        checkoutButton.isVisible=false

        var email: String? =FirebaseAuth.getInstance().currentUser!!.email

        var hostName="Demo Host"
        var address="Demo address"

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val auth=FirebaseAuth.getInstance().currentUser!!


        val ref = FirebaseDatabase.getInstance().getReference("Appointments")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {


                    checkin.isVisible = false
                    name.isVisible = false
                    phn.isVisible = false

                    checkoutButton.isVisible = true
                }
            }
        })

        checkin.setOnClickListener {
            var nameValue = name.text.toString()
            var phnValue = phn.text.toString()

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var arr = currentDate.split(" ")
            var checkInTime = arr[1]


            if (!nameValue.isEmpty() && !phnValue.isEmpty()) {
                var appointment = Appointment(nameValue, email.toString(), phnValue,checkInTime,"0",hostName,address)
                ref.setValue(appointment)
            }

            BackgroundMail.newBuilder(this@EntryDetailsActivity)
                .withUsername("anshul070300@gmail.com")
                .withPassword("aaar7300")
                .withMailto("500063399@stu.upes.ac.in")
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("test mail")
                .withBody("This is test mail when user checks in")
                .withOnSuccessCallback {
                    //do some magic
                }
                .withOnFailCallback {
                    //do some magic
                }
                .send()
        }

        checkoutButton.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var arr = currentDate.split(" ")
            var checkOutTime = arr[1]


            ref.child("checkOutTime").setValue(checkOutTime)


            BackgroundMail.newBuilder(this@EntryDetailsActivity)
                .withUsername("anshul070300@gmail.com")
                .withPassword("aaar7300")
                .withMailto("500063399@stu.upes.ac.in")
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("test mail")
                .withBody("this is a test mail when user is already logged in")
                .withOnSuccessCallback {
                    //do some magic
                    Toast.makeText(this@EntryDetailsActivity,"this is logged in",Toast.LENGTH_SHORT).show()
                }
                .withOnFailCallback {
                    //do some magic
                }
                .send()


            ref.setValue(null)
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            mGoogleSignInClient.signOut()

            Toast.makeText(this, "logged out", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
data class Appointment(var name:String,var email:String,var phnNumber:String,var checkInTime:String,var checkOutTime:String,var hostName:String,var address:String)
