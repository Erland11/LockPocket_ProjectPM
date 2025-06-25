package com.uti.lockpocket_pm

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import java.io.File
import androidx.core.net.toUri

class utama : AppCompatActivity() {

    private lateinit var profilButton: ShapeableImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama)

        profilButton = findViewById(R.id.lingkar)

        profilButton.setOnClickListener {
            val intent = Intent(this, Profil::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.tabungan).setOnClickListener {
            startActivity(Intent(this, tabunganku::class.java))
        }

        loadFotoProfil()
    }

    override fun onResume() {
        super.onResume()
        loadFotoProfil()
    }

    private fun loadFotoProfil() {
        val prefs = getSharedPreferences("ProfilPrefs", MODE_PRIVATE)
        val fotoPath = prefs.getString("foto_profil", null)

        if (!fotoPath.isNullOrEmpty()) {
            val file = File(fotoPath)
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                profilButton.setImageBitmap(bitmap)
            } else {

                try {
                    val uri = fotoPath.toUri()
                    profilButton.setImageURI(uri)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
