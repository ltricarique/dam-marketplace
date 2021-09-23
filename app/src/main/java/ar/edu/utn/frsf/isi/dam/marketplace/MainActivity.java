package ar.edu.utn.frsf.isi.dam.marketplace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.isi.dam.marketplace.model.Categoria;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<Categoria> categoriaArrayAdapter;
    private EditText tituloEditText;
    private EditText descripcionEditText;
    private EditText emailEditText;
    private EditText importeEditText;
    private Spinner categoriaSpinner;
    private LinearLayout descuentoLinearLayout;
    private Switch descuentoSwitch;
    private SeekBar descuentoSeekBar;
    private CheckBox retiroCheckBox;
    private EditText retiroEditText;
    private CheckBox terminosCheckBox;
    private Button publicarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tituloEditText = findViewById(R.id.tituloEditTextId);
        descripcionEditText = findViewById(R.id.descripionEditTextId);
        emailEditText = findViewById(R.id.emailEditTextId);
        importeEditText = findViewById(R.id.importeEditTextId);
        retiroEditText = findViewById(R.id.retiroEditTextId);

        categoriaArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getCategorias());

        categoriaSpinner = findViewById(R.id.categoriaSpinnerId);
        categoriaSpinner.setAdapter(categoriaArrayAdapter);

        descuentoLinearLayout = findViewById(R.id.linearLayoutDescuentoId);
        descuentoSeekBar = findViewById(R.id.descuentoSeekBarId);

        descuentoSwitch = findViewById(R.id.descuentoSwitchId);
        descuentoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    descuentoSwitch.setText(getString(R.string.switch_descuento_text) + getString(R.string.switch_descuento_substring_1_text) + descuentoSeekBar.getProgress() + getString(R.string.switch_descuento_substring_2_text));
                    descuentoLinearLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    descuentoSwitch.setText(getResources().getString(R.string.switch_descuento_text));
                    descuentoLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        descuentoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                descuentoSwitch.setText(getString(R.string.switch_descuento_text) + getString(R.string.switch_descuento_substring_1_text) + i + getString(R.string.switch_descuento_substring_2_text));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        retiroEditText = findViewById(R.id.retiroEditTextId);

        retiroCheckBox = findViewById(R.id.retiroCheckBoxId);
        retiroCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    retiroEditText.setVisibility(View.VISIBLE);
                else
                    retiroEditText.setVisibility(View.GONE);
            }
        });

        publicarButton = findViewById(R.id.publicarButtonId);

        terminosCheckBox = findViewById(R.id.terminosCheckBoxId);
        terminosCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                publicarButton.setEnabled(b);
            }
        });

        publicarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar())
                    Toast.makeText(getApplicationContext(), R.string.validacion_articulo_mensaje_1, Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validar() {
        if (TextUtils.isEmpty(tituloEditText.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), R.string.validacion_titulo_requerido, Toast.LENGTH_LONG).show();
            return false;
        }

        if (!TextUtils.isEmpty(emailEditText.getText().toString().trim())) {
            if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString().trim()).matches()) {
                Toast.makeText(getApplicationContext(), R.string.validacion_email_invalido, Toast.LENGTH_LONG).show();
                return false;
            }
        }

        if (TextUtils.isEmpty(importeEditText.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), R.string.validacion_importe_requerido, Toast.LENGTH_LONG).show();
            return false;
        }

        BigDecimal importe = null;

        try {
            importe = new BigDecimal(importeEditText.getText().toString().trim());
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.validacion_importe_invalido, Toast.LENGTH_LONG).show();
            return false;
        }

        if (importe.compareTo(new BigDecimal(0.0)) <= 0) {
            Toast.makeText(getApplicationContext(), R.string.validacion_importe_mayor_a_cero, Toast.LENGTH_LONG).show();
            return false;
        }

        if (((Categoria) categoriaSpinner.getSelectedItem()).getId() < 1) {
            Toast.makeText(getApplicationContext(), R.string.validacion_categoria_requerido, Toast.LENGTH_LONG).show();
            return false;
        }

        if (descuentoSwitch.isChecked())
            if (descuentoSeekBar.getProgress() == 0) {
                Toast.makeText(getApplicationContext(), R.string.validacion_descuento_mayor_a_cero, Toast.LENGTH_LONG).show();
                return false;
            }

        if (retiroCheckBox.isChecked())
            if (TextUtils.isEmpty(retiroEditText.getText().toString().trim())) {
                Toast.makeText(getApplicationContext(), R.string.validacion_retiro_requerido, Toast.LENGTH_LONG).show();
                return false;
            }

        if (!terminosCheckBox.isChecked()) {
            Toast.makeText(getApplicationContext(), R.string.validacion_terminos_requerido, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private List<Categoria> getCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();

        categorias.add(new Categoria(0, getString(R.string.categoria_vacio_nombre)));
        categorias.add(new Categoria(1, getString(R.string.categoria_indumentaria_nombre)));
        categorias.add(new Categoria(2, getString(R.string.categoria_electronica_nombre)));
        categorias.add(new Categoria(3, getString(R.string.categoria_entretenimiento_nombre)));
        categorias.add(new Categoria(4, getString(R.string.categoria_jardin_nombre)));
        categorias.add(new Categoria(5, getString(R.string.categoria_vehiculos_nombre)));
        categorias.add(new Categoria(6, getString(R.string.categoria_juguetes_nombre)));

        return categorias;
    }
}