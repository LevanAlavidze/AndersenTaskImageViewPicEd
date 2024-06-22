package com.example.andersentaskpicsum

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var etUrl: EditText
    private lateinit var ivImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etUrl = findViewById(R.id.etUrl)
        ivImage = findViewById(R.id.ivImage)

        etUrl.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} // textWatcher need this function so i left it empty

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val imageUrl = s.toString()
                if (imageUrl.isNotEmpty()) {
                    Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.loading) // added placeholder to show while loading for fun
                        .error(R.drawable.error)         // added error image to show if loading failed for fun
                        .into(ivImage, object : com.squareup.picasso.Callback {
                            override fun onSuccess() {}  // didnt add toast or something :)))

                            override fun onError(e: Exception?) { // added toast for error
                                Toast.makeText(
                                    this@MainActivity,
                                    "Error loading image",
                                    Toast.LENGTH_SHORT
                                    ).show()
                            }
                        })

                } else {
                    ivImage.setImageResource(android.R.color.transparent)
                }
            }

            override fun afterTextChanged(s: Editable?) {} // textWatcher need this function so i left it empty
        })

        etUrl.setOnClickListener {
            etUrl.requestFocus()  // to auto clear url when clicking
            etUrl.setText("")
        }

    }
}