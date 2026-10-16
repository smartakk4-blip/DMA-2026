package ru.kulikov.pr5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Один экран для всех заданий: показывает разметку, переданную в Intent.
 * Почти все задания этой работы - про верстку, поэтому отдельная Activity на каждое не нужна.
 * Поведение (где оно есть) подключается в {@link Behaviors}.
 */
public class ScreenActivity extends AppCompatActivity {

    private static final String EXTRA_LAYOUT = "layout";
    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_DARK = "dark";

    public static Intent intent(Context context, Screens.Item item) {
        Intent intent = new Intent(context, ScreenActivity.class);
        intent.putExtra(EXTRA_LAYOUT, item.layout);
        intent.putExtra(EXTRA_TITLE, item.title);
        intent.putExtra(EXTRA_DARK, item.dark);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // тему нужно выбрать до super.onCreate()
        if (getIntent().getBooleanExtra(EXTRA_DARK, false)) {
            setTheme(R.style.Theme_Layouts_Dark);
        }
        super.onCreate(savedInstanceState);

        int layout = getIntent().getIntExtra(EXTRA_LAYOUT, 0);
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        setContentView(layout);

        // Тема 7 (Overdraw): если экран сам красит фон, фон окна под ним не нужен -
        // иначе каждый пиксель закрашивается дважды
        View root = ((android.view.ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        if (root != null && root.getBackground() != null) {
            getWindow().setBackgroundDrawable(null);
        }

        // шапка из toolbar_common.xml (если экран ее подключает)
        TextView toolbarTitle = findViewById(R.id.tvToolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }
        View back = findViewById(R.id.btnToolbarBack);
        if (back != null) {
            back.setOnClickListener(v -> finish());
        }

        Behaviors.bind(this, layout);
    }
}
