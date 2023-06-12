package com.mygoals.fragments;
import com.mygoals.Alimento;
import com.mygoals.Seleccion;
import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mygoals.LoginPage;
import com.mygoals.R;
import com.mygoals.ui.main.PageViewModel;
// introducidos

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page2 extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private PageViewModel mViewModel;

    //vatiables para calculadora de calorias
    private Spinner spinnerCarnes;
    private Spinner spinnerVerduras;
    private Spinner spinnerFrutas;
    private Spinner spinnerAnimal;
    private Spinner spinnerVegetal;
    private EditText editTextCantidad;
    private Button btnAgregar;
    private Button btnCalcular;
    private RecyclerView recyclerViewAlimentos;
    //private TextView textViewTotal;
    private TextView textViewGrasas;
    private TextView textViewProteinas;
    private TextView textViewCarbohidratos;
    private TextView textViewKcal;

    //contenidos de los spinner
    private String[] carnes = {"selecciona alimento","Carne de res", "Pollo", "Cerdo"};
    private String[] verduras = {"selecciona alimento","Espinaca", "Lechuga", "Brócoli"};
    private String[] frutas = {"selecciona alimento","Manzana", "Plátano", "Naranja"};
    private String[] origenAnimal = {"selecciona alimento","Leche", "Huevo", "Queso"};
    private String[] origenVegetal = {"selecciona alimento","Arroz", "Trigo", "Avena"};

    private ArrayList<Seleccion> lista;
    private ArrayList<Alimento> registro;


    public Page2() {
        // Required empty public constructor
    }

    public static Page2 newInstance(String param1, String param2) {
        Page2 fragment = new Page2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page2, container, false);

        //inicializacin de lista
        lista = new ArrayList<>();

        // manejo de variables de la calculadora
        spinnerCarnes = view.findViewById(R.id.spinner_carnes);
        spinnerVerduras = view.findViewById(R.id.spinner_verduras);
        spinnerFrutas = view.findViewById(R.id.spinner_frutas);
        spinnerAnimal = view.findViewById(R.id.spinner_animal);
        spinnerVegetal = view.findViewById(R.id.spinner_vegetal);
        editTextCantidad = view.findViewById(R.id.edittext_cantidad);
        btnAgregar = view.findViewById(R.id.btn_agregar);
        btnCalcular = view.findViewById(R.id.btn_calcular);
        recyclerViewAlimentos = view.findViewById(R.id.recyclerview_alimentos);
        //textViewTotal = view.findViewById(R.id.textview_total);
        textViewGrasas = view.findViewById(R.id.textview_grasas);
        textViewProteinas = view.findViewById(R.id.textview_proteinas);
        textViewCarbohidratos = view.findViewById(R.id.textview_carbohidratos);
        textViewKcal = view.findViewById(R.id.textview_kcal);

        // Create ArrayAdapter using the string arrays and default spinner layout
        ArrayAdapter<String> carnesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, carnes);
        ArrayAdapter<String> verdurasAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, verduras);
        ArrayAdapter<String> frutasAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, frutas);
        ArrayAdapter<String> animalAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, origenAnimal);
        ArrayAdapter<String> vegetalAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, origenVegetal);

        // Specify the layout to use when the list of choices appears
        carnesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        verdurasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frutasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vegetalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapters to the spinners
        spinnerCarnes.setAdapter(carnesAdapter);
        spinnerVerduras.setAdapter(verdurasAdapter);
        spinnerFrutas.setAdapter(frutasAdapter);
        spinnerAnimal.setAdapter(animalAdapter);
        spinnerVegetal.setAdapter(vegetalAdapter);

        // Set the item selection listeners for each spinner
        spinnerCarnes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCarne = (String) parent.getItemAtPosition(position);
                // Handle selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        spinnerVerduras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedVerdura = (String) parent.getItemAtPosition(position);
                // Handle selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        spinnerFrutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFruta = (String) parent.getItemAtPosition(position);
                // Handle selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        spinnerAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAnimal = (String) parent.getItemAtPosition(position);
                // Handle selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        spinnerVegetal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedVegetal = (String) parent.getItemAtPosition(position);
                // Handle selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para agregar el elemento
                String carne = spinnerCarnes.getSelectedItem().toString();
                String verdura = spinnerVerduras.getSelectedItem().toString();
                String fruta = spinnerFrutas.getSelectedItem().toString();
                String animal = spinnerAnimal.getSelectedItem().toString();
                String vegetal = spinnerVegetal.getSelectedItem().toString();
                String cantidad = editTextCantidad.getText().toString();

                // Verificar que se haya seleccionado un elemento válido
                if (!carne.equals("selecciona alimento") || !verdura.equals("selecciona alimento") ||
                        !fruta.equals("selecciona alimento") || !animal.equals("selecciona alimento") ||
                        !vegetal.equals("selecciona alimento")) {
                    // Verificar que se haya ingresado una cantidad válida
                    if (!cantidad.isEmpty()) {
                        // Crear una instancia de la clase Seleccion y agregarla al ArrayList
                        //if para carne
                        if(!carne.equals("selecciona alimento") &&
                                verdura.equals("selecciona alimento")&&
                                fruta.equals("selecciona alimento")&&
                                animal.equals("selecciona alimento")&&
                                vegetal.equals("selecciona alimento"))
                        {Seleccion seleccion = new Seleccion(carne, cantidad);
                            lista.add(seleccion);}
                        //if para verdura
                        if(!verdura.equals("selecciona alimento") &&
                                carne.equals("selecciona alimento")&&
                                fruta.equals("selecciona alimento")&&
                                animal.equals("selecciona alimento")&&
                                vegetal.equals("selecciona alimento"))
                        {Seleccion seleccion = new Seleccion(verdura, cantidad);
                            lista.add(seleccion);}
                        //if para fruta
                        if(!fruta.equals("selecciona alimento") &&
                                verdura.equals("selecciona alimento")&&
                                carne.equals("selecciona alimento")&&
                                animal.equals("selecciona alimento")&&
                                vegetal.equals("selecciona alimento"))
                        {Seleccion seleccion = new Seleccion(fruta, cantidad);
                            lista.add(seleccion);}
                        //if para animal
                        if(!animal.equals("selecciona alimento") &&
                                verdura.equals("selecciona alimento")&&
                                fruta.equals("selecciona alimento")&&
                                carne.equals("selecciona alimento")&&
                                vegetal.equals("selecciona alimento"))
                        {Seleccion seleccion = new Seleccion(animal, cantidad);
                            lista.add(seleccion);}
                        //if para vegetal
                        if(!vegetal.equals("selecciona alimento") &&
                                verdura.equals("selecciona alimento")&&
                                fruta.equals("selecciona alimento")&&
                                animal.equals("selecciona alimento")&&
                                carne.equals("selecciona alimento"))
                        {Seleccion seleccion = new Seleccion(vegetal, cantidad);
                            lista.add(seleccion);}


                        // Mostrar mensaje toast de confirmación
                        Toast.makeText(requireContext(), "Agregado a la lista", Toast.LENGTH_SHORT).show();

                        // Restablecer los Spinners a la primera posición
                        spinnerCarnes.setSelection(0);
                        spinnerVerduras.setSelection(0);
                        spinnerFrutas.setSelection(0);
                        spinnerAnimal.setSelection(0);
                        spinnerVegetal.setSelection(0);

                        // Restablecer el EditText
                        editTextCantidad.setText("");
                    } else {
                        Toast.makeText(requireContext(), "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Seleccione un alimento", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;


    }


 public int reglaDeTres(int cant,int val){
        int valor=0;
        valor=(cant*val)/100;
        return valor;
}


}
