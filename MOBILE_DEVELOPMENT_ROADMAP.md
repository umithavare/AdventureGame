# Mobil Oyun Geliştirme Yol Haritası (3D & Cross-Platform)

Bu belge, mevcut metin tabanlı Java oyununu 3D, hem iOS hem de Android platformlarında çalışacak bir mobil oyuna dönüştürmek için gereken stratejileri ve araçları içerir.

## 1. Platform Seçenekleri Karşılaştırması

| Özellik | Yerel (Native) | Flutter / React Native | Oyun Motorları (Unity / Godot) |
| :--- | :--- | :--- | :--- |
| **3D Desteği** | Kısıtlı (Zor) | Orta (Pluginler ile) | **Mükemmel (Önerilen)** |
| **Cross-Platform** | Hayır (Ayrı kod) | Evet | Evet |
| **Performans** | En Yüksek | Orta/Yüksek | Yüksek (3D için en iyisi) |
| **Dil** | Java/Kotlin & Swift | Dart / JavaScript | C# (Unity) / GDScript (Godot) |

**Öneri:** Kullanıcının 3D isteği ve her iki platformda (iOS/Android) yayınlama hedefi göz önüne alındığında, **Unity** en mantıklı seçenektir.

---

## 2. Gereksinimler

### Donanım ve Yazılım
*   **iOS İçin:** Uygulamayı derlemek ve App Store'a yüklemek için mutlaka bir **macOS** cihaz (MacBook, Mac Mini vb.) ve **Xcode** gereklidir.
*   **Android İçin:** Windows veya macOS üzerinde **Android Studio** ve **Android SDK** gereklidir.
*   **Oyun Motoru:** Unity Hub ve Unity Editor (LTS sürümü önerilir).

### Hesaplar
*   **Apple Developer Program:** iOS'ta yayınlamak için yıllık 99 USD.
*   **Google Play Console:** Android'de yayınlamak için tek seferlik 25 USD.

---

## 3. Mimari Değişim (Konsoldan Mobile)

Mevcut Java kodunun mantığı korunabilir ancak giriş/çıkış (I/O) yapısının tamamen değişmesi gerekir:

1.  **Girdi (Input):** `Scanner.nextInt()` yerine dokunmatik ekran butonları (UI Buttons) veya joystick kullanılacak.
2.  **Çıktı (Output):** `System.out.println()` yerine Unity'de `TextMeshPro` bileşenleri veya 3D dünya üzerindeki görsel efektler kullanılacak.
3.  **Döngü (Game Loop):** Metin tabanlı sırayla ilerleyen yapı yerine, saniyede 60 kare (FPS) çalışan bir oyun döngüsüne (Update loop) geçilecek.

---

## 4. Uygulama Adımları

1.  **Motor Kurulumu:** Unity'nin en son kararlı sürümünü indirin.
2.  **Mantık Portlama:** Mevcut Java sınıflarını (Player, Obstacle, Inventory) C# diline çevirin (Yapısal olarak çok benzerdirler).
3.  **3D Sahne Tasarımı:** Karakterlerin ve bölgelerin (Orman, Mağara vb.) 3D modellerini sahneye yerleştirin.
4.  **Arayüz (UI) Geliştirme:** Can barı, envanter paneli ve seçim menülerini tasarlayın.
5.  **Test ve Yayın:** Önce emülatörlerde, ardından gerçek cihazlarda test ederek platform mağazalarına gönderin.
