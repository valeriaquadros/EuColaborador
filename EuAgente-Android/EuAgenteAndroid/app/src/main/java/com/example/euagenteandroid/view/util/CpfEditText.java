package com.example.euagenteandroid.view.util;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;

public class CpfEditText extends androidx.appcompat.widget.AppCompatEditText {

    private boolean isUpdating;
    private int[] positioning = {0, 1, 2, 3, 5, 6, 7, 9, 10, 11, 13, 14};

    public CpfEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public CpfEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CpfEditText(Context context) {
        super(context);
    }

    public String getCleanText() {
        String text = CpfEditText.this.getText().toString();
        return text.replaceAll("[^0-9]*", "");
    }

    private void initialize() {
        final int maxNumberLength = 11;
        this.setKeyListener(keylistenerNumber);

        this.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String current = s.toString();

                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                String number = current.replaceAll("[^0-9]*", "");
                if (number.length() > 11)
                    number = number.substring(0, 11);
                int length = number.length();

                String paddedNumber = padNumber(number, maxNumberLength);

                String part1 = paddedNumber.substring(0, 3);
                String part2 = paddedNumber.substring(3, 6);
                String part3 = paddedNumber.substring(6, 9);
                String part4 = paddedNumber.substring(9, 11);

                String cpf = part1 + "." + part2 + "." + part3 + "-" + part4;

                isUpdating = true;
                CpfEditText.this.setText(cpf);

                CpfEditText.this.setSelection(positioning[length]);

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //Sem comportamento específico da classe
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //Sem comportamento específico da classe
            }
        });
    }

    protected String padNumber(String number, int maxLength) {
        StringBuilder padded = new StringBuilder(number);
        for (int i = 0; i < maxLength - number.length(); i++)
            padded.append(" ");
        return padded.toString();
    }

    private final KeylistenerNumber keylistenerNumber = new KeylistenerNumber();

    private class KeylistenerNumber extends NumberKeyListener {
        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER
                    | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        }
    }
}