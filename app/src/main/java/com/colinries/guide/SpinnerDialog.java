package com.colinries.guide;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SpinnerDialog extends Dialog {
    private String[] mList;
    private Context mContext;
    private Spinner mSpinner;
    private DialogListener mReadyListener;

    public SpinnerDialog(Context context, String[] list, DialogListener readyListener) {
        super(context);
        mReadyListener = readyListener;
        mContext = context;
        mList = new String[9];
        mList = list;

    }

    public interface DialogListener {
        void ready(int n);
        void cancelled();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.spinner_dialog);
        mSpinner = (Spinner) findViewById (R.id.dialog_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, R.layout.simple_dropdown_item, R.id.text1, mList);
        mSpinner.setAdapter(adapter);

        Button buttonOK = (Button) findViewById(R.id.dialogOK);
        Button buttonCancel = (Button) findViewById(R.id.dialogCancel);
        buttonOK.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v) {
                int n = mSpinner.getSelectedItemPosition();
                mReadyListener.ready(n);
                SpinnerDialog.this.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v) {
                mReadyListener.cancelled();
                SpinnerDialog.this.dismiss();
            }
        });
    }
}