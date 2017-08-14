package western.ben.mediavaluecalulatorprototype;

import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainCalulatorActivity extends AppCompatActivity {

    ToggleButton filmtoggleButton, tvtoggleButton, digitaltoggleButton, atoggleButton, btoggleButton,
    ctoggleButton, premiumtoggleButton, prominenttoggleButton, standardtoggleButton;

    EditText cpmeditText, mediavalueeditText, impressioneditText;


    double baseCPM = 25.00;

    double filmChannel = 0.924;
    double tvChannel = 1.048;
    double digitalChannel = 1.048;

    double aVpower = 1.077;
    double bVpower = 1.000;
    double cVpower = 0.896;

    double premiumQuality = 1.142;
    double prominentQuality = 1.000;
    double standardQuality = 0.900;

    double channel, vPower, quality = 0;

    double finCPM = baseCPM;
    double finMediaValue = 00.00;
    double impressions = 11;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calulator);

        TextView channeltextView = (TextView) findViewById(R.id.channelTextView);
        TextView vehiclepowertextView = (TextView) findViewById(R.id.vehiclePowerTextView);
        TextView qualitytextView = (TextView) findViewById(R.id.qualityTextView);
        TextView impressiontextView = (TextView) findViewById(R.id.impressionsTextView);
        TextView cpmtextView = (TextView) findViewById(R.id.cpmTextView);
        TextView mediavaluetextView = (TextView) findViewById(R.id.mediaValueTextView);

        filmtoggleButton = (ToggleButton) findViewById(R.id.filmToggleButton);
        tvtoggleButton = (ToggleButton) findViewById(R.id.tvToggleButton);
        digitaltoggleButton = (ToggleButton) findViewById(R.id.digitalToggleButton);
        atoggleButton = (ToggleButton) findViewById(R.id.aToggleButton);
        btoggleButton = (ToggleButton) findViewById(R.id.bToggleButton);
        ctoggleButton = (ToggleButton) findViewById(R.id.cToggleButton);
        premiumtoggleButton = (ToggleButton) findViewById(R.id.premiumToggleButton);
        prominenttoggleButton = (ToggleButton) findViewById(R.id.prominentToggleButton);
        standardtoggleButton = (ToggleButton) findViewById(R.id.standardToggleButton);

        impressioneditText= (EditText) findViewById(R.id.impressionEditText);
        cpmeditText= (EditText) findViewById(R.id.cpmEditText);
        mediavalueeditText= (EditText) findViewById(R.id.mediaValueEditText);

        impressioneditText.setHint("$00.00");
        cpmeditText.setKeyListener(null);
        mediavalueeditText.setKeyListener(null);


        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Proxima Nova Light.otf");

        channeltextView.setTypeface(custom_font);
        vehiclepowertextView.setTypeface(custom_font);
        qualitytextView.setTypeface(custom_font);
        impressiontextView.setTypeface(custom_font);
        cpmtextView.setTypeface(custom_font);
        mediavaluetextView.setTypeface(custom_font);

        filmtoggleButton.setTypeface(custom_font);
        tvtoggleButton.setTypeface(custom_font);
        digitaltoggleButton.setTypeface(custom_font);
        atoggleButton.setTypeface(custom_font);
        btoggleButton.setTypeface(custom_font);
        ctoggleButton.setTypeface(custom_font);
        premiumtoggleButton.setTypeface(custom_font);
        prominenttoggleButton.setTypeface(custom_font);
        standardtoggleButton.setTypeface(custom_font);

        filmtoggleButton.setOnCheckedChangeListener(changeChecker1);
        tvtoggleButton.setOnCheckedChangeListener(changeChecker1);
        digitaltoggleButton.setOnCheckedChangeListener(changeChecker1);
        atoggleButton.setOnCheckedChangeListener(changeChecker2);
        btoggleButton.setOnCheckedChangeListener(changeChecker2);
        ctoggleButton.setOnCheckedChangeListener(changeChecker2);
        premiumtoggleButton.setOnCheckedChangeListener(changeChecker3);
        prominenttoggleButton.setOnCheckedChangeListener(changeChecker3);
        standardtoggleButton.setOnCheckedChangeListener(changeChecker3);

        impressioneditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                impressions = Double.parseDouble(impressioneditText.getText().toString());
                calcMediaValue();
            }
        });



    }

CompoundButton.OnCheckedChangeListener changeChecker1 = new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            if(buttonView == filmtoggleButton) {
                tvtoggleButton.setChecked(false);
                digitaltoggleButton.setChecked(false);
                channel = filmChannel;
            }
            if(buttonView == tvtoggleButton) {
                filmtoggleButton.setChecked(false);
                digitaltoggleButton.setChecked(false);
                channel = tvChannel;
            }
            if(buttonView == digitaltoggleButton) {
                filmtoggleButton.setChecked(false);
                tvtoggleButton.setChecked(false);
                channel = digitalChannel;
            }
        }
        calcMediaValue();
    }
};

CompoundButton.OnCheckedChangeListener changeChecker2 = new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            if(buttonView == atoggleButton) {
                btoggleButton.setChecked(false);
                ctoggleButton.setChecked(false);
                vPower = aVpower;
            }
            if(buttonView == btoggleButton) {
                atoggleButton.setChecked(false);
                ctoggleButton.setChecked(false);
                vPower = bVpower;
            }
            if(buttonView == ctoggleButton) {
                atoggleButton.setChecked(false);
                btoggleButton.setChecked(false);
                vPower = cVpower;
            }
        }
        calcMediaValue();
    }
};

CompoundButton.OnCheckedChangeListener changeChecker3 = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                if(buttonView == premiumtoggleButton) {
                    prominenttoggleButton.setChecked(false);
                    standardtoggleButton.setChecked(false);
                    quality = premiumQuality;
                }
                if(buttonView == prominenttoggleButton) {
                    premiumtoggleButton.setChecked(false);
                    standardtoggleButton.setChecked(false);
                    quality = prominentQuality;
                }
                if(buttonView == standardtoggleButton) {
                    premiumtoggleButton.setChecked(false);
                    prominenttoggleButton.setChecked(false);
                    quality = standardQuality;
                }
            }
            calcMediaValue();
        }

    };

    public void calcMediaValue() {
        finCPM = round(channel*vPower*quality*baseCPM,2);
        finMediaValue = round(finCPM*impressions, 2);
        mediavalueeditText.setText("$" + Double.toString(finMediaValue));
        cpmeditText.setText("$" + Double.toString(finCPM));
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


}
