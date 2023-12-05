package com.example.unigoiascrud;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ViewModal extends AndroidViewModel {

    // Criar uma nova variável para o repositório do produto.
    private ProductsRepository repository;

    // Criar uma variável de dados onde todos os produtos estão presentes.
    private LiveData<List<ProductsModal>> allProducts;

    // Construtor para o modo de exibição.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
        allProducts = repository.getAllProducts();
    }

    // Método para inserir os dados no repositório.
    public void insert(ProductsModal model) {
        repository.insert(model);
    }

    // Atualizar os dados no repositório.
    public void update(ProductsModal model) {
        repository.update(model);
    }

    // Deletar os dados no repositório
    public void delete(ProductsModal model) {
        repository.delete(model);
    }

    // Excluir todos os produtos da lista.
    public void deleteAllProducts() {
        repository.deleteAllProducts();
    }

    // Obter todos os cursos da lista.
    public LiveData<List<ProductsModal>> getAllProducts() {
        return allProducts;
    }
}

