package com.example.yair_.json;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.constraint.solver.widgets.Snapshot;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;

    ArrayList<String> players= new ArrayList<>();

    ProgressDialog pd;

    public Parser(Context c, ListView lv, String data) {
        this.c = c;
        this.lv = lv;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd= new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing ...... please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer ==1){

            ArrayAdapter<String> adapter= new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,players);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(c,"Tocastes",Toast.LENGTH_SHORT).show();

                }
            });

        }else{
            Toast.makeText(c,"unable to parse",Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }

    private  int parse()
    {
        try
        {
            JSONArray ja = new JSONArray(data);
            JSONObject jo= null;

            players.clear();

            for(int i=0;i<ja.length();i++){
                jo= ja.getJSONObject(i);
                String name= jo.getString("Name");

                players.add(name);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}


