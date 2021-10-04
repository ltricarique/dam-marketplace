package ar.edu.utn.frsf.isi.dam.marketplace;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.marketplace.model.Categoria;

public class CategoriaRecyclerViewAdapter extends RecyclerView.Adapter<CategoriaRecyclerViewAdapter.CategoriaViewHolder> implements View.OnClickListener {
    private List<Categoria> categorias;
    private RecyclerView recyclerView;

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView color;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreTextViewId);
            color = itemView.findViewById(R.id.colorTextViewId);
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getColor() {
            return color;
        }
    }

    public CategoriaRecyclerViewAdapter(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_categoria, viewGroup, false);
        view.setOnClickListener(this);

        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder categoriaViewHolder, int i) {
        Categoria categoria = categorias.get(i);

        categoriaViewHolder.itemView.setTag(i);
        categoriaViewHolder.getColor().setBackgroundColor(categoria.getColor());
        categoriaViewHolder.getNombre().setText(categoria.getNombre());
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    @Override
    public void onClick(View view) {
        Activity activity = getActivity();

        if (activity != null) {
            Intent intent = new Intent();
            intent.putExtra("categoria", categorias.get((int) view.getTag()));
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    private Activity getActivity() {
        if (recyclerView != null) {
            Context context = recyclerView.getContext();
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }

                context = ((ContextWrapper) context).getBaseContext();
            }
        }

        return null;
    }
}
