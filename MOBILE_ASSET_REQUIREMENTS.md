# Mobil Oyun Varlık (Asset) Gereksinimleri

Bu belge, 3D ve mobil uyumlu bir oyun için gereken görsel ve ses bileşenlerini detaylandırır.

## 1. 3D Modeller ve Animasyonlar

| Kategori | Varlıklar | Önerilen Format | Notlar |
| :--- | :--- | :--- | :--- |
| **Karakterler** | Samuray, Okçu, Şövalye | `.fbx`, `.obj` | Her karakter için en az 1500-3000 poligon (mobil için optimize). |
| **Düşmanlar** | Zombi, Vampir, Ayı, Yılan | `.fbx` | Yılan için kemik tabanlı (skeletal) animasyon gereklidir. |
| **Çevre (Environment)** | Mağara, Orman, Nehir, Maden, Güvenli Ev | `.fbx` | Düşük poligonlu (low-poly) çevre tasarımı performansı artırır. |
| **Eşyalar** | Silahlar (Kılıç, Tüfek, Tabanca), Zırhlar, İksirler | `.fbx` | Karakterlerin eline tam oturacak şekilde ölçeklenmelidir. |

### Animasyonlar
*   **Idle:** Karakterin hareketsiz beklerken yapacağı nefes alma vb. hareketleri.
*   **Attack:** Her silah türü için farklı saldırı animasyonları.
*   **Hit:** Hasar aldığında karakterin geri çekilmesi.
*   **Death:** Karakterin yere düşmesi veya yok olması.
*   **Victory:** Savaşı kazandığındaki kutlama animasyonu.

---

## 2. Arayüz (UI) Bileşenleri

Mobil ekranlarda dokunmatik etkileşim için gereken görseller:

*   **Ana Menü:** Arka plan resmi, "Oyuna Başla", "Ayarlar" ve "Çıkış" butonları.
*   **HUD (Heads-Up Display):** Can barı (HP bar), para göstergesi, aktif silah ikonu.
*   **Envanter Paneli:** Toplanan eşyaların (Yemek, Odun, Su) görselleri ve kullanma/donanma butonları.
*   **Mağaza Arayüzü:** Satın alınabilir eşyaların resimleri, fiyat etiketleri ve onay butonları.
*   **Savaş Arayüzü:** "Saldır", "Kaç" butonları ve hasar sayıları (floating combat text).

---

## 3. Ses Efektleri ve Müzik

*   **Müzik:**
    *   Sakin tema (Güvenli Ev, Menü).
    *   Gerilim teması (Mağara, Orman).
    *   Savaş müziği (Canavarlarla savaşırken).
*   **Ses Efektleri (SFX):**
    *   Kılıç savurma, silah ateşleme sesleri.
    *   Canavar homurtuları.
    *   Buton tıklama ve altın kazanma sesleri.

---

## 4. Araç Önerileri
*   **3D Modelleme:** Blender (Ücretsiz ve açık kaynak).
*   **2D Grafik & UI:** Adobe Photoshop, GIMP veya Figma.
*   **Ses Düzenleme:** Audacity.
