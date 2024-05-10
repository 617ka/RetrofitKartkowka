package com.example.retrofitkartkowka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Planszowki> wszystkiePlanszowki = new ArrayList<Planszowki>();
    EditText editTextMinWiek;
    Button button;
    ListView listView;
    ArrayAdapter<Planszowki> arrayAdapter;
    List<Planszowki> odpowiedniePlanszowki = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMinWiek = findViewById(R.id.editTextNumberDecimal);
        button = findViewById(R.id.button);
        listView = findViewById(R.id.listaGier);
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, odpowiedniePlanszowki);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/617ka/RetrofitKartkowka/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        Call <List<Planszowki>> call = jsonPlaceholderApi.getPlanszowki();
        call.enqueue(
                new Callback<List<Planszowki>>() {
                    @Override
                    public void onResponse(Call<List<Planszowki>> call, Response<List<Planszowki>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT);
                        }
                        else{
                            for (Planszowki item : response.body()) {
                                wszystkiePlanszowki.add(item);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Planszowki>> call, Throwable t) {
                    }
                }
        );


        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editTextMinWiek.getText().toString().matches("-?\\d+")) {
                            odpowiedniePlanszowki.clear();
                            for (Planszowki item : wszystkiePlanszowki) {
                                if (Integer.parseInt(editTextMinWiek.getText().toString()) >= item.getMinimWiek()) {
                                    odpowiedniePlanszowki.add(item);
                                }
                            }
                            listView.setAdapter(arrayAdapter);
                        }else{
                            Toast.makeText(MainActivity.this, "Nie ma planszówki dla ciebie", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

}