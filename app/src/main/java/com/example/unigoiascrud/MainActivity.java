package com.example.unigoiascrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Criar variáveis para a visão recicladora
    private RecyclerView productsRV;
    private static final int ADD_PRODUCTS_REQUEST = 1;
    private static final int EDIT_PRODUCTS_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar variável para a visão recicladora e fab.
        productsRV = findViewById(R.id.idProdutos);
        FloatingActionButton fab = findViewById(R.id.idBotaoAdd);

        // Adicionar ao clicar no botão de ação flutuante.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar uma nova atividade para adicionar um novo produto e passar um valor constante para ele.
                Intent intent = new Intent(MainActivity.this, NewProductsActivity.class);
                startActivityForResult(intent, ADD_PRODUCTS_REQUEST);
            }
        });

        // Configurar o gerenciador de layout para a classe adaptadora.
        productsRV.setLayoutManager(new LinearLayoutManager(this));
        productsRV.setHasFixedSize(true);

        // Inicialização para visualização do reciclador.
        final ProductsRVAdapter adapter = new ProductsRVAdapter();

        // Configurar a classe do adaptador para visualização do reciclador.
        productsRV.setAdapter(adapter);

        // Passar um dado do modo de exibição.
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        // Oobter todos os produtos do modal de exibição.
        viewmodal.getAllProducts().observe(this, new Observer<List<ProductsModal>>() {
            @Override
            public void onChanged(List<ProductsModal> models) {
                // Quando os dados são alterados no modelos, adicionamos essa lista à nossa classe de adaptador.
                adapter.submitList(models);
            }
        });
        // Método para adicionar o método de deslizar para excluir, para o item da visualização do reciclador.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Visualixação do reciclador, o item deslocado, excluindo o item de visualização do reciclador.
                viewmodal.delete(adapter.getProductsAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Produto excluído com sucesso.", Toast.LENGTH_SHORT).show();
            }
        }).
                // Anexar à visualização do reciclador.
                        attachToRecyclerView(productsRV);
        // Definir o clique de item para o item de visualização do reciclador.
        adapter.setOnItemClickListener(new ProductsRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductsModal model) {
                // Ao clicar no item da visualização do reciclador, abrir uma nova atividade e passar os dados para a nossa atividade.
                Intent intent = new Intent(MainActivity.this, NewProductsActivity.class);
                intent.putExtra(NewProductsActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewProductsActivity.EXTRA_PRODUCTS_NAME, model.getProductsName());
                intent.putExtra(NewProductsActivity.EXTRA_DESCRIPTION, model.getProductsDescription());
                intent.putExtra(NewProductsActivity.EXTRA_PRICE, model.getProductsPrice());

                // Iniciar uma nova atividade e adicionar uma constante de edição do produto.
                startActivityForResult(intent, EDIT_PRODUCTS_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRODUCTS_REQUEST && resultCode == RESULT_OK) {
            String productsName = data.getStringExtra(NewProductsActivity.EXTRA_PRODUCTS_NAME);
            String productsDescription = data.getStringExtra(NewProductsActivity.EXTRA_DESCRIPTION);
            String productsPrice = data.getStringExtra(NewProductsActivity.EXTRA_PRICE);
            ProductsModal model = new ProductsModal(productsName, productsDescription, productsPrice);
            viewmodal.insert(model);
            Toast.makeText(this, "Produto salvo.", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PRODUCTS_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewProductsActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Produto não atualizado.", Toast.LENGTH_SHORT).show();
                return;
            }
            String productsName = data.getStringExtra(NewProductsActivity.EXTRA_PRODUCTS_NAME);
            String productsDesc = data.getStringExtra(NewProductsActivity.EXTRA_DESCRIPTION);
            String productsPrice = data.getStringExtra(NewProductsActivity.EXTRA_PRICE);
            ProductsModal model = new ProductsModal(productsName, productsDesc, productsPrice);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Produto atualizado.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Produto não salvo.", Toast.LENGTH_SHORT).show();
        }
    }
}
