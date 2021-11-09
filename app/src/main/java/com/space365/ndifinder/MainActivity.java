package com.space365.ndifinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.widget.TextView;

import me.walkerknapp.devolay.Devolay;
import me.walkerknapp.devolay.DevolayFinder;
import me.walkerknapp.devolay.DevolaySource;

public class MainActivity extends AppCompatActivity {

    private NsdManager nsdManager;
    DevolayFinder finder;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.textView = this.findViewById(R.id.textView);
        nsdManager = (NsdManager)getSystemService(Context.NSD_SERVICE);
        setContentView(R.layout.activity_main);
        Devolay.loadLibraries();
        this.finder = new DevolayFinder();
        this.findViewById(R.id.btnSearch).setOnClickListener( v -> {
            if (!finder.waitForSources(5000)) {
                // If no new sources were found
                textView.setText("No change to the sources list found.");
            }
            DevolaySource[] sources = finder.getCurrentSources();
            textView.setText( String.format( "Network sources ( %s found ).",sources.length) );
        } );
    }
}