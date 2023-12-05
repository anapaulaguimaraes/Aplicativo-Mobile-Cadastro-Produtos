package com.example.unigoiascrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsRVAdapter extends ListAdapter<ProductsModal,
        ProductsRVAdapter.ViewHolder> {

    // Criar variável para quando clicar no item.
    private OnItemClickListener listener;

    // Criar uma classe de construtor para classe de adaptador.
    ProductsRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // Criar um retorno de chamada para o item da visualização do reciclador.
    private static final DiffUtil.ItemCallback<ProductsModal> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ProductsModal>() {
                @Override
                public boolean areItemsTheSame(ProductsModal oldItem, ProductsModal newItem)
                {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(ProductsModal oldItem, ProductsModal newItem) {
                    // Verificar o nome do produto, descrição e preço.
                    return oldItem.getProductsName().equals(newItem.getProductsName()) &&

                            oldItem.getProductsDescription().equals(newItem.getProductsDescription()) &&

                            oldItem.getProductsPrice().equals(newItem.getProductsPrice());
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Aumentar nosso arquivo de layout, para cada item de visualização do reciclador.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Definir dados para cada item de visão do reciclador.
        ProductsModal model = getProductsAt(position);
        holder.productsNameTV.setText(model.getProductsName());
        holder.productsDescTV.setText(model.getProductsDescription());
        holder.productsPriceTV.setText(model.getProductsPrice());
    }

    // Criar método para obter o modal do produto para uma posição específica.
    public ProductsModal getProductsAt(int position) {

        return getItem(position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Classe portadora de visão para criar uma variável para cada visão.
        TextView productsNameTV, productsDescTV, productsPriceTV;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializando cada visão de nossa visão recicladora.
            productsNameTV = itemView.findViewById(R.id.idTVNomeProduto);
            productsDescTV = itemView.findViewById(R.id.idTVDescricaoProduto);
            productsPriceTV = itemView.findViewById(R.id.idTVPrecoProduto);

            // Adicionar clique para cada item da visualização do reciclador.
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Dentro do listener do clique, estamos passando a posição para o item de visualização do reciclador.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ProductsModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
