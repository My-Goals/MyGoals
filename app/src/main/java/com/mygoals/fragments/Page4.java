package com.mygoals.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mygoals.InformacionActivity;
import com.mygoals.R;
import com.mygoals.ui.main.PageViewModel;

import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;



/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Page4 extends Fragment {
     // variable de IMC y %GC
     private EditText editTextPeso, editTextAltura, editTextEdad;
    private RadioGroup radioGroupSexo;
    private TextView textViewIMC, textViewGC;
    private Button buttonCalcular;
    private Button buttonInformacion;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Firestore";
    //private ArrayList<Bill> bills = new ArrayList<Bill>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PageViewModel mViewModel;


    public Page4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page4.
     */
    // TODO: Rename and change types and number of parameters
    public static Page4 newInstance(String param1, String param2) {
        Page4 fragment = new Page4();
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

        View view = inflater.inflate(R.layout.fragment_page4, container, false);
        //variables IMC y %GC
        editTextPeso = view.findViewById(R.id.editTextPeso);
        editTextAltura = view.findViewById(R.id.editTextAltura);
        editTextEdad = view.findViewById(R.id.editTextEdad);
        radioGroupSexo = view.findViewById(R.id.radioGroupSexo);
        textViewIMC = view.findViewById(R.id.textViewIMC);
        textViewGC = view.findViewById(R.id.textViewGC);
        buttonCalcular = view.findViewById(R.id.buttonCalcular);
        buttonInformacion = view.findViewById(R.id.buttonInformacion);

        buttonInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacion();
            }
        });

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMCyGC();
            }
        });

        return view;
    }

    private void calcularIMCyGC() {
        // Obtener los valores ingresados por el usuario
        double peso = Double.parseDouble(editTextPeso.getText().toString());
        double altura = Double.parseDouble(editTextAltura.getText().toString());
        int edad = Integer.parseInt(editTextEdad.getText().toString());

        // Calcular el IMC
        double imc = peso / (altura * altura);

        // Obtener el RadioButton seleccionado
        int radioButtonId = radioGroupSexo.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(radioButtonId);

        // Calcular el porcentaje de grasa corporal según el sexo
        double porcentajeGC;
        if (radioButton.getText().toString().equals("Hombre")) {
            porcentajeGC = (1.20 * imc) + (0.23 * edad) - 16.2;
        } else {
            porcentajeGC = (1.20 * imc) + (0.23 * edad) - 5.4;
        }

        // Mostrar los resultados en los TextView correspondientes
        textViewIMC.setText(String.format("IMC: %.2f", imc));
        textViewGC.setText(String.format("%%GC: %.2f", porcentajeGC));
    }

    private void abrirInformacion() {
        Intent intent = new Intent(getActivity(), InformacionActivity.class);
        startActivity(intent);
    }
}
