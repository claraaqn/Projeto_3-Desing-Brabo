package com.projeto1.desingbrabo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.projeto1.desingbrabo.api.RetrofitInstance
import com.projeto1.desingbrabo.model.Image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_home)

        val buttonMeusProdutos: Button = findViewById(R.id.button_meus_produtos)
        val buttonPerfil: Button = findViewById(R.id.button_perfil)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        RetrofitInstance.api.getImages().enqueue(object : Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                val images = response.body()
                if (images != null) {
                    recyclerView.adapter = ImageAdapter(images, this@HomeActivity)
                }
            }

            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }
        })

        buttonPerfil.setOnClickListener{
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }

        buttonMeusProdutos.setOnClickListener{
            val intent = Intent(this, DownloadActivity::class.java)
            startActivity(intent)
        }
    }
}
