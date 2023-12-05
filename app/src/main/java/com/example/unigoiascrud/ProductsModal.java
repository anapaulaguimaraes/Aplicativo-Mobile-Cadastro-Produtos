package com.example.unigoiascrud;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Definir o nome da tabela.
@Entity(tableName = "products_table")
public class ProductsModal {

    // Auto incrementar id para cada produto.
    @PrimaryKey(autoGenerate = true)

    // Variável para o id.
    private int id;

    // Variável para o nome do produto.
    private String productsName;

    // Variável para a descrição do produto.
    private String productsDescription;

    // Variável para a preço do produto.
    private String productsPrice;

    // Criar uma classe de construtor, dentro da classe do construtor não estamos passando o id, está incrementando automaticamente.
    public ProductsModal(String productsName, String productsDescription, String productsPrice) {
        this.productsName = productsName;
        this.productsDescription = productsDescription;
        this.productsPrice = productsPrice;
    }

    // Criar métodos getter e setter.
    public String getProductsName() {

        return productsName;
    }

    public void setProductsName(String productsName) {

        this.productsName = productsName;
    }

    public String getProductsDescription() {

        return productsDescription;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

    public String getProductsPrice() {

        return productsPrice;
    }

    public void setProductsPrice(String productsPrice) {

        this.productsPrice = productsPrice;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
}

