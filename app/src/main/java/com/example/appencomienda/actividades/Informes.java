package com.example.appencomienda.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appencomienda.R;
import com.example.appencomienda.list.EncomiendaAdapter;
import com.example.appencomienda.modelo.Encomienda;
import com.example.appencomienda.modelo.EnvioDatos;
import com.example.appencomienda.retrofit.RetrofitCliente;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Informes extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    private ListView listaView;
    private List<Encomienda> lstEncomienda;
    private Context thiscontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informes);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        listaView = (ListView) findViewById(R.id.listaInmueblesInformes);
        thiscontext= this;
        findViewById(R.id.infoVolver).setOnClickListener(View -> volver());

    }

    public void volver( ){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return   day + "-" + month  + "-" + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }


    public void openDatePicker(View view)
    {
        datePickerDialog.show();
        //buscarFecha();
    }

    public void Buscar(){
        try {
            String fecha[] = ((String) dateButton.getText()).split("-");
            int dia = Integer.parseInt(fecha[0]);
            int mes = Integer.parseInt(fecha[0]);
            int anio = Integer.parseInt(fecha[0]);
            EnvioDatos objsend= new EnvioDatos();
            Date date = new GregorianCalendar(anio, mes, dia).getTime();
            objsend.setFechax((String)dateButton.getText());

            //  System.out.println("la fecja deñl clik: " + dateButton.getText());
            retrofit2.Call<EnvioDatos> call = RetrofitCliente
                    .getInstance() //aQUI SE LLAMA A LA INSTANCIA QUE Creamos y lo instancaimos
                    .getAPI() //aui le pedimos que nos pase el objeto Api que ha sido creado junto con ahi
                    .getByFechaEncomienda(objsend);

            call.enqueue(new Callback<EnvioDatos>() {
                @Override
                public void onResponse(Call<EnvioDatos> call, Response<EnvioDatos> response) {
                    // hideProgress();
                    try {
                        String  a=   "This is my message"+ response.isSuccessful() + " ";
                        Log.i("myTag", a);
                        String  b=   "This is the code"+ response.code()+ " ";
                        Log.i("myTag", b);
                        lstEncomienda = response.body().getListaEncomiendas();


                        Log.i("myTag", "tamaño "+ response.body().getListaEncomiendas().size());

                     //   System.out.println("Entro perfectamente");
                        EncomiendaAdapter adapter = new EncomiendaAdapter(thiscontext,lstEncomienda);
                        listaView.setAdapter(adapter);

                        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                           //     Encomienda enco = lstEncomienda.get(i);
                             //   Toast.makeText(getBaseContext(), enco.getEncomienda(), Toast.LENGTH_SHORT).show();
                              /*  Intent inte = new Intent(thiscontext, EncomiendaEdit.class);
                                inte.putExtra("id", enco.getId());
                                startActivity(inte);
*/
                            }
                        } );

                    }catch(Exception e){
                        System.out.println("Es error perfectamente");
                        Toast.makeText(getBaseContext(), "error al obtener data"+ e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<EnvioDatos> call, Throwable t) {
                    //hideProgress();
                    // showProgress("Errror personajes.......");
                    Toast.makeText(getBaseContext(), "error al obtener data", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception E){
            System.out.println("el errore es: " + E.getMessage());
        }

    }


    public void buscarFecha(View view){
        Buscar();
    }
}