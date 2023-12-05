package com.example.unigoiascrud;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ProductsRepository {

    // Criar uma variável para dao e listar todos os produtos.
    private Dao dao;
    private LiveData<List<ProductsModal>> allProducts;

    // Criar construtor para variáveis e passar as variáveis para ele.
    public ProductsRepository(Application application) {
        ProductsDatabase database = ProductsDatabase.getInstance(application);
        dao = database.Dao();
        allProducts = dao.getAllProducts();
    }

    // Criar método para inserir dados no banco de dados.
    public void insert(ProductsModal model) {
        new InsertProductsAsyncTask(dao).execute(model);
    }

    // Criar método para atualizar dados no banco de dados.
    public void update(ProductsModal model) {
        new UpdateProductsAsyncTask(dao).execute(model);
    }

    // Criar método para deletar dados no banco de dados.
    public void delete(ProductsModal model) {
        new DeleteProductsAsyncTask(dao).execute(model);
    }

    // Método para deletar todos os produtos.
    public void deleteAllProducts() {
        new DeleteAllProductsAsyncTask(dao).execute();
    }

    // Método para ler todos os produtos.
    public LiveData<List<ProductsModal>> getAllProducts() {
        return allProducts;
    }

    // Criar método de tarefa assíncrona para inserir um novo produto.
    private static class InsertProductsAsyncTask extends AsyncTask<ProductsModal, Void, Void> {
        private Dao dao;

        private InsertProductsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductsModal... model) {
            // Inserir modal no dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // Criar método de tarefa assíncrona para inserir um novo produto.
    private static class UpdateProductsAsyncTask extends AsyncTask<ProductsModal, Void, Void> {
        private Dao dao;

        private UpdateProductsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductsModal... models) {
            // Atualizar modal no dao.
            dao.update(models[0]);
            return null;
        }
    }

    // Criar um método de tarefa assíncrona para atualizar produto.
    private static class DeleteProductsAsyncTask extends AsyncTask<ProductsModal, Void, Void> {
        private Dao dao;

        private DeleteProductsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ProductsModal... models) {
            // Deletar modal de produto no dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // Criar um método de tarefa assíncrona para excluir o produto.
    private static class DeleteAllProductsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllProductsAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // Método para deletar todos os produtos.
            dao.deleteAllProducts();
            return null;
        }
    }
}

