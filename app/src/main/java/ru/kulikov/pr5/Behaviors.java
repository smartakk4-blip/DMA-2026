package ru.kulikov.pr5;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Поведение отдельных экранов. Большинству экранов код не нужен - это чистая верстка.
 * Сравнение через if, потому что id ресурсов в AGP 8 не final и в switch их использовать нельзя.
 */
final class Behaviors {

    private Behaviors() {
    }

    static void bind(Activity a, int layout) {
        strikeTagged(a.findViewById(android.R.id.content));

        if (layout == R.layout.topic2_frame) {
            topic2(a);
        } else if (layout == R.layout.topic4_constraint) {
            topic4(a);
        } else if (layout == R.layout.topic6_combine) {
            topic6(a);
        } else if (layout == R.layout.p11_transfer) {
            transfer(a);
        } else if (layout == R.layout.p12_price_filter) {
            priceFilter(a);
        } else if (layout == R.layout.p13_change_password) {
            changePassword(a);
        } else if (layout == R.layout.p18_feedback) {
            feedback(a);
        } else if (layout == R.layout.p19_sms_code) {
            smsCode(a);
        } else if (layout == R.layout.p23_wallet) {
            wallet(a);
        } else if (layout == R.layout.p31_audio_player) {
            a.findViewById(R.id.tvMarquee).setSelected(true); // без этого marquee не поедет
        } else if (layout == R.layout.p34_voice_call) {
            voiceCall(a);
        } else if (layout == R.layout.p36_video_player) {
            videoPlayer(a);
        } else if (layout == R.layout.p37_voice_record) {
            Chronometer chrono = a.findViewById(R.id.chronoRecord);
            chrono.setBase(SystemClock.elapsedRealtime() - 7_000); // запись идет уже 7 секунд
            chrono.start();
        } else if (layout == R.layout.p38_gallery) {
            gallery(a);
        } else if (layout == R.layout.p47_confirm_operation) {
            confirmOperation(a);
        } else if (layout == R.layout.p50_product_page) {
            productPage(a);
        }
    }

    /** Зачеркиваем старые цены: у таких TextView в разметке android:tag="strike". */
    private static void strikeTagged(View view) {
        if (view instanceof TextView && "strike".equals(view.getTag())) {
            TextView tv = (TextView) view;
            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                strikeTagged(group.getChildAt(i));
            }
        }
    }

    // Тема 2, задание 2: по нажатию кнопка блокируется, поверх крутится ProgressBar
    private static void topic2(Activity a) {
        Button button = a.findViewById(R.id.btnSubmitOverlay);
        View progress = a.findViewById(R.id.progressOverlay);
        button.setOnClickListener(v -> {
            button.setEnabled(false);
            button.setText("");
            progress.setVisibility(View.VISIBLE);
            // имитация отправки данных на сервер
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                progress.setVisibility(View.GONE);
                button.setEnabled(true);
                button.setText(R.string.btn_confirm);
            }, 3000);
        });
    }

    // Тема 4, задание 5: вся группа скрывается одной строкой setVisibility
    private static void topic4(Activity a) {
        Group group = a.findViewById(R.id.groupWifi);
        Button toggle = a.findViewById(R.id.btnToggleGroup);
        toggle.setOnClickListener(v -> {
            boolean visible = group.getVisibility() == View.VISIBLE;
            group.setVisibility(visible ? View.GONE : View.VISIBLE);
            toggle.setText(visible ? R.string.btn_show_group : R.string.btn_hide_group);
        });
    }

    // Тема 6, задание 3: ViewStub загружает разметку только при первом нажатии
    private static void topic6(Activity a) {
        Button button = a.findViewById(R.id.btnShowNoInternet);
        button.setOnClickListener(v -> {
            ViewStub stub = a.findViewById(R.id.stubNoInternet);
            if (stub != null) {
                stub.inflate(); // после inflate() ViewStub удаляется из дерева
            }
            button.setEnabled(false);
        });
    }

    // 11. Быстрые суммы подставляются в поле
    private static void transfer(Activity a) {
        EditText amount = a.findViewById(R.id.etTransferAmount);
        int[] ids = {R.id.btnQuick500, R.id.btnQuick1000, R.id.btnQuick5000};
        int[] values = {500, 1000, 5000};
        for (int i = 0; i < ids.length; i++) {
            String value = String.valueOf(values[i]);
            a.findViewById(ids[i]).setOnClickListener(v -> {
                amount.setText(value);
                amount.setSelection(value.length());
            });
        }
    }

    // 12. Ползунок и поля "От"/"До" синхронизированы
    private static void priceFilter(Activity a) {
        RangeSlider slider = a.findViewById(R.id.rangePrice);
        EditText from = a.findViewById(R.id.etPriceFrom);
        EditText to = a.findViewById(R.id.etPriceTo);
        RangeSlider.OnChangeListener listener = (s, value, fromUser) -> {
            List<Float> values = s.getValues();
            from.setText(String.valueOf(values.get(0).intValue()));
            to.setText(String.valueOf(values.get(1).intValue()));
        };
        slider.addOnChangeListener(listener);
        listener.onValueChange(slider, 0, false);
        a.findViewById(R.id.btnResetFilters).setOnClickListener(v -> {
            slider.setValues(slider.getValueFrom(), slider.getValueTo());
            listener.onValueChange(slider, 0, false);
        });
    }

    // 13. Требования к паролю отмечаются зелеными галочками по мере ввода
    private static void changePassword(Activity a) {
        EditText newPass = a.findViewById(R.id.etNewPassword);
        EditText repeat = a.findViewById(R.id.etRepeatPassword);
        TextView length = a.findViewById(R.id.reqLength);
        TextView digit = a.findViewById(R.id.reqDigit);
        TextView upper = a.findViewById(R.id.reqUpper);
        TextView match = a.findViewById(R.id.reqMatch);
        TextWatcher watcher = new SimpleWatcher(() -> {
            String p = newPass.getText().toString();
            String r = repeat.getText().toString();
            mark(a, length, p.length() >= 8);
            mark(a, digit, p.matches(".*\\d.*"));
            mark(a, upper, !p.equals(p.toLowerCase(Locale.ROOT)));
            mark(a, match, !p.isEmpty() && p.equals(r));
        });
        newPass.addTextChangedListener(watcher);
        repeat.addTextChangedListener(watcher);
    }

    private static void mark(Activity a, TextView tv, boolean ok) {
        int color = a.getColor(ok ? R.color.green : R.color.text_secondary);
        tv.setTextColor(color);
        TextViewCompat.setCompoundDrawableTintList(tv, ColorStateList.valueOf(ok ? color : 0xFFBDBDBD));
    }

    // 18. Варианты выпадающего списка
    private static void feedback(Activity a) {
        AutoCompleteTextView dropdown = a.findViewById(R.id.dropdownTopic);
        String[] topics = a.getResources().getStringArray(R.array.feedback_topics);
        dropdown.setAdapter(new ArrayAdapter<>(a, android.R.layout.simple_list_item_1, topics));
    }

    // 19. После ввода цифры фокус переходит в следующее поле, Backspace в пустом - в предыдущее
    private static void smsCode(Activity a) {
        int[] ids = {R.id.etCode1, R.id.etCode2, R.id.etCode3, R.id.etCode4, R.id.etCode5, R.id.etCode6};
        EditText[] boxes = new EditText[ids.length];
        for (int i = 0; i < ids.length; i++) {
            boxes[i] = a.findViewById(ids[i]);
        }
        for (int i = 0; i < boxes.length; i++) {
            final int index = i;
            boxes[i].addTextChangedListener(new SimpleWatcher(() -> {
                if (boxes[index].length() == 1 && index < boxes.length - 1) {
                    boxes[index + 1].requestFocus();
                }
            }));
            boxes[i].setOnKeyListener((v, keyCode, event) -> {
                if (keyCode == android.view.KeyEvent.KEYCODE_DEL && event.getAction() == android.view.KeyEvent.ACTION_DOWN
                        && boxes[index].length() == 0 && index > 0) {
                    boxes[index - 1].requestFocus();
                    boxes[index - 1].setText("");
                    return true;
                }
                return false;
            });
        }
        boxes[0].requestFocus();
    }

    // 23. Глаз скрывает и показывает баланс
    private static void wallet(Activity a) {
        TextView balance = a.findViewById(R.id.tvBalance);
        ImageButton eye = a.findViewById(R.id.btnToggleBalance);
        CharSequence real = balance.getText();
        eye.setOnClickListener(v -> {
            boolean hidden = !balance.getText().equals(real);
            balance.setText(hidden ? real : a.getString(R.string.balance_hidden));
            eye.setImageResource(hidden ? R.drawable.ic_visibility : R.drawable.ic_visibility_off);
        });
    }

    // 34. Размытие фона звонящего (RenderEffect есть с Android 12)
    private static void voiceCall(Activity a) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ImageView bg = a.findViewById(R.id.ivCallBackground);
            bg.setRenderEffect(RenderEffect.createBlurEffect(60f, 60f, Shader.TileMode.CLAMP));
        }
    }

    // 36. Тап по видео показывает/скрывает элементы управления
    private static void videoPlayer(Activity a) {
        View controls = a.findViewById(R.id.videoControls);
        a.findViewById(R.id.videoContainer).setOnClickListener(v ->
                controls.setVisibility(controls.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
    }

    // 38. Выбор фото: номер в кружке = порядок выбора, при снятии номера пересчитываются
    private static void gallery(Activity a) {
        int[] cells = {R.id.galleryCell1, R.id.galleryCell2, R.id.galleryCell3, R.id.galleryCell4, R.id.galleryCell5,
                R.id.galleryCell6, R.id.galleryCell7, R.id.galleryCell8, R.id.galleryCell9};
        int[] marks = {R.id.gallerySelect1, R.id.gallerySelect2, R.id.gallerySelect3, R.id.gallerySelect4, R.id.gallerySelect5,
                R.id.gallerySelect6, R.id.gallerySelect7, R.id.gallerySelect8, R.id.gallerySelect9};
        List<Integer> order = new ArrayList<>();
        TextView counter = a.findViewById(R.id.tvGalleryCount);
        Runnable redraw = () -> {
            for (int i = 0; i < marks.length; i++) {
                TextView mark = a.findViewById(marks[i]);
                int position = order.indexOf(i);
                mark.setText(position >= 0 ? String.valueOf(position + 1) : "");
                mark.setBackgroundResource(position >= 0 ? R.drawable.bg_select_number : R.drawable.bg_select_empty);
            }
            counter.setText(order.isEmpty() ? a.getString(R.string.gallery_hint)
                    : a.getString(R.string.gallery_selected, order.size()));
        };
        for (int i = 0; i < cells.length; i++) {
            final Integer index = i;
            a.findViewById(cells[i]).setOnClickListener(v -> {
                if (!order.remove(index)) {
                    order.add(index);
                }
                redraw.run();
            });
        }
    }

    // 47. Обратный отсчет: пока идет таймер, повторно запросить SMS нельзя
    private static void confirmOperation(Activity a) {
        TextView timer = a.findViewById(R.id.tvOtpTimer);
        Button resend = a.findViewById(R.id.btnResendSms);
        CountDownTimer countDown = new CountDownTimer(60_000, 1_000) {
            @Override
            public void onTick(long left) {
                long s = left / 1000;
                timer.setText(a.getString(R.string.otp_timer, s / 60, s % 60));
            }

            @Override
            public void onFinish() {
                timer.setText(R.string.otp_expired);
                resend.setEnabled(true);
            }
        };
        countDown.start();
        resend.setOnClickListener(v -> {
            resend.setEnabled(false);
            countDown.start();
        });
    }

    // 50. Слайдер фото (ViewPager2) и аккордеон описания
    private static void productPage(Activity a) {
        ViewPager2 pager = a.findViewById(R.id.productPager);
        TextView counter = a.findViewById(R.id.tvPagerCounter);
        int[] images = {R.drawable.img_product, R.drawable.img_photo2, R.drawable.img_photo5};
        pager.setAdapter(new ImageAdapter(images));
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                counter.setText(a.getString(R.string.pager_counter, position + 1, images.length));
            }
        });

        View header = a.findViewById(R.id.accordionHeader);
        TextView body = a.findViewById(R.id.tvAccordionBody);
        ImageView arrow = a.findViewById(R.id.ivAccordionArrow);
        header.setOnClickListener(v -> {
            boolean open = body.getVisibility() == View.VISIBLE;
            body.setVisibility(open ? View.GONE : View.VISIBLE);
            arrow.setImageResource(open ? R.drawable.ic_expand_more : R.drawable.ic_expand_less);
        });
    }

    /** Адаптер слайдера: одна картинка на страницу. */
    private static final class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Holder> {
        private final int[] images;

        ImageAdapter(int[] images) {
            this.images = images;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager_image, parent, false);
            return new Holder((ImageView) view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.image.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        static final class Holder extends RecyclerView.ViewHolder {
            final ImageView image;

            Holder(ImageView image) {
                super(image);
                this.image = image;
            }
        }
    }

    /** TextWatcher, которому нужен только afterTextChanged. */
    private static final class SimpleWatcher implements TextWatcher {
        private final Runnable onChange;

        SimpleWatcher(Runnable onChange) {
            this.onChange = onChange;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            onChange.run();
        }
    }
}
