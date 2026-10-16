package ru.kulikov.pr5;

/**
 * 50 практических заданий (5 блоков по 10).
 */
final class PracticeScreens {

    private PracticeScreens() {
    }

    static void add() {
        Screens.addHeader("Блок 1: карточки каталогов (1–10)");
        Screens.addScreen("1. Карточка маркетплейса", R.layout.p01_marketplace_card);
        Screens.addScreen("2. Карточка ресторана", R.layout.p02_restaurant_card);
        Screens.addScreen("3. Баннер спецпредложения", R.layout.p03_promo_banner);
        Screens.addScreen("4. Банковская карта 85:54", R.layout.p04_bank_card);
        Screens.addScreen("5. Параметры автомобиля", R.layout.p05_car_specs);
        Screens.addScreen("6. Авиабилет", R.layout.p06_flight_ticket);
        Screens.addScreen("7. Карточка трека", R.layout.p07_track_card);
        Screens.addScreen("8. Тариф связи", R.layout.p08_tariff_card);
        Screens.addScreen("9. Подписка «Хит продаж»", R.layout.p09_subscription_hit);
        Screens.addScreen("10. Бронирование отеля", R.layout.p10_hotel_booking);
        Screens.addHeader("Блок 2: формы и настройки (11–20)");
        Screens.addScreen("11. Перевод по номеру", R.layout.p11_transfer);
        Screens.addScreen("12. Фильтр каталога", R.layout.p12_price_filter);
        Screens.addScreen("13. Смена пароля", R.layout.p13_change_password);
        Screens.addScreen("14. Адрес доставки", R.layout.p14_address);
        Screens.addScreen("15. Форма отзыва", R.layout.p15_review);
        Screens.addScreen("16. Приватность", R.layout.p16_privacy);
        Screens.addScreen("17. Данные карты", R.layout.p17_card_input);
        Screens.addScreen("18. Обратная связь", R.layout.p18_feedback);
        Screens.addScreen("19. Код из SMS", R.layout.p19_sms_code);
        Screens.addScreen("20. Редактирование профиля", R.layout.p20_edit_profile);
        Screens.addHeader("Блок 3: профили и дашборды (21–30)");
        Screens.addScreen("21. Шапка профиля", R.layout.p21_profile_header);
        Screens.addScreen("22. Фитнес-трекер", R.layout.p22_fitness);
        Screens.addScreen("23. Кошелек", R.layout.p23_wallet);
        Screens.addScreen("24. Аналитика продаж", R.layout.p24_sales_analytics);
        Screens.addScreen("25. Батарея", R.layout.p25_battery);
        Screens.addScreen("26. Достижения", R.layout.p26_achievements);
        Screens.addScreen("27. Водитель такси", R.layout.p27_taxi_driver);
        Screens.addScreen("28. История заказов", R.layout.p28_order_history);
        Screens.addScreen("29. Умный дом", R.layout.p29_smart_home);
        Screens.addScreen("30. Медицинская карта", R.layout.p30_medical_card);
        Screens.addHeader("Блок 4: медиа и соцсети (31–40)");
        Screens.addScreen("31. Аудиоплеер", R.layout.p31_audio_player);
        Screens.addScreen("32. Диалог мессенджера", R.layout.p32_chat);
        Screens.addDark("33. Reels / Shorts", R.layout.p33_reels);
        Screens.addDark("34. Голосовой вызов", R.layout.p34_voice_call);
        Screens.addScreen("35. Комментарии", R.layout.p35_comments);
        Screens.addDark("36. Видеоплеер", R.layout.p36_video_player);
        Screens.addScreen("37. Запись голосового", R.layout.p37_voice_record);
        Screens.addScreen("38. Галерея с выбором", R.layout.p38_gallery);
        Screens.addDark("39. Stories", R.layout.p39_stories);
        Screens.addScreen("40. Превью подкаста", R.layout.p40_podcast_preview);
        Screens.addHeader("Блок 5: сложные экраны с оверлеями (41–50)");
        Screens.addScreen("41. Такси с картой", R.layout.p41_taxi_map);
        Screens.addScreen("42. Корзина", R.layout.p42_cart);
        Screens.addScreen("43. Онбординг", R.layout.p43_onboarding);
        Screens.addDark("44. Сканер QR-кода", R.layout.p44_qr_scanner);
        Screens.addScreen("45. Пустое состояние", R.layout.p45_empty_state);
        Screens.addScreen("46. Результаты квиза", R.layout.p46_quiz_results);
        Screens.addScreen("47. Подтверждение операции", R.layout.p47_confirm_operation);
        Screens.addScreen("48. Сравнение товаров", R.layout.p48_compare);
        Screens.addScreen("49. Оформление страховки", R.layout.p49_insurance);
        Screens.addScreen("50. Страница товара", R.layout.p50_product_page);
    }
}
