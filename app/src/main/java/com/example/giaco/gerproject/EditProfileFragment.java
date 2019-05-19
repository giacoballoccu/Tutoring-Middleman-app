package com.example.giaco.gerproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giaco.gerproject.Classes.Reservation;
import com.example.giaco.gerproject.Classes.ReservationFactory;
import com.example.giaco.gerproject.Classes.User;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfileFragment extends Fragment {
    String loggedUserMail;
    User loggedStudente;
    User loggedTutor;
    UserTutorFactory factoryT = UserTutorFactory.getInstance();
    PersonalPageFragment pageFragment;
    UserStudenteFactory factoryS = UserStudenteFactory.getInstance();
    LayoutInflater layoutInflater;
    private boolean flagTutor = false;
    EditText emailField, passwordField, confirmPasswordField, nameField, surnameField;
    Button signUp;
    View myView;
    RelativeLayout mparent;
    ImageButton backButton;
    String emailStr, nameStr, surnameStr, passwordStr, confirmPasswordStr;
    int errors = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_profile, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Modifica Profilo");
        mparent = view.findViewById(R.id.parent_linearlayout);


        if (getArguments() != null) {
            if (getArguments().getInt("tFlag") == 0) { //Studente
                loggedUserMail = getArguments().getString("actualUserMail");
                loggedStudente = factoryS.getUserByEmail(loggedUserMail);
                setTutorFlag(loggedStudente);
            } else {   //Tutor
                loggedUserMail = getArguments().getString("actualUserMail");
                loggedTutor = factoryT.getUserByEmail(loggedUserMail);
                setTutorFlag(loggedTutor);
                //bundle = savedInstanceState;
            }

        }
        if (flagTutor == false) {

            emailField = view.findViewById(R.id.emailField_edit);
            passwordField = view.findViewById(R.id.passwordField_edit);
            confirmPasswordField = view.findViewById(R.id.confirmPasswordField_edit);
            nameField = view.findViewById(R.id.nameField_edit);
            surnameField = view.findViewById(R.id.surnameField_edit);
            signUp = view.findViewById(R.id.signUp_edit);

            emailField.setText(loggedStudente.getEmail());
            nameField.setText(loggedStudente.getName());
            surnameField.setText(loggedStudente.getSurname());

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailStr = emailField.getText().toString();
                    nameStr = nameField.getText().toString();
                    surnameStr = surnameField.getText().toString();
                    passwordStr = passwordField.getText().toString();
                    confirmPasswordStr = confirmPasswordField.getText().toString();

                    /*Check sugli input*/

                    /*EmptyInput*/
                    if (TextUtils.isEmpty(emailStr)) {
                        emailField.setError("Inserire una mail");
                        errors++;
                    } else {
                        passwordField.setError(null);
                    }

                    /*EmptyInput*/
                    if (TextUtils.isEmpty(nameStr)) {
                        nameField.setError("Questo campo non può essere vuoto");
                        errors++;
                    } else {
                        passwordField.setError(null);
                    }

                    if (TextUtils.isEmpty(surnameStr)) {
                        surnameField.setError("Questo campo non può essere vuoto");
                        errors++;
                    } else {
                        passwordField.setError(null);
                    }

                    if (TextUtils.isEmpty(passwordStr)) {
                        passwordField.setError("Questo campo non può essere vuoto");
                        errors++;
                    } else {
                        passwordField.setError(null);
                    }

                    if (TextUtils.isEmpty(confirmPasswordStr)) {
                        confirmPasswordField.setError("Questo campo non può essere vuoto");
                        errors++;
                    }

                    /*ConfirmPassword doesn't match with the first password*/
                    if (!confirmPasswordStr.equals(passwordStr)) {
                        confirmPasswordField.setError("Le password non corrispondono");
                        errors++;
                    }

                    if (errors == 0) {
                        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        myView = layoutInflater.inflate(R.layout.pop_up_prenotazione, null, false);
                        myView.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_trasparency));
                        mparent.addView(myView);
                        Button conferma, cancella;
                        conferma = (Button) myView.findViewById(R.id.prenotazione_yes);
                        cancella = (Button) myView.findViewById(R.id.prenotazione_no);

                        conferma.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(EditProfileFragment.this.getActivity(), "Modifica avvenuta con successo!", Toast.LENGTH_LONG).show();
                                loggedStudente.setEmail(emailStr);
                                loggedUserMail = emailStr;
                                loggedStudente.setName(nameStr);
                                loggedStudente.setPassword(passwordStr);
                                loggedStudente.setSurname(surnameStr);
                                mparent.removeView(myView);
                            }
                        });

                        cancella.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mparent.removeView(myView);
                            }
                        });

                    } else {
                        errors = 0;
                    }
                }
            });


        } else {
            emailField = view.findViewById(R.id.emailField_edit);
            passwordField = view.findViewById(R.id.passwordField_edit);
            confirmPasswordField = view.findViewById(R.id.confirmPasswordField_edit);
            nameField = view.findViewById(R.id.nameField_edit);
            surnameField = view.findViewById(R.id.surnameField_edit);
            signUp = view.findViewById(R.id.signUp_edit);


            emailField.setText(loggedTutor.getEmail());
            nameField.setText(loggedTutor.getName());
            surnameField.setText(loggedTutor.getSurname());

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    emailStr = emailField.getText().toString();
                    nameStr = nameField.getText().toString();
                    surnameStr = surnameField.getText().toString();
                    passwordStr = passwordField.getText().toString();
                    confirmPasswordStr = confirmPasswordField.getText().toString();

                    /*Check sugli input*/

                    /*EmptyInput*/
                    if (TextUtils.isEmpty(emailStr)) {
                        emailField.setError("Inserire una mail");
                        errors++;
                    } else {
                        passwordField.setError(null);
                    }

                    /*EmptyInput*/
                    if (TextUtils.isEmpty(nameStr)) {
                        nameField.setError("Questo campo non può essere vuoto");
                        errors++;
                    } else {
                        passwordField.setError(null);
                    }

                    if (TextUtils.isEmpty(surnameStr)) {
                        surnameField.setError("Questo campo non può essere vuoto");
                        errors++;
                    } else {
                        passwordField.setError(null);
                    }

                    if (TextUtils.isEmpty(passwordStr)) {
                        passwordField.setError("Questo campo non può essere vuoto");
                        errors++;
                    } else {
                        passwordField.setError(null);
                    }

                    if (TextUtils.isEmpty(confirmPasswordStr)) {
                        confirmPasswordField.setError("Questo campo non può essere vuoto");
                        errors++;
                    }

                    /*ConfirmPassword doesn't match with the first password*/
                    if (!confirmPasswordStr.equals(passwordStr)) {
                        confirmPasswordField.setError("Le password non corrispondono");
                        errors++;
                    }

                    if (errors == 0) {
                        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        myView = layoutInflater.inflate(R.layout.pop_up_prenotazione, null, false);
                        myView.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_trasparency));
                        mparent.addView(myView);
                        Button conferma, cancella;
                        conferma = (Button) myView.findViewById(R.id.prenotazione_yes);
                        cancella = (Button) myView.findViewById(R.id.prenotazione_no);

                        conferma.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(EditProfileFragment.this.getActivity(), "Modifica avvenuta con successo!", Toast.LENGTH_LONG).show();
                                loggedTutor.setEmail(emailStr);
                                loggedUserMail = emailStr;
                                loggedTutor.setName(nameStr);
                                loggedTutor.setPassword(passwordStr);
                                loggedTutor.setSurname(surnameStr);
                                mparent.removeView(myView);
                            }
                        });

                        cancella.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mparent.removeView(myView);
                            }
                        });

                    } else {
                        errors = 0;
                    }

                }
            });

        }
    }

    protected void setTutorFlag(User usr) {
        if (usr instanceof UserStudente)
            this.flagTutor = false;
        else this.flagTutor = true;
    }

    protected boolean getTutorFlag() {
        return this.flagTutor;
    }

}
