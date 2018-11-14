package org.richcode.koreatoron.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import org.richcode.koreatoron.R;

public class ExitDialog extends Dialog implements View.OnClickListener {

    private MyDialogListener dialogListener;
    private Context context;

    Button ExitButton;
    Button ReturnButton;

    NativeExpressAdView mAdView;

    public ExitDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_dialog);

        mAdView =(NativeExpressAdView)findViewById(R.id.exit_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ExitButton = (Button)findViewById(R.id.exit_true_button);
        ReturnButton = (Button)findViewById(R.id.exit_false_button);

        ExitButton.setOnClickListener(this);
        ReturnButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exit_true_button:
                dialogListener.onPositiveClicked("EXIT");
                dismiss();
                break;
            case R.id.exit_false_button:
                dismiss();
                break;
        }
    }
}