package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActitvity";

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView  displayDate, startTime, endTime;
    String facility;
    String date;
    String booked;
    String t1;
    String t2;
    int a1;
    int a2;
    int t1Hour, t1Minute, t2Hour, t2Minute;//variable for start time and end time
    Button book;

    DatePickerDialog.OnDateSetListener dateSetListener;

    //initialize array
    ArrayList<String> bookings = new ArrayList<>();
    ArrayList<String> dateArrayList = new ArrayList<String>();
    ArrayList<Date> startTimeList = new ArrayList<>();
    ArrayList<Date> endTimeList = new ArrayList<>();

    ArrayList<Date> startTimeTennis = new ArrayList<>();
    ArrayList<Date> endTimeTennis = new ArrayList<>();




    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radio_selectFacility);

        displayDate = findViewById(R.id.selectDate);

        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        book = findViewById(R.id.Book);

        ListView bookingList = findViewById(R.id.bookingList);
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, bookings);
        bookingList.setAdapter(adapter);



/// for select date
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, dateSetListener, year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(
                        Color.WHITE));
                datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setText(date);
            }
        };

        ///for select start time and end time

            startTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //initialize time picker dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            MainActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    //initialize hour and minute
                                    t1Hour = hourOfDay;
                                    t1Minute = minute;
                                    //inatilize calender
                                    Calendar calendar = Calendar.getInstance();
                                    //set hour and minute
                                    calendar.set(0, 0, 0, t1Hour, t1Minute);
                                    //set selected time to text view
                                    if (hourOfDay < 10 && minute < 10) {
                                        startTime.setText("0" + t1Hour + ":" + "0" + t1Minute);
                                        t1 = ("0" + t1Hour + ":" + "0" + t1Minute);
                                        a1 = Integer.parseInt("0" + hourOfDay + "0" + minute);
                                    } else if (hourOfDay >= 10 && minute >= 10) {
                                        startTime.setText(t1Hour + ":" + t1Minute);
                                        t1 = (t1Hour + ":" + t1Minute);
                                        a1 = Integer.parseInt(hourOfDay + "" + minute);
                                    } else if (hourOfDay < 10 && minute >= 10) {
                                        startTime.setText("0" + t1Hour + ":" + t1Minute);
                                        t1 = ("0" + t1Hour + ":" + t1Minute);
                                        a1 = Integer.parseInt("0" + hourOfDay + "" + minute);
                                    } else if (hourOfDay >= 10 && minute < 10) {
                                        startTime.setText(t1Hour + ":" + "0" + t1Minute);
                                        t1 = (t1Hour + ":" + "0" + t1Minute);
                                        a1 = Integer.parseInt(hourOfDay + "0" + minute);
                                    }
                                    Date d = null;
                                    try {
                                        d = formatTime.parse(t1);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    //   a1= Integer.parseInt(hourOfDay+""+minute);
                                    Toast.makeText(MainActivity.this, "Strat Time : " + t1,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }, 12, 0, true
                    );

                    //display previously selected time
                    timePickerDialog.updateTime(t1Hour, t1Minute);
                    //show time picker dialog
                    timePickerDialog.show();
                }
            });


        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t2Hour = hourOfDay;
                                t2Minute = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0, t2Hour,t2Minute);
                                if(hourOfDay<10 && minute<10){
                                    endTime.setText("0"+t2Hour+":"+"0"+t2Minute);
                                    t2=("0"+t2Hour+":"+"0"+t2Minute);
                                    a2= Integer.parseInt("0"+hourOfDay+"0"+minute);
                                }
                                else if(hourOfDay>=10 && minute>=10){
                                    endTime.setText(t2Hour+":"+t2Minute);
                                    t2=(t2Hour+":"+t2Minute);
                                    a2= Integer.parseInt(hourOfDay+""+minute);
                                }
                                else if (hourOfDay<10 && minute>=10){
                                    endTime.setText("0"+t2Hour+":"+t2Minute);
                                    t2=("0"+t2Hour+":"+t2Minute);
                                    a2= Integer.parseInt("0"+hourOfDay+""+minute);
                                }
                                else if (hourOfDay>=10 && minute<10){
                                    endTime.setText(t2Hour+":"+"0"+t2Minute);
                                    t2=(t2Hour+":"+"0"+t2Minute);
                                    a2= Integer.parseInt(hourOfDay+"0"+minute);
                                }
//                             else {
//                                 endTime.setText(t2Hour+":"+t2Minute);
//                                 t2=(t2Hour+":"+t2Minute);
//                             }
                                Date d= null;
                                try {
                                    d = formatTime.parse(t2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Toast.makeText(MainActivity.this, "End Time : "+t2, Toast.LENGTH_SHORT).show();

                            }
                        },12,0,true
                );
                timePickerDialog.updateTime(t2Hour,t2Minute);
                timePickerDialog.show();
            }
        });




/// on book butoon click

            book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Boolean flag = false;
                    Boolean flag_1= false;

                    String stratTime = "" + date + " " + t1 + ":00";
                    String stopTime = "" + date + " " + t2 + ":00";


// Custom date format
                    SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

                    Date d1 = null;
                    Date d2 = null;
                    try {
                        d1 = format.parse(stratTime);
                        d2 = format.parse(stopTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.d("d1",""+d1);
                    Log.d("d2",""+d2);


// Get msec from each, and subtract.
                    long diff = d2.getTime() - d1.getTime();
                    long diffMinutes = diff / (60 * 1000);
                    long diffHours = diff / (60 * 60 * 1000);


                    Log.d("Diff Minute", "" + d1.getTime());
                    Log.d("Diff Minute", "" + d2.getTime());

                    Log.d("Diff Minute", "" + diffMinutes);
                    Log.d("Diff Minute", "" + diffHours);

                    Log.d("Diff t1", "" + t1Hour);
                    Log.d("Diff t2", "" + t2Hour);

                    //check already available or not
                    if (facility.equals("Club House")) {
                        if (startTimeList.size() == 0) {
                            startTimeList.add(d1);
                            endTimeList.add(d2);
                        } else {
                            if (startTimeList.contains(d1)) {
                                Toast.makeText(MainActivity.this, "Not Available!!!!!",
                                        Toast.LENGTH_SHORT).show();
                            }
                            for (int i = 0; i < startTimeList.size(); i++) {
                                if (d1.compareTo(startTimeList.get(i)) >= 0 && d1.compareTo(
                                        endTimeList.get(i)) <= 0) {
//                                Toast.makeText(MainActivity.this, "Not Available!!!", Toast.LENGTH_SHORT).show();
                                    Log.d("ms", "not available");
                                    flag = true;

                                }
                            }

                            startTimeList.add(d1);
                            endTimeList.add(d2);
                        }
                    }
                    if (facility.equals("Tennis Court")){
                        Log.d("enter in loop","tennis");
                        if (startTimeTennis.size() != 0) {
                            if (startTimeTennis.contains(d1)) {
                                Toast.makeText(MainActivity.this, "Not Available",
                                        Toast.LENGTH_SHORT).show();
                            }
                            for (int i = 0; i < startTimeTennis.size(); i++) {
                                if (d1.compareTo(startTimeTennis.get(i)) >= 0 && d1.compareTo(
                                        startTimeTennis.get(i)) <= 0) {
//                                Toast.makeText(MainActivity.this, "Not Available!!!", Toast.LENGTH_SHORT).show();
                                    Log.d("ms", "not available tennis");
                                    flag = true;

                                }

                            }
                        }
                        startTimeTennis.add(d1);
                        endTimeTennis.add(d2);
                    }


                    //cost per hour for club house and tennis court(per minute)
                    int clubHouseCost = 100, tennisCourtCost = 50;


                    float charges = 0;
                    //calculate charges club house and tennis court
                    if (facility.equals("Club House")) {
                        if (t1Hour >= 10 && t1Hour < 16) {
                            clubHouseCost = 100;
                        } else if (t1Hour >= 16 && t1Hour <= 20) {
                            clubHouseCost = 500;
                        }
                        if (diffHours == 0) {
                            diffHours = 1;
                        }
                        charges = diffHours * clubHouseCost;
                    }
                    if (facility.equals("Tennis Court")) {
                        if (diffHours == 0) {
                            diffHours = 1;
                        }
                        charges = diffHours * tennisCourtCost;
                    }
                    if (facility.equals("Others")) {
                        Toast.makeText(MainActivity.this, "Not Available",
                                Toast.LENGTH_SHORT).show();
                    }


                    booked = facility + "," + date + ", Time: " + t1 + " To " + t2 + ", Charges: " + charges;
                    if (bookings.contains(booked)) {
                        Toast.makeText(MainActivity.this, "Already Booked",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if (flag ==false){
                            bookings.add(booked);
                        }else {
                            Log.d("falg", "NOT"+flag);
                            Toast.makeText(MainActivity.this, "NOT AVAILABLE", Toast.LENGTH_SHORT).show();
                            flag = false;
                        }

                     //   bookings.add(booked);
                        //Toast.makeText(MainActivity.this, "Slot: " + bookings, Toast.LENGTH_LONG).show();
                        for (int i = 0; i < bookings.size(); i++) {
                            Log.d(TAG, "Bookings : " + bookings.get(i));
                            Log.d("Time string : ", "" + date + " " + t1 + ":00");

                        }
                        ListView bookingList = findViewById(R.id.bookingList);
                        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                                android.R.layout.simple_list_item_1, bookings);
                        bookingList.setAdapter(adapter);

                    }
                }

            });



    }

    ///For Select Facility radio group
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        facility = radioButton.getText().toString();
        Toast.makeText(this, "Selected Facility "+facility,  Toast.LENGTH_SHORT).show();
    }



}