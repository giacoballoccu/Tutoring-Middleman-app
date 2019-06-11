package com.example.giaco.gerproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giaco.gerproject.Classes.Reservation;
import com.example.giaco.gerproject.Classes.ReservationFactory;
import com.example.giaco.gerproject.Classes.ReservationRequest;
import com.example.giaco.gerproject.Classes.ReservationRequestFactory;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import java.util.Calendar;

public class EditAgenda extends Fragment {
    private int anno = 100;
    private int mese = 100;
    private int giorno = 100;
    private int oraInizio = 8;
    private int oraFine = 9;
    private int minutoInizio = 10;
    private int minutoFine = 10;
    private int giornoSettimanaN = 0;
    CalendarView calendario;
    SeekBar seekBarOrarioInizio, seekBarOrarioFine;
    TextView oraCorrente, giornoSettimana;
    PersonalPageFragment profilo;
    String loggedUserMail = "";
    Calendar calendarioCorrente;
    Switch interruttore;
    DashBoardFragment dashboard;
    Button richiediNuovaDataButton;
    LayoutInflater layoutInflater;
    View myView;
    ConstraintLayout mparent;
    UserStudente loggedUser;
    UserTutor chosenTutor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loggedUserMail = getArguments().getString("actualUserMail");
        return inflater.inflate(R.layout.fragment_editagenda, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Aggiungi una data");
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String emailTutor = getArguments().getString("emailTutor");
            String emailUtente = getArguments().getString("emailStudente");
            loggedUser = UserStudenteFactory.getInstance().getUserByEmail(emailUtente);
            chosenTutor = UserTutorFactory.getInstance().getUserByEmail(emailTutor);
        }
        //getActivity().setTitle("Aggiungi una data");
        oraCorrente = view.findViewById(R.id.oraSelezionata);
        oraCorrente.setText("8:00 - 9:00");
        giornoSettimana = view.findViewById(R.id.ripetizioneSettimana);
        giornoSettimana.setVisibility(View.INVISIBLE);
        interruttore = view.findViewById(R.id.switch1);
        interruttore.setVisibility(View.INVISIBLE);


        seekBarOrarioInizio = view.findViewById(R.id.seekBarOrari);
        seekBarOrarioFine = view.findViewById(R.id.seekBarOrariF);
        richiediNuovaDataButton = getActivity().findViewById(R.id.salvaAggiunta);
        calendario = view.findViewById(R.id.calendario_agenda);

        mparent = (ConstraintLayout) view.findViewById(R.id.parentconstraintlayout);
        /*Sezione calendario*/
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView viewC, int year, int month, int dayOfMonth) {
                setGiorno(dayOfMonth);
                setMese(month);
                setAnno(year);
                calendarioCorrente = Calendar.getInstance();
                calendarioCorrente.setTimeInMillis(viewC.getDate());
                setGiornoSettimana(calendarioCorrente.get(Calendar.DAY_OF_WEEK));
                giornoSettimana.setVisibility(View.VISIBLE);
                setGiornoSettimana(5);
                giornoSettimana.setText("Ripeti per tutti i " + getGiornoSettimanaStringa(getGiornoSettimana()) + " di " + getMeseStringa(month + 1) + "");

                interruttore.setVisibility(View.VISIBLE);

                //radio1.setVisibility(View.VISIBLE);
                //radio2.setVisibility(View.VISIBLE);
            }
        });

        interruttore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setGiornoSettimana(1);
                    interruttore.setText("Sì");
                } else {
                    setGiornoSettimana(0);
                    interruttore.setText("No");
                }
            }
        });

        /*Sezione Ora Inizio*/
        seekBarOrarioInizio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0 && seekBarOrarioFine.getProgress() >= 1) {
                    setOraInizio(progress + 8);
                    //seekBarOrarioFine.setProgress(progress+1);
                } else if (progress == seekBar.getMax()) {
                    setOraInizio(progress - 1 + 8);
                    seekBarOrarioInizio.setProgress(progress - 1);
                } else {
                    setOraInizio(progress + 8);
                    if (seekBarOrarioFine.getProgress() <= progress)
                        seekBarOrarioFine.setProgress(progress + 1, true);
                }
                oraCorrente.setText(Integer.toString(getOraInizio()) + ":00 - " + Integer.toString(getOraFine()) + ":00");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        /*Sezione Ora Fine*/
        seekBarOrarioFine.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    setOraFine(progress + 1 + 8);
                    seekBarOrarioFine.setProgress(progress + 1);
                } else if (seekBarOrarioInizio.getProgress() >= progress) {
                    seekBarOrarioInizio.setProgress(seekBarOrarioFine.getProgress() - 1);
                    setOraFine(progress);
                } else
                    setOraFine(progress + 8);
                //getSeekBarOrarioFine.setProgress(getOraInizio() - 8, true);
                oraCorrente.setText(Integer.toString(getOraInizio()) + ":00 - " + Integer.toString(getOraFine()) + ":00");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        richiediNuovaDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = layoutInflater.inflate(R.layout.pop_up_prenotazione, null, false);
                mparent.addView(myView);
                final Button conferma, cancella;
                conferma = (Button) myView.findViewById(R.id.prenotazione_yes);
                cancella = (Button) myView.findViewById(R.id.prenotazione_no);
                myView.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_trasparency));

                conferma.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();

                        bundle.putString("actualUserMail", loggedUserMail);
                        bundle.putInt("tFlag", 1);

                        //numero di ore
                        int num_ore = getOraFine() - getOraInizio();
                        //Converto int in Integer per applicare toString dopo

                        String data = "24 Giugno 2019";

                        if (getArguments().getInt("tFlag") == 1) {
                            profilo = new PersonalPageFragment();
                            profilo.setArguments(bundle);
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            FragmentTransaction transaction = fm.beginTransaction();
                            transaction.replace(R.id.fragment_container, profilo);
                            transaction.commit();
                            Toast toast=Toast.makeText(getActivity().getApplicationContext(),"Modifica completata",Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            bundle.putInt("annoN", getAnno());
                            bundle.putInt("meseN", getMese());
                            bundle.putInt("giornoN", getGiorno());
                            bundle.putInt("oraiN", getOraInizio());
                            bundle.putInt("oraF", getOraFine());
                            bundle.putInt("giornoSettimana", 5);
                            bundle.putString("flagAggiunta", "ok");
                            ReservationRequestFactory reservationRequestFactory = ReservationRequestFactory.getInstance();
                            ReservationRequest reservation = new ReservationRequest(loggedUser, chosenTutor , data , num_ore, chosenTutor.getMateria(), getOraInizio(), getOraFine());
                            reservationRequestFactory.addReservation(reservation);
                            dashboard = new DashBoardFragment();
                            dashboard.setArguments(bundle);
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            FragmentTransaction transaction = fm.beginTransaction();
                            transaction.replace(R.id.fragment_container, dashboard);
                            transaction.commit();
                            Toast toast=Toast.makeText(getActivity().getApplicationContext(),"Richiesta inoltrata",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });

                cancella.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mparent.removeView(myView);
                    }
                });

            }
        });

    }



    public void setAnno(int a) {
        this.anno = a;
    }

    public void setMese(int m) {
        this.mese = m + 1;
    }

    public void setGiorno(int g) {
        this.giorno = g;
    }

    public void setOraInizio(int oi) {
        this.oraInizio = oi;
    }

    public void setOraFine(int of) {
        this.oraFine = of;
    }

    public void setMinutoInizio(int mi) {
        this.minutoInizio = mi;
    }

    public void setMinutoFine(int mf) {
        this.minutoFine = mf;
    }

    public int getAnno() {
        return this.anno;
    }

    public int getMese() {
        return this.mese;
    }

    public int getGiorno() {
        return this.giorno;
    }

    public int getOraInizio() {
        return this.oraInizio;
    }

    public int getOraFine() {
        return this.oraFine;
    }

    public int getMinutoInizio() {
        return this.minutoInizio;
    }

    public int getMinutoFine() {
        return this.minutoFine;
    }

    public void setGiornoSettimana(int gs) {
        this.giornoSettimanaN = gs;
    }

    public int getGiornoSettimana() {
        return this.giornoSettimanaN;
    }

    public String getMeseStringa(int m) {
        String nomemese = "";
        switch (m) {
            case 1:
                nomemese = "gennaio";
                break;
            case 2:
                nomemese = "febbraio";
                break;
            case 3:
                nomemese = "marzo";
                break;
            case 4:
                nomemese = "aprile";
                break;
            case 5:
                nomemese = "maggio";
                break;
            case 6:
                nomemese = "giugno";
                break;
            case 7:
                nomemese = "luglio";
                break;
            case 8:
                nomemese = "agosto";
                break;
            case 9:
                nomemese = "settembre";
                break;
            case 10:
                nomemese = "ottobre";
                break;
            case 11:
                nomemese = "novembre";
                break;
            case 12:
                nomemese = "dicembre";
                break;
        }
        return nomemese;
    }

    public String getGiornoSettimanaStringa(int g) {
        String giornoS = "";
        switch (g) {
            case 1:
                giornoS = "lunedì";
                break;
            case 2:
                giornoS = "martedì";
                break;
            case 3:
                giornoS = "mercoledì";
                break;
            case 4:
                giornoS = "giovedì";
                break;
            case 5:
                giornoS = "venerdì";
                break;
            case 6:
                giornoS = "sabato";
                break;
            case 0:
                giornoS = "domenica";
                break;
        }
        return giornoS;
    }

}


