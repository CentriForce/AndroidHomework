package com.example.hejunzheapp.viewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hejunzheapp.R;
import com.example.hejunzheapp.page3.SelectCountryDialog;

public class Page3Fragment extends Fragment {
    private EditText[] moneyEditText = new EditText[4];
    private TextView[] countryTextView = new TextView[4];
    private double[] exchangeRate = {1.0, 0.15, 0.12, 0.09};
    private String[] countryName = {"CNY", "USD", "JPY", "EUR"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moneyEditText[0] = view.findViewById(R.id.money_edittext1);
        moneyEditText[1] = view.findViewById(R.id.money_edittext2);
        moneyEditText[2] = view.findViewById(R.id.money_edittext3);
        moneyEditText[3] = view.findViewById(R.id.money_edittext4);

        countryTextView[0] = view.findViewById(R.id.country_select1);
        countryTextView[1] = view.findViewById(R.id.country_select2);
        countryTextView[2] = view.findViewById(R.id.country_select3);
        countryTextView[3] = view.findViewById(R.id.country_select4);

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            // 添加点击事件
            moneyEditText[finalI].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // 选中但是还没有修改的时候
                    // 如果同时没有任何文字,则通过hint显示100元本币可以兑换哪些其他货币
                    if (moneyEditText[0].getText().toString().isEmpty()
                            && moneyEditText[1].getText().toString().isEmpty()
                            && moneyEditText[2].getText().toString().isEmpty()
                            && moneyEditText[3].getText().toString().isEmpty()) {
                        moneyEditText[finalI].setHint("100");
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // 更改的时候
                    // 如果不为空,则转换货币
                    // 如果为空,则将本币转换为美元出去对比
                    if (!s.toString().isEmpty()) {
                        double source = Double.parseDouble(s.toString());
//                  TODO      double exchangeUSD = source / ;
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            // 添加点击事件
            countryTextView[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SelectCountryDialog dialog = new SelectCountryDialog(new SelectCountryDialog.OnDialogResultListener() {
                        @Override
                        public void onDialogResult(String country) {
                            countryTextView[finalI].setText(country);
                        }
                    });
                    dialog.show(getActivity().getSupportFragmentManager(), "selectCountry");
                }
            });
        }

    }
}