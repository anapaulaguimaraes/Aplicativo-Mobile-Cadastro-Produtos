package com.example.unigoiascrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NewProductsActivity extends AppCompatActivity {

    // Criar as variáveis para o botão e para os edittext.
    private EditText productsNameEdt, productsDescEdt, productsPriceEdt;
    private Button productsBtn;

    // Criar uma variável de string constante para, nome do produto, descrição e preço.
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_PRODUCTS_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PRODUCTS_NAME";
    public static final String EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PRODUCTS_DESCRIPTION";
    public static final String EXTRA_PRICE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PRODUCTS_PRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_products);

        // Inicializar as variáveis para cada visão.
        productsNameEdt = findViewById(R.id.idEdtNomeProduto);
        productsDescEdt = findViewById(R.id.idEdtDescricaoProduto);
        productsPriceEdt = findViewById(R.id.idEdtPrecoProduto);
        productsBtn = findViewById(R.id.idBtnSaveProducts);

        // Obter o intent, pois estamos obtendo dados por meio de um intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // Se obtivermos id para os dados, definir valores para os campos de edição de texto.
            productsNameEdt.setText(intent.getStringExtra(EXTRA_PRODUCTS_NAME));
            productsDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            productsPriceEdt.setText(intent.getStringExtra(EXTRA_PRICE));
        }
        // Adicionar clique para o botão Salvar.
        productsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter o valor do texto de edittext e validar, se os campos de texto estão vazios ou não.
                String productsName = productsNameEdt.getText().toString();
                String productsDesc = productsDescEdt.getText().toString();
                String productsPrice = productsPriceEdt.getText().toString();
                if (productsName.isEmpty() || productsDesc.isEmpty() || productsPrice.isEmpty()) {
                    Toast.makeText(NewProductsActivity.this, "Entre com os detalhes do produto.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Método para salvar o produto.
                saveProducts(productsName, productsDesc, productsPrice);
            }
        });
    }

    private void saveProducts(String productsName, String productsDescription, String productsPrice) {
        // Método, para passar todos os dados por meio de um intent.
        Intent data = new Intent();

        // Passar todos os detalhes do nosso produto.
        data.putExtra(EXTRA_PRODUCTS_NAME, productsName);
        data.putExtra(EXTRA_DESCRIPTION, productsDescription);
        data.putExtra(EXTRA_PRICE, productsPrice);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // na linha abaixo estamos passando nosso id.
            data.putExtra(EXTRA_ID, id);
        }

        // finalmente estamos definindo o resultado como dados.
        setResult(RESULT_OK, data);

        // exibindo uma mensagem toast após adicionar os dados
        Toast.makeText(this, "Produto salvo com sucesso no banco de dados Room. ", Toast.LENGTH_SHORT).show();
    }
}

