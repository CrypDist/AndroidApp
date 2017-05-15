package com.example.ouygu.myapplication2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import Client.Blockchain.Block;
import Client.Blockchain.Blockchain;
import Client.Blockchain.Transaction;
import Client.Util.CrypDist;

public class AkActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView textView;
    CrypDist crypDist;
    Blockchain blockchain;
    ArrayList<String> blocks = new ArrayList<String>();
    ArrayList<String> blockInfo = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ak);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        textView = (TextView) findViewById(R.id.ak_text_view);
        textView.setText("blockchain download is started!!");

        new getBlockchain().execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ak, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // show block 0 info
        if (id == R.id.block0)
        {

            String temp = "";

            // TODO
            Set<String> keySet = blockchain.getKeySet();
            ArrayList<String> keys = new ArrayList<>(keySet);
            Collections.sort(keys);

            Block block = blockchain.getBlock(keys.get(0));
            ArrayList<Transaction> transactions = block.getTransactions();

            for(int i = 0; i < transactions.size(); i++){
                temp += "Transaction " + (i + 1) + "\n" +
                        "Summary: " + transactions.get(i).getDataSummary() + "\n" +
                        "File name: " + transactions.get(i).getFileName() + "\n\n";

            }

            textView.setText(temp);


        }
        else if (id == R.id.block1) // show block 1 info
        {

            String temp = "";

            // TODO
            if(blockchain.getLength() >= 2){
                Set<String> keySet = blockchain.getKeySet();
                ArrayList<String> keys = new ArrayList<>(keySet);
                Collections.sort(keys);

                Block block = blockchain.getBlock(keys.get(1));
                ArrayList<Transaction> transactions = block.getTransactions();

                for(int i = 0; i < transactions.size(); i++){
                    temp += "Transaction " + (i + 1) + "\n" +
                            "Summary: " + transactions.get(i).getDataSummary() + "\n" +
                            "File name: " + transactions.get(i).getFileName() + "\n\n";


                }
            }else{
                temp += "block is not available";
            }


            textView.setText(temp);


        }
        else if (id == R.id.block2) // show block 2 info
        {

            String temp = "";

            // TODO
            if(blockchain.getLength() >= 3){
                Set<String> keySet = blockchain.getKeySet();
                ArrayList<String> keys = new ArrayList<>(keySet);
                Collections.sort(keys);

                Block block = blockchain.getBlock(keys.get(2));
                ArrayList<Transaction> transactions = block.getTransactions();

                for(int i = 0; i < transactions.size(); i++){
                    temp += "Transaction " + (i + 1) + "\n" +
                            "Summary: " + transactions.get(i).getDataSummary() + "\n" +
                            "File name: " + transactions.get(i).getFileName() + "\n\n";
                }
            }
            else{
                temp += "block is not available";
            }


            textView.setText(temp);


        }
        else if (id == R.id.block3) // show block 3 info
        {

            String temp = "";

            // TODO
            if(blockchain.getLength() >= 4){
                Set<String> keySet = blockchain.getKeySet();
                ArrayList<String> keys = new ArrayList<>(keySet);
                Collections.sort(keys);

                Block block = blockchain.getBlock(keys.get(3));
                ArrayList<Transaction> transactions = block.getTransactions();

                for(int i = 0; i < transactions.size(); i++){
                    temp += "Transaction " + (i + 1) + "\n" +
                            "Summary: " + transactions.get(i).getDataSummary() + "\n" +
                            "File name: " + transactions.get(i).getFileName() + "\n\n";
                }
            }
            else{
                temp += "block is not available";
            }



            textView.setText(temp);


        }
        else if (id == R.id.block4) // show block 4 info
        {

            String temp = "";

            // TODO
            if(blockchain.getLength() >= 5){
                Set<String> keySet = blockchain.getKeySet();
                ArrayList<String> keys = new ArrayList<>(keySet);
                Collections.sort(keys);

                Block block = blockchain.getBlock(keys.get(4));
                ArrayList<Transaction> transactions = block.getTransactions();

                for(int i = 0; i < transactions.size(); i++){
                    temp += "Transaction " + (i + 1) + "\n" +
                            "Summary: " + transactions.get(i).getDataSummary() + "\n" +
                            "File name: " + transactions.get(i).getFileName() + "\n\n";
                }
            }
            else{
                temp += "block is not available";
            }



            textView.setText(temp);

        }
        else if (id == R.id.blocks_info) // show all blocks' info
        {

            String temp = "";

            for(int i = 0; i<blocks.size(); i++){
                temp += blocks.get(i) + "\n";
            }

            textView.setText(temp);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class getBlockchain extends AsyncTask {

        @Override
        protected List<String> doInBackground(Object[] params) {

            crypDist = new CrypDist();
            crypDist.updateBlockchain();
            blockchain = crypDist.getBlockchainManager().getBlockchain();

            Set<String> keySet = blockchain.getKeySet();
            ArrayList<String> keys = new ArrayList<>(keySet);
            Collections.sort(keys);

            int index = 0;

            for (String key : keys)
            {
                Block block = blockchain.getBlock(key);
                blocks.add(index, "block " + index + " \t " + block.getTimestamp());
                index++;
            }

            return blocks;
        }


        @Override
        // Update user interface, add block list
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Toast.makeText(AkActivity.this, "Blockchain is downloaded", Toast.LENGTH_SHORT).show();

            textView.setText("blockchain is downloaded successfully");
        }
    }


}
