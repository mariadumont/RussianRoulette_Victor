package org.ieselcaminas.pmdm.russianroulette_victor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int NUM_BULLETS=6;
    private Button[] buttons;
    private int bulletIndex;
    private FrameLayout bangLayout;
    private TextView textBang;
    private boolean gameOver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bangLayout=findViewById(R.id.bangLayout);
        textBang=findViewById(R.id.textViewBang);

        insertBulletIntoTheBarrel();

        createButtons();

        assignActionReloadButton();
    }

    private void assignActionReloadButton() {
        Button reloadButton=findViewById(R.id.buttonReload);
        reloadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                reEnableButtons();
                resetBackground();
                insertBulletIntoTheBarrel();
                }

            private void reEnableButtons() {
                for(Button b: buttons){
                    b.setEnabled(true);
                }
            }

            private void resetBackground() {
                 bangLayout.setBackgroundColor(getResources().getColor(R.color.colorNoBang));
                 textBang.setVisibility(View.INVISIBLE);
            }
        });

        insertBulletIntoTheBarrel();

    }

    private void createButtons() {
        buttons=new Button[NUM_BULLETS];
        LinearLayout barrel=findViewById(R.id.layaoutBarrel);

        for (int i=0; i<buttons.length; i++){
            buttons[i]= new Button(this, null, android.R.attr.buttonStyleSmall);
            buttons[i].setText("" + (i + 1));
            barrel.addView(buttons[i]);
            buttons[i].setTag(i);

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(gameOver) return;

                    v.setEnabled(false);
                    if((Integer)(v.getTag())==bulletIndex){
                        bang();
                    }
                }
            });
        }


    }

    private void bang() {

        bangLayout.setBackgroundColor(getResources().getColor(R.color.colorBang));

        textBang.setVisibility(View.VISIBLE);
        gameOver=true;
    }

    private void insertBulletIntoTheBarrel() {
        bulletIndex=(int)(Math.random()*NUM_BULLETS);
        gameOver=false;
    }
}
