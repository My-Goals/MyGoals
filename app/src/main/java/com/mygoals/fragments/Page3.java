package com.mygoals.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mygoals.NotasAdapter;
import com.mygoals.Notas;
import com.mygoals.R;
import com.mygoals.ui.main.PageViewModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page3 extends Fragment {
    BarChart barChart;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG="";
    private String mParam1;
    private String mParam2;
    private PageViewModel mViewModel;
    private CalendarView calendarView;
    private EditText notaText;
    private String dateSelected;
    private Date selectedDate;
    private  Button boton;
    private DatabaseReference databaseReference;
    private FirebaseFirestore db;
    private DocumentReference noteRef;



    public Page3() {
        // Required empty public constructor
    }


    public static Page3 newInstance(String param1, String param2) {
        Page3 fragment = new Page3();
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
        View view = inflater.inflate(R.layout.fragment_page3, container, false);
        calendarView=view.findViewById(R.id.calendarView);
        notaText=view.findViewById(R.id.textNota);
        boton=view.findViewById(R.id.button);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        RecyclerView recyclerView=view.findViewById(R.id.noteView);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        CollectionReference userCollectionRef = FirebaseFirestore.getInstance()
                .collection("usuarios")
                .document(uid)
                .collection("notas");
        Query query =userCollectionRef.orderBy("fecha", Query.Direction.ASCENDING);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        List<Notas> dataList = new ArrayList<>();

                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            // Extract data from Firestore document
                            Date fecha = document.getDate("fecha");
                            String nota = document.getString("nota");

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String fechaString = dateFormat.format(fecha);

                            // Create a MyData object with retrieved data
                            Notas data = new Notas(fechaString, nota);

                            // Add the MyData object to the list
                            dataList.add(data);
                        }

                        // Create an instance of the adapter, passing the retrieved data
                        NotasAdapter adapter = new NotasAdapter(dataList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);

                        // Set the adapter on the RecyclerView
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure to retrieve data from Firestore
                        Toast.makeText(getActivity(), "Error retrieving data from Firestore", Toast.LENGTH_SHORT).show();
                    }
                });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                // Update the selected date based on the values from the parameters
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                selectedDate = calendar.getTime();
            }
        });
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nota=notaText.getText().toString();

                Map<String, Object> data = new HashMap<>();
                data.put("fecha", selectedDate);
                data.put("nota", nota);


                Log.d(TAG, "Selected Date: " + selectedDate);
                Log.d(TAG, "Nota: " + nota);



                userCollectionRef.add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Datos guardados correctamente
                        Toast.makeText(getActivity(), "Datos guardados en Firestore", Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error al guardar los datos
                                Toast.makeText(getActivity(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();

                                Log.d(TAG, selectedDate + " " + nota);
                            }
                        });

            }
        });



        return view;

    }


}