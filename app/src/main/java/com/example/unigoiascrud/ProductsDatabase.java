package com.example.unigoiascrud;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// Adicionar anotações para nossas entidades de banco de dados e versão db.
@Database(entities = {ProductsModal.class}, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {

    // Criar uma instância para classe do banco de dados.
    private static ProductsDatabase instance;

    // Criar uma variável abstrata para dao.

    public abstract Dao Dao();

    // Obter instância para banco de dados.
    public static synchronized ProductsDatabase getInstance(Context context) {
        // Verificar se a instância é nula ou não.
        if (instance == null) {
            // Se a instância for nula, criar uma nova instância.
            instance =
                    // Para criar uma instância para o banco de dados, criar um construtor do banco de dados e passar a classe do banco de dados com nome do banco de dados.
                    Room.databaseBuilder(context.getApplicationContext(),
                                    ProductsDatabase.class, "products_database")
                            // Adiciona retorno à migração destrutiva para o banco de dados.
                            .fallbackToDestructiveMigration()
                            // Adiciona retorno de chamada ao banco de dados.
                            .addCallback(roomCallback)
                            // Construir o banco de dados.
                            .build();
        }
        // Criar uma instância, e retorna a instância.
        return instance;
    }

    // Criar um retorno de chamada para o banco de dados ROOM.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Método chamado quando o banco de dados é criado, a linha abaixo é para preencher os dados.
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // Criar uma classe de tarefa assíncrona para realizar a tarefa em segundo plano.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(ProductsDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}

