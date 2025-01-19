package com.projeto1.desingbrabo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.projeto1.desingbrabo.model.Perfil

class PerfilActivity : AppCompatActivity() {

    private lateinit var nomeUsuarioTextView: TextView
    private lateinit var emailUsuarioTextView: TextView
    private lateinit var fotoPerfilImageView: ImageView
    private lateinit var minhaAssinaturaButton: Button
    private lateinit var editarPerfilButton: Button
    private lateinit var configuracoesButton: Button
    private lateinit var sairButton: Button

    private lateinit var buttonHome: Button
    private lateinit var buttonMeusProdutos: Button
    private lateinit var buttonExplorar: Button
    private lateinit var buttonCarrinho: Button
    private lateinit var buttonPerfil: Button

    private lateinit var userViewModel: Perfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_perfil)

        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        nomeUsuarioTextView = findViewById(R.id.nome_usuario)
        emailUsuarioTextView = findViewById(R.id.email_perfil)
        fotoPerfilImageView = findViewById(R.id.foto_perfil)

        minhaAssinaturaButton = findViewById(R.id.button_minha_assinatura)
        editarPerfilButton = findViewById(R.id.button_editar_perfil)
        configuracoesButton = findViewById(R.id.button_configuracoes)
        sairButton = findViewById(R.id.button_sair)

        buttonHome = findViewById(R.id.button_home)
        buttonMeusProdutos = findViewById(R.id.button_meus_produtos)
        buttonExplorar = findViewById(R.id.button_explorar)
        buttonCarrinho = findViewById(R.id.button_carrinho)
        buttonPerfil = findViewById(R.id.button_perfil)

        carregarDadosPerfil(sharedPreferences)

        editarPerfilButton.setOnClickListener {
            val intent = Intent(this, EditarPerfilActivity::class.java)
            startActivityForResult(intent, 1)
        }

        sairButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        configuracoesButton.setOnClickListener {
            val intent = Intent(this, ConfiguracoesActivity::class.java)
            startActivity(intent)
        }

        // barra de navegaçãp
        buttonHome.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        buttonMeusProdutos.setOnClickListener{
            val intent = Intent(this, DownloadActivity::class.java)
            startActivity(intent)
        }
    }

    private fun carregarDadosPerfil(sharedPreferences: SharedPreferences) {
        val nomeUsuario = sharedPreferences.getString("user_name", "Nome não encontrado")
        val emailUsuario = sharedPreferences.getString("user_email", "Email não encontrado")

        nomeUsuarioTextView.text = nomeUsuario
        emailUsuarioTextView.text = emailUsuario
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            carregarDadosPerfil(sharedPreferences)
        }
    }
}
