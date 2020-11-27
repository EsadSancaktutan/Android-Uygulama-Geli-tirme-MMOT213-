package com.example.bilgisehir

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_giris.view.*
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_city_details.view.*


class MainActivity : AppCompatActivity() {
    var adapter: CityAdapter? = null
    var sehirList = ArrayList<Sehir>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sehirList.add(Sehir("İstanbul", R.drawable.istanbul,"İstanbul, Türkiye'de yer alan şehir ve ülkenin 81 ilinden biri. Ülkenin en kalabalık, ekonomik, tarihi ve sosyo-kültürel açıdan önde gelen şehridir.Nüfusu 15,52 milyon'dur. İklim ve Bitki Örtüsü : Akdeniz, Karadeniz, Balkan ve Anadolu kara ikliminin tesiri altında bulunur.İstanbul oldukça engebeli bir arazi yapısına sahiptir. Yüksek dağlar yoksa da, arazinin % 74'ü plato ve yaylalardan, % 16'sı dağlardan, % 10'a yakını da ovalardan ibarettir. İstanbul, Avrupa ile Asya kıtaları arasında köprü görevi gören, bunların birbirine en çok yaklaştığı iki uç üzerinde kurulmuş bir şehirdir."))
        sehirList.add(Sehir("Antalya", R.drawable.antalya,"Antalya, Türkiye'nin bir ili ve en kalabalık beşinci şehridir. 2019 sonu itibarıyla il nüfusu 2.511.700'dır.Yazları sıcak ve kurak, kışları ılık ve yağışlı olarak ifade edilen iklim tipi diğer bir değişle mutedil deniz ve sıcak deniz iklim sınıfına girer, daha iç kesimlerde ise soğuk ve yarı-kara iklim tipi görülmektedir.Akdeniz Bölgesi'nin batısında bulunan Antalya ili, bölge yüzölçümünün ise % 17,6' sını oluşturur. Antalya İl arazisinin ortalama olarak % 77,8' i dağlık, % 10,2' si ova, % 12' si ise engebeli bir yapıya sahiptir. İl alanının 3/4' ünü kaplayan Torosların birçok tepesi 2500 - 3000 metreyi aşar. "))
        sehirList.add(Sehir("Balıkesir", R.drawable.balikesir,"Balıkesir İli, Türkiye'nin kuzeybatısında Marmara Denizi ve Ege kıyıları olan bir ildir.Nüfusu 1.152 milyondur.Hem Ege Denizi'ne hem de Marmara Denizi'ne kıyısı olan Balıkesir ve ilçelerinde yazları sıcak ve kurak, kışları ise ılık ve yağışlı geçer. Yörede, genellikle Akdeniz iklimi hüküm sürmekte ise de bu iklim karakteristiğine daha çok Ege kıyılarında rastlanmaktadır.Balıkesir, Türkiye'nin bir ili ve en kalabalık on yedinci şehri. Marmara Bölgesi'nin Güney Marmara Bölümü'nde, topraklarının bir kısmı ise Ege Bölgesi'nde yer alan ilin hem Marmara hem de Ege Denizi'ne kıyısı bulunur."))
        sehirList.add(Sehir("İzmir", R.drawable.izmir,"İzmir, Türkiye'nin bir ili ve en kalabalık üçüncü şehridir. Nüfusu 2019 itibarıyla 4.367.251 kişidir.İzmir ilinde Akdeniz iklimi hüküm sürer. Yazları kurak ve sıcak kışları ılık ve yağışlı geçer. Temmuz-ağustos ayları en sıcak ve ocak-şubat en soğuk aylardır."))
        sehirList.add(Sehir("Muğla", R.drawable.mugla,"Muğla, Türkiye'nin bir ili ve en kalabalık yirmi dördüncü şehri. 2019 itibarıyla 983.142 nüfusa sahiptir.Muğla ilinde Akdeniz iklimi hüküm sürer. Yazlar sıcak ve kurak, kışlar ılık ve yağışlıdır. Kıyıdan içeriye gidildikçe kara ikliminin tesiri görülür ve ısı düşer.Fizikî YapıMuğla ilinin % 77'si dağlar, % 12'si platolar ve % 11'i ovalarla kaplıdır. Akdeniz ve Ege Denizinde sâhilleri bulunan Muğla'nın kıyıları dünyânın en girintili ve çıkıntılı yerlerinden biridir."))
        sehirList.add(Sehir("Nevşehir", R.drawable.nevsehir,"Nevşehir, Türkiye'nin İç Anadolu Bölgesinde yer alan bir ildir.Nüfusu 282.337.İklimi: Nevşehir ilinde kara iklimi hüküm sürer. Yazlar sıcak ve kurak, kışlar soğuk geçer. Senenin 70 gününde sıcaklık 0 (sıfır) °C'nin altında ve 20 gün +30°C'nin üstünde seyreder.Nevşehir, İç Anadolu Bölgesi'nde 38° 12' ve 39° 20' kuzey enlemleri ile 34° 11' ve 35° 06' doğu boylamları arasında kalır. Konya Kapalı Havzasında kalan Derinkuyu ilçesi dışında, bütünüyle Orta Kızılırmak Havzasına giren İl, konum itibariyle Türkiye'nin tam ortasında olup, yüzölçümü 5.392 km²'dir."))
        sehirList.add(Sehir("Rize", R.drawable.rize,"Rize, Karadeniz Bölgesi'nin Doğu Karadeniz bölümünde yer alan bir şehirdir.Nüfusu 348.608.Rize yöresinde Karadeniz iklimi hüküm sürmektedir. Karadeniz ikliminin özelliği, yazları serin kışları ılıman ve her mevsim yağışlı olmasıdır. Bunda en büyük etken dağların kıyıya paralel uzanmasıdır.Rize ili batıda Trabzon, güneyde Erzurum ve Bayburt, doğuda Artvin illeri ile kuzeyde Karadeniz'le çevrilidir. Rize İlinin yüzölçümü 3920 km² 'dir. Çok engebeli ve dağlık bir arazi yapısına sahip olan Rize'nin kıyı şeridinin uzunluğu 80 km, genişliği ise 20-150 m arasında değişmektedir."))
        sehirList.add(Sehir("Trabzon", R.drawable.trabzon,"Trabzon Türkiye'nin bir ili ve en kalabalık yirmi yedinci şehridir. Trabzon İl Nüfusu: 808.974'tur.Trabzon nemli bir iklime sahip, olup nem oranı zaman zaman % 99' lara kadar çıkmaktadır. Yıllık ortalama yağış miktarı 800-850 kg/m2 . İç kesimlere doğru çıkıldıkça yağmur oranı da artmaktadır.4.664 km2 yüzölçüme sahip Trabzon ili, Doğu Karadeniz Dağlarının oluşturduğu yayın ortasındaki Kalkanlı dağlık kütlesinin kuzeye bakan yamaçlarında 38° 30′ – 40° 30′ doğu meridyenleri ile 40° 30′ – 41° 30′ kuzey paralelleri arasında yer almaktadır."))
        sehirList.add(Sehir("Urfa", R.drawable.urfa,"Şanlıurfa, eski ve halk arasındaki kısa adıyla Urfa, Türkiye'nin bir ili ve en kalabalık sekizinci şehri. 2019 Yılı verilerine göre nüfusu 2.073.614’dir.Şanlıurfa kontinental (karasal) iklim özelliği göstermektedir. Yazları çok kurak ve sıcak, kışları bol yağışlı, nispeten ılıman geçmektedir. Şanlıurfa matematik konum itibariyle Ekvatora daha yakın ve deniz etkisinden uzak bir bölgede bulunmaktadır. Bu nedenle Kontinental iklim özelliği ağır basmaktadır.Şanlıurfa doğuda Mardin, batıda Gaziantep, kuzeybatıda Adıyaman, kuzeydoğuda Diyarbakır ile çevrilidir. İlin güneyinde Türkiye - Suriye sınırı uzanır. Yüzölçümü 19.336 km² olup genelde bir ova görünümündeki il merkezinin rakımı 518 m'dir. Şanlıurfa kara iklimi özelliği gösterir."))
        sehirList.add(Sehir("Artvin", R.drawable.artvin,"Artvin; Türkiye'nin Karadeniz Bölgesi'nin doğu ucunda bulunan bir şehirdir ve Artvin ilinin merkezidir.Artvin'in iklimi Türkiye'de Karadeniz iklimi dir. Kıyı kesimlerinde ılık ve yağışlı iklim tipi egemendir. Artvin merkezinin de ılık ve yağışlı bir iklim tipi vardır. İlin yüksek kesimleri diğer Karadeniz Bölgesi illerinde de olduğu gibi kışları kar yağışlıdır.Artvin ili 400 35' ile 410 32' kuzey enlemleri ve 410 07' ile 420 00' doğu boylamları arasında yer alan, 7 367 km2 yüzölçümünde, Karadeniz Bölgesinin bir ilidir. İl alanı Türkiye yüzölçümünün (783.577 km2) % 0.9'u kadardır. Kuzey-batısında Karadeniz vardır ve kıyı uzunluğu 34 km'dir."))

        adapter = CityAdapter(this, sehirList)

        gvSehirler.adapter = adapter
    }

    class CityAdapter : BaseAdapter {
        var sehirlist = ArrayList<Sehir>()
        var context: Context? = null

        constructor(context: Context, sehirList: ArrayList<Sehir>) : super() {
            this.context = context
            this.sehirlist = sehirList
        }

        override fun getCount(): Int {
            return sehirlist.size
        }

        override fun getItem(position: Int): Any {
            return sehirlist[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val sehir = this.sehirlist[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var sehirView = inflator.inflate(R.layout.city_giris, null)
            sehirView.imgCity.setImageResource(sehir.image!!)
            sehirView.cityName.text = sehir.name!!

            sehirView.setOnClickListener{
                var intent = Intent(context,CityDetails::class.java)
                intent.putExtra("name",sehir.name!!)
                intent.putExtra("des",sehir.des!!)
                intent.putExtra("image",sehir.image!!)
                context!!.startActivity(intent)
            }

            return sehirView
        }
    }
}