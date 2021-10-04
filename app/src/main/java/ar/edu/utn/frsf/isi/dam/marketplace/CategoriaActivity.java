package ar.edu.utn.frsf.isi.dam.marketplace;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.isi.dam.marketplace.model.Categoria;

public class CategoriaActivity extends AppCompatActivity {
    private RecyclerView categoriaRecyclerView;
    private CategoriaRecyclerViewAdapter categoriaRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_categoria);

        categoriaRecyclerView = findViewById(R.id.categoriaRecyclerViewId);
        categoriaRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        categoriaRecyclerView.setLayoutManager(layoutManager);

        categoriaRecyclerAdapter = new CategoriaRecyclerViewAdapter(getCategorias());
        categoriaRecyclerView.setAdapter(categoriaRecyclerAdapter);
    }

    private List<Categoria> getCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();

        categorias.add(new Categoria(getString(R.string.categoria_01_id), getString(R.string.categoria_01_nombre), getColor(R.color.categoria_01_color)));
        categorias.add(new Categoria(getString(R.string.categoria_02_id), getString(R.string.categoria_02_nombre), getColor(R.color.categoria_02_color)));
        categorias.add(new Categoria(getString(R.string.categoria_03_id), getString(R.string.categoria_03_nombre), getColor(R.color.categoria_03_color)));
        categorias.add(new Categoria(getString(R.string.categoria_04_id), getString(R.string.categoria_04_nombre), getColor(R.color.categoria_04_color)));
        categorias.add(new Categoria(getString(R.string.categoria_05_id), getString(R.string.categoria_05_nombre), getColor(R.color.categoria_05_color)));
        categorias.add(new Categoria(getString(R.string.categoria_06_id), getString(R.string.categoria_06_nombre), getColor(R.color.categoria_06_color)));
        categorias.add(new Categoria(getString(R.string.categoria_07_id), getString(R.string.categoria_07_nombre), getColor(R.color.categoria_07_color)));
        categorias.add(new Categoria(getString(R.string.categoria_08_id), getString(R.string.categoria_08_nombre), getColor(R.color.categoria_08_color)));
        categorias.add(new Categoria(getString(R.string.categoria_09_id), getString(R.string.categoria_09_nombre), getColor(R.color.categoria_09_color)));
        categorias.add(new Categoria(getString(R.string.categoria_10_id), getString(R.string.categoria_10_nombre), getColor(R.color.categoria_10_color)));
        categorias.add(new Categoria(getString(R.string.categoria_11_id), getString(R.string.categoria_11_nombre), getColor(R.color.categoria_11_color)));
        categorias.add(new Categoria(getString(R.string.categoria_12_id), getString(R.string.categoria_12_nombre), getColor(R.color.categoria_12_color)));
        categorias.add(new Categoria(getString(R.string.categoria_13_id), getString(R.string.categoria_13_nombre), getColor(R.color.categoria_13_color)));
        categorias.add(new Categoria(getString(R.string.categoria_14_id), getString(R.string.categoria_14_nombre), getColor(R.color.categoria_14_color)));
        categorias.add(new Categoria(getString(R.string.categoria_15_id), getString(R.string.categoria_15_nombre), getColor(R.color.categoria_15_color)));
        categorias.add(new Categoria(getString(R.string.categoria_16_id), getString(R.string.categoria_16_nombre), getColor(R.color.categoria_16_color)));
        categorias.add(new Categoria(getString(R.string.categoria_17_id), getString(R.string.categoria_17_nombre), getColor(R.color.categoria_17_color)));
        categorias.add(new Categoria(getString(R.string.categoria_18_id), getString(R.string.categoria_18_nombre), getColor(R.color.categoria_18_color)));
        categorias.add(new Categoria(getString(R.string.categoria_19_id), getString(R.string.categoria_19_nombre), getColor(R.color.categoria_19_color)));
        categorias.add(new Categoria(getString(R.string.categoria_20_id), getString(R.string.categoria_20_nombre), getColor(R.color.categoria_20_color)));
        categorias.add(new Categoria(getString(R.string.categoria_21_id), getString(R.string.categoria_21_nombre), getColor(R.color.categoria_21_color)));
        categorias.add(new Categoria(getString(R.string.categoria_22_id), getString(R.string.categoria_22_nombre), getColor(R.color.categoria_22_color)));
        categorias.add(new Categoria(getString(R.string.categoria_23_id), getString(R.string.categoria_23_nombre), getColor(R.color.categoria_23_color)));
        categorias.add(new Categoria(getString(R.string.categoria_24_id), getString(R.string.categoria_24_nombre), getColor(R.color.categoria_24_color)));
        categorias.add(new Categoria(getString(R.string.categoria_25_id), getString(R.string.categoria_25_nombre), getColor(R.color.categoria_25_color)));
        categorias.add(new Categoria(getString(R.string.categoria_26_id), getString(R.string.categoria_26_nombre), getColor(R.color.categoria_26_color)));
        categorias.add(new Categoria(getString(R.string.categoria_27_id), getString(R.string.categoria_27_nombre), getColor(R.color.categoria_27_color)));
        categorias.add(new Categoria(getString(R.string.categoria_28_id), getString(R.string.categoria_28_nombre), getColor(R.color.categoria_28_color)));
        categorias.add(new Categoria(getString(R.string.categoria_29_id), getString(R.string.categoria_29_nombre), getColor(R.color.categoria_29_color)));
        categorias.add(new Categoria(getString(R.string.categoria_30_id), getString(R.string.categoria_30_nombre), getColor(R.color.categoria_30_color)));
        categorias.add(new Categoria(getString(R.string.categoria_31_id), getString(R.string.categoria_31_nombre), getColor(R.color.categoria_31_color)));
        categorias.add(new Categoria(getString(R.string.categoria_32_id), getString(R.string.categoria_32_nombre), getColor(R.color.categoria_32_color)));

        return categorias;
    }
}