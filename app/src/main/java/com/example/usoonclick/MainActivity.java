package com.example.usoonclick;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    // Propiedades personalizadas de la clase MainActivity
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imgviewFoto;
    private Uri imgURI;
    ListView lstdepartamentos;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Generando elemetos del componente ListView
        lstdepartamentos = findViewById(R.id.lstDepartamentos);
        items = new ArrayList<>();
        items.add("1 - Ahuachapán");
        items.add("2 - Santa Ana");
        items.add("3 - Sonsonate");
        //Estableciendo el tipo de distribución de los elementos: lista simple
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        //Asignando la lista de elementos de la clase Adapter al componente ListView
        lstdepartamentos.setAdapter(adapter);
        //Recuperando imagen de la sesión actual
        //Se usa para evitar que la imagen se pierda cuando se cambia entre modo claro y modo oscuro
        imgviewFoto = findViewById(R.id.ivImagenLogo);
        if (savedInstanceState != null) {
            String uriGuardada = savedInstanceState.getString("img_uri");
            if (uriGuardada != null) {
                imgURI = Uri.parse(uriGuardada);
                imgviewFoto.setImageURI(imgURI);
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v,
                                                                            insets) -> {
            Insets systemBars =
                    insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });
        // Refencias a elementos de la UI que serán utilizados desde la capa lógica.
        Switch swModo = findViewById(R.id.SwCambiarModo);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        EditText tvestadomodo = findViewById(R.id.edtEstadoModo);
        CheckBox chkboxdesamodo =
                findViewById(R.id.chboxDesactivacambiomodo);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        ImageButton imgbuttonfecha = findViewById(R.id.imgButtonFecha);
        ImageButton imgbuttonhora = findViewById(R.id.imgButtonHora);
        // Manejo del evento onClick del elemento de tipo Switch
        swModo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Cambiando modo")
                        .setMessage("Se cambiará el modo de la aplicación")
                        .setPositiveButton("OK", null)
                        .show();
                boolean estado = ((Switch) view).isChecked();
                if (estado) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    tvestadomodo.setText("Modo oscuro está activado");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    tvestadomodo.setText("");
                }
            }
        });
        // Manejo del evento onCheckedChanged que representa una acción equivalente a onClick radioGroup.
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId != 1) {
                    RadioButton radioButtonSelec =
                            findViewById(checkedId);
                    int indexbutton =
                            radioGroup.indexOfChild(radioButtonSelec);
                    if (indexbutton == 1) {
                        LayoutInflater inflater = getLayoutInflater();
                        View dialogView =
                                inflater.inflate(R.layout.dialogo_personalizado, null);
                        EditText razonocultar =
                                dialogView.findViewById(R.id.edtJustificacion);
                        AlertDialog.Builder builder = new
                                AlertDialog.Builder(MainActivity.this);
                        builder.setView(dialogView);
                        builder.setTitle("Venta - Razón para ocultar ");
                        builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                String razon =
                                        razonocultar.getText().toString();
                                tvestadomodo.setText(razon);
                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tvestadomodo.setText("");
                            }
                        });
                        builder.show();
                        imgviewFoto.setVisibility(View.INVISIBLE);
                    } else {
                        imgviewFoto.setVisibility(View.VISIBLE);
                        tvestadomodo.setText("");
                    }
                }
            }
        });
        // Manejo del evento onClick del elemento de tipo
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // Manejo del evento onClick del elemento de tipo CheckBox
        chkboxdesamodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmar cambio de modo")
                        .setMessage("Esta seguro que desea deshabilitar el cambio de modo")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                swModo.setEnabled(!chkboxdesamodo.isChecked());
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                chkboxdesamodo.setChecked(!chkboxdesamodo.isChecked());
                            }
                        })
                        .show();
            }
        });
        // Manejo del evento onItemClick del elemento de tipo ListView
        lstdepartamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView itemselected = (TextView) view;
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ingrese el nuevo valor para el elemento seleccionado");
                final EditText nuevotexto = new EditText(MainActivity.this);
                nuevotexto.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(nuevotexto);
                builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String textonuevo =
                                nuevotexto.getText().toString();
                        itemselected.setText(textonuevo);
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                builder.show();
            }
        });
        // Manejo del evento onClick del elemento de tipo ImageView
        imgviewFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGalery();
            }
        });
        imgbuttonfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int annio = LocalDate.now().getYear();
                int mes = LocalDate.now().getMonthValue();
                int dia = LocalDate.now().getDayOfMonth();
                DatePickerDialog datePickerDialog = new
                        DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String fecha = dayOfMonth + "/" + (month +
                                        1) + "/" + year;
                                tvestadomodo.setText("Fecha: " + fecha);
                            }
                        }, annio, mes - 1, dia);
                datePickerDialog.show();
            }
        });
        imgbuttonhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hora =
                        Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1;
                int minutos =
                        Calendar.getInstance().get(Calendar.MINUTE) + 1;
                TimePickerDialog timePickerDialog = new
                        TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String hora = hourOfDay + ":" +
                                        String.format("%02d", minute);
                                tvestadomodo.setText("Hora : " + hora);
                            }
                        }, hora - 1, minutos, true);
                timePickerDialog.show();
            }
        });
    }

    // Metodo personalizado de la clase que se usa para crear un Intend
    // que servira para abrir la galeria de fotos y seleccionar una imagen que sera cargada en el ImageView

    private void OpenGalery() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    // Método de la clse sobreescrito para optener la URI de la imagen seleccionda en la galeria de fotos

    // y asignarla como valor a la propiedad URI del elemento ImageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            imgURI = data.getData();
            imgviewFoto.setImageURI(imgURI);
        }
    }

    //Método de la clae para guardar cambios en el propiedad URI
    // Que servira para recuperar la imagen asiganda al elemento ImageView cuando se haga un cambio de modo

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (imgURI != null) {
            outState.putString("img_uri", imgURI.toString());
        }
    }
}