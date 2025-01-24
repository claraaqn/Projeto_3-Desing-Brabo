package com.projeto1.desingbrabo.model

data class Produto(
    val id: Int,
    val nome: String,
    val preco: String,
    val formatos: String,
    val dataPublicacao: String,
    val url: String,
    val dono: String,
    val tamanho: String,
    val cores: List<String>
)
