package ar.edu.utn.frsf.isi.dam.marketplace;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frsf.isi.dam.marketplace.model.Categoria;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_SELECCIONAR_CATEGORIA = 1;

    private EditText tituloEditText;
    private EditText descripcionEditText;
    private EditText emailEditText;
    private EditText importeEditText;
    private TextView categoriaTextView;
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
        categoriaTextView = findViewById(R.id.categoriaTextViewId);
        categoriaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CategoriaActivity.class);
                startActivityForResult(i, CODIGO_SELECCIONAR_CATEGORIA);
            }
        });

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

    private List<Categoria> getCategorias() {
        return Arrays.asList(new Categoria("", "", 1), new Categoria("1", "dfsdf", 1));
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

        if (TextUtils.isEmpty(categoriaTextView.getText().toString().trim())) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_SELECCIONAR_CATEGORIA) {
                Categoria categoria = data.getParcelableExtra("categoria");
                categoriaTextView.setText(categoria.getNombre());
            }
        }
    }
}