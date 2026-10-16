package ru.kulikov.pr5;

import java.util.ArrayList;
import java.util.List;

/**
 * Список всех экранов работы: темы 1-6 и практические задания 1-50.
 */
public final class Screens {

    public static final class Item {
        public final String title;
        public final int layout;
        public final boolean dark;
        public final boolean isHeader;

        Item(String title, int layout, boolean dark, boolean isHeader) {
            this.title = title;
            this.layout = layout;
            this.dark = dark;
            this.isHeader = isHeader;
        }
    }

    private Screens() {
    }

    private static final List<Item> ALL = new ArrayList<>();

    private static void header(String title) {
        ALL.add(new Item(title, 0, false, true));
    }

    private static void screen(String title, int layout) {
        ALL.add(new Item(title, layout, false, false));
    }

    /** полноэкранный темный экран (видео, камера, звонок) */
    private static void dark(String title, int layout) {
        ALL.add(new Item(title, layout, true, false));
    }

    public static List<Item> all() {
        if (!ALL.isEmpty()) {
            return ALL;
        }
        header("Темы 1–6: задания для закрепления");
        screen("Тема 1. LinearLayout", R.layout.topic1_linear);
        screen("Тема 2. FrameLayout", R.layout.topic2_frame);
        screen("Тема 3. RelativeLayout", R.layout.topic3_relative);
        screen("Тема 4. ConstraintLayout", R.layout.topic4_constraint);
        screen("Тема 5. ScrollView", R.layout.topic5_scroll);
        screen("Тема 5.2. Лицензионное соглашение", R.layout.topic5_license);
        screen("Тема 5.3. Вход (клавиатура)", R.layout.topic5_login);
        screen("Тема 6. include, merge, ViewStub", R.layout.topic6_combine);
        PracticeScreens.add();
        return ALL;
    }

    // используется из PracticeScreens
    static void addHeader(String title) {
        header(title);
    }

    static void addScreen(String title, int layout) {
        screen(title, layout);
    }

    static void addDark(String title, int layout) {
        dark(title, layout);
    }
}
