package com.example.ouygu.myapplication2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import Client.Blockchain.Block;
import Client.Blockchain.Blockchain;
import Client.Blockchain.BlockchainManager;
import Client.Util.CrypDist;

public class HomeActivity extends AppCompatActivity {



    List<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Set adapter for list view
        adapter = new ArrayAdapter<String>(this, R.layout.content_home, R.id.block_view, arrayList);
        ListView listView = (ListView) findViewById(R.id.block_list);

        listView.setAdapter(adapter);


        new SynchronizeBlockchain().execute();
    }

    // Print the blocks info on the screen
    private class SynchronizeBlockchain extends AsyncTask {

        @Override
        protected List<String> doInBackground(Object[] params) {

            /*CrypDist crypDist = new CrypDist();
            crypDist.updateBlockchain();
            Blockchain blockchain = crypDist.getBlockchainManager().getBlockchain();





            ConcurrentHashMap<String, Block> chm = blockchain.getBlockMap();
            ArrayList<String> res = new ArrayList<String>();

            int i = 1;

            for(String key : chm.keySet())
            {
                Long temp = blockchain.getBlock(key).getTimestamp();
                String el = "Block " + i + " \t " + temp;
                i++;

                res.add(el);
            }
            // Copy the list content to adapter's string array
            String[] result = new String[res.size()];

            for(int k = 0; k < res.size(); k++){
                sa[k] = res.get(k);
            }





            Set<String> keySet = blockchain.getKeySet();
            ArrayList<String> keys = new ArrayList<>(keySet);
            Collections.sort(keys);

            int index = 0;

            for (String key : keys)
            {
                Block block = blockchain.getBlock(key);
                arrayList.add(index, "block " + index + " \t " + block.getTimestamp());
                index++;
            }*/

            arrayList.add("rshjtj");
            arrayList.add("njn");
            arrayList.add("rsynyn");
            arrayList.add("jyjnthm");
            arrayList.add("tmuytnm");
            arrayList.add("mn");

            return arrayList;
        }


        @Override
        // Update user interface, add block list
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            adapter.notifyDataSetChanged();
        }
    }
}
