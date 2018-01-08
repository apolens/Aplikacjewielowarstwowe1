package com.example.adrian.aplikacjewielowarstwowe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityLogin extends AppCompatActivity
{
    private UserAuthTask userAuthTask = null;

    private AutoCompleteTextView tvLoginUser;
    private EditText etPassword;
    private Button bSignIn;
    private View vProgress;
    private View vLoginForm;
    private ImageView ivLogo;

    private String responseAuth;
    private String responseData;
    private String xAuthToken;

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
        ivLogo = (ImageView) findViewById(R.id.id_logo);

        bSignIn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showProgress(true);

                userAuthTask = new UserAuthTask(tvLoginUser.getText().toString(), etPassword.getText().toString());
                userAuthTask.execute((Void) null);
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

            float alpha = (float) 1.0;

            ivLogo.setAlpha(alpha);
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

    public class UserAuthTask extends AsyncTask<Void, Void, Boolean>
    {
        private static final String PATH_AUTH = "/auth?";
        private static final String PATH_DATA = "/users/user";

        private String loginUserAT;
        private String passwordUserAT;

        private String firstNameUserAT;
        private String lastNameUserAT;

        private String idUserAT;

        boolean successData;

        public UserAuthTask(String loginUserAT, String passwordUserAT)
        {
            this.loginUserAT = loginUserAT;
            this.passwordUserAT = passwordUserAT;

            this.firstNameUserAT = "";
            this.lastNameUserAT = "";

            idUserAT = "";

            successData = false;
        }

        private boolean getUserAuth(String loginUser, String passwordUser)
        {
            String login = "login=" + loginUser;
            String password = "password=" + passwordUser;

            String linkedURL = CServerSettings.DOMAIN_ADDRESS + PATH_AUTH + login + "&" + password;

            responseAuth = "";

            boolean success;

            try
            {
                URL url = new URL(linkedURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("HTTP_ACCEPT", "application/json");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));

                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    xAuthToken = httpURLConnection.getHeaderField("x-auth-token");

                    String line = "";

                    while((line = bufferedReader.readLine()) != null)
                    {
                        responseAuth += line;
                    }
                }

                httpURLConnection.disconnect();

            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if(responseAuth.isEmpty() == false)
            {
                success = true;
            }
            else
            {
                success = false;
            }

            return success;
        }

        private boolean getUserData()
        {
            String linkedURL = CServerSettings.DOMAIN_ADDRESS + PATH_DATA;

            responseData = "";

            boolean success;

            try
            {
                URL url = new URL(linkedURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("HTTP_ACCEPT", "application/json");
                httpURLConnection.setRequestProperty("x-auth-token", xAuthToken);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));

                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                    {
                        responseData += line;
                    }
                }

                httpURLConnection.disconnect();

            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if(responseData.isEmpty() == false)
            {
                success = true;
            }
            else
            {
                success = false;
            }

            return success;
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

            boolean isCorrectAuth = getUserAuth(loginUserAT, passwordUserAT);

            if(isCorrectAuth)
            {
                successData = getUserData();
            }

            return isCorrectAuth;
        }

        /*
        metoda wywoływana w momencie zakończenia pracy metody doInBackground
         */

        @Override
        protected void onPostExecute(final Boolean success)
        {
            userAuthTask = null;

            showProgress(false);

            if(success)
            {
                try
                {
                    JSONObject objectJSONauth = new JSONObject(responseAuth);

                    String result = objectJSONauth.getString("errorDesc");

                    if(result.equals("Success"))
                    {
                        finish();

                        if(successData)
                        {
                            try
                            {
                                JSONObject objectJSONdata = new JSONObject(responseData);

                                JSONObject objectJSONdataNext = objectJSONdata.getJSONObject("body");

                                firstNameUserAT = objectJSONdataNext.getString("firstName");
                                lastNameUserAT = objectJSONdataNext.getString("lastName");
                                idUserAT = objectJSONdataNext.getString("id");
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }

                        Intent nextIntent = new Intent(ActivityLogin.this, ActivityMain.class);

                        nextIntent.putExtra("x_auth_token", xAuthToken);
                        nextIntent.putExtra("login", loginUserAT);
                        nextIntent.putExtra("id", idUserAT);
                        nextIntent.putExtra("first_name", firstNameUserAT);
                        nextIntent.putExtra("last_name", lastNameUserAT);

                        startActivity(nextIntent);
                    }
                    else
                    {
                        etPassword.setError(getString(R.string.error_incorrect_password));
                        etPassword.requestFocus();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
            else
            {
                etPassword.setError(getString(R.string.error_incorrect_password));
                etPassword.requestFocus();
            }
        }

        @Override
        protected void onCancelled()
        {
            userAuthTask = null;

            showProgress(false);
        }
    }
}