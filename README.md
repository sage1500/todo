Todoアプリケーション
===

[TERASOLUNAガイドラインのチュートリアル](http://terasolunaorg.github.io/guideline/5.6.0.RELEASE/ja/Tutorial/index.html) を基にしたTodoアプリケーション。

以下の部分がオリジナルと異なる。

- SpringBoot (+Thymeleaf) を利用
- lombok を利用
- フィールドインジェクションではなく、コンストラクタインジェクションを利用

また、このTodoアプリケーションを
[Macchinetta Framework for Java クラウド拡張 Development Guideline](https://macchinetta.github.io/cloud-guideline/current/ja/index.html)
を参考にクラウド・ネイティブアプリケーション化する。

- セッション外部管理
  - `Spring Session with Redis` ※TODO Redisを使うかどうかは時間次第
- アップロードファイル管理
  - 取り扱わない
- データ永続層のスケール性の確保
  - 取り扱わない（クラウドに限らない）
- 非同期実行
  - `Spring JMS / Amazon SQS` ※TODO 微妙
  - ※JMSはMSAとしては微妙。istio に移行することを踏まえると OpenAPI + WebFlux の方がよいか？
- 環境依存値の外部管理
  - `Spring Cloud Config` ※時間次第
- ヘルスチェック
  - `Spring Boot Actuator`
- ログ管理
  - ※ログは標準出力への出力でよさげ

技術検証
---

- セッション外部管理
  - `Spring Session JDBC` で試した。
  - 手順
    - 依存関係追加
      - `org.springframework.session:spring-session-core`
      - `org.springframework.session:spring-session-jdbc`
    - スキーマ追加
      - https://github.com/spring-projects/spring-session/tree/master/spring-session-jdbc/src/main/resources/org/springframework/session/jdbc
        - 念のため、tag は利用している Spring Session JDBC のバージョンに合わせた方がよい。
        - 上記リソースの場所を指定するプロパティがあるので、自動的に実行させる方法がありそうだが。
    - デフォルト通りであれば、以下はなくても動く
      - `@EnableJdbcHttpSession`
      - `spring.session.store-type=jdbc`
      - `spring.session.jdbc.*`
- アップロードファイル管理
  - 取り扱わない
- データ永続層のスケール性の確保
  - 取り扱わない（クラウドに限らない）
- 非同期実行
  - `Spring JMS / Amazon SQS` ※TODO 微妙
  - ※JMSはMSAとしては微妙。istio に移行することを踏まえると OpenAPI + WebFlux の方がよいか？
- 環境依存値の外部管理
  - `Spring Cloud Config` ※時間次第
- ヘルスチェック
  - `Spring Boot Actuator`
- ログ管理
  - ※ログは標準出力への出力でよさげ



今後の作業
---

- やれていること
  - クラウド・ネイティブアプリケーション化のためのベース作成

- オプション：やるかも
  - CSRF対策

- 今後
  - REST編をベースにAPを分離
  - OpenAPI化する
  - オプション：API側をWebFlux化する(＋R2DBC)
