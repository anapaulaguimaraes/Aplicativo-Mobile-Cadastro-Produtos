package com.example.unigoiascrud;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

// Adicionando anotação à classe Dao
@androidx.room.Dao
public interface Dao {

    // Método para adicionar dados ao banco de dados.
    @Insert
    void insert(ProductsModal model);

    // Método para atualizar os dados no banco de dados.
    @Update
    void update(ProductsModal model);

    // Deletar um produto específico no banco de dados.
    @Delete
    void delete(ProductsModal model);

    // Realizar consulta para deletar todos os produtos no banco de dados.
    @Query("DELETE FROM products_table")
    void deleteAllProducts();

    // Ler todos os produtos do banco de dados e ordenar os produtos em ordem crescente com o nome do produto.
    @Query("SELECT * FROM products_table ORDER BY productsName ASC")
    LiveData<List<ProductsModal>> getAllProducts();
}

