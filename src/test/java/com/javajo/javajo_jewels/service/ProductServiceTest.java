package com.javajo.javajo_jewels.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.javajo.javajo_jewels.model.Product;
import com.javajo.javajo_jewels.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@DataJpaTest
@Import(ProductService.class)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    // DB初期化直後に実行してください。
    }

    @Test
    void getAllProductsTest() {
        var actual = productService.getAllProducts();

        assertThat(actual)
                .hasSize(20)
                .extracting(
                        Product::getName,
                        Product::getDescription,
                        Product::getPrice,
                        Product::getImageUrl)
                .containsExactlyInAnyOrder(
                        tuple("きらきらお星様チャーム", "きらきら輝くお星様のチャーム☆ カバンやポーチにつければ、毎日がちょっぴり特別に♪",125, "/images/product-1.png"),
                        tuple("虹のゆめかわヘアクリップ", "パステルカラーの虹がきらっと光る、ゆめかわいいヘアクリップ。つけるだけで毎日がときめく魔法タイムに♪",210, "/images/product-2.png"),
                        tuple("ときめきハートリング", "つけるたび、ドキドキしちゃう♡ 小さなハートがきらっと光る、乙女心くすぐる指輪。今日はどの指につける？",160, "/images/product-3.png"),
                        tuple("しあわせひらり♪蝶々キーホルダー", "幸せを運ぶクローバーと、ふわり舞う蝶々のキーホルダー。カバンにつけると、ラッキーなことが起こるかも…？",230, "/images/product-4.png"),
                        tuple("虹のトキメキキーホルダー", "ふわっと広がる七色の虹がついたキーホルダー。見てるだけで元気になれる、とっておきのハッピーアイテム♡",200, "/images/product-5.png"),
                        tuple("しあわせクローバー&パールヘアピン", "小さなクローバーと、きらきら光るパールが出会ったら…しあわせを運ぶヘアピンの完成♡ おでかけの日のお守りに♪",190, "/images/product-6.png"),
                        tuple("星の魔法スティックキーホルダー", "星の魔法スティック型キーホルダー☆ カバンにつければ、ちょっぴり魔法が使える気分に♪",240, "/images/product-7.png"),
                        tuple("ときめき☆魔法スティックキーホルダー", "ハートとお星さまが一緒になった、キラキラの魔法スティックキーホルダー☆ ふれるたび、ドキドキとワクワクがあふれちゃう♡",260, "/images/product-8.png"),
                        tuple("きゅんリボン♡ハートピン", "ハートとふんわりリボンがついた、乙女気分全開のヘアピン♡ つけるだけで気分もぱっと華やかに！",170, "/images/product-9.png"),
                        tuple("にじいろハッピーブレスレット", "カラフルなビーズが手元でキラキラ輝く、元気いっぱいのブレスレット♡ つけるだけでハッピーが集まってくるよ♪",220, "/images/product-10.png"),
                        tuple("おほしさまとうさぎのゆめブレス", "夜空をぴょんっと跳ねるうさぎと、きらきら輝くお星さまのブレスレット☆ 手首に巻けば、ふんわり夢の世界へご招待♪",230, "/images/product-11.png"),
                        tuple("ルビークローバーの願いチャーム", "真っ赤なクローバーがきらっと輝く、小さな願いをこめたチャーム♡ ポーチやカバンにつけて、幸せを呼びこもう♪",210, "/images/product-12.png"),
                        tuple("うさちゃんキーホルダー", "にんじんをぎゅっ！と抱えた、にっこりうさちゃんのキーホルダー♡ 見てるだけで元気をくれる、癒しのおともだち♪",250, "/images/product-13.png"),
                        tuple("きらきら☆にじいろスターチャーム", "虹色に輝くおほしさまが、まるで空からこぼれたみたい…☆ カバンやポーチにきらっとつけて、とっておきの毎日に♪",220, "/images/product-14.png"),
                        tuple("ときめき♡ハートキー", "小さなハート型の鍵は、あなたの大切な気持ちをそっと開く魔法のカギ♡ ポーチやネックレスにつけても可愛さ満点♪",230, "/images/product-15.png"),
                        tuple("うさぎマカロン♡スイートヘアクリップ", "まるくてふわふわなマカロンにのったうさぎちゃん♡ 甘〜い夢をとじこめた、ゆめかわスイーツヘアクリップ♪",260, "/images/product-16.png"),
                        tuple("ラブリボン♡レッドハートチャーム", "真っ赤なハートにふんわりリボンが寄り添うチャーム♡ カバンや鍵につけて、毎日に甘くてかわいい魔法をかけよう♪",240, "/images/product-17.png"),
                        tuple("ほしのひかりネックレス", "星が胸元でキラリと輝くネックレス☆ 存在感ばっちりで、毎日のアクセントにぴったり♪",280, "/images/product-18.png"),
                        tuple("ミスティックパープルハートチャーム", "透き通るような紫のハートがキラキラ輝くチャーム☆ 不思議な魅力で毎日をちょっと特別に♪",230, "/images/product-19.png"),
                        tuple("スイートキャンディボールペン", "カラフルなキャンディ風デザインのボールペン。友だちに自慢しちゃおう！",180, "/images/product-20.png"));
    }

    @Test
    void getProductByIdTest() {
        var actual = productService.getProductById(1);

        assertThat(actual)
                .extracting(
                        Product::getName,
                        Product::getDescription,
                        Product::getPrice,
                        Product::getImageUrl)
                .containsExactly(
                        "きらきらお星様チャーム",
                        "きらきら輝くお星様のチャーム☆ カバンやポーチにつければ、毎日がちょっぴり特別に♪",
                        125,
                        "/images/product-1.png");
    }
}
