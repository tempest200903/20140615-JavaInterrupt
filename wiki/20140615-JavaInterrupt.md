# 20140615-JavaInterrupt #

- [2014-06-15 日 15:32] begin
- [2014-06-15 日 16:54] end
- http://www.ibm.com/developerworks/jp/java/library/j-jtp05236/
    - Javaの理論と実践: 割り込み例外の処理
    - キャンセル可能タスクの実装
- http://java.keicode.com/lang/multithreading-cancel.php
    - ExecutorService での処理のキャンセル方法 - Java 入門

## sample 1 ##

- http://d.hatena.ne.jp/hhelibex/20091102/1257170464
    - Threadはinterruptすれば停止するというものではないのだよ - HHeLiBeXの日記 正道編
    - そもそも停止できない
- F:\goat-pc-data\ecworkspace\20140615-JavaInterrupt\src\main\java\com\example\Main1.java
- F:\goat-pc-data\ecworkspace\20140615-JavaInterrupt\src\main\java\com\example\Main2.java
- F:\goat-pc-data\ecworkspace\20140615-JavaInterrupt\src\main\java\com\example\Main3.java
- キャンセルする判断A isInterrupted() だけで判断するのはよくない。キャンセル以外の目的で割り込みした場合と区別がついていない。グローバル変数にスレッドの状態を記録しておき、自信のスレッドがキャンセル要求状態かどうかを都度判定する必要がある。

## 考察 ##

- B-E で即時キャンセルを実現するにはどうすればいいのか？
- あちこちに中断処理を埋め込む。たとえば、 file import するとき。
- F:\goat-pc-data\ecworkspace\20140615-JavaInterrupt\src\main\java\com\example\FooEntityImporter.java
- F:\goat-pc-data\ecworkspace\20140615-JavaInterrupt\src\main\java\com\example\FooProcessor.java
- 問題点1 要件定義 中止リクエストが届いてから停止するまでの時間
    - ある FileImport から次の FileImport まで何秒かかるかは不定。データサイズが大きいと長い。10分以上かかることもある。
    - これで要件を満たせるか？
- 問題点2 キュー管理
    - キュー管理から中止リクエストが届いてから、実際にスレッドが停止するまで、時間がかかる。
    - キュー管理は引き続きステータスファイル監視を続けて、ステータスファイルが変化して初めてスレッドが停止したと判断してよい。このようにインタフェースを設計する。
- 問題点3 テスト
    - 停止を判断する箇所が多い。 Importer だけで20以上。単体テストはこの数に比例する。
    - 要件により Importer 以外でも停止したいなら、40以上。
    - 下位モジュール内部以外にも停止箇所を設置したいなら、結合テストでやることになる。
    - 結合テストでやる場合、狙った箇所に中止リクエストが届くようにタイミングを制御するのは事実上困難。

## ソースコード ##

- git clone https://github.com/tempest200903/20140615-JavaInterrupt
- F:\goat-pc-data\ecworkspace\20140615-JavaInterrupt\wiki\20140615-JavaInterrupt.md
