package com.example.hejunzheapp.page1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hejunzheapp.R;

public class Page1Fragment extends Fragment {

    private final String[] btn_numIndex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final String[] btn_opIndex = {"+", "-", "×", "÷", ".", "(", ")"};
    private TextView tv_result;
    private final View[] btn_num = new View[10];
    private final View[] btn_op = new View[7];
    private View btn_eql;
    private View btn_del;
    private View btn_plus_minus;
    private Boolean isCalculating = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            btn_num[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCalculating) {
                        tv_result.setText("");
                        isCalculating = false;
                    }
                    if (tv_result.getText().toString().equals("0")) {
                        tv_result.setText(btn_numIndex[finalI]);
                    } else {
                        tv_result.append(btn_numIndex[finalI]);
                    }
                }
            });
        }

        for (int i = 0; i < 7; i++) {
            int finalI = i;
            btn_op[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCalculating) {
                        tv_result.setText("");
                        isCalculating = false;
                    }
                    tv_result.append(btn_opIndex[finalI]);
                }
            });
        }

        btn_eql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_result.setText(
                        String.valueOf(
                                BasicCalculator.getInstance().calculate(
                                        tv_result.getText().toString())));
                isCalculating = true;
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = tv_result.getText().toString();
                if (string.length() > 1) {
                    string = string.substring(0, string.length() - 1);
                }
                if (string.length() == 1 && string.charAt(0) != '0') {
                    string = "0";
                }
                tv_result.setText(string);
            }
        });

        btn_del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tv_result.setText("0");
                return false;
            }
        });


    }

    private void initView(@NonNull View view) {
        tv_result = view.findViewById(R.id.tv_result);

        btn_num[0] = view.findViewById(R.id.button_0);
        btn_num[1] = view.findViewById(R.id.button_1);
        btn_num[2] = view.findViewById(R.id.button_2);
        btn_num[3] = view.findViewById(R.id.button_3);
        btn_num[4] = view.findViewById(R.id.button_4);
        btn_num[5] = view.findViewById(R.id.button_5);
        btn_num[6] = view.findViewById(R.id.button_6);
        btn_num[7] = view.findViewById(R.id.button_7);
        btn_num[8] = view.findViewById(R.id.button_8);
        btn_num[9] = view.findViewById(R.id.button_9);

        btn_op[0] = view.findViewById(R.id.button_add);
        btn_op[1] = view.findViewById(R.id.button_sub);
        btn_op[2] = view.findViewById(R.id.button_mul);
        btn_op[3] = view.findViewById(R.id.button_div);
        btn_op[4] = view.findViewById(R.id.button_dot);
        btn_op[5] = view.findViewById(R.id.button_left_bracket);
        btn_op[6] = view.findViewById(R.id.button_right_bracket);

        btn_eql = view.findViewById(R.id.button_eql);
        btn_del = view.findViewById(R.id.button_del);
        btn_plus_minus = view.findViewById(R.id.button_plus_minus);
    }
}