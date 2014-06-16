package annanatorilla.splittipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SplitTipActivity extends Activity {
    private EditText totalBill;
    private EditText numberOfSplit;
    private EditText customTip;
    private TextView totalTip;
    private TextView totalSplitTip;
    private double numBill = 0;
    private double numTotalTip = 0;
    private double numSplit = 0;
    private double numTotalSplit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_tip);

        totalBill = (EditText) findViewById(R.id.etTotalBill);
        totalTip = (TextView) findViewById(R.id.tvTotalTip);
        numberOfSplit = (EditText) findViewById(R.id.etSplitNumber);
        customTip = (EditText) findViewById(R.id.etCustomizeTip);
        totalSplitTip = (TextView) findViewById(R.id.tvTotalSplit);

        customTip.addTextChangedListener(mTextEditorWatcher);
        numberOfSplit.addTextChangedListener(mTextEditorWatcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.split_tip, menu);
        return true;
    }

    public TextWatcher  mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            calculateTip(customTip);
            try {
                Double.parseDouble(numberOfSplit.getText().toString());
                calculateTip(numberOfSplit);
            }catch (Exception e){}

        }

        public void afterTextChanged(Editable s)
        {

        }
    };

    public void calculateTip(View v) {
        try {
            numBill = Double.parseDouble(totalBill.getText().toString());

            switch (v.getId()) {

                case R.id.btnTen:
                    numTotalTip = numBill * (.10);
                    break;
                case R.id.btnFifteen:
                    numTotalTip = numBill * (.15);
                    break;
                case R.id.btnTwenty:
                    numTotalTip = numBill * (.20);
                    break;
                case R.id.etCustomizeTip:
                    numTotalTip = numBill * (Double.parseDouble(customTip.getText().toString()) / 100);

                    break;
                case R.id.etSplitNumber:
                    try {
                        numTotalSplit = numTotalTip / Double.parseDouble(numberOfSplit.getText().toString());
                        totalSplitTip.setText("Split per person: $ " + numTotalSplit);
                    }catch (Exception e){}
                default:
                    throw new IllegalArgumentException("Invalid");
            }

            totalTip.setText("Tip is: $ " + Double.toString(numTotalTip));

        }catch (Exception e){

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
