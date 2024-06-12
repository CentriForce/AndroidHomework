package com.example.hejunzheapp.page3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hejunzheapp.R;
import com.example.hejunzheapp.customview.NavigationItemView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SelectCountryDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    private OnDialogResultListener listener;
    public SelectCountryDialog(OnDialogResultListener listener){
        this.listener = listener;
    }
    private NavigationItemView country[] = new NavigationItemView[20];
    private final String[] countryName = {
            "CNY", "USD", "JPY", "EUR",
            "GBP", "AUD", "CAD", "NZD",
            "MOP", "HKD", "NTD", "SGD",
            "KRW", "ZAR", "RUB", "INR",
            "BRL", "CHF", "SEK", "PHP"
    };

    @Nullable
    @Override
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_select_country_dialog, container, false);
        country[0] = view.findViewById(R.id.cny);
        country[1] = view.findViewById(R.id.usd);
        country[2] = view.findViewById(R.id.jpy);
        country[3] = view.findViewById(R.id.eur);

        country[4] = view.findViewById(R.id.gbp);
        country[5] = view.findViewById(R.id.aud);
        country[6] = view.findViewById(R.id.cad);
        country[7] = view.findViewById(R.id.nzd);

        country[8] = view.findViewById(R.id.mop);
        country[9] = view.findViewById(R.id.hkd);
        country[10] = view.findViewById(R.id.ntd);
        country[11] = view.findViewById(R.id.sgd);

        country[12] = view.findViewById(R.id.krw);
        country[13] = view.findViewById(R.id.zar);
        country[14] = view.findViewById(R.id.rub);
        country[15] = view.findViewById(R.id.inr);

        country[16] = view.findViewById(R.id.brl);
        country[17] = view.findViewById(R.id.chf);
        country[18] = view.findViewById(R.id.sek);
        country[19] = view.findViewById(R.id.php);
        for (int i = 0; i < country.length; i++) {
            country[i].setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        NavigationItemView item = (NavigationItemView) v;
        String text = item.getText();
        listener.onDialogResult(text);
        getDialog().dismiss();
    }

    public interface OnDialogResultListener{
        void onDialogResult(String country);
    }
}
