package ru.kulikov.pr5;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

/**
 * Главное меню практической работы №5.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = findViewById(R.id.menuContainer);
        for (Screens.Item item : Screens.all()) {
            if (item.isHeader) {
                TextView header = new TextView(this);
                header.setText(item.title);
                header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                header.setTextColor(getColor(R.color.primary));
                header.setPadding(0, dp(20), 0, dp(4));
                container.addView(header);
            } else {
                MaterialButton button = new MaterialButton(this, null,
                        com.google.android.material.R.attr.materialButtonOutlinedStyle);
                button.setText(item.title);
                button.setAllCaps(false);
                button.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                button.setOnClickListener(v -> startActivity(ScreenActivity.intent(this, item)));
                container.addView(button);
            }
        }
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
