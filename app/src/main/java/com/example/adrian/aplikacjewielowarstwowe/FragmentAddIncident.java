package com.example.adrian.aplikacjewielowarstwowe;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FragmentAddIncident extends Fragment
{
    private EditText etIncidentId;
    private EditText etRequestor;
    private EditText etRequestDate;
    private EditText etTitle;
    private EditText etDescription;

    private Spinner sCategory;
    private Spinner sPriority;

    private Button bCreate;

    private String response;

    private String xAuthToken;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add_incident, container, false);

        etIncidentId = (EditText) view.findViewById(R.id.id_form_incident_id);
        etRequestor = (EditText) view.findViewById(R.id.id_form_requestor);
        etRequestDate = (EditText) view.findViewById(R.id.id_form_requestDate);
        etTitle = (EditText) view.findViewById(R.id.id_form_title);
        etDescription = (EditText) view.findViewById(R.id.id_form_description);

        sCategory = (Spinner) view.findViewById(R.id.id_form_category);
        sPriority = (Spinner) view.findViewById(R.id.id_form_priority);

        bCreate = (Button) view.findViewById(R.id.id_form_btn_create);

        setInitialData();
        exampleData();

        xAuthToken = getActivity().getIntent().getStringExtra("x_auth_token");

        ListCategoryTask listCategoryTask = new ListCategoryTask(this);
        listCategoryTask.execute((Void) null);

        ListPriorityTask listPriorityTask = new ListPriorityTask(this);
        listPriorityTask.execute((Void) null);

        bCreate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                NewIncidentTask newIncidentTask = new NewIncidentTask();
                newIncidentTask.execute((Void) null);
            }
        });

        return view;
    }

    private void setInitialData()
    {
        etIncidentId.setEnabled(false);

        String loginUser = getActivity().getIntent().getStringExtra("login");

        etRequestor.setEnabled(false);
        etRequestor.setText(loginUser);

        etRequestDate.setEnabled(false);
        etRequestDate.setText("2017-12-31T09:19:46.544Z");
    }

    private void exampleData()
    {
        etIncidentId.setText("1");
    }

    private class ListCategoryTask extends AsyncTask<Void, Void, Boolean>
    {
        private static final String PATH = "/category";

        private Context context;

        private ArrayList<CCategory> listCategory;

        public ListCategoryTask(Fragment fragment)
        {
            context = fragment.getContext();

            listCategory = new ArrayList<CCategory>();
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... arg0)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                return false;
            }

            return getCategoryData();
        }

        private boolean getCategoryData()
        {
            String linkedURL = CServerSettings.DOMAIN_ADDRESS + PATH;

            response = "";

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
                        response += line;
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

            if(response.isEmpty() == false)
            {
                success = true;
            }
            else
            {
                success = false;
            }

            return success;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            super.onPostExecute(success);

            if (success) {
                try {
                    JSONArray arrayJSON = new JSONArray(response);

                    JSONObject objectJSON = null;

                    for (int i = 0; i < arrayJSON.length(); i++)
                    {
                        objectJSON = arrayJSON.getJSONObject(i);

                        int id = Integer.parseInt(objectJSON.getString("id"));
                        String name = objectJSON.getString("name");

                        CCategory cCategory = new CCategory(id, name);

                        listCategory.add(cCategory);

                        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, listCategory);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        sCategory.setAdapter(adapter);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ListPriorityTask extends AsyncTask<Void, Void, Boolean>
    {
        private static final String PATH = "/priority";

        private Context context;

        private ArrayList<CPriority> listPriority;

        public ListPriorityTask(Fragment fragment)
        {
            context = fragment.getContext();

            listPriority = new ArrayList<CPriority>();
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... arg0)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                return false;
            }

            return getPriorityData();
        }

        private boolean getPriorityData()
        {
            String linkedURL = CServerSettings.DOMAIN_ADDRESS + PATH;

            response = "";

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
                        response += line;
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

            if(response.isEmpty() == false)
            {
                success = true;
            }
            else
            {
                success = false;
            }

            return success;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            super.onPostExecute(success);

            if (success) {
                try {
                    JSONArray arrayJSON = new JSONArray(response);

                    JSONObject objectJSON = null;

                    for (int i = 0; i < arrayJSON.length(); i++)
                    {
                        objectJSON = arrayJSON.getJSONObject(i);

                        int id = Integer.parseInt(objectJSON.getString("id"));
                        String name = objectJSON.getString("name");

                        CPriority cPriority = new CPriority(id, name);

                        listPriority.add(cPriority);

                        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, listPriority);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        sPriority.setAdapter(adapter);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private class NewIncidentTask extends AsyncTask<Void, Void, Boolean>
    {
        private static final String PATH = "/priority";

        public NewIncidentTask()
        {
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

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

            return addIncident();
        }

        private boolean addIncident()
        {
            String linkedURL = CServerSettings.DOMAIN_ADDRESS + PATH;

            response = "";

            boolean success = true;

            try
            {
                URL url = new URL(linkedURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("HTTP_ACCEPT", "application/json");
                httpURLConnection.setRequestProperty("x-auth-token", xAuthToken);
                httpURLConnection.setDoOutput(true);

                JSONObject objectJSONcategory = new JSONObject();

                CCategory cCategory = (CCategory) sCategory.getSelectedItem();

                objectJSONcategory.put("id", cCategory.getId());
                objectJSONcategory.put("name", cCategory.getName());

                JSONObject objectJSONpriority = new JSONObject();

                CPriority cPriority = (CPriority) sPriority.getSelectedItem();

                objectJSONpriority.put("id", cPriority.getId());
                objectJSONpriority.put("name", cPriority.getName());

                JSONObject objectJSONstatus = new JSONObject();

                objectJSONstatus.put("id", 22);
                objectJSONstatus.put("name", "Status 1");

                JSONObject objectJSONuser = new JSONObject();

                objectJSONuser.put("firstName", getActivity().getIntent().getStringExtra("first_name"));
                objectJSONuser.put("id", Integer.parseInt(getActivity().getIntent().getStringExtra("id")));
                objectJSONuser.put("lastName", getActivity().getIntent().getStringExtra("last_name"));

                JSONObject objectJSONuserComments = new JSONObject();

                objectJSONuserComments.put("firstName", "string");
                objectJSONuserComments.put("id", 0);
                objectJSONuserComments.put("lastName", "string");

                JSONObject objectJSONcomment = new JSONObject();

                objectJSONcomment.put("content", "string");
                objectJSONcomment.put("date", etRequestDate.getText().toString());
                objectJSONcomment.put("id", 0);
                objectJSONcomment.put("userData", objectJSONuserComments);

                JSONArray arrayJSONcomment = new JSONArray();

                arrayJSONcomment.put(objectJSONcomment);

                JSONObject objectJSONall = new JSONObject();

                objectJSONall.put("category", objectJSONcategory);
                objectJSONall.put("comments", arrayJSONcomment);
                objectJSONall.put("date", etRequestDate.getText() + "");
                objectJSONall.put("description", etDescription.getText());
                objectJSONall.put("id", 1);
                objectJSONall.put("priority", objectJSONpriority);
                objectJSONall.put("status", objectJSONstatus);
                objectJSONall.put("title", etTitle.getText());
                objectJSONall.put("userData", objectJSONuser);

                String json = objectJSONall.toString();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                bufferedWriter.write(json);

                bufferedWriter.flush();
                bufferedWriter.close();

                Log.d("test", "code: " + httpURLConnection.getResponseCode());

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
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            super.onPostExecute(success);
        }
    }
}
