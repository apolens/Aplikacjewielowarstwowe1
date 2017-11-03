package com.example.adrian.aplikacjewielowarstwowe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity
{
    private UserLoginTask authTask = null;

    private AutoCompleteTextView tvLoginUser;
    private EditText etPassword;
    private Button bSignIn;
    private View vProgress;
    private View vLoginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvLoginUser = (AutoCompleteTextView) findViewById(R.id.id_login);
        etPassword = (EditText) findViewById(R.id.id_password);
        bSignIn = (Button) findViewById(R.id.id_btn_sign_in);
        vLoginForm = findViewById(R.id.id_login_form);
        vProgress = findViewById(R.id.id_login_progress);

        bSignIn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showProgress(true);

                authTask = new UserLoginTask(tvLoginUser.getText().toString(), etPassword.getText().toString());
                authTask.execute((Void) null);
            }
        });
    }

    /*
    Metoda showProgress() wyświetla interfejs postępu i ukrywa formularz logowania.
     */

    private void showProgress(final boolean show)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            vLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            vLoginForm.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    vLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            vProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            vProgress.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    vProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
        else
        {
            vProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            vLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /*
    Zdarzenia asynchroniczne AsyncTask wykorzystane do wykonywania działań czasochłonnych.
     */

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean>
    {
        private String loginUserAT;
        private String passwordUserAT;

        public UserLoginTask(String loginUserAT, String passwordUserAT)
        {
            this.loginUserAT = loginUserAT;
            this.passwordUserAT = passwordUserAT;
        }

        /*
        metoda doInBackground uruchamiana jest w oddzielnym wątku
         */

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                return false;
            }

            return true;
        }

        /*
        metoda wywoływana w momencie zakończenia pracy metody doInBackground
         */

        @Override
        protected void onPostExecute(final Boolean success)
        {
            authTask = null;

            showProgress(false);

            if(success)
            {
                finish();

                Intent nextIntent = new Intent(ActivityLogin.this, ActivityMain.class);

                startActivity(nextIntent);
            }
        }

        @Override
        protected void onCancelled()
        {
            authTask = null;

            showProgress(false);
        }
    }
}